package org.wsbn.vo;

import java.io.Serializable;
import java.util.List;



/**
 * @author JULIAN
 *
 */
public class ProgramVo implements Serializable
{
	 
	private static final long	serialVersionUID	= 1L;
	// STATE
	
	private Long programRid;
	private String programName;
	private List<SchoolVo> schoolVoList;
	/**
	 * @return the programRid
	 */
	public Long getProgramRid()
	{
		return programRid;
	}
	/**
	 * @param programRid the programRid to set
	 */
	public void setProgramRid(Long programRid)
	{
		this.programRid = programRid;
	}
	/**
	 * @return the programName
	 */
	public String getProgramName()
	{
		return programName;
	}
	/**
	 * @param programName the programName to set
	 */
	public void setProgramName(String programName)
	{
		this.programName = programName;
	}
	/**
	 * @return the schoolVoList
	 */
	public List<SchoolVo> getSchoolVoList()
	{
		return schoolVoList;
	}
	/**
	 * @param schoolVoList the schoolVoList to set
	 */
	public void setSchoolVoList(List<SchoolVo> schoolVoList)
	{
		this.schoolVoList = schoolVoList;
	}
		
	
	
	
	
	
	
 
	
	
	
	
}
