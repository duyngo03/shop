package org.example.decorshop.security;

import org.example.decorshop.Model.Users;
import org.example.decorshop.Repository.UserRepository;
import org.example.decorshop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userService.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthorities(user.getRole()));
        }
        throw new UsernameNotFoundException("User not found");
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String roles) {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + roles);
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(simpleGrantedAuthority);
        return list;
    }
}
