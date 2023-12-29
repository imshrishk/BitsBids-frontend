package com.fin.oopsproject.Controllers;

import com.fin.oopsproject.Model.UserModel;
import com.fin.oopsproject.Model.UserRepository;
import com.fin.oopsproject.Model.UserRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

        @Autowired
        private final UserRepository userRepository;
        @Autowired
        private final UserRepository2 userRepository2;
    public boolean isUsernameExists(String username) {
        return userRepository2.existsByUsername(username);
    }
    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }
        public UserService(UserRepository userRepository, UserRepository2 userRepository2) {
            this.userRepository = userRepository;
            this.userRepository2 = userRepository2;
        }

    public User addUser(User user) {
        return this.userRepository2.save(user);
    }


    public UserModel getUserById(Long userId) {
            return userRepository.findById(userId).orElse(null);
        }

        public Iterable<UserModel> getAllUsers() {
            return userRepository.findAll();
        }

        public UserModel setCampusId(Long userId, String campusId) {
            UserModel userModel = userRepository.findById(userId).orElse(null);
            assert userModel != null;
            userModel.setCampusID(campusId);
            return userRepository.save(userModel);
        }

        public UserModel setHostel(Long userId, String hostel) {
            UserModel userModel = userRepository.findById(userId).orElse(null);
            assert userModel != null;
            userModel.setHostel(hostel);
            return userRepository.save(userModel);
        }

        public UserModel setPhone(Long userId, String phone) {
            UserModel userModel = userRepository.findById(userId).orElse(null);
            assert userModel != null;
            userModel.setPhone(phone);
            return userRepository.save(userModel);
        }
}
