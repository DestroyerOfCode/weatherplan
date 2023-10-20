package com.babkovic.current.mapper

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import org.bson.types.ObjectId
import org.springframework.stereotype.Component
import java.io.IOException


@Component
class ObjectIdDeserializer : JsonDeserializer<ObjectId?>() {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): ObjectId {
        val oid = p.readValueAsTree<JsonNode>()["\$oid"]
        return ObjectId(oid.asText())
    }
}