package org.wsbn.dto.reports;

import java.io.Serializable;

import org.wsbn.vo.reports.AnnualDataVo;

 
public class SchoolAnnualDataReport implements Serializable
{

	private static final long	serialVersionUID	= 1L;
	
	public enum eData {ADMISSIONS, GRADUATIONS, GRADUATE, NCLEX};
	
	// STATE
 
	private Long				schoolRid;
	private String schoolName;
	 
	private Long				programRid;
	
	private Long degreeRid;
	
	 
	private Integer	Y2008;
	
	 
	private Integer	Y2009;
	
	 
	private Integer	Y2010;
	
	 
	private Integer	Y2011;
	
	 
	private Integer	Y2012;
	
	 
	private Integer	Y2013;
	
	 
	private Integer	Y2014;
	
	 
	private Integer	Y2015;
		
	
	// CONSTRUCTOR(S)
	public SchoolAnnualDataReport() 
	{
		
	}


	/**
	 * @return the schoolRid
	 */
	public Long getSchoolRid()
	{
		return schoolRid;
	}


	/**
	 * @param schoolRid the schoolRid to set
	 */
	public void setSchoolRid(Long schoolRid)
	{
		this.schoolRid = schoolRid;
	}

	
	/**
	 * @return the schoolName
	 */
	public String getSchoolName()
	{
		return schoolName;
	}


	/**
	 * @param schoolName the schoolName to set
	 */
	public void setSchoolName(String schoolName)
	{
		this.schoolName = schoolName;
	}


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
	 * @return the degreeRid
	 */
	public Long getDegreeRid()
	{
		return degreeRid;
	}


	/**
	 * @param degreeRid the degreeRid to set
	 */
	public void setDegreeRid(Long degreeRid)
	{
		this.degreeRid = degreeRid;
	}


	/**
	 * @return the y2008
	 */
	public Integer getY2008()
	{
		return Y2008;
	}


	/**
	 * @param y2008 the y2008 to set
	 */
	public void setY2008(Integer y2008)
	{
		Y2008 = y2008;
	}


	/**
	 * @return the y2009
	 */
	public Integer getY2009()
	{
		return Y2009;
	}


	/**
	 * @param y2009 the y2009 to set
	 */
	public void setY2009(Integer y2009)
	{
		Y2009 = y2009;
	}


	/**
	 * @return the y2010
	 */
	public Integer getY2010()
	{
		return Y2010;
	}


	/**
	 * @param y2010 the y2010 to set
	 */
	public void setY2010(Integer y2010)
	{
		Y2010 = y2010;
	}


	/**
	 * @return the y2011
	 */
	public Integer getY2011()
	{
		return Y2011;
	}


	/**
	 * @param y2011 the y2011 to set
	 */
	public void setY2011(Integer y2011)
	{
		Y2011 = y2011;
	}


	/**
	 * @return the y2012
	 */
	public Integer getY2012()
	{
		return Y2012;
	}


	/**
	 * @param y2012 the y2012 to set
	 */
	public void setY2012(Integer y2012)
	{
		Y2012 = y2012;
	}


	/**
	 * @return the y2013
	 */
	public Integer getY2013()
	{
		return Y2013;
	}


	/**
	 * @param y2013 the y2013 to set
	 */
	public void setY2013(Integer y2013)
	{
		Y2013 = y2013;
	}


	/**
	 * @return the y2014
	 */
	public Integer getY2014()
	{
		return Y2014;
	}


	/**
	 * @param y2014 the y2014 to set
	 */
	public void setY2014(Integer y2014)
	{
		Y2014 = y2014;
	}


	/**
	 * @return the y2015
	 */
	public Integer getY2015()
	{
		return Y2015;
	}


	/**
	 * @param y2015 the y2015 to set
	 */
	public void setY2015(Integer y2015)
	{
		Y2015 = y2015;
	}
	
	
	// ============================ METHODS =====================================
	public AnnualDataVo getVo()
	{
		AnnualDataVo oVo = new AnnualDataVo();
		oVo.setSchoolRid(this.schoolRid.toString());
		oVo.setProgramRid(this.programRid.toString());
		oVo.setDegreeRid(this.degreeRid.toString());
		oVo.setY2008( (this.Y2008 != null) ? this.Y2008.toString() : "0");
		oVo.setY2009( (this.Y2009 != null) ? this.Y2009.toString() : "0");
		oVo.setY2010( (this.Y2010 != null) ? this.Y2010.toString() : "0");
		oVo.setY2011( (this.Y2011 != null) ? this.Y2011.toString() : "0");
		oVo.setY2012( (this.Y2012 != null) ? this.Y2012.toString() : "0");
		oVo.setY2013( (this.Y2013 != null) ? this.Y2013.toString() : "0");
		oVo.setY2014( (this.Y2014 != null) ? this.Y2014.toString() : "0");
		oVo.setY2015( (this.Y2015 != null) ? this.Y2015.toString() : "0");
		
		return oVo;
		
		
		
	}

	
	

}
