package org.wsbn.data_services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.wsbn.dao.DegreesDao;
import org.wsbn.dao.NclexAnnualDataDao;
import org.wsbn.dao.ProgramGroupsDao;
import org.wsbn.dao.ProgramsDao;
import org.wsbn.dao.SchoolAnnualDataDao;
import org.wsbn.dao.SchoolsDao;
import org.wsbn.dao.SchoolsProgramsDao;
import org.wsbn.dao.YearsDao;
import org.wsbn.dto.DegreeDto;
import org.wsbn.dto.NclexAnnualDataDto;
import org.wsbn.dto.ProgramDto;
import org.wsbn.dto.ProgramGroupDto;
import org.wsbn.dto.SchoolAnnualDataDto;
import org.wsbn.dto.SchoolDto;
import org.wsbn.dto.SchoolProgramDto;
import org.wsbn.dto.YearDto;
import org.wsbn.dto.reports.SchoolAnnualDataReport;
import org.wsbn.dto.reports.SchoolAnnualDataReport.eData;


@ManagedBean(name="schoolService")
@SessionScoped
public class SchoolService implements Serializable
{
	
	private static final long	serialVersionUID	= 1L;
	
	// COLLABORATORS
	private SchoolsDao mSchoolsDao; 
	private SchoolsProgramsDao mSchoolsProgramsDao;
	private SchoolAnnualDataDao mSchoolAnnualDataDao;
	private NclexAnnualDataDao mNclexAnnualDataDao;
	private ProgramsDao mProgramsDao;
	private YearsDao mYearsDao;
	DegreesDao mDegreesDao;
	private ProgramGroupsDao mProgramGroupsDao;
	
	
	
	// STATE
	boolean bInit = false;
	
	// lists
	private List<SchoolDto> allSchoolsList;
	private List<ProgramDto> allProgramsList;
	private List<SchoolProgramDto> allSchoolProgramList;
	private List<YearDto> mYearsList;
	private List<DegreeDto> mDegreeDtoList;
	private List<ProgramGroupDto>    nclexProgramGroupsList;
	
		
	
	// CONSTRUCTORS
	public SchoolService()
	{
		
	}
	
	 @PostConstruct
    public void init() 
	{
		 // prevent double call
		 if(this.bInit == true) return;
		 
		 
		 try
		 {
			 // DAOs
			 if(this.mSchoolsDao == null)   this.mSchoolsDao = new SchoolsDao();
			 if(this.mProgramsDao == null)  this.mProgramsDao = new ProgramsDao();
			 if(this.mSchoolsProgramsDao == null) this.mSchoolsProgramsDao = new SchoolsProgramsDao();
			 if(this.mSchoolAnnualDataDao == null) this.mSchoolAnnualDataDao = new SchoolAnnualDataDao();
			 if(this.mNclexAnnualDataDao == null) this.mNclexAnnualDataDao = new NclexAnnualDataDao();
			 if(this.mYearsDao == null) this.mYearsDao = new YearsDao();
			 if(this.mDegreesDao == null) this.mDegreesDao = new DegreesDao();
			 if(this.mProgramGroupsDao == null) this.mProgramGroupsDao = new ProgramGroupsDao();
			 
			 // Lists
			 this.allSchoolsList = null;
			 this.allProgramsList = null;
			 this.allSchoolProgramList = null;
			 this.mDegreeDtoList = null;
			 this.nclexProgramGroupsList = null;
			 
			 this.allSchoolsList = this.mSchoolsDao.findAll();
			 this.allProgramsList = this.mProgramsDao.findAll();
			 this.allSchoolProgramList = this.mSchoolsProgramsDao.findAll();
			 this.mYearsList = this.mYearsDao.findAll();
			 this.mDegreeDtoList = this.mDegreesDao.findAll();
			 this.allSchoolsList =  this.buildFullSchoolList();
			 this.nclexProgramGroupsList = this.mProgramGroupsDao.findNclex();
			 
			 
		 }
		 catch(Exception e)
		 {
			 this.bInit = false;
		 }
		 
       
    }
	public  List<YearDto> getYearsList()
	{
		return this.mYearsList;
	}
	 
	public List<SchoolDto> getAllSchoolsList()
	{
		return this.allSchoolsList;
	}
	
	public List<SchoolAnnualDataDto> getSchoolAnnualData(Long schoolId)
	{
		return this.mSchoolAnnualDataDao.findBySchoolRid(schoolId);
	}
	
	public List<SchoolAnnualDataReport> getSchoolAnnualDataByPnProgramGroup(eData pValue)
	{
		return this.mSchoolAnnualDataDao.findByPnProgramGroup(pValue);
	}
	
	public List<SchoolAnnualDataReport> getSchoolAnnualDataByAdnBsnProgramGroups(eData pValue)
	{
		return this.mSchoolAnnualDataDao.findByAdnBsnProgramGroups(pValue);
	}
	
	public List<SchoolAnnualDataReport> getSchoolAnnualDataByGraduatePrograms(eData pValue)
	{
		return this.mSchoolAnnualDataDao.findByGraduatePrograms(pValue);
	}
	
	public List<DegreeDto> getDegreeDtoList()
	{
		return this.mDegreeDtoList;
		
	}
		
	
	public List<NclexAnnualDataDto> getNclexAnnualData(Long schoolId)
	{
		return this.mNclexAnnualDataDao.findBySchoolRid(schoolId);
	}
	public List<SchoolAnnualDataDto> getAllSchoolsAnnualData()
	{
		return this.mSchoolAnnualDataDao.findAll();
	}
		
