package com.company.testing.domain.friend

class IFriendBoundary {

    interface IFriendRepository {
        fun id(id: String): Friend
        fun insert(friend: FriendCreationForm): String
        fun friends(): List<Friend>
    }

    interface IMeetNewPeopleService {
        fun suggestions(): List<Friend>
    }

    interface IInsertFriend {
        fun run(data: FriendCreationForm): String
    }

    interface IGetFriendById {
        fun run(id: String): Friend
    }

    interface IGetFriends {
        fun run(openToSuggestions: Boolean): List<Friend>
    }
}