package com.lireddit.example.database.queries

import com.lireddit.example.entities.Users
import com.lireddit.example.graphql.types.UserType
import com.lireddit.example.usecases.user.UsersQuery
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.schema.ColumnDeclaring
import org.springframework.stereotype.Repository
import java.time.ZoneId
import java.time.ZonedDateTime

@Repository
class UsersQueryImpl(val database: Database) : UsersQuery {

    private fun QueryRowSet.toUserType(): UserType {
        val id = this[Users.id]
        val username = this[Users.username]
        val email = this[Users.email]
        val password = this[Users.password]
        val createdAt = this[Users.createdAt]

//        checkNotNull(id)
        checkNotNull(username)
        checkNotNull(email)
//        checkNotNull(createdAt)

        return UserType(
            id = id!!,
            username = username,
            email = email,
            password = password,
            createdAt = ZonedDateTime.of(createdAt!!, ZoneId.systemDefault()),
            updatedAt = null
        )
    }


    override fun findByUsernameOrEmail(usernameOrEmail: String): UserType? {
        return if (usernameOrEmail.contains("@")) {
            databaseQuery(Users.email, usernameOrEmail)
        } else {
            databaseQuery(Users.username, usernameOrEmail)
        }
    }

    private fun databaseQuery(user: ColumnDeclaring<String>, usernameOrEmail: String): UserType? {
        return database
            .from(Users)
            .select()
            .where { user eq usernameOrEmail }
            .limit(1)
            .map { it.toUserType() }
            .singleOrNull()
    }
}