	public void  saveFullSchoolDto(SchoolDto pSchoolDto)
	{
		
		
		// save the school dto
		this.mSchoolsDao.saveEntity(pSchoolDto);
			
		// save the school-program dto list
		this.mSchoolsProgramsDao.updateSchoolRids(pSchoolDto);
		
		// refresh everything
		this.refreshAllLists();
		
	}
	

	public void  addFullSchoolDto(SchoolDto pSchoolDto)
	{
		
		
		// save the school dto
		this.mSchoolsDao.addEntity(pSchoolDto);
				
		// save the school-program dto list
		this.mSchoolsProgramsDao.updateSchoolRids(pSchoolDto);
		
		// refresh just updated list ...
		this.refreshAllLists();
		
	}
	
	public SchoolAnnualDataDto  addSchoolAnnualData(SchoolAnnualDataDto pSchoolDto)
	{
		// response
		SchoolAnnualDataDto oResponse;
		
		// save the school dto
		 oResponse = this.mSchoolAnnualDataDao.addEntity(pSchoolDto);
				
				
		// refresh just updated list ...
		this.mSchoolAnnualDataDao.findBySchoolRid(pSchoolDto.getSchoolRid());
		
		return oResponse;
		
	}
	
	public NclexAnnualDataDto  addNclexAnnualData(NclexAnnualDataDto pSchoolDto)
	{
		// response
		NclexAnnualDataDto oResponse;
		
		// save the school dto
		oResponse = this.mNclexAnnualDataDao.addEntity(pSchoolDto);
				
				
		// refresh just updated list ...
		this.mSchoolAnnualDataDao.findBySchoolRid(pSchoolDto.getSchoolRid());
		
		
		return oResponse;
		
	}
	
	public void  updateSchoolAnnualData(SchoolAnnualDataDto pSchoolDto)
	{
		
		
		// save the school dto
		this.mSchoolAnnualDataDao.saveEntity(pSchoolDto);
				
				
		// refresh just updated list ...
		this.mSchoolAnnualDataDao.findBySchoolRid(pSchoolDto.getSchoolRid());
		
	}
	
	
	public void  updateNclexAnnualData(NclexAnnualDataDto pSchoolDto)
	{
		
		
		// save the school dto
		this.mNclexAnnualDataDao.saveEntity(pSchoolDto);
				
				
		// refresh just updated list ...
		this.mNclexAnnualDataDao.findBySchoolRid(pSchoolDto.getSchoolRid());
		
	}
	
	public void  deleteFullSchoolDto(SchoolDto pSchoolDto)
	{
		
		// delete school programs
		this.mSchoolsProgramsDao.deleteSchoolPrograms(pSchoolDto);
		
		// delete school 
		this.mSchoolsDao.deleteEntity(pSchoolDto);
		
		// refresh just updated list
		this.refreshAllLists();
		
	}
	
	
	public void  deleteSchoolAnnualData(SchoolAnnualDataDto pSchoolDto)
	{
		
		
		// save the school dto
		this.mSchoolAnnualDataDao.deleteEntity(pSchoolDto);
				
				
				
	}
	
	public void  deleteNclexAnnualData(NclexAnnualDataDto pSchoolDto)
	{
		
		
		// save the school dto
		this.mNclexAnnualDataDao.deleteEntity(pSchoolDto);
				
				
				
	}
	
	private List<SchoolDto> buildFullSchoolList()
	{
		
				
		// for every school
		for(SchoolDto oSchoolDto : this.allSchoolsList)
		{
			// variables
			List<String> oStringList;
			oStringList = new ArrayList<String>();
			
			
			boolean bAdded = false;
			
			// for every dto in the school-program dto list
			for (SchoolProgramDto oSchoolProgram : this.allSchoolProgramList)
			{
				
				
				
				// compare the rid of the school dto and the school-program dto
				if (oSchoolDto.getRid().equals(oSchoolProgram.getSchoolRid()))
				{
					// if they are equal, add it to string list
					oStringList.add(oSchoolProgram.getProgramRid().toString());
					bAdded = true;
				}
			
			}
			
			// if school-program dtos were created
			if(bAdded == true)
			{
				// add them to the school dto
				oSchoolDto.setProgramRids(oStringList.toArray(new String[oStringList.size()]));
			}
		}
		
		return this.allSchoolsList;
		
	}
	
		
	public List<ProgramDto> getAllProgramsList()
	{
		
		return this.allProgramsList;
		
	}
	
	public List<ProgramGroupDto> getNclexProgramGroupsList()
	{
		
		return this.nclexProgramGroupsList;
		
	}
	
	public void refreshAllLists()
	{
		this.bInit = false;
		this.init();
	}
	
	public SchoolDto getSchoolDto(String pRid)
	{
		SchoolDto oResponse;
		
		SchoolDto oTmpDto = new SchoolDto();
		oTmpDto.setRid(Long.parseLong(pRid));
		if(this.allSchoolsList.contains(oTmpDto));
		{
			int iIndex = this.allSchoolsList.indexOf(oTmpDto);
			oResponse = this.allSchoolsList.get(iIndex);
		}
		
		return oResponse;
		
	}
	
	
	
	
	

}
