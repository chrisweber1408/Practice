package com.example.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class MyUserController {

    private final MyUserService myUserService;

        @PostMapping
        public void postNewUser(@RequestBody MyUser newUser){
            myUserService.addNewUser(newUser);
        }
        @GetMapping("/{username}")
        public MyUser getUser(@PathVariable String username){
            return myUserService.getByUsername(username);
        }

}
