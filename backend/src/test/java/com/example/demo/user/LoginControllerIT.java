package com.example.demo.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void shouldReturnUserNameIfAuthorized(){
        MyUser user1 = new MyUser();
        user1.setUsername("Hans");
        user1.setPassword("123");
        ResponseEntity<Void> postResponse = testRestTemplate.postForEntity("/api/user", user1, Void.class);
        Assertions.assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        LoginData loginData = new LoginData();
        loginData.setUserName("Hans");
        loginData.setPassword("123");
        ResponseEntity<LoginResponse> loginResponseResponseEntity = testRestTemplate.postForEntity("/api/login", loginData, LoginResponse.class);
        Assertions.assertThat(loginResponseResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(loginResponseResponseEntity.getBody().getToken()).isNotBlank();

        var token = loginResponseResponseEntity.getBody().getToken();
        ResponseEntity<MyUser> getResponse = testRestTemplate.exchange("/api/user/Hans",
                                                            HttpMethod.GET,
                                                            new HttpEntity<>(createHeader(token)),
                                                            MyUser.class);

        Assertions.assertThat(getResponse.getBody().getUsername()).isEqualTo("Hans");

        LoginData loginDatafalse = new LoginData();
        loginDatafalse.setUserName("Hans");
        loginDatafalse.setPassword("124");
        ResponseEntity<LoginResponse> falseloginResponseResponseEntity = testRestTemplate.postForEntity("/api/login", loginDatafalse, LoginResponse.class);

        Assertions.assertThat(falseloginResponseResponseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

        var falsetoken = "falsetoken";
        ResponseEntity<MyUser> getFalseResponse = testRestTemplate.exchange("/api/user/Hans",
                HttpMethod.GET,
                new HttpEntity<>(createHeader(falsetoken)),
                MyUser.class);

        Assertions.assertThat(getFalseResponse.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);


    }

    private HttpHeaders createHeader(String token) {
        String headerValue = "Bearer " + token;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", headerValue);
        return httpHeaders;
    }

}