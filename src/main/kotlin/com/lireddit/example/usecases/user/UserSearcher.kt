package com.lireddit.example.usecases.user

import com.lireddit.example.graphql.types.UserType
import org.springframework.stereotype.Service

interface UsersQuery{
    fun findByUsernameOrEmail(usernameOrEmail: String): UserType?
}

@Service
class UserSearcher(private val usersQuery: UsersQuery) {
    fun findOne(usernameOrEmail: String): UserType? {
        return usersQuery.findByUsernameOrEmail(usernameOrEmail)
    }
}