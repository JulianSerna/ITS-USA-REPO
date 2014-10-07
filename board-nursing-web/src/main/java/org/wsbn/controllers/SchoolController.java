package org.wsbn.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
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
@SessionScoped
public class SchoolController implements Serializable
{

	private static final long	serialVersionUID	= 1L;

	// COLLABORATORS
	@ManagedProperty(value="#{schoolService}")
	private SchoolService schoolService;
 
	
	private SchoolsProgramsDao	mSchoolsProgramsDao	= new SchoolsProgramsDao();
	private ProgramsDao			mProgramsDao		= new ProgramsDao();

	// STATE

	// lists
	private List<Boolean>		mDisabledList		= buildDisabledList();

	private List<SchoolDto>		mAllSchoolsList		= null;
	private List<ProgramDto>	mAllProgramsList	= null;

	// selections
	private SchoolDto			mSelectedSchool		= null;
	private SchoolDto			mNewSchoolDto		= null;
	private List<ProgramDto>	mSelectedPrograms	= null;
	// private List<Object> mSelectedProgramIds;

	// CONSTRUCTOR(S)
	public SchoolController() {
		super();
		// dao(s)

	}
    
	// IoC
	//must povide the setter method
	public void setSchoolService(SchoolService pSchoolService) 
	{
		this.schoolService = pSchoolService;
	}
	
	
	// GUI CALLS
	public void updateLists()
	{

		mAllSchoolsList = this.schoolService.loadFullSchoolDtoList();
		this.mSchoolsProgramsDao.findAll();

		if (this.mAllProgramsList == null) 
		{
			this.mAllProgramsList = this.mProgramsDao.findAll();
		}
	}

	// GETTER/SETTERS
	private List<Boolean> buildDisabledList()
	{
		// hard-coded
		this.mDisabledList = new ArrayList<Boolean>(2);
		this.mDisabledList.add(true);
		this.mDisabledList.add(false);

		return mDisabledList;
	}

	public List<ProgramDto> getAllProgramDtoList()
	{
		if (this.mAllProgramsList == null) {
			return new ArrayList<ProgramDto>();
		}

		return this.mAllProgramsList;

	}
	public List<Long> getSelectedPrograms()
	{
		if (this.mSelectedSchool == null) 
		{
			return new ArrayList<Long>();
		}
		
		return this.mSelectedSchool.getProgramRids();
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
		if (this.mNewSchoolDto == null) {
			this.mNewSchoolDto = new SchoolDto();
		}

		return mNewSchoolDto;
	}

	public void setSelectedSchool(SchoolDto pDto)
	{
		this.mSelectedSchool = pDto;
	}
	public SchoolDto getSelectedDto()
	{
		if (this.mSelectedSchool == null) {
			return new SchoolDto();
		}
		return mSelectedSchool;
	}
	public void setSelectedDto(SchoolDto pDto)
	{
		this.mSelectedSchool = pDto;
	}
	
	public List<SchoolDto> getSchoolDtoList()
	{
		return this.mAllSchoolsList;
		
	}

	
	// METHODS
	public void deleteRow()
	{

		this.schoolService.deleteFullSchoolDto(mSelectedSchool);
		
		this.mAllSchoolsList = this.schoolService.loadFullSchoolDtoList();
	}

	public void addAction()
	{
		// add school dto
		this.schoolService.saveFullSchoolDto(this.mNewSchoolDto);
		
		// load all schools
		this.mAllSchoolsList = this.schoolService.loadFullSchoolDtoList();

		
	}

	public void onRowSelect(SelectEvent event)
	{
		FacesMessage msg = new FacesMessage("Row Selected", ((SchoolDto) event.getObject()).getRid().toString());
		this.mSelectedSchool = (SchoolDto) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event)
	{
		this.mSelectedSchool = null;
		FacesMessage msg = new FacesMessage("Row Unselected", ((SchoolDto) event.getObject()).getRid().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public synchronized void onEdit(RowEditEvent event)
	{

		try {
			
			// cast from event to dto
			SchoolDto oEditDto = (SchoolDto) event.getObject();

			// save school and selected school programs
			this.schoolService.saveFullSchoolDto(oEditDto);
			
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

	/**
	 * This method gets a list of all the ProgramDtos in the database and
	 * converts them to a SchooProgramDto list
	 * 
	 * @param pSchoolDto
	 *            The SchoolDto used to get all the only the Programs for a
	 *            particular School
	 * @return
	 */
	private void addRemoveSchoolPrograms(SchoolDto pSchoolDto)
	{

		// validate arguments
		if (pSchoolDto == null) {
			return;
		}

		// remove all the school programs from database
		

		
		// validate user has selected some programs
		if (pSchoolDto != null ) 
		{
			try {
				// update the selected program rids
				this.schoolService.saveFullSchoolDto(pSchoolDto);
				
				// update the schools list
				this.mAllSchoolsList = this.schoolService.loadFullSchoolDtoList();
				

			}
			catch (Exception e) {
				System.out.println(e.toString());
			}
		}

	}

}
