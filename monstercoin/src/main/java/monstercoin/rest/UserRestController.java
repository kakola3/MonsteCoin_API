package monstercoin.rest;

import monstercoin.entity.User;
import monstercoin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userapi")
public class UserRestController
{
    // autowire the UserService
    @Autowired
    private UserService userService;

    // add mapping for GET /users
    @CrossOrigin
    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    // add mapping for GET /users/{userLogin}
    @CrossOrigin
    @GetMapping("/usersLogin")
    public User getUser(@RequestParam (value = "login", required = true) String login,
                        @RequestParam (value = "password", required = true) String password){
        System.out.println("UserRestController number 1: " + login + " " + password);
        String tempLogin = login;
        String tempPassword = password;
        User theUser = userService.getUser(login, password);
        System.out.println("UserRestController number 2: " + login + " " + password);

        if(theUser == null){
            throw new CustomerNotFoundException("Customer id not found - " + login);
        }

        return theUser;
    }


    // add mapping for POST /users - add new user
    @CrossOrigin
    @PostMapping("/users")
    public User saveUser(@RequestBody User theUser){
        // also just in case the pass an id in JSON ... set id to 0
        // this is force a save of new item ... instead of update
        theUser.setId(0);

        userService.saveUser(theUser);
        return theUser;
    }

}
