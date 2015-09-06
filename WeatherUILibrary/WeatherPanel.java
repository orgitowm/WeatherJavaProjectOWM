package WeatherUILibrary;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormatSymbols;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import WeatherCoreLibrary.ForecastWeatherData;
import WeatherCoreLibrary.WeatherData;

// Define and implement all data and views that the user get from the program exclude the frame.
public class WeatherPanel extends JPanel {
	
	public static final String FONT_SEGOE = "Segoe UI Semilight";
	public static final String WEATHER_PIC_PATH = "/pictures/WeatherPictures/";
	public static final String FORECAST_PIC_PATH = "/pictures/ForecastPictures/";
	
	JButton sendButton;
	JLabel citySelectioinLabel;
	JTextField cityTextField;
		
	JLabel lblHumidity;
	JLabel lblLastUpdate;
	JLabel lblWeatherDescription;
	JLabel lblCityData;
	JLabel lblCityNotFound;
	JLabel lblCountryCodeData;
	JLabel lblTemperatureData;
	JLabel lblHumidityData;
	JLabel lblLastUpdateData;
	JLabel lblWeatherDescriptionData;
	JLabel lblImage;
	JLabel lblBackground;
	JLabel lblMeter;
	
	
	JLabel[] dailyTempData = new JLabel[3];
	JLabel[] dailyImage = new JLabel[3];
	JLabel[] dailydDate = new JLabel[3];
		
	ImageIcon currentClouds;
	ImageIcon currentClear;
	ImageIcon currentRain;
	ImageIcon currentSnow;
	ImageIcon forecastClouds;
	ImageIcon forecastClear;
	ImageIcon forecastRain;
	ImageIcon forecastSnow;
	ImageIcon meter;
	ImageIcon backGroundImg;

	public WeatherPanel() {
		super();
		initPanel();
	}

	public void initPanel() {
		Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
		setBorder(blackBorder);
		setLayout(null);
		initWeatherAndForecastArea();
	}
	
	void addPanelListener(ActionListener actionListenerGetInfoButton)
	{
		sendButton.addActionListener(actionListenerGetInfoButton);
	}
	
	public void setPanelWithWeatherData(WeatherData data)
	{
		SetCityLabel(data.getCity());
		SetCityNotFoundLabel("");
		SetCountryCodeLabel(data.getCountryCode());
		SetTemperatureLabel(data.getTemperature() + " °C");
		SetHumidityLabel(data.getHumidity() + "%");
		SetLastUpdateLabel(data.getLastUpdate());
		SetWeatherDescLabel(data.getWeatherDesc());
		SetWeatherPicture(data.getWeatherMain());
	}
	
	public void setPanelWithForecastData(ForecastWeatherData forecastData)
	{
		for(int i=0;i<3;i++){
			SetForecastTemperatureLabel(forecastData.forecastDataArr[i].getTemperature() + " °C", i);
			SetForecastPicture(forecastData.forecastDataArr[i].getWeatherMain(), i);
			SetForecastDateLabel(forecastData.forecastDataArr[i].getLastUpdate(), i);
		}
	}
	
	public void setPanelCityNotFound(String cityNotFound){
		ResetPanelFields();
		SetCityNotFoundLabel(cityNotFound);
	}
	
	public String getCityTextField() {
		return cityTextField.getText();
	}
	
	public void setCityTextField(String cityTextFieldData) {
		cityTextField.setText(cityTextFieldData);
	}
	
	public void SetCityLabel(String cityData) {
		lblCityData.setText(cityData);
	}
	
	public void SetCityNotFoundLabel(String cityNotFound) {
		lblCityNotFound.setText(cityNotFound);
	}
	
	public void SetCountryCodeLabel(String countryCodeData) {
		lblCountryCodeData.setText(countryCodeData);
	}

	public void SetTemperatureLabel(String temperatureData) {
		lblTemperatureData.setText(temperatureData);
	}

	public void SetHumidityLabel(String humidityData) {
		lblHumidityData.setText(humidityData);
	}

	public void SetLastUpdateLabel(String lastUpdateData) {
		lblLastUpdateData.setText(lastUpdateData);
	}

	public void SetWeatherDescLabel(String weatherDescData) {
		lblWeatherDescriptionData.setText(weatherDescData);
	}
	
	public void SetWeatherPicture(String imgDecisionByWeather) {
		lblImage.setVisible(true);
		
		if (imgDecisionByWeather.equals("Clouds") || imgDecisionByWeather.equals("Fog") || imgDecisionByWeather.equals("Mist"))
			lblImage.setIcon(currentClouds);
		else if (imgDecisionByWeather.equals("Rain") || imgDecisionByWeather.equals("Drizzle"))
			lblImage.setIcon(currentRain);
		else if (imgDecisionByWeather.equals("Clear"))
			lblImage.setIcon(currentClear);
		else
			lblImage.setIcon(currentSnow);
	}
	
