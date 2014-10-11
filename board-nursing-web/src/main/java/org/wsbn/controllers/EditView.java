package org.wsbn.controllers;



import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.wsbn.dto.Car;
import org.wsbn.data_services.CarService;
 
@ManagedBean(name="dtEditView")
@ViewScoped
public class EditView implements Serializable {
     
    /**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	
         
    // COLLABORATORS
    
    // IoC
    @ManagedProperty("#{carService}")
    private CarService service;
    
    // this is needed for IoC
    public void setService(CarService service) {
        this.service = service;
    }
    
    // STATE
    
    // all lists
 	private List<Car> cars1;
     
    
    
    // IoC setter
    // this can get called twice
    @PostConstruct
    public void init() {
        cars1 = service.createCars(10);
       
    }
 
    // get all list
    public List<Car> getCars1() {
        return cars1;
    }
 
    // get all list   
    public List<String> getBrands() {
        return service.getBrands();
    }
    
    // get all list
    public List<String> getColors() {
        return service.getColors();
    }
 
    
    // when a row has been edited ....
    public void onRowEdit(RowEditEvent event) {
    	
    	Car oCarsDto = (Car) event.getObject();
    	service.saveCard(oCarsDto);
    	this.cars1 = service.getCarsList();
 
        
    }
    
    // when an edit has been cancelled
    public void onRowCancel(RowEditEvent event) 
    {
        return;
    }
     
    
}