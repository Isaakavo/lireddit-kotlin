package com.lireddit.example.usecases.user

import com.lireddit.example.graphql.types.UserType
import com.lireddit.example.handlers.graphql.queries.UserQuery
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
    fun call(usernameOrEmail: String, password: String): UserType {
//        userRepository.loginUser(User(1, "isaakavo", "asdad", null, LocalDateTime.now(), "asdasd"))
        val user = userQuery.user(usernameOrEmail)
        return UserType(1, "isaakavo", "asdad", ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()), "asdasd")
    }
}