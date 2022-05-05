package com.lireddit.example.controller

import com.expediagroup.graphql.generator.execution.OptionalInput
import com.lireddit.example.entities.*
import org.springframework.stereotype.Component
import com.expediagroup.graphql.server.operations.Query
import com.lireddit.example.graphql.types.PostType
import com.lireddit.example.usecases.post.PostSearcher
import java.time.DateTimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class PostQuery(private val postSearcher: PostSearcher) : Query {
    @Suppress("unused")

    fun post(id: Int): PostType? {
        return postSearcher.findOne(id)?.let { PostType(it) }
    }

    fun posts(limit: Int, cursor: String? = "default"): List<PostType> {
        return postSearcher.allPosts(limit, cursor).map { PostType(it) }
    }
}