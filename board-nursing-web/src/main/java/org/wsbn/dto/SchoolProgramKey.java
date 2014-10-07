package org.wsbn.dto;

import java.io.Serializable;

public class SchoolProgramKey implements Serializable 
{
	private static final long	serialVersionUID	= 1L;
	
	private Long schoolRid;
	private Long programRid;
	
	// CONSTRUCTOR
	public SchoolProgramKey()
	{
		
		
	}
	public SchoolProgramKey(Long pSchoolRid, Long pProgramRid)
	{
		this.schoolRid = pSchoolRid;
		this.programRid = pProgramRid;
		
	}
	
	
	/**
	 * @return the schoolId
	 */
	public Long getSchoolId()
	{
		return schoolRid;
	}
	/**
	 * @param schoolId the schoolId to set
	 */
	public void setSchoolId(Long schoolId)
	{
		this.schoolRid = schoolId;
	}
	/**
	 * @return the programId
	 */
	public Long getProgramId()
	{
		return programRid;
	}
	/**
	 * @param programId the programId to set
	 */
	public void setProgramId(Long pProgramRid)
	{
		this.programRid = pProgramRid;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((programRid == null) ? 0 : programRid.hashCode());
		result = prime * result + ((schoolRid == null) ? 0 : schoolRid.hashCode());
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
		SchoolProgramKey other = (SchoolProgramKey) obj;
		if (programRid == null) {
			if (other.programRid != null) return false;
		}
		else
			if (!programRid.equals(other.programRid)) return false;
		if (schoolRid == null) {
			if (other.schoolRid != null) return false;
		}
		else
			if (!schoolRid.equals(other.schoolRid)) return false;
		return true;
	}
	
	
	
	
	
	
	
	
}
