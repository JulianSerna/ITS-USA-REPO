package org.wsbn.data_services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
	
	// lists
	private List<SchoolDto> mSchoolDtoList;
		
	
	// CONSTRUCTORS
	public SchoolService()
	{
		
	}
	
	
	
	List<SchoolDto> saveEntityList(List<SchoolDto> pDtoList)
	{
		List<SchoolDto> oResponseList = new LinkedList<SchoolDto>();
		
		return oResponseList;
		
	}
	
	public List<SchoolDto>  loadFullSchoolDtoList()
	{
		// init collaborators
		this.init();
		
		// variables
		List<SchoolDto> oResponseList = new ArrayList<SchoolDto>();
		
		
		// get all the schools
		this.mSchoolDtoList = this.mSchoolsDao.findAll();
		
		// for every school, load its program rids
		for (SchoolDto schoolDto : this.mSchoolDtoList)
		{
			// load school-program dto list for the school in question
			List<SchoolProgramDto>  oSchoolProgramDtoList = this.mSchoolsProgramsDao.findSchoolProgramDtoList(schoolDto);
			
			// for each school-program-dto
			for(SchoolProgramDto schoolProgramDto  :oSchoolProgramDtoList)
			{
				// add it to the school in question
				schoolDto.addProgramRid(schoolProgramDto.getProgramRid());
			}
			
		}
			
		
		return oResponseList;
	}
	
	
	public void  saveFullSchoolDto(SchoolDto pSchoolDto)
	{
		// init collaborators
		this.init();
		
		// save the school dto
		this.mSchoolsDao.saveEntity(pSchoolDto);
		
		// save the school-program dto list
		this.mSchoolsProgramsDao.saveEntity(pSchoolDto);
		
			
		// for every school, load its program rids
		for (SchoolDto schoolDto : this.mSchoolDtoList)
		{
			// load school-program dto list for the school in question
			List<SchoolProgramDto>  oSchoolProgramDtoList = this.mSchoolsProgramsDao.findSchoolProgramDtoList(schoolDto);
			
			// for each school-program-dto
			for(SchoolProgramDto schoolProgramDto  :oSchoolProgramDtoList)
			{
				// add it to the school in question
				schoolDto.addProgramRid(schoolProgramDto.getProgramRid());
			}
			
		}
			
		
		
	}
	
	public void  deleteFullSchoolDto(SchoolDto pSchoolDto)
	{
		// init collaborators
		this.init();
		
		
		
		// delete school programs
		this.mSchoolsProgramsDao.deleteSchoolPrograms(pSchoolDto);
		
		// delete school 
		this.mSchoolsDao.deleteEntity(pSchoolDto);
		
			
				
		
	}
	
	private void init()
	{
		if(this.mSchoolsDao == null) this.mSchoolsDao = new SchoolsDao();
		if(this.mSchoolsProgramsDao == null) this.mSchoolsProgramsDao = new SchoolsProgramsDao();
		if(this.mProgramsDao == null) this.mProgramsDao = new ProgramsDao();
	}
	
	

}
