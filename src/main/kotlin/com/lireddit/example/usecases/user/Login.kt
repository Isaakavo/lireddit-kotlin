package com.lireddit.example.usecases.user

import com.lireddit.example.graphql.types.UserErrors
import com.lireddit.example.graphql.types.UserResponse
import com.lireddit.example.graphql.types.UserType
import com.lireddit.example.handlers.graphql.queries.UserQuery
import de.mkammerer.argon2.Argon2
import de.mkammerer.argon2.Argon2Factory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

interface UsersRepository {
    fun loginUser(user: UserType): UserType
}

@Service
class Login(private val userQuery: UserQuery) {
    @Transactional
    fun call(usernameOrEmail: String, password: String): UserResponse {
        val user = userQuery.user(usernameOrEmail)
        if (user == null) {
            val errors = UserErrors(field = "usernameOrEmail", message = "Username doesnt exists")
            return UserResponse(errors, null)
        }

        val argon = Argon2Factory.create()
        val valid = argon.verify(user.password, password)

        if (!valid) {
            val errors = UserErrors(field = "usernameOrEmail", message = "password is not correct")
            return UserResponse(errors, null)
        }

        return UserResponse(null, user)
    }
}