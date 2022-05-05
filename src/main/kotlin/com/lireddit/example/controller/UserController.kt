package com.lireddit.example.controller

import com.lireddit.example.entities.User
import com.lireddit.example.entities.posts
import com.lireddit.example.entities.users
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.find
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
//    @Autowired
//    lateinit var database: Database
//
//    @GetMapping("/users/get-by-id")
//    fun getUserById(@RequestParam("id") id: Int): User? {
//        return database.users.find { it.id eq id }
//    }
}