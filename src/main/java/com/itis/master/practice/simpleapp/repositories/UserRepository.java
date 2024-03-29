package com.itis.master.practice.simpleapp.repositories;

import com.itis.master.practice.simpleapp.entitites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*****
 * @author Igor Astafyev
 * June 2019
 * "Users" JpaRepository
 *****/

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Modifying
	@Transactional
	@Query(value = "update users u set approved = true where u.validkey = :keyvalue",
			nativeQuery = true)
	void updateUserApprovedByValidkey(@Param("keyvalue") String key);

	@Transactional
	User findByEmail(String email);
}