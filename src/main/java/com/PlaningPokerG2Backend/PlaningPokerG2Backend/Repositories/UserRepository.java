package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Repositories;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserName(String userName);
}
