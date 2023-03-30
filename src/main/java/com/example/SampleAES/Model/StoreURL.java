package com.example.SampleAES.Model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Document(collection = "storeURL")
@Service
public class StoreURL {
	public String Id;
	public String Original_URl;
	public String Shortened_URL;
	@DateTimeFormat(pattern="dd/MM/yyyy hh:mm:ss")
	public Date Created_At;
	@DateTimeFormat(pattern="dd/MM/yyyy hh:mm:ss")
	public Date Expires_At;
	
	public void setId(String id) {
		Id = id;
	}
	public void setOriginal_URl(String original_URl) {
		Original_URl = original_URl;
	}
	public void setShortened_URL(String shortened_URL) {
		Shortened_URL = shortened_URL;
	}
	public void setCreated_At(Date date) {
		Created_At = date;
	}
	public void setExpires_At(Date expires_At) {
		Expires_At = expires_At;
	}
	

}
