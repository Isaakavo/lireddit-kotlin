package com.lireddit.example.usecases.post

import com.lireddit.example.entities.Post
import org.springframework.stereotype.Service
import java.time.LocalDate
import kotlin.math.min

interface PostsQuery {
    fun findById(id: Int): Post?
    fun findByLimit(limit: Int): List<Post>
}

@Service
class PostSearcher(private val postsQuery: PostsQuery) {
    fun findOne(id: Int): Post? {
        return postsQuery.findById(id)
    }

    fun allPosts(limit: Int): List<Post>{
        val realLimit = min(50, limit)
        val realLimitPlusOne = realLimit + 1

        return postsQuery.findByLimit(realLimit)
    }
}