package com.example.a22_10_cdan_spring.controller

import com.example.a22_10_cdan_spring.model.UserBean
import com.example.a22_10_cdan_spring.model.UserService
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("/user")
class LoginController(val userService: UserService) {


    //point d'entrée
    //http://localhost:8080/user/login
    @GetMapping("/login")
    fun login(userBean: UserBean, session: HttpSession, model : Model): String {

        try {
            //cas qui marche
            //je cherche en abse par session id
            val userBdd = userService.findBySessionId(session.id)
            if (userBdd != null) {
                //redirection il est déjà logué
                return "redirect:/user/userregister";
            }
        }
        catch(e:java.lang.Exception) {
            //cas qui ne marche pas
            e.printStackTrace()
            model.addAttribute("errorMessage", e.message)
        }

        //Lance studentForm.html
        return "login/login";

    }

    //Méthode de récéption du formulaire
    @PostMapping("/loginSubmit")
    fun formResponse(userBean: UserBean, session: HttpSession, redirectAttributes: RedirectAttributes): String {
        println("User créé : $userBean")

        try {
            //Controle
            if (userBean.login.isNullOrBlank()) {
                throw Exception("Le login est manquant")
            }

            val userBDD = userService.findByLogin(userBean.login)
            //Existe déjà
            if (userBDD != null) {
                if (userBDD.password != userBean.password) {
                    throw Exception("Password incorrect")
                }
                //si ca marche on sauvegarde la session
                userBDD.sessionId = session.id
                userService.save(userBDD)
            }
            else {
                //S'il n'existe pas je le crée
                userBean.sessionId = session.id
                userService.save(userBean)
            }

            //redirige sur une url
            return "redirect:/user/userregister";
        }
        catch (e: Exception) {
            //afficher dans la console
            e.printStackTrace()

            redirectAttributes.addFlashAttribute("errorMessage", e.message)

            return "redirect:/user/login"
        }

        //redirige sur une autre url

    }

    //http://localhost:8080/user/userregister
    //Affiche la page userregister
    @GetMapping("/userregister")
    fun userregister(session: HttpSession, model: Model, redirectAttributes: RedirectAttributes): String {

        try {
            //Je regarde si j'ai un utilisateur en abse avec cette session
            val user = userService.findBySessionId(session.id)

            if (user == null) {
                //L'utilisateur de cette session n'est pas en base
                //je redirige sur le login
                return "redirect:/user/login"
            }
            else {
                //Liste des utilisateurs de la base
                model.addAttribute("userList", userService.load())
                model.addAttribute("userConnected", user)
                return "login/userregister"
            }
        }
        catch (e: Exception) {
            e.printStackTrace()

            redirectAttributes.addFlashAttribute("errorMessage", e.message)

            return "redirect:/user/login"
        }


    }
}