	public void SetForecastPicture(String imgDecisionByWeather, int i) {
		dailyImage[i].setVisible(true);
		
		if (imgDecisionByWeather.equals("Clouds") || imgDecisionByWeather.equals("Fog") || imgDecisionByWeather.equals("Mist"))
			dailyImage[i].setIcon(forecastClouds);
		else if (imgDecisionByWeather.equals("Rain") || imgDecisionByWeather.equals("Drizzle"))
			dailyImage[i].setIcon(forecastRain);
		else if (imgDecisionByWeather.equals("Clear"))
			dailyImage[i].setIcon(forecastClear);
		else
			dailyImage[i].setIcon(forecastSnow);
	}

	public void SetForecastTemperatureLabel(String temperatureData, int i) {
		dailyTempData[i].setText(temperatureData);
	}
	
	public void SetForecastDateLabel(String dateData, int i) {
		
		// Check if date comes from ResetPanelFields()
		if (dateData.equals("")) {
			dailydDate[i].setText("");
		}
		
		else {
		String[] tokens = dateData.split("\\W");
		String monthNum = tokens[1];
		String monthName = new DateFormatSymbols().getMonths()[Integer.parseInt(monthNum)-1];
		String dayNum = tokens[2];
		dailydDate[i].setText(dayNum + " " +  monthName.substring(0, 3));
		}
	}
	
