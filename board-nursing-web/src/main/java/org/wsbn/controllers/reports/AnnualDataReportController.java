package org.wsbn.controllers.reports;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.wsbn.data_services.SchoolService;
import org.wsbn.dto.DegreeDto;
import org.wsbn.dto.ProgramDto;
import org.wsbn.dto.SchoolDto;
import org.wsbn.dto.YearDto;
import org.wsbn.dto.reports.SchoolAnnualDataReport;
import org.wsbn.dto.reports.SchoolAnnualDataReport.eData;
import org.wsbn.vo.DegreeVo;
import org.wsbn.vo.reports.ProgramAdmissionVo;

@ManagedBean
@ViewScoped
public class AnnualDataReportController implements Serializable
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
	private boolean				bInit			= false;
	 
	// lists
	private List<YearDto> mYearsList;
	private List<SchoolDto>		mAllSchoolsList;
	private List<ProgramAdmissionVo>		mProgramAdmissionVoList;
	private List<ProgramAdmissionVo>		mRnProgramAdmissionVoList;
	private List<ProgramDto>	mAllProgramsDtoList;
	private List<ProgramDto>    mSchoolProgramsList;
	private List<DegreeVo> mDegreeVoList;
 
	// flags
		

	// GUI CALLS
	public void init(String pReportType)
	{
		// try to prevent this method from being called twice ...
		if (bInit == true) return;

		// set flag
		bInit = true;

		try {

			 
					
			this.mYearsList = null;
			this.mAllSchoolsList = null;
			this.mProgramAdmissionVoList = null;
			this.mRnProgramAdmissionVoList = null;
			this.mAllProgramsDtoList = null;
			this.mDegreeVoList = null;
			
			this.schoolService.init();
			this.mAllProgramsDtoList = this.schoolService.getAllProgramsList();
			this.mAllSchoolsList = this.schoolService.getAllSchoolsList();
			this.mYearsList = this.schoolService.getYearsList();
			List<DegreeDto>  oDegreeDtoList = this.schoolService.getDegreeDtoList();
			this.mDegreeVoList = this.buildDegreeVoList(oDegreeDtoList);
			
			// load school's annual data
			if(pReportType.equalsIgnoreCase(eData.ADMISSIONS.toString() ))
			{
				List<SchoolAnnualDataReport> oSchoolAnnualDataDtoList = this.schoolService.getSchoolAnnualDataByPnProgramGroup(eData.ADMISSIONS);
				this.mProgramAdmissionVoList = buildSchoolsAnnualDataList(oSchoolAnnualDataDtoList);
				oSchoolAnnualDataDtoList = this.schoolService.getSchoolAnnualDataByAdnBsnProgramGroups(eData.ADMISSIONS);
				this.mRnProgramAdmissionVoList = this.buildSchoolsAnnualDataList(oSchoolAnnualDataDtoList);
			}
			else if(pReportType.equalsIgnoreCase(eData.GRADUATIONS.toString() ))
			{
				List<SchoolAnnualDataReport> oSchoolAnnualDataDtoList = this.schoolService.getSchoolAnnualDataByPnProgramGroup(eData.GRADUATIONS);
				this.mProgramAdmissionVoList = buildSchoolsAnnualDataList(oSchoolAnnualDataDtoList);
				oSchoolAnnualDataDtoList = this.schoolService.getSchoolAnnualDataByAdnBsnProgramGroups(eData.GRADUATIONS);
				this.mRnProgramAdmissionVoList = this.buildSchoolsAnnualDataList(oSchoolAnnualDataDtoList);
			}
			else if(pReportType.equalsIgnoreCase(eData.GRADUATE.toString() ))
			{
				List<SchoolAnnualDataReport> oSchoolAnnualDataDtoList = this.schoolService.getSchoolAnnualDataByGraduatePrograms(eData.ADMISSIONS);
				this.mProgramAdmissionVoList = buildSchoolsAnnualDataList(oSchoolAnnualDataDtoList);
				oSchoolAnnualDataDtoList = this.schoolService.getSchoolAnnualDataByGraduatePrograms(eData.GRADUATIONS);
				this.mRnProgramAdmissionVoList = this.buildSchoolsAnnualDataList(oSchoolAnnualDataDtoList);
			}
			
			
			
			
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
	
	
	
	private List<ProgramAdmissionVo>buildSchoolsAnnualDataList(List<SchoolAnnualDataReport> pDtoList)
	{
		// validate arguments
		if(pDtoList == null || pDtoList.size() < 1) return null;
		
		// variables
		int Y2008 = 0;
		int Y2009 = 0;
		int Y2010 = 0;
		int Y2011 = 0;
		int Y2012= 0;
		int Y2013= 0;
		int Y2014= 0;
		int Y2015= 0;
		
		
		List<ProgramAdmissionVo> oResponse = new ArrayList<ProgramAdmissionVo>();
		
		
		// for every school annual data dto in the list
		for(SchoolAnnualDataReport oSchoolAnnualDataDto : pDtoList )
		{
			// convert it into a value object
			ProgramAdmissionVo oVo = oSchoolAnnualDataDto.getVo();
			
			// add vo school name
			for(SchoolDto schoolDto: this.mAllSchoolsList)
			{
				if(  schoolDto.getRid().equals(Long.parseLong(oVo.getSchoolRid())))
				{
					oVo.setSchoolName(schoolDto.getName());
					break;
				}
			}
			
			// add vo program name
			for(ProgramDto oProgramDto : this.mAllProgramsDtoList)
			{
				if(oProgramDto.getRid() == Long.parseLong(oVo.getProgramRid()))
				{
					oVo.setProgramName(oProgramDto.getName());
					break;
				}
			}
			
			// add vo degree name
			for(DegreeVo oDegreeVo : this.mDegreeVoList)
			{
				if(oDegreeVo.getRid().equalsIgnoreCase(oVo.getDegreeRid()))
				{
					oVo.setDegreeName(oDegreeVo.getName());
					break;
				}
			}
			
			
			Y2008 += oSchoolAnnualDataDto.getY2008();
			Y2009 += oSchoolAnnualDataDto.getY2009();
			Y2010 += oSchoolAnnualDataDto.getY2010();
			Y2011 += oSchoolAnnualDataDto.getY2011();
			Y2012 += oSchoolAnnualDataDto.getY2012();
			Y2013 += oSchoolAnnualDataDto.getY2013();
			Y2014 += oSchoolAnnualDataDto.getY2014();
			Y2015 += oSchoolAnnualDataDto.getY2015();
			
			
			// add the vo to the response
			oResponse.add(oVo);
					
		}
		
		ProgramAdmissionVo oNewVo = new ProgramAdmissionVo();
		oNewVo.setSchoolRid("-1");
		oNewVo.setSchoolName("   ");
		oNewVo.setProgramName("         TOTALS:");
		oNewVo.setDegreeName("         TOTALS:");
		oNewVo.setY2008(String.valueOf(Y2008));
		oNewVo.setY2009(String.valueOf(Y2009));
		oNewVo.setY2010(String.valueOf(Y2010));
		oNewVo.setY2011(String.valueOf(Y2011));
		oNewVo.setY2012(String.valueOf(Y2012));
		oNewVo.setY2013(String.valueOf(Y2013));
		oNewVo.setY2014(String.valueOf(Y2014));
		oNewVo.setY2015(String.valueOf(Y2015));
		
		oResponse.add(oNewVo);
		
		
		return oResponse;
	}
	
	public List<ProgramAdmissionVo> getSchoolsAnnualDataVoList()
	{
		return this.mProgramAdmissionVoList;

	}
	
	public List<ProgramAdmissionVo> getRcSchoolsAnnualDataVoList()
	{
		return this.mRnProgramAdmissionVoList;

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


		

	// ============ EVENT HANDLERS ==================
	
	
	
	
	
		
	
	

}
