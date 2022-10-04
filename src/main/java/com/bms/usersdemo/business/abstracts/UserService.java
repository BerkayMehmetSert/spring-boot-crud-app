package com.bms.usersdemo.business.abstracts;

import com.bms.usersdemo.entities.User;
import com.bms.usersdemo.core.utilities.DataResult;
import com.bms.usersdemo.core.utilities.Result;

import java.util.List;

public interface UserService {
    DataResult<List<User>> getAll();
    
    DataResult<User> getByUserId(Long userId);
    
    DataResult<User> getByUserName(String userName);
    
    Result add(User user);
    
    Result delete(Long userId);
    
    Result update(User user);
}
