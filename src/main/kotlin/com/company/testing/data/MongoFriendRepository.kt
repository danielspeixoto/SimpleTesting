package com.company.testing.data

import com.company.testing.domain.friend.Friend
import com.company.testing.domain.friend.FriendCreationForm
import com.company.testing.domain.friend.IFriendBoundary
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.query.Query

class MongoFriendRepository(
        val mongoTemplate: MongoTemplate
) : IFriendBoundary.IFriendRepository {

    companion object {
        private const val COLLECTION_NAME = "friends"
    }

    override fun id(id: String): Friend {
        TODO("Not yet implemented")
    }

    override fun insert(friend: FriendCreationForm): String {
        val firstName = friend.name.split(" ")[0]
        // What happens if a friend has only one name?
        val lastName = friend.name.split(" ")[1]
        val mongoFriend = MongoFriend(
                firstName,
                lastName,
                friend.phone,
                friend.age
        )
        val id = mongoTemplate.save(mongoFriend, COLLECTION_NAME).id.toHexString()
        return id
    }

    override fun friends(): List<Friend> {
        val query = Query()
        val result = mongoTemplate.find(query, MongoFriend::class.java, COLLECTION_NAME)
        val friends = result.map {
            Friend(
                    it.id.toHexString(),
                    "${it.firstName} ${it.lastName}",
                    it.phone,
                    it.age
            )
        }
        return friends
    }

    @Document(collection = COLLECTION_NAME)
    data class MongoFriend(
            val firstName: String,
            val lastName: String,
            // Must be in a xxxxxxxxxxx format (11-digit sequence) Ex.: (11) 9 9999 9999 -> 11999999999
            val phone: String,
            val age: Int,
    ) {
        @Id
        lateinit var id: ObjectId
    }
}