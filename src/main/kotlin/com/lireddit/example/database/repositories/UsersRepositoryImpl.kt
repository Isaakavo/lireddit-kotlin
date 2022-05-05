package com.lireddit.example.database.repositories

import com.lireddit.example.entities.User
import com.lireddit.example.usecases.user.UsersRepository
import org.ktorm.database.Database
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class UsersRepositoryImpl(val database: Database): UsersRepository {
    override fun loginUser(user: User): User {
        return User(1, "isaakavo", "asdad", null, LocalDateTime.now(), "asdasd")
    }
}