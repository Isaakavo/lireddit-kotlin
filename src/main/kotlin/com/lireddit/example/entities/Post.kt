package com.lireddit.example.entities

import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*
import java.time.LocalDate

data class Post(
    val id: Int,
    val createdAt: String,
    val updatedAt: String,
    val title: String,
    val points: Int,
    val text: String,
    val creatorId: Int,
    val creator: User?
)


object Posts : Table<Nothing>("post") {
    val id = int("id").primaryKey()
    val createdAt = varchar("createdAt")
    val updatedAt = varchar("updatedAt")
    val title = varchar("title")
    val points = int("points")
    val text = varchar("text")
    val creatorId = int("creatorId")
}

val Database.posts get() = this.sequenceOf(Posts)