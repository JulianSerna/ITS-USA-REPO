package org.wsbn.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "PROGRAMS")
public class ProgramDto implements iDto, Serializable
{

	private static final long	serialVersionUID	= 9003298511893551722L;

	@Id
	@GeneratedValue
	@Column(name = "RID")
	private Long				rid;

	@Column(name = "VALUE")
	private String				name;

	@Column(name = "GROUP_RID")
	private Long groupRid;
	
	@Column(name = "DISABLED")
	private Boolean				disabled			= false;

	@Column(name = "PRINT_INDEX")
	private Integer				printIndex			= 0;

	// CONSTRUCTOR(S)
	public ProgramDto(String pValue, boolean pDisabled) {

		this.name = pValue;
		this.disabled = pDisabled;
	}
	public ProgramDto() {

	}

	// SETTERS/GETTERS
	public Boolean getDisabled()
	{
		return disabled;
	}
	public void setDisabled(Boolean pDisabled)
	{
		this.disabled = pDisabled;
	}
	public Long getRid()
	{
		return rid;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	
	
	
	

	/**
	 * @return the groupId
	 */
	public Long getGroupRid()
	{
		return groupRid;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupRid(Long groupRid)
	{
		this.groupRid = groupRid;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((disabled == null) ? 0 : disabled.hashCode());
		result = prime * result + ((rid == null) ? 0 : rid.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ProgramDto other = (ProgramDto) obj;
		if (disabled == null) {
			if (other.disabled != null) return false;
		}
		else
			if (!disabled.equals(other.disabled)) return false;
		if (rid == null) {
			if (other.rid != null) return false;
		}
		else
			if (!rid.equals(other.rid)) return false;
		if (name == null) {
			if (other.name != null) return false;
		}
		else
			if (!name.equals(other.name)) return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProgramDto [rid=" + rid + ", value=" + name + ", disabled=" + disabled + "]";
	}

	/**
	 * @return the printIndex
	 */
	public Integer getPrintIndex()
	{
		return printIndex;
	}

	/**
	 * @param printIndex
	 *            the printIndex to set
	 */
	public void setPrintIndex(Integer printIndex)
	{
		this.printIndex = printIndex;
	}

}