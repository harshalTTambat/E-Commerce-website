package com.example.ECommerce_website.repository;

import com.example.ECommerce_website.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RollRepository extends JpaRepository<Role, Integer> {
}
