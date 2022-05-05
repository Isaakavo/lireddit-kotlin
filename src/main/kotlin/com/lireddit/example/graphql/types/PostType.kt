package com.lireddit.example.graphql.types

import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.lireddit.example.entities.Post
import com.lireddit.example.entities.User
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

@GraphQLName("Post")
data class PostType(
    val id: Int,
    val createdAt: ZonedDateTime,
    val updatedAt: String,
    val title: String,
    val points: Int,
    val text: String,
    val creatorId: Int,
    val creator: User?
) {
    constructor(post: Post) : this(
        post.id,
        ZonedDateTime.of(post.createdAt, ZoneId.systemDefault()),
        post.updatedAt,
        post.title,
        post.points,
        post.text,
        post.creatorId,
        post.creator
    )
}