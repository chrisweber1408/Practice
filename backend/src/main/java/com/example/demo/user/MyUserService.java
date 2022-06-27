package com.example.demo.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserService {

    private final MyUserRepo myUserRepo;

    public void addNewUser(MyUser newUser) {
        myUserRepo.save(newUser);
    }

    public MyUser getByUsername(String username) {
        return myUserRepo.findByUsername(username).orElseThrow();
    }
}
