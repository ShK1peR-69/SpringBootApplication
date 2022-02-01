package com.itis.master.practice.simpleapp.repositories;

import com.itis.master.practice.simpleapp.entitites.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*****
 * @author Igor Astafyev
 * January 2022
 * "Roles" JpaRepository
 *****/

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
