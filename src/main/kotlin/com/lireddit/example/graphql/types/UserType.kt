package com.lireddit.example.graphql.types

import com.lireddit.example.entities.Post
import com.lireddit.example.entities.User
import java.time.LocalDate

data class UserType(val id: Int,
                    val username: String,
                    val email: String,
                    val createdAt: String?,
                    val updatedAt: String?
) {
    constructor(user: User): this(
        user.id,
        user.username,
        user.email,
        user.createdAt,
        user.updatedAt
    )
}
