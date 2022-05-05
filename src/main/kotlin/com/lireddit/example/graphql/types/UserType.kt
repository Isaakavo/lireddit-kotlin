package com.lireddit.example.graphql.types

import com.expediagroup.graphql.generator.annotations.GraphQLName
import java.time.ZonedDateTime

@GraphQLName("User")
data class UserType(
    val id: Int?,
    val username: String,
    val email: String,
    val createdAt: ZonedDateTime?,
    val updatedAt: String?
) {
    constructor(user: UserType) : this(
        user.id,
        user.username,
        user.email,
        user.createdAt,
        user.updatedAt
    )
}
