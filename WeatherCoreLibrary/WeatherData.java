package WeatherCoreLibrary;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.json.JSONObject;

//Define WeatherData Data Type and implement functions to set/get data.

public class WeatherData {
	private String city;
	private String countryCode;
	private String humidity;
	private String temperature;
	private String lastUpdate;
	private String weatherMain;
	private String weatherDesc;

	
	public void setWeatherData(JSONObject owmAllData) throws WeatherDataServiceException{
		try{
			setCity(owmAllData.get("name").toString());
			setCountryCode(owmAllData.getJSONObject("sys").get("country").toString());
			setTemperature(owmAllData.getJSONObject("main").get("temp").toString());
			setHumidity(owmAllData.getJSONObject("main").get("humidity").toString());
			setLastUpdate(setLastUpdateTime(owmAllData.get("dt").toString()));
			setWeatherMain(owmAllData.getJSONArray("weather").getJSONObject(0).get("main").toString());
			setWeatherDesc(owmAllData.getJSONArray("weather").getJSONObject(0).get("description").toString());
		}
		catch(Exception SetWeatherDataException){
			throw new WeatherDataServiceException(SetWeatherDataException.getMessage());
		}
	}

	static String setLastUpdateTime(String lastUpdate){
		long lastUpdateUnixUTC = Long.parseLong(lastUpdate);
		Date date = new Date(lastUpdateUnixUTC*1000L);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String formattedDate = sdf.format(date);
		return formattedDate;
	}

	public String getCity() {
		return city;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public String getHumidity() {
		return humidity;
	}
	
	public String getTemperature() {
		return temperature;
	}
	
	public String getWeatherMain() {
		return weatherMain;
	}

	public String getWeatherDesc() {
		return weatherDesc;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	
	public void setCity(String city) {
		this.city =  city;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode =  countryCode;
	}
	
	public void setTemperature(String temperature) {
		this.temperature =  temperature;
	}

	public void setHumidity(String humidity) {
		this.humidity =  humidity;
	}
	
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate =  lastUpdate;
	}
	
	public void setWeatherMain(String weatherMain) {
		this.weatherMain =  weatherMain;
	}
	
	public void setWeatherDesc(String weatherDesc) {
		this.weatherDesc =  weatherDesc;
	}
}
