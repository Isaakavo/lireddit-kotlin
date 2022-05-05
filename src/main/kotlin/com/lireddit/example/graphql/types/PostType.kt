package com.lireddit.example.graphql.types

import com.expediagroup.graphql.generator.annotations.GraphQLName
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
    val creator: UserType?
) {
    constructor(post: PostType) : this(
        post.id,
        post.createdAt,
        post.updatedAt,
        post.title,
        post.points,
        post.text,
        post.creatorId,
        post.creator
    )
}