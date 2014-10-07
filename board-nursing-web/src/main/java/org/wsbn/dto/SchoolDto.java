package org.wsbn.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;




@Entity
@Table(name = "SCHOOLS")
public class SchoolDto implements Serializable
{

	
	
	
	
	private static final long serialVersionUID = 7614582647263561082L;
	
	// STATE
	
	@Transient
	private List<Long> programRids;
	
	@Id
	@GeneratedValue
	@Column(name = "RID")
	private Long rid;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DISABLED")
	private Boolean disabled;
	
	
		
	
	// CONSTRUCTOR(S)
	public SchoolDto() 
	{

	}
	public SchoolDto(String pName) 
	{

		this.name = pName;

	}

	// SETTERS/GETTERS
	public List<Long> getProgramRids()
	{
		if(this.programRids == null)
		{
			this.programRids = new ArrayList<Long>();
		}
		
		return this.programRids;
	}
	public void setProgramsRids(List<Long> pProgramRids)
	{
		this.programRids = pProgramRids;
	}
		
	public Long getRid()
	{
		return rid;
	}
	public void setRid(Long rid)
	{
		this.rid = rid;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String pName)
	{
		this.name = pName;
	}
	
	public void addProgramRid(Long pProgramRid)
	{
		if (programRids == null)
		{
			this.programRids = new ArrayList<Long>();
		}
		if(!this.programRids.contains(pProgramRid))
		{
			this.programRids.add(pProgramRid);
		}
			
	}
	
	public void removeProgram(String pProgramRid)
	{
		// method validation
		if (this.programRids == null)
		{
			return;
		}
		
				
		if(this.programRids.contains(pProgramRid))
		{
			this.programRids.remove(pProgramRid);
		}
			
	}
	
	public void removeAllPrograms()
	{
		this.programRids = new ArrayList<Long>();
		
	}
	/**
	 * @return the disabled
	 */
	public Boolean getDisabled()
	{
		return disabled;
	}
	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(Boolean disabled)
	{
		this.disabled = disabled;
	}
	
	
	
	
	
}
