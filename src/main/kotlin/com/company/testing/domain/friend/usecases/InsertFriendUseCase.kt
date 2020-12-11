package com.company.testing.domain.friend.usecases

import com.company.testing.domain.exceptions.FormatException
import com.company.testing.domain.friend.FriendCreationForm
import com.company.testing.domain.friend.IFriendBoundary

class InsertFriendUseCase(
        private val friendRepository: IFriendBoundary.IFriendRepository
) : IFriendBoundary.IInsertFriend {

    override fun run(data: FriendCreationForm): String {
        if(data.age < 0 || data.age > 140) {
            throw FormatException()
        }
        if(data.name.length < 2 || data.name.length > 20) {
            throw FormatException()
        }
        // TODO Validate phone number
        val userId = friendRepository.insert(data)
        return userId
    }
}