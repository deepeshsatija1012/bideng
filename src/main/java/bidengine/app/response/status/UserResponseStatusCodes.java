package bidengine.app.response.status;

public enum UserResponseStatusCodes  implements StatusCodes {
	SUCCESS(0,"SUCCESS"),
	USER_ALREADY_EXISTS(1, "User already exists."),
	USER_DOES_NOT_EXISTS(2, "User does not exists."),
	;
	
	private int code;
	private String message;
	
	UserResponseStatusCodes(int code, String message){
		this.code = code; this.message = message;
	}
	
	public int code() {return this.code;}
	public String message(){return this.message;}
}
