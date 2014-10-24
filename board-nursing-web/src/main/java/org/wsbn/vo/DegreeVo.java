package org.wsbn.vo;

import java.io.Serializable;

import javax.persistence.*;


public class DegreeVo implements Serializable
{

	private static final long	serialVersionUID	= 1L;

	
	private String				rid;
	private String				name;
	private String			printIndex;
	private String			disabled;

	// CONSTRUCTOR(S)
	public DegreeVo() 
	{

	}
	public DegreeVo(String pRid) 
	{

		this.rid = pRid.toString(); 
		
	}
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	/**
	 * @return the printIndex
	 */
	public String getPrintIndex()
	{
		return printIndex;
	}
	/**
	 * @param printIndex the printIndex to set
	 */
	public void setPrintIndex(String printIndex)
	{
		this.printIndex = printIndex;
	}
	/**
	 * @return the disabled
	 */
	public String getDisabled()
	{
		return disabled;
	}
	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(String disabled)
	{
		this.disabled = disabled;
	}
	/**
	 * @return the rid
	 */
	public String getRid()
	{
		return rid;
	}

	 
	
	
	
	
	
	
	
	
	

}