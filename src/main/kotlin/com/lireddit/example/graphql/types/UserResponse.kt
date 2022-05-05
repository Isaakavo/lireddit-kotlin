package com.lireddit.example.graphql.types


data class UserResponse(val errors: UserErrors?, val user: UserType?)
