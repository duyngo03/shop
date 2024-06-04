package org.example.decorshop.Service;

import org.example.decorshop.Model.Users;
import org.example.decorshop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public Optional<Users> findByUserName(String user_name) {
        return userRepository.findByUserName(user_name);
    }
}
