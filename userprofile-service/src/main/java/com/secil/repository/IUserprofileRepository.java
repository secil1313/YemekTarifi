package com.secil.repository;

import com.secil.repository.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserprofileRepository extends MongoRepository<UserProfile,String> {
Optional<UserProfile> findOptionalByAuthId(Long authId);

}
