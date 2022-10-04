package com.bms.usersdemo.business.concretes;

import com.bms.usersdemo.entities.User;
import com.bms.usersdemo.business.abstracts.UserService;
import com.bms.usersdemo.core.utilities.DataResult;
import com.bms.usersdemo.core.utilities.Result;
import com.bms.usersdemo.core.utilities.SuccessDataResult;
import com.bms.usersdemo.core.utilities.SuccessResult;
import com.bms.usersdemo.dataAccess.UserDao;
import com.bms.usersdemo.exceptions.AlreadyExistsException;
import com.bms.usersdemo.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManager implements UserService {
    private UserDao userDao;
    
    @Autowired
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }
    
    @Override
    public DataResult<List<User>> getAll() {
        if (this.userDao.findAll().isEmpty()) throw new NotFoundException("No users found");
        return new DataResult<List<User>>(this.userDao.findAll(), true, "Users listed");
    }
    
    @Override
    public DataResult<User> getByUserId(Long userId) {
        Optional<User> user = this.userDao.findById(userId);
        if (user.isEmpty()) throw new NotFoundException("User not found");
        return new DataResult<User>(user.get(), true, "User listed " + userId);
    }
    
    @Override
    public DataResult<User> getByUserName(String userName) {
        if (this.userDao.findByUserNameIgnoreCase(userName) == null) throw new NotFoundException("User not found");
        return new SuccessDataResult<User>(this.userDao.findByUserNameIgnoreCase(userName), "User listed");
    }
    
    @Override
    public Result add(User user) {
        User existUser = this.userDao.findByUserNameIgnoreCase(user.getUserName());
        if (existUser != null) throw new AlreadyExistsException("User already exists");
        this.userDao.save(user);
        return new SuccessResult("User added");
    }
    
    @Override
    public Result delete(Long userId) {
        Optional<User> user = this.userDao.findById(userId);
        if (user.isEmpty()) throw new NotFoundException("User not found");
        this.userDao.delete(user.get());
        return new SuccessResult("User deleted");
    }
    
    @Override
    public Result update(User user) {
        this.userDao.save(user);
        return new SuccessResult("User updated");
    }
}
