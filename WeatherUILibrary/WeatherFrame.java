package WeatherUILibrary;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import WeatherCoreLibrary.ForecastWeatherData;
import WeatherCoreLibrary.WeatherData;

// Implement of the frame of the user interface
public class WeatherFrame implements IWeatherUI {
	JFrame weatherAppFrame;
	WeatherPanel weatherAppPanel;
	JMenuBar weatherAppMenuBar;
	JMenu weatherAppFileMenu;
	JMenu weatherAppHelpMenu;
	JMenuItem weatherAppSaveToFileMenuItem;
	JMenuItem weatherAppClearMenuItem;
	JMenuItem weatherAppExitMenuItem;
	JMenuItem weatherAppAboutMenuItem;
	JTextArea weatherAppAboutTextArea;
	ImageIcon applicationIcon;
	JFileChooser fileChooser = new JFileChooser();
	
	
	public WeatherFrame() {
		weatherAppFrame = new JFrame("Weather Application");
		ImageIcon applicationIcon = new ImageIcon( getClass().getResource("/pictures/globe.png/"));
		weatherAppFrame.setIconImage(applicationIcon.getImage());
		weatherAppFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		weatherAppFrame.setBounds(100, 100, 900, 600);
		weatherAppFrame.setResizable(false);
		weatherAppFrame.setLayout(new BorderLayout());
		CreateMenu();
		weatherAppFrame.setJMenuBar(weatherAppMenuBar);
		
		weatherAppPanel = new WeatherPanel();
		weatherAppFrame.add(weatherAppPanel,BorderLayout.CENTER);
		weatherAppFrame.setVisible(true);
	}
	
	public void CreateMenu() {
		JSeparator separator = new JSeparator();
		weatherAppMenuBar = new JMenuBar();
		weatherAppFileMenu = new JMenu("File");
		weatherAppSaveToFileMenuItem = new JMenuItem("Export to file");
		weatherAppSaveToFileMenuItem.addActionListener(new MenuListener());
		weatherAppClearMenuItem = new JMenuItem("Clear");
		weatherAppClearMenuItem.addActionListener(new MenuListener());
		weatherAppExitMenuItem = new JMenuItem("Exit");
		weatherAppExitMenuItem.addActionListener(new MenuListener());
		weatherAppFileMenu.add(weatherAppSaveToFileMenuItem);
		weatherAppFileMenu.add(separator);
		weatherAppFileMenu.add(weatherAppClearMenuItem);
		weatherAppFileMenu.add(separator);
		weatherAppFileMenu.add(weatherAppExitMenuItem);
		weatherAppMenuBar.add(weatherAppFileMenu);
		
		weatherAppAboutMenuItem = new JMenuItem("About");
		weatherAppAboutTextArea = new JTextArea();
		weatherAppAboutTextArea.setOpaque(false);
		weatherAppAboutTextArea.setText("Version: 1.0" + System.getProperty("line.separator") + "Copyright © 2015 by Or E, Goel R." + System.getProperty("line.separator") + "All rights reserved." + System.getProperty("line.separator"));
		weatherAppHelpMenu = new JMenu("Help");
		weatherAppAboutMenuItem.addActionListener(new MenuListener());
		
		weatherAppHelpMenu.add(weatherAppAboutMenuItem);
		weatherAppMenuBar.add(weatherAppHelpMenu);	
	}
	
	class MenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent acEvent) {
			
			if(acEvent.getSource() == weatherAppSaveToFileMenuItem){
				if (weatherAppPanel.lblCityData.getText() != "") {
					fileChooser.setDialogTitle("Specify a file to save");   
					int userSelection = fileChooser.showSaveDialog(weatherAppFrame);
					if (userSelection == JFileChooser.APPROVE_OPTION) {
					    File fileToSave = fileChooser.getSelectedFile();
					    weatherAppPanel.saveDataToFile(fileToSave, weatherAppPanel.lblCityData.getText(), weatherAppPanel.lblCountryCodeData.getText(),weatherAppPanel.lblTemperatureData.getText(),weatherAppPanel.lblHumidityData.getText(),weatherAppPanel.lblLastUpdateData.getText(),weatherAppPanel.lblWeatherDescriptionData.getText());
					    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "There is no data to export!");
				}
			}
			
			else if(acEvent.getSource() == weatherAppClearMenuItem){
				int resetDialogButton = JOptionPane.YES_NO_OPTION;
				int resetDialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to clear current data?","Warning",resetDialogButton);
				if(resetDialogResult == JOptionPane.YES_OPTION){
					weatherAppPanel.ResetPanelFields();
				}
			}

			else if(acEvent.getSource() == weatherAppAboutMenuItem){			
				JOptionPane.showMessageDialog(weatherAppFrame, weatherAppAboutTextArea, "About - Weather Application", JOptionPane.PLAIN_MESSAGE);
			}	
			else if(acEvent.getSource() == weatherAppExitMenuItem){
				int exitDialogButton = JOptionPane.YES_NO_OPTION;
				int exitDialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to close the application?","Warning",exitDialogButton);
				if(exitDialogResult == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		}				
	}
	
	

	@Override
	public void addFrameListener(ActionListener actionListenerGetInfoButton)
	{
		weatherAppPanel.addPanelListener(actionListenerGetInfoButton);
	}
		
	@Override
	public WeatherPanel getInfoFromFrame() {
		return weatherAppPanel;
	}
	
	@Override
	public void setFrameWithWeatherData(WeatherData todayData,ForecastWeatherData forecastData)
	{
		weatherAppPanel.setPanelWithWeatherData(todayData);
		weatherAppPanel.setPanelWithForecastData(forecastData);
	}
	
	@Override
	public void setFrameCityNotFound(String cityNotFound)
	{
		weatherAppPanel.setPanelCityNotFound(cityNotFound);
	}
	
	
}
