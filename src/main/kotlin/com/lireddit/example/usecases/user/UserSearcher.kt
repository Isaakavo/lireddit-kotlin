package com.lireddit.example.usecases.user

import com.lireddit.example.entities.User
import com.lireddit.example.graphql.types.UserType
import org.springframework.stereotype.Service

interface UsersQuery{
    fun findByUsernameOrEmail(usernameOrEmail: String): User?
}

@Service
class UserSearcher(private val usersQuery: UsersQuery) {
    fun findOne(usernameOrEmail: String): User? {
        return usersQuery.findByUsernameOrEmail(usernameOrEmail)
    }
}