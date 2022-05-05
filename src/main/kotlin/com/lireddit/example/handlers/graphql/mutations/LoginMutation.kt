package com.lireddit.example.handlers.graphql.mutations

import com.expediagroup.graphql.server.operations.Mutation
import com.lireddit.example.graphql.types.LoginInput
import com.lireddit.example.graphql.types.UserResponse
import com.lireddit.example.graphql.types.UserType
import org.springframework.stereotype.Component
import com.lireddit.example.usecases.user.Login

@Component
class LoginMutation(private val loginCreator: Login): Mutation {

    fun loginUser(input: LoginInput): UserResponse {
        loginCreator.call(input.userNameOrEmail, input.password)
        return UserResponse(null, null)
    }
}