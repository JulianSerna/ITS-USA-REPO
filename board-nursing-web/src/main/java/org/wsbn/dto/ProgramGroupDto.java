package org.wsbn.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * @author JULIAN
 *
 */
@Entity
@Table(name = "PROGRAM_GROUPS_LK")
public class ProgramGroupDto implements iDto, Serializable
{
		

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue
	@Column(name = "RID")
	private Long				rid;

	@Column(name = "GROUP_NAME")
	private String				groupName;

	
	@Column(name = "PRINT_INDEX")
	private Integer				printIndex			= 0;


	@Column(name = "DISABLED")
	private Boolean				disabled			= false;
	
	
	@Column(name = "NCLEX")
	private Boolean				nclex			= false;
	
	
	// CONSTRUCTOR(S)
	public ProgramGroupDto() 
	{
		
	}
	public ProgramGroupDto(Long pRid) 
	{
		this.rid = pRid;
	}

	// SETTERS/GETTERS
	public Long getRid()
	{
		return rid;
	}
	public String getGroupName()
	{
		return groupName;
	}
	public void setName(String name)
	{
		this.groupName = name;
	}
	public Integer getPrintIndex()
	{
		return printIndex;
	}
	public void setPrintIndex(Integer printIndex)
	{
		this.printIndex = printIndex;
	}

	@Override
	public Boolean getDisabled()
	{
		return this.disabled;
	}

	@Override
	public void setDisabled(Boolean pValue)
	{
		this.disabled = pValue;
		
	}
	
	
	/**
	 * @return the nclex
	 */
	public Boolean getNclex()
	{
		return nclex;
	}

	/**
	 * @param nclex the nclex to set
	 */
	public void setNclex(Boolean nclex)
	{
		this.nclex = nclex;
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
		ProgramGroupDto other = (ProgramGroupDto) obj;
		if (rid == null) {
			if (other.rid != null) return false;
		}
		else
			if (!rid.equals(other.rid)) return false;
		return true;
	}
	
	

}