package WeatherCoreLibrary;

// Extend Exception Object to "WeatherDataServiceException" To Handle custom exception
public class WeatherDataServiceException extends Exception {
	private String exMessage;
	private Throwable cause;

	public WeatherDataServiceException(String exMessage) {
		super(exMessage);
		setExceptionMessage(exMessage);
	}
	
	public WeatherDataServiceException(String exMessage, Throwable throwable) {
		super(exMessage, throwable);
		setExceptionMessage(exMessage);
	}

	public String getExceptionMessage() {
		return exMessage;
	}

	public void setExceptionMessage(String exMessage) {
		this.exMessage = exMessage;
	}
}
