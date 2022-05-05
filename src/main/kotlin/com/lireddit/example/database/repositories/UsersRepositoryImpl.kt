package com.lireddit.example.database.repositories


import com.lireddit.example.graphql.types.UserType
import com.lireddit.example.usecases.user.UsersRepository
import org.ktorm.database.Database
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

@Repository
class UsersRepositoryImpl(val database: Database): UsersRepository {
    override fun loginUser(user: UserType): UserType {
        return UserType(1, "isaakavo", "asdad",  ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()), "asdasd")
    }
}