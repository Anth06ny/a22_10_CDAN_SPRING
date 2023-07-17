package com.example.a22_10_cdan_spring.controller

import com.example.a22_10_cdan_spring.UserBean
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
class LoginController {

    //http://localhost:8080/user/login
    @GetMapping("/login")
    fun login(userBean: UserBean, session: HttpSession, model : Model): String {

        try {
            val userBdd = UserService.findBySessionId(session.id)
            if (userBdd != null) {
                //redirection il est déjà logué
                return "redirect:/user/userregister";
            }

            userBean.login = "toto@toto.fr"
        }
        catch(e:java.lang.Exception) {
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
            if (userBean.login.isBlank()) {
                throw Exception("Le login est manquant")
            }

            val userBDD = UserService.findByLogin(userBean.login)
            //Existe déjà
            if (userBDD != null) {
                if (userBDD.password != userBean.password) {
                    throw Exception("Password incorrect")
                }
                //si ca marche on sauvegarde la session
                userBDD.sessionId = session.id
                UserService.save(userBDD)
            }
            else {
                //S'il n'existe pas je le crée
                userBean.sessionId = session.id
                UserService.save(userBean)
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
            val user = UserService.findBySessionId(session.id)

            if (user == null) {
                //L'utilisateur de cette session n'est pas en base
                //je redirige sur le login
                return "redirect:/user/login"
            }
            else {
                //Liste des utilisateurs de la base
                model.addAttribute("userList", UserService.load())
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