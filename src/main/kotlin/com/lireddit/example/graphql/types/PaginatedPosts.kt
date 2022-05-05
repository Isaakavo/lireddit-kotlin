package com.lireddit.example.graphql.types

data class PaginatedPosts(
    val posts: List<PostType>,
    val hasMore: Boolean
)
