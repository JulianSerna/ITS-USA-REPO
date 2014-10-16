package org.wsbn.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.wsbn.data_services.SchoolService;
import org.wsbn.dto.ProgramDto;
import org.wsbn.dto.SchoolAnnualDataDto;
import org.wsbn.dto.SchoolDto;

@ManagedBean
@ViewScoped
public class SchoolAnnualDataController implements iController, Serializable
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
	private SchoolAnnualDataDto			mNewSchoolDto;
	private SchoolAnnualDataDto			mSelectedSchoolDto;
	private boolean				bInit			= false;

	// lists
	private List<SchoolDto>		mAllSchoolsList;
	private List<SchoolAnnualDataDto>		mSchoolsAnnualDataDtoList;
	private List<ProgramDto>	mAllProgramsList;
	// private List<SchoolProgramDto> mAllSchoolProgramList;
	
	// variables
	private String mSelectedSchoolRid;
	

	// GUI CALLS
	public void init()
	{
		// try to prevent this method from being called twice ...
		if (bInit == true) return;

		// set flag
		bInit = true;

		try {

			this.mAllSchoolsList = null;
			this.mSchoolsAnnualDataDtoList = null;
			this.mAllProgramsList = null;

			//this.mSchoolsAnnualDataDtoList = this.schoolService.getAllSchoolsAnnualData();
			this.mAllProgramsList = this.schoolService.getAllProgramsList();
			this.mAllSchoolsList = this.schoolService.getAllSchoolsList();
		}
		catch (Exception e) {
			bInit = false;
		}
			

	}

	// GETTER/SETTERS
	
	public void setSelectedSchoolRid(String pSchoolRid)
	{
		this.mSelectedSchoolRid = pSchoolRid;
	}
	public String getSelectedSchoolRid()
	{
		return this.mSelectedSchoolRid;
	}

	public void setSelectedSchool(SchoolAnnualDataDto pDto)
	{
		this.mSelectedSchoolDto = pDto;
	}
	public SchoolAnnualDataDto getSelectedSchool()
	{
		return this.mSelectedSchoolDto;
	}
	
	public List<SchoolDto> getAllSchoolsDtoList()
	{
		return this.mAllSchoolsList;

	}


	public void onRowSelect(SelectEvent event)
	{
		this.mSelectedSchoolDto = (((SchoolAnnualDataDto) event.getObject()));

	}

	public void onRowUnselect(UnselectEvent event)
	{

		this.mSelectedSchoolDto = null;

	}

	public List<ProgramDto> getAllProgramsDtoList()
	{
		return this.mAllProgramsList;

	}

	public SchoolAnnualDataDto getNewSchoolDto()
	{

		if (this.mNewSchoolDto == null) this.mNewSchoolDto = new SchoolAnnualDataDto();
		return this.mNewSchoolDto;

	}

	public List<SchoolAnnualDataDto> getAllSchoolsAnnualDataDtoList()
	{
		return this.mSchoolsAnnualDataDtoList;

	}

	public void addAction()
	{
		// add school dto
		this.schoolService.addSchoolAnnualData(this.mNewSchoolDto);
		
		this.mNewSchoolDto = null;

		// refresh lists
		this.bInit = false;
		this.init();

	}
	public void deleteAction()
	{
		this.deleteSchool();

	}

	// ============ EVENT HANDLERS ==================
	public void onSchoolChangedEvent()
	{
		if(this.mSelectedSchoolRid == null || this.mSelectedSchoolRid == "")
		{
			this.mSchoolsAnnualDataDtoList = null;
			
		}
		else
		{
			this.mSchoolsAnnualDataDtoList = this.schoolService.getSchoolAnnualData(Long.parseLong(this.mSelectedSchoolRid));
		}
		
		
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
		if (this.mSelectedSchoolDto == null) return;
		
		//this.schoolService.deleteFullSchoolDto(this.mSelectedSchoolDto);
		this.mSchoolsAnnualDataDtoList.remove(this.mSelectedSchoolDto);  
		this.mSelectedSchoolDto = null;
		this.refreshLists();

	}

	public void refreshLists()
	{
		this.bInit = false;
		this.init();
	}

}
