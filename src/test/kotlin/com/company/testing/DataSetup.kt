package com.company.testing

import com.company.testing.data.MongoFriendRepository
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.stereotype.Component
import org.springframework.test.context.ContextConfiguration

@Component
@ContextConfiguration(classes=[TestConfiguration::class])
class DataSetup {

    @Autowired
    lateinit var friendRepo : FriendRepository

    fun setup() {
        friendRepo.deleteAll()
    }

}


