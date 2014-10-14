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
import org.wsbn.vo.ProgramVo;
import org.wsbn.vo.SchoolVo;


@ManagedBean(name = "programServices")
@SessionScoped
public class ProgramServices implements Serializable
{

	private static final long		serialVersionUID	= 1L;

	// COLLABORATORS
	SchoolsDao						mSchoolsDao;
	SchoolsProgramsDao				mSchoolsProgramsDao;
	ProgramsDao						mProgramsDao;

	// STATE
	boolean							bInit				= false;

	// lists
	private List<ProgramDto>		mAllProgramsDtoList;
	private List<SchoolDto>			mAllSchoolsDtoList;
	private List<SchoolProgramDto>	mAllScholsProgramsDtoList;
	private List<ProgramVo>			mAllProgramVoList;

	@PostConstruct
	public void init()
	{
		// prevent double call
		if (this.bInit == true) return;

		this.bInit = true;

		try {
			// DAOs
			if (this.mSchoolsDao == null) this.mSchoolsDao = new SchoolsDao();
			if (this.mProgramsDao == null) this.mProgramsDao = new ProgramsDao();
			if (this.mSchoolsProgramsDao == null) this.mSchoolsProgramsDao = new SchoolsProgramsDao();

			// Lists
			this.mAllProgramsDtoList = null;
			this.mAllScholsProgramsDtoList = null;
			this.mAllSchoolsDtoList = null;
			this.mAllProgramVoList = null;

			this.mAllProgramsDtoList = this.mProgramsDao.findAll();
			this.mAllScholsProgramsDtoList = this.mSchoolsProgramsDao.findAll();
			this.mAllSchoolsDtoList = this.mSchoolsDao.findAll();
			// build this one
			this.mAllProgramVoList = this.buildProgramSchoolsList();

			this.bInit = true;

		}
		catch (Exception e) {
			this.bInit = false;
		}

	}

	//
	private List<ProgramVo> buildProgramSchoolsList()
	{

		// prepare list to hold all program vos
		List<ProgramVo> oResponse = new ArrayList<ProgramVo>();

		// for every program
		for (ProgramDto oProgramDto : this.mAllProgramsDtoList) 
		{

			// create a program vo
			ProgramVo oProgramVo = new ProgramVo();
			oProgramVo.setProgramRid(oProgramDto.getRid());
			oProgramVo.setProgramName(oProgramDto.getName());

			// prepare list to hold all the schools vo for the program in question
			List<SchoolVo> oProgramSchoolsVoList = this.getAllProgramSchools(oProgramDto);
			
			// add the school vo list to the program vo
			oProgramVo.setSchoolVoList(oProgramSchoolsVoList);
			
			// add the program vo to the response
			oResponse.add(oProgramVo);

			
			
		}
		
		

		return oResponse;

	}

	/**
	 * This method returns a List of all the SchoolVo belonging to a ProgramDto
	 * 
	 * @param pProgramDto
	 * @return
	 */
	private List<SchoolVo> getAllProgramSchools(ProgramDto pProgramDto)
	{

		// create the response
		List<SchoolVo> oResponse = new ArrayList<SchoolVo>();

		
		// get the program id in question
		Long lProgramRid = pProgramDto.getRid();

		// for all the school dtos ..
		for (SchoolDto schoolDto : this.mAllSchoolsDtoList) 
		{
			// get the school rid and create a dto
			Long schoolRid = schoolDto.getRid();
			SchoolProgramDto oSchoolProgramDto = new SchoolProgramDto(schoolRid, lProgramRid);
			
			// see if this school-program dto is found in the list of all school-program dtos
			int iIndex = this.mAllScholsProgramsDtoList.indexOf(oSchoolProgramDto);
			
			// if it was found ....
			if(iIndex > -1)
			{
				// add it to the response
				SchoolVo oSchoolVo = new SchoolVo();
				oSchoolVo.setSchoolRid(schoolDto.getRid());
				oSchoolVo.setName(schoolDto.getName());
				
				oResponse.add(oSchoolVo);
			}
			

		}

		// return the response
		return oResponse;
	}


	public List<ProgramVo> getAllProgramsVoList()
	{
		if (this.mAllProgramVoList == null) this.mAllProgramVoList = this.buildProgramSchoolsList();
		
		return this.mAllProgramVoList;

	}

	public void refreshAllLists()
	{
		this.bInit = false;
		this.init();
	}

}
