package com.example.a22_10_cdan_spring.model

import com.example.a22_10_cdan_spring.StudentBean

//Object permet d'écrire un Singleton instantiable qu'une seule fois
//Il peut être utiliser pour les classes n'ayant que des éléments dans un companion object
object StudentRepository {

    private val list = ArrayList<StudentBean>()

    //    Si besoin d'un jeu de donnée
        init {
            list.add(StudentBean("toto", 12))
            list.add(StudentBean("toto", 18))
            list.add(StudentBean("tata", 12))
            list.add(StudentBean("tata", 14))
        }

    //Sauvegarde un étudiant
    fun save(student: StudentBean) = list.add(student)

    //Retourne la liste des étudiants
    fun load() =  list
}
