package com.company.testing.domain.friend.usecases

import com.company.testing.domain.friend.Friend
import com.company.testing.domain.friend.IFriendBoundary

class GetFriendsUseCase(
        private val friendRepository: IFriendBoundary.IFriendRepository,
        private val meetNewPeopleService: IFriendBoundary.IMeetNewPeopleService
) : IFriendBoundary.IGetFriends {

    override fun run(openToSuggestions: Boolean): List<Friend> {
        val friends = friendRepository.friends()
        if(openToSuggestions) {
            val suggestions = meetNewPeopleService.suggestions()
            return friends + suggestions
        }
        return friends
    }
}