package bidengine.app.response.status;

public enum ResponseStatus implements StatusCodes {
	SUCCESS(0,"SUCCESS")
	;
	
	private int code;
	private String message;
	
	ResponseStatus(int code, String message){
		this.code = code; this.message = message;
	}
	
	public int code() {return this.code;}
	public String message(){return this.message;}

}
