package com.lireddit.example.handlers.graphql.mutations

import com.expediagroup.graphql.server.operations.Mutation
import com.lireddit.example.graphql.types.UserType
import org.springframework.stereotype.Component
import com.lireddit.example.usecases.user.Login

data class LoginInput(val userNameOrEmail: String, val password: String)
data class UserErrors(val field: String, val message: String)
data class UserResponse(val errors: UserErrors?, val user: UserType?)
@Component
class LoginMutation(private val loginCreator: Login): Mutation {

    fun loginUser(input: LoginInput): UserResponse {
        loginCreator.call(input.userNameOrEmail, input.password)
        return UserResponse(null, null)
    }
}