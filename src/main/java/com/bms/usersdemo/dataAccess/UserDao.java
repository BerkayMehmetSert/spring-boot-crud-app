package com.bms.usersdemo.dataAccess;

import com.bms.usersdemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    
    User findByUserNameIgnoreCase(String userName);
    
}