	public void initWeatherAndForecastArea() {
		citySelectioinLabel = new JLabel("Select City:");
		citySelectioinLabel.setFont(new Font(FONT_SEGOE, Font.BOLD, 12));
		citySelectioinLabel.setForeground(Color.WHITE);
		citySelectioinLabel.setBounds(336, 11, 70, 14);
		add(citySelectioinLabel);

		cityTextField = new JTextField();
		cityTextField.setOpaque(false);
		cityTextField.setBounds(405, 8, 86, 20);
		cityTextField.setForeground(Color.WHITE);
		add(cityTextField);
		cityTextField.setColumns(15);

		sendButton = new JButton("Get Info");
		sendButton.setBounds(495, 7, 100, 20);
		add(sendButton);

		lblHumidity = new JLabel("Humidity:");
		lblHumidity.setFont(new Font(FONT_SEGOE, Font.BOLD, 20));
		lblHumidity.setForeground(Color.WHITE);
		lblHumidity.setBounds(360, 120, 200, 200);
		add(lblHumidity);

		lblLastUpdate = new JLabel("Last Update:");
		lblLastUpdate.setFont(new Font(FONT_SEGOE, Font.PLAIN, 13));
		lblLastUpdate.setForeground(Color.WHITE);
		lblLastUpdate.setBounds(0, 490, 100, 100);
		add(lblLastUpdate);
		
		lblWeatherDescription = new JLabel("Weather Description:");
		lblWeatherDescription.setFont(new Font(FONT_SEGOE, Font.BOLD, 20));
		lblWeatherDescription.setForeground(Color.WHITE);
		lblWeatherDescription.setBounds(115, 120, 200, 200);
		add(lblWeatherDescription);
		

		lblCityData = new JLabel("");
		lblCityData.setBounds(340, 5, 400, 150);
		lblCityData.setFont(new Font(FONT_SEGOE, Font.PLAIN, 55));
		lblCityData.setForeground(Color.WHITE);
		add(lblCityData);
		
		lblCityNotFound = new JLabel("");
		lblCityNotFound.setBounds(405, 30, 200, 30);
		lblCityNotFound.setFont(new Font("Arial Bold", Font.BOLD, 13));
		lblCityNotFound.setForeground(Color.RED);
		add(lblCityNotFound);

		lblCountryCodeData = new JLabel("");
		lblCountryCodeData.setBounds(340, 30, 200, 180);
		lblCountryCodeData.setFont(new Font(FONT_SEGOE, Font.PLAIN, 32));
		lblCountryCodeData.setForeground(Color.WHITE);
		add(lblCountryCodeData);
		
		lblTemperatureData = new JLabel("");
		lblTemperatureData.setFont(new Font(FONT_SEGOE, Font.BOLD, 40));
		lblTemperatureData.setForeground(Color.WHITE);
		lblTemperatureData.setBounds(420, 50, 200, 180);
		add(lblTemperatureData);

		lblHumidityData = new JLabel("");
		lblHumidityData.setFont(new Font(FONT_SEGOE, Font.PLAIN, 20));
		lblHumidityData.setForeground(Color.WHITE);
		lblHumidityData.setBounds(360, 140, 200, 200);
		add(lblHumidityData);

		lblLastUpdateData = new JLabel("");
		lblLastUpdateData.setFont(new Font("FONT_SEGOE", Font.PLAIN, 12));
		lblLastUpdateData.setForeground(Color.WHITE);
		lblLastUpdateData.setBounds(70, 491, 200, 100);
		add(lblLastUpdateData);

		lblWeatherDescriptionData = new JLabel("");
		lblWeatherDescriptionData.setFont(new Font(FONT_SEGOE, Font.PLAIN, 20));
		lblWeatherDescriptionData.setForeground(Color.WHITE);
		lblWeatherDescriptionData.setBounds(115, 140, 200, 200);
		add(lblWeatherDescriptionData);
		
		// init forecast lables
		for(int i=0;i<3;i++)
		{
			dailydDate[i] = new JLabel("");
			dailydDate[i].setFont(new Font(FONT_SEGOE, Font.BOLD, 20));
			dailydDate[i].setForeground(Color.WHITE);
			dailydDate[i].setBounds(((i+1)*145), 320, 100, 100);
			add(dailydDate[i]);
			
			dailyTempData[i] = new JLabel("");
			dailyTempData[i].setFont(new Font(FONT_SEGOE, Font.PLAIN, 20));
			dailyTempData[i].setForeground(Color.WHITE);
			dailyTempData[i].setBounds(((i+1)*145), 355, 100, 100);
			add(dailyTempData[i]);
			
			dailyImage[i] = new JLabel("");
			dailyImage[i].setBounds(((i+1)*140), 370, 200, 200);
			add(dailyImage[i]);
		}
		
		lblImage = new JLabel("");
		lblBackground = new JLabel("");
		lblMeter = new JLabel("");
		try{
			currentClouds = new ImageIcon(getClass().getResource(WEATHER_PIC_PATH + "current_clouds.png/"));
			currentClear = new ImageIcon(getClass().getResource(WEATHER_PIC_PATH + "current_clear.png/"));
			currentRain = new ImageIcon(getClass().getResource(WEATHER_PIC_PATH + "current_rain.png/"));
			currentSnow = new ImageIcon(getClass().getResource(WEATHER_PIC_PATH + "current_snow.png/"));
			forecastClouds = new ImageIcon(getClass().getResource(FORECAST_PIC_PATH + "forecast_clouds.png/"));
			forecastClear = new ImageIcon(getClass().getResource(FORECAST_PIC_PATH + "forecast_clear.png/"));
			forecastRain = new ImageIcon(getClass().getResource(FORECAST_PIC_PATH + "forecast_rain.png/"));
			forecastSnow = new ImageIcon(getClass().getResource(FORECAST_PIC_PATH + "forecast_snow.png/"));
			meter = new ImageIcon(getClass().getResource("/pictures/meter.png/"));
			backGroundImg = new ImageIcon(getClass().getResource("/pictures/background.jpg/"));
			
		}
		catch(Exception e){
			System.out.print(e.getMessage());
		}
		lblImage.setBounds(500, 0, 400, 500);
		add(lblImage);
		lblMeter.setBounds(0, 0, 100, 100);
		add(lblMeter);
		lblMeter.setIcon(meter);
		lblBackground.setBounds(0, 0, 900, 600);
		add(lblBackground);
		lblBackground.setIcon(backGroundImg);
	}

	public void ResetPanelFields() {
		SetCityLabel("");
		SetCityNotFoundLabel("");
		SetCountryCodeLabel("");
		SetTemperatureLabel("");
		SetHumidityLabel("");
		SetLastUpdateLabel("");
		SetWeatherDescLabel("");
		setCityTextField("");
		for(int i=0;i<3;i++){
			dailyImage[i].setVisible(false);
			SetForecastTemperatureLabel("",i);
			SetForecastDateLabel("",i);
		}
		lblImage.setVisible(false);
		validate();
		repaint();
	}
	
	public void saveDataToFile(File fileToSave, String city, String countryCode, String temperature, String humidity, String lastUpdate, String weatherDesc) {
		String dataToWrite = city + System.getProperty("line.separator") + countryCode + System.getProperty("line.separator") + temperature + System.getProperty("line.separator") + humidity + System.getProperty("line.separator") + lastUpdate + System.getProperty("line.separator") + weatherDesc + System.getProperty("line.separator");
		try {
			// if file doesnt exists, then create it
			if (!fileToSave.exists()) {
				fileToSave.createNewFile();
			}

			FileWriter fw = new FileWriter(fileToSave.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(dataToWrite);
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
