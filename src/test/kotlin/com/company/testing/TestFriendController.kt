package com.company.testing

import com.google.common.truth.Truth
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.client.exchange

class TestFriendController : TestSystem("/friend") {

    @Autowired
    lateinit var friendRepository: FriendRepository

	@Test
	fun submitFriendData_DataIsValid_ShouldBeSaved() {
		val httpEntity = HttpEntity(
				hashMapOf(
						"name" to "Jorge Amado",
						"age" to 50,
						"phone" to "99999999999"
				)
		)

		val response = restTemplate.exchange<HashMap<String, Any>>(
				"$baseUrl",
				HttpMethod.POST,
				httpEntity,
		)

		val body = response.body!!
        val id = body["id"] as String

        val friend = friendRepository.findById(id).get()

        Truth.assertThat(friend.firstName).isEqualTo("Jorge")
	}

}
