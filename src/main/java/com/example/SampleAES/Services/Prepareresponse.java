package com.example.SampleAES.Services;


import org.springframework.stereotype.Service;

import com.example.SampleAES.Model.SendResponseData;

@Service
public class Prepareresponse {
	
	public SendResponseData PrepareresponseData(String responseCode,String responseStatus,String msg,Object data) {
		SendResponseData response = new SendResponseData();
		response.setResponseCode(responseCode);
		response.setResponseStatus(responseStatus);
		response.setResponseMessage(msg);
		response.setData(data);
		return response;
	}

}
