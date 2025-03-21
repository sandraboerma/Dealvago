package com.boerma.dealvago.repository;

import com.boerma.dealvago.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
