package com.babkovic.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import java.util.Collections.singleton


@Configuration
@EnableReactiveMongoRepositories(basePackages = ["com.babkovic"])
class MongoConfig : AbstractReactiveMongoConfiguration() {

    override fun getDatabaseName(): String {
        return "test";
    }

    override fun reactiveMongoClient(): MongoClient {
        val connectionString = ConnectionString("mongodb://localhost:27017/test");
        val mongoClientSettings: MongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        return MongoClients.create(mongoClientSettings);
    }

    override fun getMappingBasePackages(): Collection<String> {
        return singleton("com.babkovic.current.model.repository")
    }

    @Bean
    fun reactiveMongoTemplate(): ReactiveMongoTemplate {
        return ReactiveMongoTemplate(reactiveMongoClient(), databaseName)
    }
}