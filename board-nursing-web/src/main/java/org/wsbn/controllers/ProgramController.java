package org.wsbn.controllers;



import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.wsbn.dao.ProgramsDao;
import org.wsbn.dto.ProgramDto;


@ManagedBean
@SessionScoped
public class ProgramController implements Serializable
{
	
	
	private static final long serialVersionUID = 1L;
	// STATE
	private List<ProgramDto> mDtoList = null;
	private ProgramDto mSelectedDto = null;

	// COLLABORATORS
	private ProgramsDao mDao = null;

	// CONSTRUCTOR(S)
	public ProgramController() 
	{
		mDao = new ProgramsDao();
	}

	// GETTER/SETTERS
	public List<ProgramDto>  getDtoList()
	{
		System.out.print("ProgramController.getDtoList called");
		if( this.mDtoList == null )
		{
			this.loadDtoList();    
		}
		
		return this.mDtoList;
	}

	// METHODS
	public String  loadDtoList()
	{

		this.mDtoList = mDao.findAll();
		return "success";
		
	}
	public String enableEntity(ProgramDto pDto)
	{

		mDao.enableEntity(pDto);
		this.mDtoList = mDao.findAll();
		return "success";
	}

	public String disableEntity(ProgramDto pDto)
	{

		mDao.disableEntity(pDto);
		this.mDtoList = mDao.findAll();
		return "success";
	}
	
	public void findAll()
	{
		this.mDtoList = mDao.findAll();
	
	}
	
	
	
	
	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Row Selected", ((ProgramDto) event.getObject()).getRid().toString());
        this.mSelectedDto = (ProgramDto) event.getObject();
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
	public void onRowEdit(RowEditEvent event) {  
        ProgramDto oEditDto = (ProgramDto) event.getObject();
        this.mDao.saveEntity(oEditDto);
    	FacesMessage msg = new FacesMessage("Edited Successful ...",((ProgramDto) event.getObject()).getRid().toString());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
       
    public void onRowCancel(RowEditEvent event) 
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled ...");   
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        
    }  
	

}
