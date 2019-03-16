package com.jaygame.base.controller;

import JwtUtil.JwtUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jaygame.base.pojo.User;
import com.jaygame.base.service.UserService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public Result findUser(@RequestBody User user){

            user = userService.login(user);
//            System.out.println(user.getUsername() + " " + user.getPassword() + "" + user.getState());
            if (user == null) {
                return new Result(false, StatusCode.ERROR, "username or password error", null);
            }

            Integer usr_privacy_id = user.getUsr_privacy_id();
            String role = "user";
            if(usr_privacy_id.equals(2)){
                role = "admin";
            }
        //create jwt
        String token = jwtUtil.createJWT(user.getUser_id().toString(), user.getUsername(), role);
            Map<String, Object>  map = new HashMap<>();
            map.put("token",token);
            map.put("role", role);
            map.put("state", user.getState());
            map.put("country", user.getCountry());
            return new Result(true, StatusCode.OK, "username and password correct", map);
    }


    @GetMapping(value="/profile")
    public Result userProfile(){

        User user = userService.userProfile();
        return new Result(true, StatusCode.OK, "username and password correct", user);
    }

}
