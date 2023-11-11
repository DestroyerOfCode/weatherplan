package com.babkovic.smssender.model.repository

import com.babkovic.smssender.model.domain.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository  : ReactiveMongoRepository<User, ObjectId>