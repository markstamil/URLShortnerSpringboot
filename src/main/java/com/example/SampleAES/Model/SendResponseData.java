package com.example.SampleAES.Model;

import org.springframework.stereotype.Service;

@Service
public class SendResponseData {
	String responseCode;
	String responseStatus;
	String responseMessage;
	Object data;
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
