package com.example.a22_10_cdan_spring

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MyRestController {

    //http://localhost:8080/receiveStudent
    @PostMapping("/receiveStudent")
    fun receiveStudent(@RequestBody studentBean: StudentBean) {
        println("/receiveStudent : $studentBean")
    }

    //http://localhost:8080/increment
    @PostMapping("/increment")
    fun increment(@RequestBody studentBean: StudentBean): StudentBean {
        println("/increment : $studentBean")

         studentBean.note++
        return studentBean
    }


    //http://localhost:8080/max?p1=0&p2=3
    @GetMapping("/max")
    fun max(p1 : Int?, p2 : Int?): Int? {
        //p1 contiendra bob
        //note contiendra 12
        println("/max : p1=$p1 p2=$p2")

        if(p2 == null) {
            return p1
        }
        else if(p1 == null) {
            return p2
        }

        return Math.max(p1,p2)
    }


    //http://localhost:8080/createStudent?nom=bob&note=12
    @GetMapping("/createStudent")
    fun createStudent(@RequestParam(value = "nom", defaultValue = "toto") p1: String,
                      @RequestParam( defaultValue = "5")
                      note: Int): StudentBean? {
        //p1 contiendra bob
        //note contiendra 12
        println("/createStudent : p1=$p1 note=$note")

        return StudentBean(p1, note)
    }

    //http://localhost:8080/test
    @GetMapping("/test")
    fun testMethode(): String {
        println("/test")

        return "helloWorld"
    }



}