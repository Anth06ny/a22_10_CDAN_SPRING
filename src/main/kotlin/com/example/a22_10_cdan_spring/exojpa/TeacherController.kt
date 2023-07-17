package com.example.a22_10_cdan_spring.exojpa

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("teacher")
class TeacherController(val teacherService: TeacherService) {

    //http://localhost:8080/teacher/add?name=totoasas&code=7
    @GetMapping("/add")
    fun add(name : String?, code : Int): List<TeacherBean> {
         println("/add name=$name code=$code")

        teacherService.createTeacher(name, code)

        return teacherService.getAll()
    }

    //http://localhost:8080/teacher/getByName?name=toto
    @GetMapping("/getByName")
    fun getByName(name : String): List<TeacherBean> {
        println("/getByName name=$name")

        return teacherService.getByName(name)
    }
}