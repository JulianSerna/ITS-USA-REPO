package org.wsbn.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table( name = "PROGRAMS")
public class ProgramDto implements iDto, Serializable
{
    
	
	private static final long serialVersionUID = 9003298511893551722L;

	@Id
	@GeneratedValue
	@Column( name = "RID")
	private Long rid;
	
	@Column( name = "VALUE")
	private String value;
	
	@Column( name = "DISABLED")
	private Boolean disabled = false;
	
	
	
	
	
	// CONSTRUCTOR(S)
	public ProgramDto(String pValue, boolean pDisabled) {
		
		this.value = pValue;
		this.disabled = pDisabled;
	}

    public ProgramDto()
    {
    	
    }
    
    
    // SETTERS/GETTERS
    public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean pDisabled) 
	{
		this.disabled = pDisabled;
	}
	public Long getRid() {
		return rid;
	}
	public String getName() {
		return value;
	}
	public void setName(String name) {
		this.value = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((disabled == null) ? 0 : disabled.hashCode());
		result = prime * result + ((rid == null) ? 0 : rid.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		if (value == null) {
			if (other.value != null) return false;
		}
		else
			if (!value.equals(other.value)) return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProgramDto [rid=" + rid + ", value=" + value + ", disabled=" + disabled + "]";
	}
	

	

    
    
    
    
}