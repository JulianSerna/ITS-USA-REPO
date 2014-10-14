package org.wsbn.controllers;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.ReorderEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.wsbn.dao.ProgramsDao;
import org.wsbn.dto.ProgramDto;
import org.wsbn.dto.SchoolDto;


@ManagedBean
@ViewScoped
public class ProgramController implements Serializable
{
	
	
	private static final long serialVersionUID = 1L;
	// STATE
	private boolean bInit = false;
	private ProgramDto mNewProgramDto;
	
	// lists
	private List<ProgramDto> mProgramsList;
	private List<Boolean> mDisabledList;
	
	// variables
	private ProgramDto mSelectedProgram;

	// COLLABORATORS
	private ProgramsDao mProgramsDao = null;

	

	// GUI CALLS
	public void init()
	{
		// try to prevent this method from being called twice ...
		if(bInit == true) return;

		// set flag
		bInit = true;
		
		try
		{
			// set all daos
			if(this.mProgramsDao == null) this.mProgramsDao = new ProgramsDao();
			
			// make all lists null;
			this.mProgramsList = null;
			this.mSelectedProgram = null;
			
			// reload the lists
			this.mProgramsList = this.mProgramsDao.findAll();
			this.buildDisabledList();
		}
		catch(Exception e)
		{
			bInit = false;
		}
		
		
	}
	
	
	
	
	// INIT HELPERS
	private void buildDisabledList()
	{
		if(this.mDisabledList == null)
		{
			
			// hard-coded
			this.mDisabledList = new ArrayList<Boolean>(2);
			this.mDisabledList.add(true);
			this.mDisabledList.add(false);
		
		}
	}
	
	
	// GETTER/SETTERS

	public ProgramDto getNewProgramDto()
	{
		
		if(this.mNewProgramDto  == null)  this.mNewProgramDto = new ProgramDto();
		return this.mNewProgramDto;
		
	}
	
	
	public List<ProgramDto> getAllProgramsList()
	{
		return this.mProgramsList;
	}
	public void setAllProgramsList(List<ProgramDto> pDtoList)
	{
		this.mProgramsList = pDtoList;
	}
	public List<Boolean> getDisabledList()
	{
		return this.mDisabledList;
	}
	public void setSelectedProgram(ProgramDto pDto)
	{
		this.mSelectedProgram = pDto;
	}
	public ProgramDto getSelectedProgram()
	{
		return this.mSelectedProgram;
	}
	 
		
	
	
	// EDITS PROCESSING
	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Row Selected", ((ProgramDto) event.getObject()).getRid().toString());
        this.mSelectedProgram = (ProgramDto) event.getObject();
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
	public void onRowEdit(RowEditEvent event) 
	{  
        ProgramDto oEditDto = (ProgramDto) event.getObject();
        this.mProgramsDao.saveEntity(oEditDto);
    	FacesMessage msg = new FacesMessage("Edited Successful ...",((ProgramDto) event.getObject()).getRid().toString());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
       
    public void onRowCancel(RowEditEvent event) 
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled ...");   
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        
    }  
    
    public void onRowReorder(ReorderEvent event) 
    {
    	// get the object from local state
    	// TODO:JULS:nice to implement later on ...
    	
    	/*
    	int iFromIndex = event.getFromIndex();
    	int iToIndex = event.getToIndex();
    	
    	
    	ProgramDto oEditDto = this.mProgramsList.get(iFromIndex);
    	oEditDto.setPrintIndex(iToIndex);
    	
    	// a down drop operation
    	if(iFromIndex < iToIndex )
    	{
    		// 
    		for(int i = (iFromIndex + 1); i < (iToIndex + 1); i++)
    		{
    			ProgramDto oTmpDto = this.mProgramsList.get(i);
    			int iTempIndex = oTmpDto.getPrintIndex();
    			iTempIndex = (iTempIndex - 1); // decrease it
    			oTmpDto.setPrintIndex(iTempIndex);
    			this.mProgramsDao.saveEntity(oEditDto);
    			
    		}
    	}
    	else
    	// an up drop operation	
    	{
    		// 
    		for(int i = (iToIndex + 1); i > (iFromIndex + 1); i--)
    		{
    			ProgramDto oTmpDto = this.mProgramsList.get(i);
    			int iTempIndex = oTmpDto.getPrintIndex();
    			iTempIndex = (iTempIndex + 1); // increase it
    			oTmpDto.setPrintIndex(iTempIndex);
    			this.mProgramsDao.saveEntity(oEditDto);
    			
    		}
    	}
    	       
        this.bInit = false;
        this.init();
        
        */
    	
    }
    
    public void addAction()
	{
		// add the program dto
    	this.mProgramsDao.addEntity(this.mNewProgramDto);
    	    		
		// refresh lists
		this.bInit = false;
		this.init();
		
			
	}
	
	

}
