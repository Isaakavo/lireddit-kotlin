package com.lireddit.example.graphql.types

import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.lireddit.example.entities.Post
import com.lireddit.example.entities.User
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime

@GraphQLName("Usery")
data class UserType(
    val id: Int?,
    val username: String,
    val email: String,
    val createdAt: ZonedDateTime?,
    val updatedAt: String?
) {
    constructor(user: User) : this(
        user.id,
        user.username,
        user.email,
        ZonedDateTime.of(user.createdAt, ZoneId.systemDefault()),
        user.updatedAt
    )
}
