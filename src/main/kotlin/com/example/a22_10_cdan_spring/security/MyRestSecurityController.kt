package com.example.a22_10_cdan_spring.security

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MyRestSecurityController {

    //http://localhost:8080/testPublic
    @GetMapping("/testPublic")
    fun testPublic(): String {
        println("/testPublic")
        return "Hello public"
    }

    //http://localhost:8080/testPrivate
    @GetMapping("/testPrivate")
    fun testPrivate(): String {
        println("/testPrivate")
        return "Hello private"
    }

    //http://localhost:8080/testPrivateAdmin
    @GetMapping("/testPrivateAdmin")
    fun testPrivateAdmin(): String {
        println("/testPrivateAdmin")
        return "Hello private admin"
    }
}