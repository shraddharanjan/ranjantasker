package com.shraddha.ranjantasker.repository;

import com.shraddha.ranjantasker.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

}
