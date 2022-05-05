package com.lireddit.example.handlers.graphql.queries

import org.springframework.stereotype.Component
import com.expediagroup.graphql.server.operations.Query
import com.lireddit.example.graphql.types.PaginatedPosts
import com.lireddit.example.graphql.types.PostType
import com.lireddit.example.usecases.post.PostSearcher

@Component
class PostQuery(private val postSearcher: PostSearcher) : Query {
    @Suppress("unused")

    fun post(id: Int): PostType? {
        return postSearcher.findOne(id)
    }

    fun posts(limit: Int, cursor: String? = "default"): PaginatedPosts {
        return postSearcher.paginatedPosts(limit, cursor)
    }
}