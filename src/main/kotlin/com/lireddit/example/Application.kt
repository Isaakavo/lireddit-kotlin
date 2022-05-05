package com.lireddit.example

import com.lireddit.example.graphql.types.CustomSchemaGeneratorHooks
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {
	@Bean
	fun customGraphqlSchemaGeneratorHooks() = CustomSchemaGeneratorHooks()
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
