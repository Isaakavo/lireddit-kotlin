package com.lireddit.example.graphql.types

import com.expediagroup.graphql.generator.hooks.SchemaGeneratorHooks
import graphql.scalars.ExtendedScalars
import graphql.schema.GraphQLType
import java.time.LocalDateTime
import java.time.ZonedDateTime
import kotlin.reflect.KClass
import kotlin.reflect.KType

class CustomSchemaGeneratorHooks:SchemaGeneratorHooks {

    override fun willGenerateGraphQLType(type: KType): GraphQLType? = when(type.classifier as? KClass<*>) {
        ZonedDateTime::class -> ExtendedScalars.DateTime
        LocalDateTime::class -> ExtendedScalars.Date
        else -> null
    }
}