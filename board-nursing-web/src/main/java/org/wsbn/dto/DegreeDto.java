package org.wsbn.dto;

import java.io.Serializable;

import javax.persistence.*;

import org.wsbn.vo.DegreeVo;

@Entity
@Table(name = "DEGREES_LK")
public class DegreeDto implements iDto, Serializable
{

	// CONSTANTS
	private static final long	serialVersionUID	= 1L;
	public static final Long NA_DEGREE_RID = 1L;
	
	

	@Id
	@GeneratedValue
	@Column(name = "RID")
	private Long				rid;

	@Column(name = "DEGREE_NAME")
	private String				name;

	
	@Column(name = "PRINT_INDEX")
	private Integer				printIndex;
	
	
	@Column(name = "DISABLED")
	private Boolean				disabled			= false;

	// CONSTRUCTOR(S)
	public DegreeDto() 
	{

	}
	public DegreeDto(Long pRid) 
	{

		this.rid = pRid; 
		
	}

	// SETTERS/GETTERS
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
	 
	
	
	// ================== METHODS ===========================
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((printIndex == null) ? 0 : printIndex.hashCode());
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
		DegreeDto other = (DegreeDto) obj;
		if (name == null) {
			if (other.name != null) return false;
		}
		else
			if (!name.equals(other.name)) return false;
		if (printIndex == null) {
			if (other.printIndex != null) return false;
		}
		else
			if (!printIndex.equals(other.printIndex)) return false;
		if (rid == null) {
			if (other.rid != null) return false;
		}
		else
			if (!rid.equals(other.rid)) return false;
		return true;
	}
	
	// ================= METHODS =========================
	
	public DegreeVo getVo()
	{
		DegreeVo oVo = new DegreeVo(this.rid.toString());
		oVo.setName(this.name.toString());
		oVo.setPrintIndex(this.printIndex.toString());
		oVo.setDisabled(this.disabled.toString());
		
		return oVo;
		
	}
	

}