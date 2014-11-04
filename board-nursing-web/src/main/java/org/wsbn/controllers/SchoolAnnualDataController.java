package org.wsbn.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.wsbn.data_services.SchoolService;
import org.wsbn.dto.DegreeDto;
import org.wsbn.dto.ProgramDto;
import org.wsbn.dto.SchoolAnnualDataDto;
import org.wsbn.dto.SchoolDto;
import org.wsbn.dto.YearDto;
import org.wsbn.vo.DegreeVo;
import org.wsbn.vo.SchoolAnnualDataVo;

@ManagedBean
@SessionScoped
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
	private SchoolAnnualDataVo			mNewSchoolAnnualDataVo;
	private SchoolAnnualDataVo mSelectedSchoolAnnualDataVo; 
	private SchoolDto mSelectedSchoolDto;
	private String mSelectedSchoolRid;
	private boolean				bInit			= false;
	private boolean bShowDegrees = false;

	// lists
	private List<YearDto> mYearsList;
	private List<SchoolDto>		mAllSchoolsList;
	private List<SchoolAnnualDataVo>		mSchoolsAnnualDataVoList;
	private List<ProgramDto>	mAllProgramsDtoList;
	private List<ProgramDto>    mSchoolProgramsList;
	private List<DegreeVo> mDegreeVoList;
 
	
	// variables
	
	
	
	

	// GUI CALLS
	public void init()
	{
		// try to prevent this method from being called twice ...
		if (bInit == true) return;

		// set flag
		bInit = true;

		try {

			this.bShowDegrees = false;
			this.mYearsList = null;
			this.mAllSchoolsList = null;
			this.mSchoolsAnnualDataVoList = null;
			this.mAllProgramsDtoList = null;
			this.mDegreeVoList = null;
			
			this.schoolService.init();
			this.mAllProgramsDtoList = this.schoolService.getAllProgramsList();
			this.mAllSchoolsList = this.schoolService.getAllSchoolsList();
			this.mYearsList = this.schoolService.getYearsList();
			List<DegreeDto>  oDegreeDtoList = this.schoolService.getDegreeDtoList();
			this.mDegreeVoList = this.buildDegreeVoList(oDegreeDtoList);
		}
		catch (Exception e) {
			bInit = false;
		}
			

	}
	

	// GETTER/SETTERS
	public List<DegreeVo> getDegreeVoList()
	{
		return this.mDegreeVoList;
	}
	
	public List<YearDto> getYearsList()
	{
		return this.mYearsList;
	}
	
	
	
	/**
	 * @return the mSchoolProgramsList
	 */
	public List<ProgramDto> getSchoolProgramsList()
	{
		return mSchoolProgramsList;
	}
	// this property is changed every time a school is selected
	public void setSelectedSchoolRid(String pSchoolRid)
	{
		this.mSelectedSchoolRid = pSchoolRid;
		
		
		if(this.mSelectedSchoolRid == null || this.mSelectedSchoolRid == "")
		{
			this.mSchoolsAnnualDataVoList = null;
			this.mSelectedSchoolDto = null;
			
			this.mSelectedSchoolAnnualDataVo = null;
			this.mSchoolProgramsList = null;
			
		}
		else
		{
			// set selected school-dto
			this.mSelectedSchoolDto = this.getSelectedSchoolDto(Long.parseLong(pSchoolRid));
			
			// load school's annual data
			List<SchoolAnnualDataDto> oSchoolAnnualDataDtoList = this.schoolService.getSchoolAnnualData(Long.parseLong(pSchoolRid));
			this.mSchoolsAnnualDataVoList = buildSchoolsAnnualDataList(oSchoolAnnualDataDtoList);
			
			// load list of all programs for the selected school
			this.mSchoolProgramsList = this.buildSchoolProgramsList();
						
			
			// make the new school ready
			this.mNewSchoolAnnualDataVo = new SchoolAnnualDataVo();
			this.mNewSchoolAnnualDataVo.setSchoolRid(this.mSelectedSchoolDto.getRid().toString());
			this.mNewSchoolAnnualDataVo.setSchoolName(this.mSelectedSchoolDto.getName().toString());
			
			// set flags
			this.bShowDegrees = false;
				
		}
		
		
		
				
	}
	
	public void setSelectedProgramRid(String pProgramRid)
	{
		
		this.mNewSchoolAnnualDataVo.setProgramRid(pProgramRid);
		
		// get the program dto
		ProgramDto oProgramDto = this.getProgramDto(Long.parseLong(pProgramRid));
		
		if(oProgramDto == null)
		{
			// make sure you set a flag to disable the degrees drop-down box
			this.bShowDegrees = false;
			
		}
		else if(  oProgramDto.getName().equals(ProgramDto.GRADUATE_NURSING_PROGRAM_NAME)  )
		{
			// make sure you set a flag to enable the degrees drop-down box
			this.bShowDegrees = true;
		}
		else
		{
			// make sure you set a flag to disable the degrees drop-down box
			this.bShowDegrees = false;
		}
		
	}
	public String getSelectedProgramRid()
	{
		// procedure validation
		if(this.mNewSchoolAnnualDataVo == null) return null;
		
		return this.mNewSchoolAnnualDataVo.getProgramRid();
	}
	public void refresh()
	{
		this.bInit = false;
		this.init();
		
		// set selected school-dto
		this.mSelectedSchoolDto = this.getSelectedSchoolDto(Long.parseLong(this.mSelectedSchoolRid));
		
		// load school's annual data
		List<SchoolAnnualDataDto> oSchoolAnnualDataDtoList = this.schoolService.getSchoolAnnualData(Long.parseLong(this.mSelectedSchoolRid));
		this.mSchoolsAnnualDataVoList = buildSchoolsAnnualDataList(oSchoolAnnualDataDtoList);
		
		// load list of all programs for the selected school
		this.mSchoolProgramsList = this.buildSchoolProgramsList();
					
		
		// make the new school ready
		this.mNewSchoolAnnualDataVo = new SchoolAnnualDataVo();
		this.mNewSchoolAnnualDataVo.setSchoolRid(this.mSelectedSchoolDto.getRid().toString());
		this.mNewSchoolAnnualDataVo.setSchoolName(this.mSelectedSchoolDto.getName().toString());
		
		
		
	}
	
	private List<SchoolAnnualDataVo>buildSchoolsAnnualDataList(List<SchoolAnnualDataDto> pDtoList)
	{
		// validate arguments
		if(pDtoList == null || pDtoList.size() < 1) return null;
		
		List<SchoolAnnualDataVo> oResponse = new ArrayList<SchoolAnnualDataVo>();
		
		
		// for every school annual data dto in the list
		for(SchoolAnnualDataDto oSchoolAnnualDataDto : pDtoList )
		{
			// convert it into a value object
			SchoolAnnualDataVo oVo = oSchoolAnnualDataDto.getVo();
			
			// add vo school name
			oVo.setSchoolName(mSelectedSchoolDto.getName());
			// add vo program name
			for(ProgramDto oProgramDto : this.mAllProgramsDtoList)
			{
				if(oProgramDto.getRid() == Long.parseLong(oVo.getProgramRid()))
				{
					oVo.setProgramName(oProgramDto.getName());
					break;
				}
			}
			// add the degree name
			for(DegreeVo oDegreeVo : this.mDegreeVoList)
			{
				 if(  oDegreeVo.getRid().equalsIgnoreCase(oVo.getDegreeRid()) )
				 {
					 oVo.setDegreeName(oDegreeVo.getName());
				 }
					 
			}
			
			// add the vo to the response
			oResponse.add(oVo);
			
		}
				
		return oResponse;
	}
	
	public String getSelectedSchoolRid()
	{
		return this.mSelectedSchoolRid;
	}

	public void setSelectedSchool(SchoolAnnualDataVo pDto)
	{
		this.mSelectedSchoolAnnualDataVo = pDto;
	}
	public SchoolAnnualDataVo getSelectedSchool()
	{
		return this.mSelectedSchoolAnnualDataVo;
	}
	
	public List<SchoolDto> getAllSchoolsDtoList()
	{
		return this.mAllSchoolsList;

	}
	 
	
	public SchoolAnnualDataVo getNewSchoolAnnualDataVo()
	{
		// error prevention
		if(this.mNewSchoolAnnualDataVo == null)
		{
			return  new SchoolAnnualDataVo();
		}
		else
		{
			return this.mNewSchoolAnnualDataVo;
		}
		
		

	}
	

	public List<SchoolAnnualDataVo> getSchoolsAnnualDataVoList()
	{
		return this.mSchoolsAnnualDataVoList;

	}
	
	
	
	// ==================== METHODS ===========================
	public Integer getMaxYear()
	{
		// procudure validation
		if(this.mYearsList == null || this.mYearsList.size() == 0)
		{
			return 2014;
		}
		
		int iLastIndex = (this.mYearsList.size() - 1);
				
		return this.mYearsList.get(iLastIndex).getYear();
		
		
		
	}
	
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
	
	
	
	public void addAction(ActionEvent event)
	{
		
		
		RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean bValidated = true;
        
           
       
		SchoolAnnualDataDto oNewAnnualDataDto = new SchoolAnnualDataDto();
		oNewAnnualDataDto.setSchoolRid(Long.parseLong(this.mNewSchoolAnnualDataVo.getSchoolRid()));
		oNewAnnualDataDto.setProgramRid(Long.parseLong(this.mNewSchoolAnnualDataVo.getProgramRid()));
		oNewAnnualDataDto.setDegreeRid(     (this.mNewSchoolAnnualDataVo.getDegreeRid() == null ? 1L  :  Long.parseLong(this.mNewSchoolAnnualDataVo.getDegreeRid() )     ) );	
		
		// if graduate nursing program is selected 
		ProgramDto oProgramDto = this.getProgramDto(oNewAnnualDataDto.getProgramRid());
		if ( oProgramDto.getName().equalsIgnoreCase(ProgramDto.GRADUATE_NURSING_PROGRAM_NAME))
		{
			// a degree must be selected
			if (oNewAnnualDataDto.getDegreeRid().equals(DegreeDto.NA_DEGREE_RID))
			{
				// a validation has failed
				bValidated = false;
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Record Not Added", "Degree cannot be 'N/A'  ");
				
			}
		}
		
				
		
		
		oNewAnnualDataDto.setYear(Integer.parseInt(this.mNewSchoolAnnualDataVo.getYear()));
		
		if(mNewSchoolAnnualDataVo.getNclexPassPercent() == null  || mNewSchoolAnnualDataVo.getNclexPassPercent() == "") 
		{
			oNewAnnualDataDto.setNclexPassPercent(0.0);
		}
		else
		{
			oNewAnnualDataDto.setNclexPassPercent(Double.valueOf(this.mNewSchoolAnnualDataVo.getNclexPassPercent()));
		}
		
		
		if(mNewSchoolAnnualDataVo.getAdmissions() == null  || mNewSchoolAnnualDataVo.getAdmissions() == "") 
		{
			oNewAnnualDataDto.setAdmissions(0);
		}
		else
		{
			oNewAnnualDataDto.setAdmissions(Integer.parseInt(this.mNewSchoolAnnualDataVo.getAdmissions()));
		}
		
		if(mNewSchoolAnnualDataVo.getGraduations() == null  || mNewSchoolAnnualDataVo.getGraduations() == "") 
		{
			oNewAnnualDataDto.setGraduations(0);
		}
		else
		{
			oNewAnnualDataDto.setGraduations(Integer.parseInt(this.mNewSchoolAnnualDataVo.getGraduations()));
		}
		
		
		// add school annual data
		oNewAnnualDataDto = this.schoolService.addSchoolAnnualData(oNewAnnualDataDto);
		
		
		// load school's annual data
		List<SchoolAnnualDataDto> oSchoolAnnualDataDtoList = this.schoolService.getSchoolAnnualData(this.mSelectedSchoolDto.getRid());
		this.mSchoolsAnnualDataVoList = buildSchoolsAnnualDataList(oSchoolAnnualDataDtoList);
		
		// make the new school ready
		this.mNewSchoolAnnualDataVo = new SchoolAnnualDataVo();
		this.mNewSchoolAnnualDataVo.setSchoolRid(this.mSelectedSchoolDto.getRid().toString());
		this.mNewSchoolAnnualDataVo.setSchoolName(this.mSelectedSchoolDto.getName().toString());
		
		
		if(bValidated == true)
		{
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Record Added", oNewAnnualDataDto.getRid().toString());
		}
	
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("validated", bValidated);
        
        this.bShowDegrees = false;
					
		

	}
	public void deleteAction()
	{
		
		// validate procedure
		if(this.mSelectedSchoolAnnualDataVo == null) return;
		
		// convert vo to dto
		SchoolAnnualDataDto oDto = new SchoolAnnualDataDto(Long.parseLong(this.mSelectedSchoolAnnualDataVo.getRid()));
		
		// delete dto
		this.schoolService.deleteSchoolAnnualData(oDto);
		
		// load school's annual data
		List<SchoolAnnualDataDto> oSchoolAnnualDataDtoList = this.schoolService.getSchoolAnnualData(this.mSelectedSchoolDto.getRid());
		this.mSchoolsAnnualDataVoList = buildSchoolsAnnualDataList(oSchoolAnnualDataDtoList);

	}

	// ============ EVENT HANDLERS ==================
	public void onRowSelect(SelectEvent event)
	{
		this.mSelectedSchoolAnnualDataVo = (((SchoolAnnualDataVo) event.getObject()));

	}

	public void onRowUnselect(UnselectEvent event)
	{

		this.mSelectedSchoolAnnualDataVo = null;

	}
	
	
	public void onSchoolChangedEvent()
	{
		
		
	}
	
	public void onProgramChangedEvent()
	{
		
		
	}
	public boolean showDegrees()
	{
		return this.bShowDegrees;
	}
	
	private List<DegreeVo> buildDegreeVoList(List<DegreeDto> pDtoList)
	{
		List<DegreeVo> oResponseList = new ArrayList<DegreeVo>();
		
		for(DegreeDto oDto : pDtoList)
		{
			DegreeVo oVo = oDto.getVo();
			oResponseList.add(oVo);
			
		}
		
		return oResponseList;
		
	
	}
	
	
	
	private List<ProgramDto> buildSchoolProgramsList()
	{
		
		List<ProgramDto> oResponseList = new ArrayList<ProgramDto>();
		
		// every school has a string array of all the programs it belongs to ...
		for(String programRid :   this.mSelectedSchoolDto.getProgramRids())
		{
			// find the real program and add it to the school's program list
			for(ProgramDto programDto : this.mAllProgramsDtoList)
			{
				// if this program belongs to the list of school programs
				if(programDto.getRid() == Long.parseLong(programRid))
				 {
					 
					 // ensure the program has not already been added to the school annual data list; IGNORE FOR NOW
					 boolean bFound = false;
					 /*
					 for(SchoolAnnualDataVo oVo :    this.mSchoolsAnnualDataVoList)
					 {
						 if(oVo.getProgramRid() == programDto.getRid().toString())
						 {
							 bFound = true;
							 break;
						 }
					 }
					 */
					 if(bFound == false)
					 {
						 oResponseList.add(programDto);
					 }
					
				 }
			}
		}
		
		return oResponseList;
		
		
	}
	
	
	public synchronized void onEdit(RowEditEvent event)
	{

		// cast from event to vo
		SchoolAnnualDataVo oEditVo = (SchoolAnnualDataVo) event.getObject();
		
		try {

			
			
			// cast vo to dto
			Long lRid = Long.parseLong(oEditVo.getRid());
			SchoolAnnualDataDto oEditDto = new SchoolAnnualDataDto(lRid);
			
			oEditDto.setSchoolRid(Long.parseLong(oEditVo.getSchoolRid()));
			oEditDto.setProgramRid(Long.parseLong(oEditVo.getProgramRid()));
			oEditDto.setDegreeRid(Long.parseLong(oEditVo.getDegreeRid()));
			oEditDto.setYear(Integer.parseInt(oEditVo.getYear()));
			
			if(oEditVo.getAdmissions() == null || oEditVo.getAdmissions() == "")
			{
				oEditDto.setAdmissions(0);
			}
			else
			{
				oEditDto.setAdmissions(Integer.parseInt(oEditVo.getAdmissions()));
			}
			
			if(oEditVo.getGraduations() == null || oEditVo.getGraduations() == "")
			{
				oEditDto.setGraduations(0);
			}
			else
			{
				oEditDto.setGraduations(Integer.parseInt(oEditVo.getGraduations()));
			}
						
			if(oEditVo.getNclexPassPercent() == null || oEditVo.getNclexPassPercent() == "")
			{
				oEditDto.setNclexPassPercent(0.0);
			}
			else
			{
				oEditDto.setNclexPassPercent(Double.valueOf(oEditVo.getNclexPassPercent()));
			}
			
			
			// save dto
			this.schoolService.updateSchoolAnnualData(oEditDto);
			
			 // reload list
			List<SchoolAnnualDataDto> oSchoolAnnualDataDtoList = this.schoolService.getSchoolAnnualData(this.mSelectedSchoolDto.getRid());
			this.mSchoolsAnnualDataVoList = buildSchoolsAnnualDataList(oSchoolAnnualDataDtoList);
			 

			FacesMessage msg = new FacesMessage("Edit Successful ...", oEditVo.getRid());
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
		catch (Exception e) {

			FacesMessage msg = new FacesMessage("Edit Not Successful ...", oEditVo.getRid());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			System.out.println(e.toString());
		}
	}

	public void onCancel(RowEditEvent event)
	{
		FacesMessage msg = new FacesMessage("Edit Cancelled ...");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
	// TODO:JULS:implement this ...
	public void deleteSchoolAnnualData()
	{
		// method validation
		if (this.mSelectedSchoolAnnualDataVo == null) return;
		
		// cast vo into dto for deletion ...
		SchoolAnnualDataDto oDeleteDto = new SchoolAnnualDataDto(Long.parseLong(this.mSelectedSchoolAnnualDataVo.getRid()));
		this.schoolService.deleteSchoolAnnualData(oDeleteDto);
		
		
		// load school's annual data
		List<SchoolAnnualDataDto> oSchoolAnnualDataDtoList = this.schoolService.getSchoolAnnualData(Long.parseLong(this.mSelectedSchoolAnnualDataVo.getRid()));
		this.mSchoolsAnnualDataVoList = buildSchoolsAnnualDataList(oSchoolAnnualDataDtoList);
		
		// no selected record now
		this.mSelectedSchoolAnnualDataVo = null;
		

	}

	public void refreshLists()
	{
		this.bInit = false;
		this.init();
	}
	
	private ProgramDto getProgramDto(Long pProgramRid)
	{
		ProgramDto oResponse = null;
		
		for(ProgramDto oDto : this.mAllProgramsDtoList)
		{
			if (oDto.getRid().equals(pProgramRid))
			{
				oResponse = oDto;
				break;
			}
		}
		
		
		return oResponse;
	}

}
