package org.wsbn.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.wsbn.vo.NclexAnnualDataVo;

@Entity
@Table(name = "NCLEX_ANNUAL_DATA")
public class NclexAnnualDataDto implements Serializable
{

	private static final long	serialVersionUID	= 1L;
	// STATE

	@Id
	@GeneratedValue
	@Column(name = "RID")
	private Long				rid;

	@Column(name = "SCHOOL_RID")
	private Long				schoolRid;

	@Column(name = "PROGRAM_GROUP_RID")
	private Long				programGroupRid;

	
	
	@Column(name = "YEAR")
	private Integer				year;
	
	
	@Column(name = "NCLEX_PASS_PERCENT")
	private Double nclexPassPercent;

	
	
	// CONSTRUCTOR(S)
	public NclexAnnualDataDto() 
	{
		 
	}
	public NclexAnnualDataDto(Long pRid) {
		this.rid = pRid;
		this.nclexPassPercent = (double) 0;
	}

	/**
	 * @return the schoolRid
	 */
	public Long getSchoolRid()
	{
		return schoolRid;
	}

	/**
	 * @param schoolRid
	 *            the schoolRid to set
	 */
	public void setSchoolRid(Long schoolRid)
	{
		this.schoolRid = schoolRid;
	}

	
	/**
	 * @return the year
	 */
	public Integer getYear()
	{
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(Integer year)
	{
		this.year = year;
	}

	


	/**
	 * @return the rid
	 */
	public Long getRid()
	{
		return rid;
	}

	
	
	/**
	 * @return the programGroupRid
	 */
	public Long getProgramGroupRid()
	{
		return programGroupRid;
	}
	/**
	 * @param programGroupRid the programGroupRid to set
	 */
	public void setProgramGroupRid(Long programGroupRid)
	{
		this.programGroupRid = programGroupRid;
	}
	/**
	 * @return the nclexPassPercent
	 */
	public Double getNclexPassPercent()
	{
		return nclexPassPercent;
	}
	/**
	 * @param nclexPassPercent the nclexPassPercent to set
	 */
	public void setNclexPassPercent(Double nclexPassPercent)
	{
		this.nclexPassPercent = nclexPassPercent;
	}
	// ============== METHODS ==================
	public NclexAnnualDataVo getVo()
	{
		NclexAnnualDataVo oResponse = new NclexAnnualDataVo(this.rid.toString());
		
		oResponse.setSchoolRid(this.schoolRid.toString());
		oResponse.setProgramGroupRid(this.programGroupRid.toString());	 
		oResponse.setYear(this.year.toString());
		oResponse.setNclexPassPercent(this.nclexPassPercent.toString());
				 
		
		return oResponse;
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
		NclexAnnualDataDto other = (NclexAnnualDataDto) obj;
		if (rid == null) {
			if (other.rid != null) return false;
		}
		else
			if (!rid.equals(other.rid)) return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	

}
