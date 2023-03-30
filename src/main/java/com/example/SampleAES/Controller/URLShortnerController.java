package com.example.SampleAES.Controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SampleAES.Model.StoreURL;
import com.example.SampleAES.Repository.URLPostAndGetRepo;
import com.example.SampleAES.Services.NextSequenceService;
import com.example.SampleAES.Services.Prepareresponse;
import com.example.SampleAES.Services.SHA256Operation;

import jakarta.servlet.http.HttpServletRequest;



@RequestMapping("/url")
@RestController
@CrossOrigin(origins = "*")
public class URLShortnerController {
	@Autowired
	public Prepareresponse prepareResponse;
	@Autowired
	public StoreURL urlObj;
	@Autowired
	public URLPostAndGetRepo urlRepo;
	@Autowired
	public SHA256Operation sha256;
	@Autowired
	public NextSequenceService nextSeq;
	
	@PostMapping("/shortenedAndStorage")
	public Object storeAndShortenURL(@RequestBody StoreURL storeurl) {
		try {
			Calendar cal = Calendar.getInstance();
			///adding five minutes
			long timeInSeconds = cal.getTimeInMillis();
			Date adding5Minutes = new Date(timeInSeconds + ( 5 * 60 * 1000));
			String id = nextSeq.getNextSequence("customSequences");
			if(id == null) {
				id="0";
			}

			urlObj.setId(id);
			urlObj.setOriginal_URl(storeurl.Original_URl);
			System.out.println("helloooo"+storeurl.Original_URl);

			//hash  the original url and concat with id of the document.Because same original url may produce same hashed data.
			//in order to over come this.Concat the id with hash.
			String hashedURL = SHA256Operation.hashTheOriginalURLAndGenerateHash(storeurl.Original_URl);

			////
			urlObj.setShortened_URL(hashedURL+id);
			urlObj.setCreated_At(cal.getTime());
			urlObj.setExpires_At(adding5Minutes);
			urlRepo.save(urlObj);
			return prepareResponse.PrepareresponseData("201","Success", "Successfully saved", storeurl.Shortened_URL+(hashedURL+id));
		}catch(Exception ex) {
			System.out.println(ex.toString());
			return prepareResponse.PrepareresponseData("400", ex.toString(), "Something Went Wrong", "");
		}
	}
	@GetMapping("/getTheOriginalURL/{hashedString}")
	public Object storeAndShortenURL(@PathVariable String hashedString) {
		try {
			System.out.println("getttt");
			List<StoreURL> urlData = urlRepo.findByShortened_URL(hashedString);
			if(urlData.size() == 0) {
				return prepareResponse.PrepareresponseData("400","Failure", "No link available", "");

			}
			Calendar cal = Calendar.getInstance();
			int isAfter = cal.getTime().compareTo(urlData.get(0).Expires_At);
			if(isAfter > 0) {
				return prepareResponse.PrepareresponseData("400","Expires", "Link Expired", "");
			}
			System.out.println("isafter"  + isAfter);
			return prepareResponse.PrepareresponseData("200","Success", "Successfully fetched", urlData.get(0).Original_URl);
		}catch(Exception ex) {
			return prepareResponse.PrepareresponseData("400", ex.toString(), "Something Went Wrong", "");
		}
	}
	

}
