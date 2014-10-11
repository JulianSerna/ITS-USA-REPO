package org.wsbn.data_services;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


//import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.wsbn.dto.Car;
 
@ManagedBean(name = "carService")
@SessionScoped
public class CarService implements Serializable
{
     
    /**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private final static String[] colors;
     
    private final static String[] brands;
    
    private List<Car> mCarsList;
    
     
    static {
        colors = new String[10];
        colors[0] = "Black";
        colors[1] = "White";
        colors[2] = "Green";
        colors[3] = "Red";
        colors[4] = "Blue";
        colors[5] = "Orange";
        colors[6] = "Silver";
        colors[7] = "Yellow";
        colors[8] = "Brown";
        colors[9] = "Maroon";
         
        brands = new String[10];
        brands[0] = "BMW";
        brands[1] = "Mercedes";
        brands[2] = "Volvo";
        brands[3] = "Audi";
        brands[4] = "Renault";
        brands[5] = "Fiat";
        brands[6] = "Volkswagen";
        brands[7] = "Honda";
        brands[8] = "Jaguar";
        brands[9] = "Ford";
    }
     
    public List<Car> createCars(int size) {
        this.mCarsList = new ArrayList<Car>();
        for(int i = 0 ; i < size ; i++) 
        {
        	
        	String[] colors = new String[2];	
        	colors[0] = getRandomColor();
        	colors[1] = getRandomColor();
            
        	Car oCar = new Car(getRandomId(), getRandomBrand(), getRandomYear(), colors, getRandomPrice(), getRandomSoldState());
        	
        	
            this.mCarsList.add(oCar);
           
        }
         
        return this.mCarsList;
    }
     
    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
     
    private int getRandomYear() {
        return (int) (Math.random() * 50 + 1960);
    }
     
    private String getRandomColor() {
        return colors[(int) (Math.random() * 10)];
    }
     
    private String getRandomBrand() {
        return brands[(int) (Math.random() * 10)];
    }
     
    public int getRandomPrice() {
        return (int) (Math.random() * 100000);
    }
     
    public boolean getRandomSoldState() {
        return (Math.random() > 0.5) ? true: false;
    }
 
    public List<String> getColors() {
        return Arrays.asList(colors);
    }
     
    public List<String> getBrands() {
        return Arrays.asList(brands);
    }
    
    public void saveCard(Car pCar)
    {
    	if(this.mCarsList.contains(pCar))
    	{
    		int iIndex =   this.mCarsList.indexOf(pCar);
    		this.mCarsList.remove(iIndex);
    		this.mCarsList.add(pCar);
    	}
    }
    
    public List<Car> getCarsList()
    {
    	return this.mCarsList;
    }
}