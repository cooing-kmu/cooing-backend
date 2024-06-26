package com.alpha.kooing.user.repository

import com.alpha.kooing.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface UserRepository:JpaRepository<User, Long>{
    @Override
    fun findByEmail(email:String):Optional<User>

    @Query(value = "SELECT * FROM user u WHERE u.id != :userId ORDER BY RAND() LIMIT :count", nativeQuery = true)
    fun findRandomUsers(userId: Long, count: Int): List<User>

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.userInterestKeyword LEFT JOIN FETCH u.userConcernKeyword")
    fun findAllWithKeyword():List<User>
}