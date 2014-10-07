package org.wsbn.business;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.wsbn.dto.UserDto;

public class UserManager implements Serializable
{
   
	private static final long serialVersionUID = -389360543958974864L;
	private static final Map<Long, UserDto> USERS_TABLE = new HashMap<Long, UserDto>();
    static
    {
        USERS_TABLE.put(1L, new UserDto(1L, "Administrator", "test"));
        USERS_TABLE.put(2L, new UserDto(2L, "Guest", "test"));
        USERS_TABLE.put(3L, new UserDto(3L, "John","test"));
        USERS_TABLE.put(4L, new UserDto(4L, "Paul", "test"));
        USERS_TABLE.put(5L, new UserDto(5L, "raju", "test"));
        USERS_TABLE.put(6L, new UserDto(6L, "raghav", "test"));
        
        
        
    }
    public long create(UserDto user)
    {
        if(user == null)
        {
            throw new RuntimeException("Unable to create User. User object is null.");
        }
        Long rid = this.getMaxUserId();
        user.setRid(rid);
        USERS_TABLE.put(rid, user);
        return rid;
    }

    public void delete(UserDto user)
    {
        if(user == null)
        {
            throw new RuntimeException("Unable to delete User. User object is null.");
        }
        USERS_TABLE.remove(user.getRid());
    }

    public Collection<UserDto> getAllUsers()
    {
        return USERS_TABLE.values();
    }

    public UserDto getUser(Integer userId)
    {
        return USERS_TABLE.get(userId);
    }

    public Collection<UserDto> searchUsers(String username)
    {
        String searchCriteria = (username == null)? "":username.toLowerCase().trim();
        Collection<UserDto> users = USERS_TABLE.values();
        Collection<UserDto> searchResults = new ArrayList<UserDto>();
        for (UserDto user : users)
        {
            if(user.getName() != null && user.getName().toLowerCase().trim().startsWith(searchCriteria))
            {
                searchResults.add(user);
            }
        }
        return searchResults;
    }

    public void update(UserDto user)
    {
        if(user == null || !USERS_TABLE.containsKey(user.getRid()))
        {
            throw new RuntimeException("Unable to update User. User object is null or User Id ["+user.getRid()+"] is invalid." );
        }
        USERS_TABLE.put(user.getRid(), user);
    }
    
    protected long getMaxUserId()
    {
        Set<Long> keys = USERS_TABLE.keySet();
        Long maxId = (long) 1;
        for (Long key : keys)
        {
            if(key > maxId)
            {
                maxId = key;
            }
        }
        return maxId;
    }
}