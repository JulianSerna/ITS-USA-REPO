package org.wsbn.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.wsbn.dao.ProgramsDao;
import org.wsbn.dao.SchoolsProgramsDao;
import org.wsbn.data_services.SchoolService;
import org.wsbn.dto.ProgramDto;
import org.wsbn.dto.SchoolDto;
import org.wsbn.dto.SchoolProgramDto;

@ManagedBean
@ViewScoped
public class SchoolController implements Serializable
{

	private static final long	serialVersionUID	= 1L;
	
	// COLLABORATORS
	
	// IoC
	@ManagedProperty(value="#{schoolService}")
	private SchoolService schoolService;
 
	// STATE
	private SchoolDto mNewSchoolDto;
	private SchoolDto mSelectedSchoolDto;
	private boolean bInit = false;
		

	// lists
	private List<Boolean>		mDisabledList		= buildDisabledList();
	private List<SchoolDto> mAllSchoolsList;
	private List<ProgramDto> mAllProgramsList;
	//private List<SchoolProgramDto> mAllSchoolProgramList;
   
	// IoC
	//must provide the setter method
	public void setSchoolService(SchoolService pSchoolService) 
	{
		
		this.schoolService = pSchoolService;
	}
	
	
	// GUI CALLS
	public void init()
	{
		// try to prevent this method from being called twice ...
		if(bInit == true) return;

		// set flag
		bInit = true;
		
		try
		{
			
			this.mAllSchoolsList = null;
			this.mAllProgramsList = null;
			
			this.mAllSchoolsList = this.schoolService.getAllSchoolsList();
			this.mAllProgramsList = this.schoolService.getAllProgramsList();
		}
		catch(Exception e)
		{
			bInit = false;
		}
		
		
	}

	// GETTER/SETTERS
	
	public void setSelectedSchool(SchoolDto pDto)
	{
		this.mSelectedSchoolDto = pDto;
	}
	public SchoolDto getSelectedSchool()
	{
		return this.mSelectedSchoolDto;	
	}
	
		
	private List<Boolean> buildDisabledList()
	{
		if(this.mDisabledList == null)
		{
			
			// hard-coded
			this.mDisabledList = new ArrayList<Boolean>(2);
			this.mDisabledList.add(true);
			this.mDisabledList.add(false);
		
		}

		return mDisabledList;
	}
	
	public void onRowSelect(SelectEvent event) 
	{
        this.setSelectedSchool(  ((SchoolDto) event.getObject()));
		
    }
 
	
    public void onRowUnselect(UnselectEvent event) 
    {
    	
    	this.mSelectedSchoolDto = null;
    
    }
	
	
	
	public List<ProgramDto> getAllProgramsDtoList()
	{
		return this.mAllProgramsList;

	}
	
	

	public List<Boolean> getDisabledList()
	{

		if (this.mDisabledList == null) {
			return new ArrayList<Boolean>();
		}
		return this.mDisabledList;

	}

	public SchoolDto getNewSchoolDto()
	{
		
		if(this.mNewSchoolDto  == null)  this.mNewSchoolDto = new SchoolDto();
		return this.mNewSchoolDto;
		
	}
	
	
	public List<SchoolDto> getAllSchoolsDtoList()
	{
		return this.mAllSchoolsList;
		
	}
	
	

	public void addAction()
	{
		// add school dto
		this.schoolService.addFullSchoolDto(this.mNewSchoolDto);
		
		
		

		
	}
	public void deleteAction()
	{
		this.deleteSchool();
		
	}

	public synchronized void onEdit(RowEditEvent event)
	{

		try {
			
			// cast from event to dto
			SchoolDto oEditDto = (SchoolDto) event.getObject();

			// save school and selected school programs
			this.schoolService.saveFullSchoolDto(oEditDto);
			
			// reload schools list
			this.bInit = false;
			this.init();
			
			FacesMessage msg = new FacesMessage("Edit Successful ...", ((SchoolDto) event.getObject()).getRid()
					.toString());
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
		catch (Exception e) {

			FacesMessage msg = new FacesMessage("Edit Not Successful ...", ((SchoolDto) event.getObject()).getRid()
					.toString());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			System.out.println(e.toString());
		}
	}

	public void onCancel(RowEditEvent event)
	{
		FacesMessage msg = new FacesMessage("Edit Cancelled ...");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
	
	public void deleteSchool() 
	{
        // method validation
		if(this.mSelectedSchoolDto == null) return;
		this.schoolService.deleteFullSchoolDto(this.mSelectedSchoolDto);
		this.mAllSchoolsList.remove(this.mSelectedSchoolDto); // test, see if this can be deleted
		this.mSelectedSchoolDto = null;
		this.refreshLists();
		
    }
	
	public void refreshLists()
	{
		this.bInit = false;
		this.init();
	}

	
	

}
