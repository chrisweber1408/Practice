package com.example.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class MyUserController {

    private final MyUserService myUserService;
    private final PasswordEncoder encoder;

        @PostMapping
        public void postNewUser(@RequestBody MyUser newUser){
            String password = encoder.encode(newUser.getPassword());
            newUser.setPassword(password);
            myUserService.addNewUser(newUser);
        }
        @GetMapping("/{username}")
        public MyUser getUser(@PathVariable String username){
            return myUserService.getByUsername(username);
        }

        @GetMapping("/me")
        public MyUser findMe(Principal principal){
            return myUserService.getByUsername(principal.getName());
        }

}