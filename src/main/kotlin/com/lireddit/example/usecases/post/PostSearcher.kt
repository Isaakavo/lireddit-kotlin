package com.lireddit.example.usecases.post

import com.lireddit.example.graphql.types.PaginatedPosts
import com.lireddit.example.graphql.types.PostType
import org.springframework.stereotype.Service
import kotlin.math.min

interface PostsQuery {
    fun findById(id: Int): PostType?
    fun findByLimit(limit: Int, cursor: String?): List<PostType>
}

@Service
class PostSearcher(private val postsQuery: PostsQuery) {
    fun findOne(id: Int): PostType? {
        return postsQuery.findById(id)
    }

    fun paginatedPosts(limit: Int, cursor: String?): PaginatedPosts{
        val realLimit = min(50, limit)
        val realLimitPlusOne = realLimit + 1
        val posts =  postsQuery.findByLimit(realLimit, cursor)
        val hasMore = posts.size == realLimitPlusOne

        println("Real limit value: $realLimit")
        println("Posts size: ${posts.size}")

        return PaginatedPosts(posts.toList().slice(0 until realLimit), hasMore)
    }
}