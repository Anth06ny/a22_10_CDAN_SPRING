package com.example.a22_10_cdan_spring.model

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

//UserBean étant déjà pris pour les premiers exo je l'appelle UserDAO
@Entity
@Table(name="user")
data class UserBean(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,
    var login:String? = null,
    var password:String? = null,
    var sessionId:String? = null,
)

@Repository
interface UserRepository : JpaRepository<UserBean, Long> {
    fun findByLogin(login:String?) : UserBean?
    fun findBySessionId(sessionId: String) : UserBean?
}

@Service
class UserService(var userRepository: UserRepository) {
    //Jeu de donnée
    //    init {
    //        list.add(UserBean("toto", "aaa"));
    //        list.add(UserBean("tata", "bbb"));
    //    }
    //Sauvegarde
    fun save(user: UserBean) = //On regarde s'il n'est pas déjà en base
        userRepository.save(user)


    //Retourne la liste
    fun load() = userRepository.findAll()

    //Retourne l'utilisateur qui a ce login ou null
    fun findByLogin(login: String?)  = userRepository.findByLogin(login)


    //Retourne l'utilisateur qui a cette session ou null
    fun findBySessionId(sessionId: String?): UserBean? {
        if (sessionId == null) {
            return null
        }
        else return userRepository.findBySessionId(sessionId)
    }
}