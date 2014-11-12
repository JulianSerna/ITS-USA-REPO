package org.wsbn.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.wsbn.data_services.SchoolService;
import org.wsbn.dto.SchoolDetailsDto;
import org.wsbn.dto.SchoolDto;
import org.wsbn.dto.YearDto;
import org.wsbn.vo.SchoolDetailsVo;

@ManagedBean
@SessionScoped
public class SchoolDetailsController implements iController, Serializable
{

	private static final long	serialVersionUID	= 1L;

	// COLLABORATORS

	// IoC
	@ManagedProperty(value = "#{schoolService}")
	private SchoolService		schoolService;

	// IoC
	// must provide the setter method
	
	public void setSchoolService(SchoolService pSchoolService)
	{

		this.schoolService = pSchoolService;
	}

	// STATE
	private SchoolDetailsVo mSelectedSchoolDetailsVo; 
	private SchoolDto mSelectedSchoolDto;
	private String mSelectedSchoolRid;
	List<YearDto> mYearsDtoList;
	private String mSelectedYear;
	private boolean				bInit			= false;
	

	// lists
	private List<SchoolDto>		mAllSchoolsList;
	
	
	 
	
 
	
	// variables
	
	
	
	

	// GUI CALLS
	public void init()
	{
		// try to prevent this method from being called twice ...
		if (bInit == true) return;

		// set flag
		bInit = true;

		try {

		 
			 
			this.mAllSchoolsList = null;
			this.mSelectedSchoolDetailsVo = null;
			this.mYearsDtoList = null;
			 
			
			this.schoolService.init();
			this.mAllSchoolsList = this.schoolService.getAllSchoolsList();
			this.mYearsDtoList = this.schoolService.getYearsList();
					 
			 
		}
		catch (Exception e) {
			bInit = false;
		}
			

	}
	

	// GETTER/SETTERS
	public SchoolDetailsVo   getSelectedShoolDetailsVo()
	{
		return mSelectedSchoolDetailsVo;
	}
	
	
	
	public void setSelectedYearRid(String pYearRid)
	{
		this.mSelectedYear = pYearRid;
		
		this.processSelectionChange();
		
		
	}
	public String getSelectedYearRid()
	{
		return this.mSelectedYear;
	}
	
	public List<YearDto> getYearDtoList()
	{
		return this.mYearsDtoList;
	}
	
	// this property is changed every time a school is selected
	public void setSelectedSchoolRid(String pSchoolRid)
	{
		this.mSelectedSchoolRid = pSchoolRid;
		
		this.processSelectionChange();
		
				
	}
	
	private void processSelectionChange()
	{
		if(this.mSelectedSchoolRid == null || this.mSelectedSchoolRid == "")
		{
			this.mSelectedSchoolDetailsVo = null;
			this.mSelectedSchoolDto = null;
		}
		else if(this.mSelectedYear == null || this.mSelectedYear == "")
		{
			this.mSelectedSchoolDetailsVo = null;
			this.mSelectedSchoolDto = null;
		}
		
		else
		{
			// set selected school-dto
			this.mSelectedSchoolDto = this.getSelectedSchoolDto(Long.parseLong(this.mSelectedSchoolRid));
			
			// load school's details
			SchoolDetailsDto oDto  = this.schoolService.getSchoolDetailsDto(Long.parseLong(this.mSelectedSchoolRid), Integer.parseInt(this.mSelectedYear));
			mSelectedSchoolDetailsVo = buildSchoolDetailsVo(oDto);
			 
				
		}
		
		
		
	}
	
	
	private SchoolDetailsVo buildSchoolDetailsVo(SchoolDetailsDto pDto)
	{
		
		SchoolDetailsVo oResponse = null;
		
		
		// prevent errors
		if(pDto == null )
		{
			oResponse =  new SchoolDetailsVo();
						
		}
		else
		{
			oResponse = pDto.getVo();
		}
			
							
		return oResponse;
	}
	
	public String getSelectedSchoolRid()
	{
		return this.mSelectedSchoolRid;
	}

	
	public List<SchoolDto> getAllSchoolsDtoList()
	{
		return this.mAllSchoolsList;

	}
	 
	public boolean displayUpdate()
	{
		boolean bResponse = false;
		
		if (this.mSelectedYear == null  || this.mSelectedYear == "") 
		{
			bResponse = true;
		}
		else if(this.mSelectedSchoolRid== null  || this.mSelectedSchoolRid == "")
		{
			bResponse = true;
		}
			
		
		return bResponse;
	}
	
	
		

	
	
	// ==================== METHODS ===========================
	
	
	
	
	private SchoolDto getSelectedSchoolDto(Long pSchoolRid)
	{
		 SchoolDto oResponse = null;
		
		for(SchoolDto schoolDto : this.mAllSchoolsList)
		{
			if(schoolDto.getRid().equals(pSchoolRid))
			{
				oResponse = schoolDto;
				break;
			}
				
		}
		
		return oResponse;
		
		
		 
	}
	
	
	
	public void updateAction(ActionEvent event )
	{
				
		RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean bValidated = true;
        boolean bAddNew = false;
        
        
        // set flags
        if(this.mSelectedSchoolDetailsVo.getRid() == null)
        {
        	bAddNew = true;
        }
        	
       
        // set VO values
        this.mSelectedSchoolDetailsVo.setYear(this.mSelectedYear);
        this.mSelectedSchoolDetailsVo.setSchoolRid(this.mSelectedSchoolRid);
                
       // transfer VO values to DTO
       SchoolDetailsDto oDto = new SchoolDetailsDto(this.mSelectedSchoolDetailsVo);
       
       if(bAddNew)
       {
    	   oDto = this.schoolService.addSchoolDetailsDto(oDto);
    	   this.mSelectedSchoolDetailsVo = oDto.getVo();
    	   message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Record Added", oDto.getRid().toString());
       }
       else
       {
    	   oDto = this.schoolService.updateSchoolDetailsDto(oDto);
    	   message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Record Edited", oDto.getRid().toString());
       }
    	
	
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("validated", bValidated);
        
	}
	
	public SchoolDto getSelectedSchoolDto()
	{
		SchoolDto oResponse = null;
		
		if (this.mSelectedSchoolDto == null) 
		{
			oResponse = new SchoolDto();
			oResponse.setName("NO SELECTION MADE");
			
		}
		else
		{
			oResponse = this.mSelectedSchoolDto;
		}
		
		return oResponse;
	}
	
	public void refreshLists()
	{
		this.bInit = false;
		this.init();
	}

	// ============ EVENT HANDLERS ==================
	public void onYearChangedEvent()
	{
		
		
	}
	
	public void onSchoolChangedEvent()
	{
		
		
	}
		

	
	
	

}
