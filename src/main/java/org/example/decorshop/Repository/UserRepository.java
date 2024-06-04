package org.example.decorshop.Repository;

import org.example.decorshop.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{
    Optional<Users> findByUserName(String user_name);
}
