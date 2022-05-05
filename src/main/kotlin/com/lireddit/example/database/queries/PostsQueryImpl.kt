package com.lireddit.example.database.queries

import com.lireddit.example.entities.Post
import com.lireddit.example.entities.Posts
import com.lireddit.example.entities.User
import com.lireddit.example.entities.Users
import com.lireddit.example.usecases.post.PostsQuery
import org.ktorm.database.Database
import org.ktorm.database.asIterable
import org.ktorm.dsl.*
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class PostsQueryImpl(val database: Database) : PostsQuery {

    private fun QueryRowSet.toPost(): Post {
        val id = this[Posts.id]
        val createdAt = this[Posts.createdAt]
        val updatedAt = this[Posts.updatedAt]
        val title = this[Posts.title]
        val points = this[Posts.points]
        val text = this[Posts.text]
        val creatorId = this[Posts.creatorId]
        val userId = this[Users.id]
        val username = this[Users.username]
        val email = this[Users.email]
        val userCreatedAt = this[Users.createdAt]
        val userUpdatedAt = this[Users.updatedAt]

        checkNotNull(id)
        checkNotNull(createdAt)
        checkNotNull(updatedAt)
        checkNotNull(title)
        checkNotNull(points)
        checkNotNull(text)
        checkNotNull(creatorId)

        return Post(
            id = id,
            createdAt = createdAt,
            updatedAt = updatedAt,
            title = title,
            points = points,
            text = text,
            creatorId = creatorId,
            creator = User(userId!!, username!!, email!!, null, userCreatedAt, userUpdatedAt)
        )
    }

    override fun findById(id: Int): Post? {
        return database
            .from(Posts)
            .innerJoin(Users, on = Posts.creatorId eq Users.id)
            .select()
            .where { Posts.id eq id }
            .limit(1)
            .map { it.toPost() }
            .singleOrNull()
    }

    override fun findByLimit(limit: Int): List<Post> {

        val query = database
            .from(Posts)
            .innerJoin(Users, on = Posts.creatorId eq Users.id)
            .select()
            .orderBy(Posts.createdAt.desc())
            .limit(limit)

        return query.map { it.toPost() }

//        return listOf(
//            Post(
//                1,
//                "asda",
//                "asdw",
//                "Hola",
//                23,
//                "asda",
//                23,
//                User(1, "sdas", "asd", "asd", "asd", "asd")
//            )
//        )
    }
}