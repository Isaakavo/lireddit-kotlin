package com.lireddit.example.configuration

import com.expediagroup.graphql.server.spring.execution.SpringGraphQLContext
import com.expediagroup.graphql.server.spring.execution.SpringGraphQLContextFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest


class MyGraphQLContext(val myCustomValue: String, request: ServerRequest): SpringGraphQLContext(request)

@Component
class MyGraphQLContextFactory: SpringGraphQLContextFactory<MyGraphQLContext>() {
    override suspend fun generateContext(request: ServerRequest): MyGraphQLContext {
        val customVal = request.headers().firstHeader("MyHeader") ?: "defaultHeader"
        return MyGraphQLContext(customVal, request)
    }
}