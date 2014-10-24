package org.wsbn.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "YEARS_LK")
public class YearDto implements Serializable
{
    
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	@Id
	@Column( name = "YEAR")
	private Integer year;
	
	 
    
    public YearDto()
    {
    	
    }
    
    
    public YearDto(Integer pYear)
    { 
    	this.year = pYear;
    	
    }


	/**
	 * @return the year
	 */
	public Integer getYear()
	{
		return year;
	}


	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year)
	{
		this.year = year;
	}
    
    
     
    
}