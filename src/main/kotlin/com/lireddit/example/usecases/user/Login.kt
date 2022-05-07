package com.lireddit.example.usecases.user

import com.lireddit.example.graphql.types.UserErrors
import com.lireddit.example.graphql.types.UserResponse
import com.lireddit.example.graphql.types.UserType
import com.lireddit.example.handlers.graphql.queries.UserQuery
import de.mkammerer.argon2.Argon2Factory
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.server.ServerRequest

interface UsersRepository {
    fun loginUser(user: UserType): UserType
}
@Component
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
        val valid = argon.verify(user.password, password.toCharArray())

        if (!valid) {
            val errors = UserErrors(field = "usernameOrEmail", message = "password is not correct")
            return UserResponse(errors, null)
        }

        val pito = spring
        return UserResponse(null, user)
    }
}