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

    fun posts(limit: Int): List<PostType> {


        return postSearcher.allPosts(limit).map { PostType(it) }
    }

//    fun posts(): Post = Post()

//    @GetMapping("/posts/get-by-id")
//    fun postById(id: Int): Post? = getPostById(id)

//    fun getPostById(id: Int): Post? {
//        val post = database.posts.find { it.id eq id }
//        println(post)
//        return database.posts.find { it.id eq id }
//    }

//    @GetMapping("/posts/get-all")
//    fun getAllPosts(): List<Post> {
//        return database.posts.toList()
//    }

//    @GetMapping("/posts/get-by-user")
//    fun getByUser(userId: Int): List<PostData> {
//        val query = database.from(Posts).innerJoin(Users, on = Posts.creatorId eq Users.id).select()
//            .orderBy(Posts.createdAt.desc()).limit(15).map {
//                row -> PostData(row[Posts.title], row[Posts.text], row[Posts.points])
//            }
//
//        return query
//    }
}