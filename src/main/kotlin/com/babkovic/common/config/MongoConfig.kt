//package com.babkovic.common.config
//
//import com.mongodb.reactivestreams.client.MongoClient
//import com.mongodb.reactivestreams.client.MongoClients
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
//import org.springframework.data.mongodb.core.ReactiveMongoTemplate
//import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
//
//@Configuration
//@EnableReactiveMongoRepositories(basePackages = ["com.babkovic"])
//class MongoConfig : AbstractReactiveMongoConfiguration() {
//
//    @Value("\${port}")
//    private val port: String? = null
//
//    @Value("\${dbname}")
//    private val dbName: String? = null
//    override fun reactiveMongoClient(): MongoClient {
//        return MongoClients.create()
//    }
//
//    override fun getDatabaseName(): String {
//        return dbName!!
//    }
//
//    @Bean
//    fun reactiveMongoTemplate(): ReactiveMongoTemplate {
//        return ReactiveMongoTemplate(reactiveMongoClient(), databaseName)
//    }
//}
//
//
