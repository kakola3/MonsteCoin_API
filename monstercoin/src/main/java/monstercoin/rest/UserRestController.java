package monstercoin.rest;

import monstercoin.dao.UserDAO;
import monstercoin.entity.User;
import monstercoin.entity.Wallet;
import monstercoin.service.UserService;
import monstercoin.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/userapi")
public class UserRestController
{
    // autowire the UserService
    @Autowired
    private UserService userService;

    @Autowired
    WalletService walletService;

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
    public int saveUser(@Valid @RequestBody User theUser, BindingResult bindingResult){//@RequestBody User theUser
        // also just in case the pass an id in JSON ... set id to 0
        // this is force a save of new item ... instead of update
        if (!bindingResult.hasErrors()){
            if(userService.userExist(theUser) == 0){
                System.out.println("userService.userExist-Line56: " + userService.userExist(theUser));
                theUser.setId(0);
                theUser.setBallance_account(10000);
                userService.saveUser(theUser);
                System.out.println("theUser.getId(): " + theUser.getId());

                Wallet wallet = new Wallet(theUser.getId(), 0, 0, 0, 0, 0);
                walletService.createWalletForNewUser(wallet);

                //return theUser;
                return 0;
            }
            else {
                System.out.println("userService.userExist-Line63: " + userService.userExist(theUser));
                return userService.userExist(theUser);
            }

        }
        else{
            System.out.println(bindingResult.toString());
            if(bindingResult.toString().contains("login")){
                return 4;
            }
            else if (bindingResult.toString().contains("password")){
                return 5;
            }
            else if (bindingResult.toString().contains("email")){
                return 6;
            }
            else return 666; // unknown field
        }
}

}
