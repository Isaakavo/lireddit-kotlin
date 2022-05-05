package com.lireddit.example.handlers.graphql.queries

import com.expediagroup.graphql.server.operations.Query
import com.lireddit.example.entities.User
import com.lireddit.example.graphql.types.UserType
import com.lireddit.example.usecases.user.UserSearcher
import org.springframework.stereotype.Component

@Component
class UserQuery(private val userSearcher: UserSearcher) : Query {

    fun user(usernameOrEmail: String): User? {
        return userSearcher.findOne(usernameOrEmail)
    }
}