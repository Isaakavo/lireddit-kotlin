package com.lireddit.example.database.queries

import com.lireddit.example.entities.Posts
import com.lireddit.example.entities.Users
import com.lireddit.example.graphql.types.PostType
import com.lireddit.example.graphql.types.UserType
import com.lireddit.example.usecases.post.PostsQuery
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Repository
class PostsQueryImpl(val database: Database) : PostsQuery {

    private fun QueryRowSet.toPost(): PostType {
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

        return PostType(
            id = id,
            createdAt = ZonedDateTime.of(createdAt, ZoneId.systemDefault()),
            updatedAt = updatedAt,
            title = title,
            points = points,
            text = text,
            creatorId = creatorId,
            creator = UserType(userId!!, username!!, email!!, null, ZonedDateTime.of(userCreatedAt, ZoneId.systemDefault()), userUpdatedAt)
        )
    }

    override fun findById(id: Int): PostType? {
        return database
            .from(Posts)
            .innerJoin(Users, on = Posts.creatorId eq Users.id)
            .select()
            .where { Posts.id eq id }
            .limit(1)
            .map { it.toPost() }
            .singleOrNull()
    }

    override fun findByLimit(limit: Int, cursor: String?): List<PostType> {

        val query = database
            .from(Posts)
            .innerJoin(Users, on = Posts.creatorId eq Users.id)

        return if (cursor != null && cursor != "default") {
            val formatCursor = LocalDateTime.parse(cursor, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            query
                .select()
                .where { Posts.createdAt less formatCursor }
                .orderBy(Posts.createdAt.desc())
                .limit(limit)
                .map { it.toPost() }
        } else {
            query.select()
                .orderBy(Posts.createdAt.desc())
                .limit(limit)
                .map { it.toPost() }
        }

    }
}