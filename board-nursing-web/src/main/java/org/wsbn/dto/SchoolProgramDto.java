package org.wsbn.dto;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity @IdClass(SchoolProgramKey.class)
@Table(name = "SCHOOLS_PROGRAMS_M2M")
public class SchoolProgramDto implements Serializable
{

	private static final long serialVersionUID = 7078479514590276564L;

	@Id
	@Column(name = "SCHOOL_RID")
	private Long schoolRid;
	@Id
	@Column(name = "PROGRAM_RID")
	private Long programRid;
	
		
		
	// CONSTRUCTOR(S)
	public SchoolProgramDto() 
	{

	}
	// CONSTRUCTOR(S)
		public SchoolProgramDto(Long pSchoolRid, Long pProgramRid) 
		{
			this.schoolRid = pSchoolRid;
			this.programRid = pProgramRid;
		}

	// SETTERS/GETTERS
	public Long getSchoolRid()
	{
		return schoolRid;
	}

	public void setSchoolRid(Long schoolRid)
	{
		this.schoolRid = schoolRid;
	}



	public Long getProgramRid()
	{
		return programRid;
	}



	public void setProgramRid(Long programRid)
	{
		this.programRid = programRid;
	}

	

	
	
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((programRid == null) ? 0 : programRid.hashCode());
		result = prime * result
				+ ((schoolRid == null) ? 0 : schoolRid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		SchoolProgramDto other = (SchoolProgramDto) obj;
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
