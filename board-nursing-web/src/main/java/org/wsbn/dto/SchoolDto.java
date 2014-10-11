package org.wsbn.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
	private String[] programRids;
	//private List<SchoolProgramDto> schoolProgramDtoList;
		
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
	/*
	public void setProgramRids(List<Long> pProgramRids) {
		this.programRids = pProgramRids;
	}
	
	public List<Long> getProgramRids()
	{
		return this.programRids;
	}
	*/
	
	/*
	public void setSchoolProgramDtoList(List<SchoolProgramDto> pDtoList) {
		this.schoolProgramDtoList = pDtoList;
	}
	
	public List<SchoolProgramDto> getSchoolProgramDtoList()
	{
		return this.schoolProgramDtoList;
	}
	*/
	
	
	
		
			
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
	
	public String[] getProgramRids()
	{
		return this.programRids;
	}
	public void setProgramRids(String[] pIds)
	{
		this.programRids = pIds;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rid == null) ? 0 : rid.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		SchoolDto other = (SchoolDto) obj;
		if (rid == null) {
			if (other.rid != null) return false;
		}
		else
			if (!rid.equals(other.rid)) return false;
		return true;
	}
	
	
	
	
	
}
