package com.sportsday.rest.webservices.restfulwebservices.Repository;

import com.sportsday.rest.webservices.restfulwebservices.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
