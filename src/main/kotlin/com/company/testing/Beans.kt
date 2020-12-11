package com.company.testing

import com.company.testing.data.MongoFriendRepository
import com.company.testing.domain.friend.IFriendBoundary
import com.company.testing.domain.friend.usecases.InsertFriendUseCase
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClientFactory
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
@ServletComponentScan
class Beans {

    @Value("\${mongo.uri}")
    lateinit var mongoURI: String

    @Value("\${mongo.dbName}")
    lateinit var mongoDBName: String

    fun mongoClient(): MongoClient {
        return MongoClients.create(mongoURI)
    }

    fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(mongoClient(), mongoDBName)
    }

    @Bean
    fun insertFriendUseCase(): IFriendBoundary.IInsertFriend {
        return InsertFriendUseCase(mongoFriendRepository())
    }

    @Bean
    fun mongoFriendRepository(): IFriendBoundary.IFriendRepository {
        return MongoFriendRepository(
                mongoTemplate()
        )
    }
}