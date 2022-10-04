package com.bms.usersdemo.api.controllers;

import com.bms.usersdemo.entities.User;
import com.bms.usersdemo.business.abstracts.UserService;
import com.bms.usersdemo.core.utilities.DataResult;
import com.bms.usersdemo.core.utilities.ErrorDataResult;
import com.bms.usersdemo.core.utilities.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersController {
    private UserService userService;
    
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public DataResult<List<User>> getAll() {
        return this.userService.getAll();
    }
    
    @GetMapping("/userName")
    public DataResult<User> getByUserName(@RequestParam String userName) {
        return this.userService.getByUserName(userName);
    }
    
    @GetMapping("/{userId}")
    public DataResult<User> getByUserId(@PathVariable Long userId) {
        return this.userService.getByUserId(userId);
    }
    
    @PostMapping
    public Result add(@Valid @RequestBody User user) {
        return this.userService.add(user);
    }
    
    @DeleteMapping
    public Result delete(@RequestBody User user) {
        return this.userService.delete(user.getUserId());
    }
    
    @PutMapping
    public Result update(@Valid @RequestBody User user) {
        return this.userService.update(user);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException
      (MethodArgumentNotValidException exceptions){
        Map<String,String> validationErrors = new HashMap<String, String>();
        for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        
        ErrorDataResult<Object> errors
          = new ErrorDataResult<Object>(validationErrors,"Validation errors");
        return errors;
    }
}
