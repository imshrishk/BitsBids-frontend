package com.example.bitsbids3.security;

import com.example.bitsbids3.userdao.Userdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import com.example.bitsbids3.entity.User;

import java.util.Optional;

public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private Userdao userdao;

    public CustomOAuth2UserService(Userdao userdao) {
        this.userdao = userdao;
    }

    public CustomOAuth2UserService() {

    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        processOAuth2User(oAuth2User);

        return oAuth2User;
    }

    private void processOAuth2User(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");

        // Check if the user's email is from your university domain
        if (!email.endsWith("@hyderabad.bits-pilani.ac.in")) {
            throw new RuntimeException("Access denied. Only students of bits hyd are allowed.");
        }

        Optional<User> userOptional = userdao.findByEmail(email);

        User user = userOptional.orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setUsername(oAuth2User.getAttribute("username"));
            // set other attributes as needed
            return userdao.save(newUser);
        });

        // update user details if needed
    }
}
