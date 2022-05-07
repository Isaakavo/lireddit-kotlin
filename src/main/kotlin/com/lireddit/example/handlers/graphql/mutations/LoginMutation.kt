package com.lireddit.example.handlers.graphql.mutations

import com.expediagroup.graphql.server.operations.Mutation
import com.lireddit.example.graphql.types.LoginInput
import com.lireddit.example.graphql.types.UserResponse
import org.springframework.stereotype.Component
import com.lireddit.example.usecases.user.Login
import graphql.schema.DataFetchingEnvironment
import org.springframework.session.web.http.HttpSessionIdResolver
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.util.HttpSessionMutexListener

@Component
class LoginMutation(private val loginMutation: Login): Mutation {

    fun login(input: LoginInput): UserResponse {
        return loginMutation.call(input.userNameOrEmail, input.password)
    }
}