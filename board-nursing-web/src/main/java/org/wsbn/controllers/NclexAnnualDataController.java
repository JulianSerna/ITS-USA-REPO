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
import org.wsbn.dto.NclexAnnualDataDto;
import org.wsbn.dto.ProgramDto;
import org.wsbn.dto.ProgramGroupDto;
import org.wsbn.dto.SchoolAnnualDataDto;
import org.wsbn.dto.SchoolDto;
import org.wsbn.dto.YearDto;
import org.wsbn.vo.DegreeVo;
import org.wsbn.vo.NclexAnnualDataVo;
import org.wsbn.vo.SchoolAnnualDataVo;

@ManagedBean
@SessionScoped
public class NclexAnnualDataController implements iController, Serializable
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
	private NclexAnnualDataVo			mNewNclexAnnualDataVo;
	private NclexAnnualDataVo mSelectedNclexAnnualDataVo; 
	private SchoolDto mSelectedSchoolDto;
	private String mSelectedSchoolRid;
	private boolean				bInit			= false;
	

	// lists
	private List<YearDto> mYearsList;
	private List<SchoolDto>		mAllSchoolsList;
	private List<NclexAnnualDataVo>		mNclexAnnualDataVoList;
	private List<ProgramGroupDto>	mProgramGroupsDtoList;
		
	// variables
	
	
	
	

	// GUI CALLS
	public void init()
	{
		// try to prevent this method from being called twice ...
		if (bInit == true) return;

		// set flag
		bInit = true;

		try {

			 
			this.mYearsList = null;
			this.mAllSchoolsList = null;
			this.mNclexAnnualDataVoList = null;
			this.mProgramGroupsDtoList = null;
						
			this.schoolService.init();
			this.mProgramGroupsDtoList = this.schoolService.getNclexProgramGroupsList();
			this.mAllSchoolsList = this.schoolService.getAllSchoolsList();
			this.mYearsList = this.schoolService.getYearsList();
			
		}
		catch (Exception e) {
			bInit = false;
		}
			

	}
	

	// GETTER/SETTERS
	public List<YearDto> getYearsList()
	{
		return this.mYearsList;
	}
	
	
	
	/**
	 * @return the mSchoolProgramsList
	 */
	public List<ProgramGroupDto> getProgramGroupList()
	{
		return mProgramGroupsDtoList;
	}
	
	// this property is changed every time a school is selected
	public void setSelectedSchoolRid(String pSchoolRid)
	{
		this.mSelectedSchoolRid = pSchoolRid;
		
		
		if(this.mSelectedSchoolRid == null || this.mSelectedSchoolRid == "")
		{
			this.mNclexAnnualDataVoList = null;
			this.mSelectedSchoolDto = null;
			
			this.mSelectedNclexAnnualDataVo = null;
			this.mProgramGroupsDtoList = null;
			
		}
		else
		{
			// set selected school-dto
			this.mSelectedSchoolDto = this.getSelectedSchoolDto(Long.parseLong(pSchoolRid));
			
			// load school's annual data
			List<NclexAnnualDataDto> oNclexAnnualDataDtoList = this.schoolService.getNclexAnnualData(Long.parseLong(pSchoolRid));
			this.mNclexAnnualDataVoList = buildSchoolsAnnualDataList(oNclexAnnualDataDtoList);
					
			
			// make the new school ready
			this.mNewNclexAnnualDataVo = new NclexAnnualDataVo();
			this.mNewNclexAnnualDataVo.setSchoolRid(this.mSelectedSchoolDto.getRid().toString());
			this.mNewNclexAnnualDataVo.setSchoolName(this.mSelectedSchoolDto.getName().toString());
			
			 
				
		}
		
		
		
				
	}
	
	public void setSelectedProgramgGroupRid(String pProgramGroupRid)
	{
		
		this.mNewNclexAnnualDataVo.setProgramGroupRid(pProgramGroupRid);
		
		// get the program dto
		ProgramGroupDto oProgramGroupDto = this.getProgramGroupDto(Long.parseLong(pProgramGroupRid));
		
				
	}
	public String getSelectedProgramRid()
	{
		// procedure validation
		if(this.mNewNclexAnnualDataVo == null) return null;
		
		return this.mNewNclexAnnualDataVo.getProgramGroupRid();
	}
	public void refresh()
	{
		this.bInit = false;
		this.init();
		
		// set selected school-dto
		this.mSelectedSchoolDto = this.getSelectedSchoolDto(Long.parseLong(this.mSelectedSchoolRid));
		
		// load nclex annual data
		List<NclexAnnualDataDto> oSchoolAnnualDataDtoList = this.schoolService.getNclexAnnualData(Long.parseLong(this.mSelectedSchoolRid));
		this.mNclexAnnualDataVoList = buildSchoolsAnnualDataList(oSchoolAnnualDataDtoList);
								
		
		// make the new school ready
		this.mNewNclexAnnualDataVo = new NclexAnnualDataVo();
		this.mNewNclexAnnualDataVo.setSchoolRid(this.mSelectedSchoolDto.getRid().toString());
		this.mNewNclexAnnualDataVo.setSchoolName(this.mSelectedSchoolDto.getName().toString());
		
		
		
	}
	
	private List<NclexAnnualDataVo>buildSchoolsAnnualDataList(List<NclexAnnualDataDto> pDtoList)
	{
		// validate arguments
		if(pDtoList == null || pDtoList.size() < 1) return null;
		
		List<NclexAnnualDataVo> oResponse = new ArrayList<NclexAnnualDataVo>();
		
		
		// for every school annual data dto in the list
		for(NclexAnnualDataDto oNclexAnnualDataDto : pDtoList )
		{
			// convert it into a value object
			NclexAnnualDataVo oVo = oNclexAnnualDataDto.getVo();
			
			// add vo school name
			oVo.setSchoolName(mSelectedSchoolDto.getName());
			
			
			// add vo program name
			for(ProgramGroupDto oProgramGroupDto : this.mProgramGroupsDtoList)
			{
				if(  oProgramGroupDto.getRid() == Long.parseLong(oVo.getProgramGroupRid()))
				{
					oVo.setGroupName(oProgramGroupDto.getGroupName());
					break;
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

		
	public List<SchoolDto> getAllSchoolsDtoList()
	{
		return this.mAllSchoolsList;

	}
	 
	
	public NclexAnnualDataVo getNewSchoolAnnualDataVo()
	{
		// error prevention
		if(this.mNewNclexAnnualDataVo == null)
		{
			return  new NclexAnnualDataVo();
		}
		else
		{
			return this.mNewNclexAnnualDataVo;
		}
		
		

	}
	

	public List<NclexAnnualDataVo> getNclexAnnualDataVoList()
	{
		return this.mNclexAnnualDataVoList;

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
	
	public void setSelectedNclexAnnualDataVo(NclexAnnualDataVo pDto)
	{
		this.mSelectedNclexAnnualDataVo = pDto;
	}
	public NclexAnnualDataVo getSelectedNclexAnnualDataVo()
	{
		return this.mSelectedNclexAnnualDataVo;
	}
	
	
	public void addAction(ActionEvent event)
	{
		
		
		RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean bValidated = true;
        
           
       
        NclexAnnualDataDto oNewAnnualDataDto = new NclexAnnualDataDto();
		oNewAnnualDataDto.setSchoolRid(Long.parseLong(this.mNewNclexAnnualDataVo.getSchoolRid()));
		oNewAnnualDataDto.setProgramGroupRid(Long.parseLong(this.mNewNclexAnnualDataVo.getProgramGroupRid()));
		oNewAnnualDataDto.setYear(Integer.parseInt(this.mNewNclexAnnualDataVo.getYear()));
		
		if(mNewNclexAnnualDataVo.getNclexPassPercent() == null  || mNewNclexAnnualDataVo.getNclexPassPercent() == "") 
		{
			oNewAnnualDataDto.setNclexPassPercent(0.0);
		}
		else
		{
			oNewAnnualDataDto.setNclexPassPercent(Double.valueOf(this.mNewNclexAnnualDataVo.getNclexPassPercent()));
		}
		
			
		
		// add nclex annual data
		oNewAnnualDataDto = this.schoolService.addNclexAnnualData(oNewAnnualDataDto);
		
		
		// load nclex annual data
		List<NclexAnnualDataDto> oSchoolAnnualDataDtoList = this.schoolService.getNclexAnnualData(this.mSelectedSchoolDto.getRid());
		this.mNclexAnnualDataVoList = buildSchoolsAnnualDataList(oSchoolAnnualDataDtoList);
		
		// make the new school ready
		this.mNewNclexAnnualDataVo = new NclexAnnualDataVo();
		this.mNewNclexAnnualDataVo.setSchoolRid(this.mSelectedSchoolDto.getRid().toString());
		this.mNewNclexAnnualDataVo.setSchoolName(this.mSelectedSchoolDto.getName().toString());
		
		
		if(bValidated == true)
		{
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Record Added", oNewAnnualDataDto.getRid().toString());
		}
	
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("validated", bValidated);
        
       
					
		

	}
	public void deleteAction()
	{
		
		// validate procedure
		if(this.mSelectedNclexAnnualDataVo == null) return;
		
		// convert vo to dto
		NclexAnnualDataDto oDto = new NclexAnnualDataDto(Long.parseLong(this.mSelectedNclexAnnualDataVo.getRid()));
		
		// delete dto
		this.schoolService.deleteNclexAnnualData(oDto);
		
		// load nclex annual data
		List<NclexAnnualDataDto> oNclexAnnualDataDtoList = this.schoolService.getNclexAnnualData(this.mSelectedSchoolDto.getRid());
		this.mNclexAnnualDataVoList = buildSchoolsAnnualDataList(oNclexAnnualDataDtoList);

	}

	// ============ EVENT HANDLERS ==================
	public void onProgramGroupChangedEvent()
	{
		
		
	}
	
	
	public void onSchoolChangedEvent()
	{
		
		
	}
	
	
	public void onRowSelect(SelectEvent event)
	{
		this.mSelectedNclexAnnualDataVo = (((NclexAnnualDataVo) event.getObject()));

	}

	public void onRowUnselect(UnselectEvent event)
	{

		this.mSelectedNclexAnnualDataVo = null;

	}
			
	
	
	public synchronized void onEdit(RowEditEvent event)
	{

		// cast from event to vo
		NclexAnnualDataVo oEditVo = (NclexAnnualDataVo) event.getObject();
		
		try {

			
			
			// cast vo to dto
			Long lRid = Long.parseLong(oEditVo.getRid());
			NclexAnnualDataDto oEditDto = new NclexAnnualDataDto(lRid);
			
			oEditDto.setSchoolRid(Long.parseLong(oEditVo.getSchoolRid()));
			oEditDto.setProgramGroupRid(Long.parseLong(oEditVo.getProgramGroupRid()));
			oEditDto.setYear(Integer.parseInt(oEditVo.getYear()));
			
									
			if(oEditVo.getNclexPassPercent() == null || oEditVo.getNclexPassPercent() == "")
			{
				oEditDto.setNclexPassPercent(0.0);
			}
			else
			{
				oEditDto.setNclexPassPercent(Double.valueOf(oEditVo.getNclexPassPercent()));
			}
			
			
			// save dto
			this.schoolService.updateNclexAnnualData(oEditDto);
			
			 // reload list
			List<NclexAnnualDataDto> oSchoolAnnualDataDtoList = this.schoolService.getNclexAnnualData(this.mSelectedSchoolDto.getRid());
			this.mNclexAnnualDataVoList = buildSchoolsAnnualDataList(oSchoolAnnualDataDtoList);
			 

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
	 
	public void deleteSchoolAnnualData()
	{
		// method validation
		if (this.mSelectedNclexAnnualDataVo == null) return;
		
		// cast vo into dto for deletion ...
		NclexAnnualDataDto oDeleteDto = new NclexAnnualDataDto(Long.parseLong(this.mSelectedNclexAnnualDataVo.getRid()));
		this.schoolService.deleteNclexAnnualData(oDeleteDto);
		
		
		// load school's annual data
		List<NclexAnnualDataDto> oSchoolAnnualDataDtoList = this.schoolService.getNclexAnnualData(Long.parseLong(this.mSelectedNclexAnnualDataVo.getRid()));
		this.mNclexAnnualDataVoList = buildSchoolsAnnualDataList(oSchoolAnnualDataDtoList);
		
		// no selected record now
		this.mSelectedNclexAnnualDataVo = null;
		

	}

	public void refreshLists()
	{
		this.bInit = false;
		this.init();
	}
	
	private ProgramGroupDto getProgramGroupDto(Long pProgramGroupRid)
	{
		ProgramGroupDto oResponse = null;
		
		for(ProgramGroupDto oDto : this.mProgramGroupsDtoList)
		{
			if (oDto.getRid().equals(pProgramGroupRid))
			{
				oResponse = oDto;
				break;
			}
		}
		
		
		return oResponse;
	}

}
