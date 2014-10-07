package org.wsbn.dto;

import java.io.Serializable;

public class CarDto implements Serializable 
{

	private static final long serialVersionUID = 6825840912528046868L;
	
	// STATE
	private String model;
	private int year;
	private String manufacturer;
	private String color;
	
			
	// CONSTRUCTORS
	public CarDto (String pModel,  int pYear,  String pManufacturer, String pColor)             
	{
		this.model = pModel;
		this.year = pYear;
		this.manufacturer = pManufacturer;
		this.color = pColor;
		
	}
	
	
	// GETTER/SETTERS
	
	
	
	
	
	




	public String getModel()
	{
		return model;
	}


	public void setModel(String model)
	{
		this.model = model;
	}


	public int getYear()
	{
		return year;
	}


	public void setYear(int year)
	{
		this.year = year;
	}


	public String getManufacturer()
	{
		return manufacturer;
	}


	public void setManufacturer(String manufacturer)
	{
		this.manufacturer = manufacturer;
	}


	public String getColor()
	{
		return color;
	}


	public void setColor(String color)
	{
		this.color = color;
	}
}
