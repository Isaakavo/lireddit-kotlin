package com.lireddit.example.database.queries

import com.lireddit.example.entities.User
import com.lireddit.example.entities.Users
import com.lireddit.example.graphql.types.UserType
import com.lireddit.example.usecases.user.UsersQuery
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.springframework.stereotype.Repository

@Repository
class UsersQueryImpl(val database: Database) : UsersQuery {

    private fun QueryRowSet.toUser(): User {
        val id = this[Users.id]
        val username = this[Users.username]
        val email = this[Users.email]
        val createdAt = this[Users.createdAt]

//        checkNotNull(id)
        checkNotNull(username)
        checkNotNull(email)
//        checkNotNull(createdAt)

        return User(
            id = id!!,
            username = username,
            email = email,
            null,
            createdAt = createdAt!!,
            updatedAt = null
        )
    }


    override fun findByUsernameOrEmail(usernameOrEmail: String): User? {
        return if (usernameOrEmail.contains("@")) {
            database.from(Users).select()
                .where { Users.email eq usernameOrEmail }
                .limit(1)
                .map { it.toUser() }
                .singleOrNull()
        } else {
            database.from(Users).select()
                .where { Users.username eq usernameOrEmail }
                .limit(1)
                .map { it.toUser() }
                .singleOrNull()
        }
    }
}