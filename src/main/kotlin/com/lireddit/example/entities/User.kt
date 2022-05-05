package com.lireddit.example.entities

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.date
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import java.sql.Date
import java.time.LocalDate

data class User
    (
    val id: Int,
    val username: String,
    val email: String,
    val password: String?,
    //Updoots
    val createdAt: String?,
    val updatedAt: String?
)


object Users : Table<Nothing>("user") {
    val id = int("id").primaryKey()
    val username = varchar("username")
    val email = varchar("email")
    val password = varchar("password")
    val createdAt = varchar("createdAt")
    val updatedAt = varchar("updatedAt")
}

val Database.users get() = this.sequenceOf(Users)