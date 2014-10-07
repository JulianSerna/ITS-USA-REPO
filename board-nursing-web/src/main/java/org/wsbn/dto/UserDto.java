package org.wsbn.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue; 
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "USERS")
public class UserDto implements Serializable
{
    
	private static final long serialVersionUID = -2800858903325593419L;
	
	@Id
	@GeneratedValue
	@Column( name = "RID")
	private Long rid;
	
	@Column( name = "NAME")
	private String username;
	
	@Column( name = "PASSWORD")
    private String userPassword;
    
    public UserDto()
    {
    	
    }
    
    
    public UserDto(Long rid, String username, String userPassword)
    {
        this.rid = rid;
    	this.username = username;
        this.userPassword = userPassword;
        
    }
    //setter and getters    
	public Long getRid() {
		return rid;
	}
	public void setRid(Long rid) {
		this.rid = rid;
	}
	public String getName() {
		return username;
	}
	public void setName(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return userPassword;
	}
	public void setPassword(String userPassword) {
		this.userPassword = userPassword;
	}
    
}