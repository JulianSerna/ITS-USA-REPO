 package org.wsbn.controllers.reports;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.wsbn.data_services.ProgramServices;
import org.wsbn.vo.ProgramVo;

@ManagedBean
@ViewScoped
public class ProgramSchoolsReportController implements Serializable
{

	private static final long		serialVersionUID	= 1L;

	// STATE
	private boolean					bInit				= false;

	// variables

	// lists
	private List<ProgramVo>	mSchoolProgramsVoList;

	// COLLABORATORS

	// IoC
	@ManagedProperty(value = "#{programServices}")
	private ProgramServices	programServices;

	// IoC
	// must provide the setter method
	public void setProgramServices(ProgramServices pProgramServices)
	{

		this.programServices = pProgramServices;
	}

	// GUI CALLS
	public void init()
	{
		// try to prevent this method from being called twice ...
		if (bInit == true) return;

		// set flag
		bInit = true;

		try {

			// make all lists null;
			this.mSchoolProgramsVoList = null;
			
			// clar service manager lists
			this.programServices.refreshAllLists();

			// reload the lists
			this.mSchoolProgramsVoList = this.programServices.getAllProgramsVoList();

		}
		catch (Exception e) {
			bInit = false;
		}

	}

	// GETTER/SETTERS
	public List<ProgramVo> getAllProgramsVoList()
	{
		return this.mSchoolProgramsVoList;
	}
	public void setAllProgramsVoList(List<ProgramVo> pDtoList)
	{
		this.mSchoolProgramsVoList = pDtoList;
	}

}
