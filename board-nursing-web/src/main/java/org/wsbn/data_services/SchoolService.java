package org.wsbn.data_services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.wsbn.dao.ProgramsDao;
import org.wsbn.dao.SchoolsDao;
import org.wsbn.dao.SchoolsProgramsDao;
import org.wsbn.dto.ProgramDto;
import org.wsbn.dto.SchoolDto;
import org.wsbn.dto.SchoolProgramDto;


@ManagedBean(name="schoolService")
@SessionScoped
public class SchoolService implements Serializable
{
	
	private static final long	serialVersionUID	= 1L;
	
	// COLLABORATORS
	SchoolsDao mSchoolsDao; 
	SchoolsProgramsDao mSchoolsProgramsDao;
	ProgramsDao mProgramsDao;
	
	
	// STATE
	boolean bInit = false;
	
	// lists
	private List<SchoolDto> allSchoolsList;
	private List<ProgramDto> allProgramsList;
	private List<SchoolProgramDto> allSchoolProgramList;
	
		
	
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
			 
			 // Lists
			 this.allSchoolsList = null;
			 this.allProgramsList = null;
			 this.allSchoolProgramList = null;
			 
			 this.allSchoolsList = this.mSchoolsDao.findAll();
			 this.allProgramsList = this.mProgramsDao.findAll();
			 this.allSchoolProgramList = this.mSchoolsProgramsDao.findAll();
			 
			 this.allSchoolsList =  this.buildFullSchoolList();
		 }
		 catch(Exception e)
		 {
			 this.bInit = false;
		 }
		 
       
    }
	
	 
	public List<SchoolDto> getAllSchoolsList()
	{
		return this.allSchoolsList;
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
	
	public void  deleteFullSchoolDto(SchoolDto pSchoolDto)
	{
		
		// delete school programs
		this.mSchoolsProgramsDao.deleteSchoolPrograms(pSchoolDto);
		
		// delete school 
		this.mSchoolsDao.deleteEntity(pSchoolDto);
		
		// refresh just updated list
		this.refreshAllLists();
		
		
		
		
							
		
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
