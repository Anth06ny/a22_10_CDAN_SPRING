package com.example.a22_10_cdan_spring.controller

import com.example.a22_10_cdan_spring.UserBean
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/user")
class LoginController {

    //http://localhost:8080/user/login
    @GetMapping("/login")
    fun login(userBean: UserBean): String {
        userBean.login = "toto@toto.fr"
        userBean.password = "tata"

        //Lance studentForm.html
        return "login/login";
    }

    //Méthode de récéption du formulaire
    @PostMapping("/loginSubmit")
    fun formResponse(userBean: UserBean): String {
        println("User créé : $userBean")

        //redirige sur une autre url
        return "redirect:/user/userregister";
    }

    //http://localhost:8080/user/userregister
    //Affiche la page userregister
    @GetMapping("/userregister")
    fun userregister(): String {

        return "login/userregister";
    }
}