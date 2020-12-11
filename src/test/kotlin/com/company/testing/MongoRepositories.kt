package com.company.testing

import com.company.testing.data.MongoFriendRepository
import org.springframework.data.mongodb.repository.MongoRepository


interface FriendRepository : MongoRepository<MongoFriendRepository.MongoFriend, String>
