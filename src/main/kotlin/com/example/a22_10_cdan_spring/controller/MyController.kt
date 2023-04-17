package com.example.a22_10_cdan_spring.controller

import com.example.a22_10_cdan_spring.StudentBean
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MyController {

    //http://localhost:8080/hello
    @GetMapping("/hello")
    fun testHello(model: Model): String {
        println("/hello")

        //Donnée brut
        model.addAttribute("texte", "Bonjour")
        //1 objet
        val student = StudentBean("Bobby", 5)
        model.addAttribute("studentBean", student)

        //List
        val list = arrayListOf(
            StudentBean("Bobby", 5),
            StudentBean("Tobby", 15),
            StudentBean("Gustave", 12),
            )
        model.addAttribute("studentList", list)

        //Nom du fichier HTML que l'on souhaite afficher
        return "welcome"
    }
}