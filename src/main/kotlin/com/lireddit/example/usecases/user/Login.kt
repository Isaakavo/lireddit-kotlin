package com.lireddit.example.usecases.user

import com.lireddit.example.entities.User
import com.lireddit.example.handlers.graphql.queries.UserQuery
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

interface UsersRepository {
    fun loginUser(user: User): User
}

@Service
class Login(private val userQuery: UserQuery) {
    @Transactional
    fun call(usernameOrEmail: String, password: String): User {
//        userRepository.loginUser(User(1, "isaakavo", "asdad", null, LocalDateTime.now(), "asdasd"))
        val user = userQuery.user(usernameOrEmail)
        return User(1, "isaakavo", "asdad", null, LocalDateTime.now(), "asdasd")
    }
}