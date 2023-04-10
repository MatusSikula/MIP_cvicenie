package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import planning.City;
import planning.Country;

public class CSVReader {
	
	
	public ArrayList<Country> setCountries(File file) throws IOException{
		ArrayList<Country> countries = new ArrayList<Country>();
	BufferedReader bufRdr = new BufferedReader(new FileReader(file));
	String line=null;
	while((line=bufRdr.readLine())!=null) {
		String[] row=line.split(",");
		countries.add(new Country(row[0],row[1],row[2]));
		
	}
	bufRdr.close();
	return countries;
	}
	
	public void setCities(File file,ArrayList<Country> countries) throws IOException{
		int i=0;
		BufferedReader bufRdr = new BufferedReader(new FileReader(file));
		String line=null;
		while((line=bufRdr.readLine())!=null) {
			String[] row=line.split(",");
			if(row[0].equals("next")) {
				i++;
			}else {
			countries.get(i).addCity(new City(row[0],Integer.parseInt(row[1]),Integer.parseInt(row[2]),Integer.parseInt(row[3])));
			}
			
		}
		bufRdr.close();
	}
}
