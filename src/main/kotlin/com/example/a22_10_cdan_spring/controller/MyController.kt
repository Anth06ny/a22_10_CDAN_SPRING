package com.example.a22_10_cdan_spring.controller

import com.example.a22_10_cdan_spring.StudentBean
import com.example.a22_10_cdan_spring.model.StudentRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class MyController {


    /* -------------------------------- */
    // TP Formulaire
    /* -------------------------------- */
    //http://localhost:8080/form
    @GetMapping("/form")
    fun showForm(studentBean: StudentBean): String {

        studentBean.name = "toto"
        studentBean.note = 5

        //Lance studentForm.html
        return "studentForm";
    }

    //Méthode de récéption du formulaire
    @PostMapping("/formSubmit")
    fun formResponse(studentBean: StudentBean): String {
        println("L'élève créé : " + studentBean.name + " " + studentBean.note)

        //Lance studentFormResult.html
        return "studentFormResult";
    }


    /* -------------------------------- */
    // 1ere Partie
    /* -------------------------------- */


    //http://localhost:8080/hello
    @GetMapping("/hello")
    fun hello(model: Model): String {
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

    //http://localhost:8080/add?name=bobby&note=14
    @GetMapping("/add")
    fun add(name: String, note: Int, model: Model): String {
        println("/add name=$name note=$note")

        val student = StudentBean(name, note)
        StudentRepository.save(student)

        //Donnée brut
        model.addAttribute("texte", "Ajout de")
        //1 objet
        model.addAttribute("studentBean", student)

        //List
        model.addAttribute("studentList", StudentRepository.load())

        //Nom du fichier HTML que l'on souhaite afficher
        return "welcome"
    }

    //http://localhost:8080/filter?name=toto
    @GetMapping("/filter")
    fun filter(name: String?, note: Int?, model: Model): String {
        println("/filter name=$name note=$note")

        val list = StudentRepository.load().filter {
            (name == null || it.name == name)
                    && (note == null || it.note == note)
        }

        //Donnée brut
        model.addAttribute("texte", "Recherche : name=$name note=$note")

        //List
        model.addAttribute("studentList", list)

        //Nom du fichier HTML que l'on souhaite afficher
        return "welcome"
    }
}