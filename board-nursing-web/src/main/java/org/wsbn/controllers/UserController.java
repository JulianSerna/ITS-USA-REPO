package org.wsbn.controllers;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.wsbn.business.UserManager;
import org.wsbn.dto.UserDto;

@ManagedBean
@SessionScoped
public class UserController implements Serializable  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -559797764084903143L;
	
	// COLLABORATORS
	private UserManager userManger = new UserManager();
	private UserDto userDto = new UserDto();

	// STATE

	private String searchUser;
	private Collection<UserDto> searchUsersResults;
	private UserDto selectedUser;

	// CONSTRUCTOR(S)
	public UserController() {

	}

	public UserDto getUserDto() {
		return this.userDto;
	}
	public void setUserDto(UserDto userDto)
	{
		this.userDto = userDto;
	}
	
	public UserDto getSelectedUser() {
		if (selectedUser == null) {
			selectedUser = new UserDto();
		}
		return selectedUser;
	}

	public void setSelectedUser(UserDto selectedUser) {
		this.selectedUser = selectedUser;
	}

	public Collection<UserDto> getSearchUsersResults() {
		return searchUsersResults;
	}

	public void setSearchUsersResults(Collection<UserDto> searchUsersResults) {
		this.searchUsersResults = searchUsersResults;
	}

	public String getSearchUser() {
		return searchUser;
	}

	public void setSearchUser(String searchUser) {
		this.searchUser = searchUser;
	}

	public String login() {
		if ("test".equalsIgnoreCase(this.userDto.getName())
				&& "test".equals(this.userDto.getPassword())) {
			System.out.println("PROCESSING LOGIN SUCCESS");

			return "SUCCESS";
		} 
		else {

			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sample info message", "PrimeFaces rocks!"));
			//FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Sample warn message","Watch out for PrimeFaces!"));
			FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error:","Invalid Credentials!"));
			//FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Sample fatal message", "Fatal Error in System"));

			return "FAILED";
		}
	}

	public String searchUser() 
	{
		String username = (this.searchUser == null) ? "" : this.searchUser
				.trim();
		this.searchUsersResults = userManger.searchUsers(username);
		System.out.println(searchUsersResults);
		return "index";
	}

	public String updateUser() {
		userManger.update(this.selectedUser);
		return "home";
	}

	public void onUserSelect(SelectEvent event) {
	}

	public void onUserUnselect(UnselectEvent event) {
	}
}
