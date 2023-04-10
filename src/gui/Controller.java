package gui;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import database.CSVReader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import planning.*;
import vehicles.*;


public class Controller implements Initializable {
	
	@FXML
	private Label output;
	@FXML
	private Label vOutput;
	@FXML
	private Label distance;
	@FXML
	private ChoiceBox<Country> countrySelection;
	@FXML
	private ChoiceBox<City> citySelection;
	@FXML
	private ListView<Country> countryList;
	@FXML
	private CheckBox box0;
	@FXML
	private CheckBox box1;
	@FXML
	private CheckBox box2;
	@FXML
	private CheckBox box3;
	@FXML
	private CheckBox box4;
	@FXML
	private Button run;
	@FXML
	private RadioButton wRadio;
	@FXML
	private RadioButton eRadio;
	
	private ArrayList<Country> countries = new ArrayList<>();
	
    private Route route1 = new Route();
    
    private Car car1 = new Car("Car",5,8);
    private Plane plane1 = new Plane("Plane");
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        
		
        try {
        CSVReader reader = new CSVReader();
        URL url = this.getClass().getResource("/database/countries.csv");
        File countriesFile = new File(url.toURI());
        URL url2 = this.getClass().getResource("/database/cities.csv");
        File citiesFile = new File(url2.toURI());
        countries=reader.setCountries(countriesFile);
        reader.setCities(citiesFile,countries);
        }
        catch(IOException | URISyntaxException e) {
        	output.setText("Exception");
        }
        
        countries.get(0).cities.get(1).setClosestAirport(countries.get(0).cities.get(0));
        countries.get(2).cities.get(2).setClosestAirport(countries.get(2).cities.get(1));
        countrySelection.getItems().addAll(countries);
        countrySelection.setOnAction(this::startCities);
        citySelection.setOnAction(this::citySelection);
        countryList.getItems().addAll(countries);
        countryList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Country>(){

			@Override
			public void changed(ObservableValue<? extends Country> arg0, Country arg1, Country arg2) {
				// TODO Auto-generated method stub
				box0.setVisible(true);
				box1.setVisible(true);
				box2.setVisible(true);
				box3.setVisible(true);
				box4.setVisible(true);
				box0.setText(countryList.getSelectionModel().getSelectedItem().cities.get(0).getName());
				box1.setText(countryList.getSelectionModel().getSelectedItem().cities.get(1).getName());
				box2.setText(countryList.getSelectionModel().getSelectedItem().cities.get(2).getName());
				box3.setText(countryList.getSelectionModel().getSelectedItem().cities.get(3).getName());
				box4.setText(countryList.getSelectionModel().getSelectedItem().cities.get(4).getName());
				box0.setSelected(false);
				box1.setSelected(false);
				box2.setSelected(false);
				box3.setSelected(false);
				box4.setSelected(false);
			}
        	
        });
        
	}
	
	public void startCities(ActionEvent event) {
		citySelection.getItems().clear();
		citySelection.getItems().addAll(countrySelection.getValue().cities);
	}
	public void citySelection(ActionEvent event) {
		route1.setStart(citySelection.getValue());
	}
	public void box0(ActionEvent event) {
		if(box0.isSelected()) {
			route1.addCity(countryList.getSelectionModel().getSelectedItem().cities.get(0));

		}else {
			route1.getCities().remove(countryList.getSelectionModel().getSelectedItem().cities.get(0));
		}
	}
	public void box1(ActionEvent event) {
		if(box1.isSelected()) {
			route1.addCity(countryList.getSelectionModel().getSelectedItem().cities.get(1));

		}else {
			route1.getCities().remove(countryList.getSelectionModel().getSelectedItem().cities.get(1));
		}
		
	}
	public void box2(ActionEvent event) {
		if(box2.isSelected()) {
			route1.addCity(countryList.getSelectionModel().getSelectedItem().cities.get(2));

		}else {
			route1.getCities().remove(countryList.getSelectionModel().getSelectedItem().cities.get(2));
		}
		
	}
	public void box3(ActionEvent event) {
		if(box3.isSelected()) {
			route1.addCity(countryList.getSelectionModel().getSelectedItem().cities.get(3));

		}else {
			route1.getCities().remove(countryList.getSelectionModel().getSelectedItem().cities.get(3));
		}
		
	}
	public void box4(ActionEvent event) {
		if(box4.isSelected()) {
			route1.addCity(countryList.getSelectionModel().getSelectedItem().cities.get(4));

		}else {
			route1.getCities().remove(countryList.getSelectionModel().getSelectedItem().cities.get(4));
		}
		
	}
	public void decideRoute(ActionEvent event) {
		
      if(wRadio.isSelected()) {
    	  route1.westSort();
      }else {
    	  route1.eastSort();
      }
      route1.setVehicles(plane1,car1);
      route1.planeAdjust(car1);
		
		String out=route1.getStart().toString();
		for(int i=0;i<route1.getCities().size();i++) {
			out = out.concat(" -> " + route1.getCities().get(i));
		}
		out=out.concat(" -> " + route1.getStart().toString());
		output.setText(out);
		
		String vOut="";
		
		for(int i=0;i<route1.getVehicles().size();i++){
            vOut=vOut.concat("       " + route1.getVehicles().get(i));
        }
		route1.calculateDistance();
		vOutput.setText(vOut);
		distance.setText("Total distance: " + route1.getTotalDistance() + " km");

	}
}
