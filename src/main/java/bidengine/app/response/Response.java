package bidengine.app.response;

import bidengine.app.response.status.ResponseStatus;

public class Response {
	
	private int code = ResponseStatus.SUCCESS.code();
	private String messgage = ResponseStatus.SUCCESS.message();
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessgage() {
		return messgage;
	}
	public void setMessgage(String messgage) {
		this.messgage = messgage;
	}
	
	

}
