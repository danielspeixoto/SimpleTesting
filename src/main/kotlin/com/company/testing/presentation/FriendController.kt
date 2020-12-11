package com.company.testing.presentation

import com.company.testing.domain.exceptions.FormatException
import com.company.testing.domain.friend.FriendCreationForm
import com.company.testing.domain.friend.IFriendBoundary
import com.company.testing.domain.friend.usecases.GetFriendsUseCase
import com.company.testing.domain.friend.usecases.InsertFriendUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/friend")
class FriendController(
        @Autowired val insertFriendUseCase: IFriendBoundary.IInsertFriend,
//        @Autowired val getFriendsUseCase: IFriendBoundary.IGetFriends
) {

    @PostMapping
    fun insertFriend(
            @RequestBody body: HashMap<String, Any>
    ): ResponseEntity<HashMap<String, Any>> {
        // Logging, security checks and other stuff
        try {
            val friend = FriendCreationForm(
                    body["name"] as String? ?: throw FormatException(),
                    body["phone"] as String? ?: throw FormatException(),
                    body["age"] as Int? ?: throw FormatException(),
            )
            val id = insertFriendUseCase.run(friend)
            return ResponseEntity(hashMapOf(
                    "id" to id
            ), HttpStatus.OK)
        } catch (e: FormatException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        } catch (e: Exception) {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping
    fun hello(): String {
        return "world"
    }
}