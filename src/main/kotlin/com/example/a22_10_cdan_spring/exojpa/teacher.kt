package com.example.a22_10_cdan_spring.exojpa

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Entity
@Table(name="teacher")
data class TeacherBean(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,
    var name:String? = null,
    var code: Int = 1
)

@Repository
interface TeacherRepository : JpaRepository<TeacherBean, Long> {
     fun findByNameEquals(name:String) : List<TeacherBean>
}

@Service
class TeacherService(var teacherRepository: TeacherRepository) {

    fun createTeacher(name:String?, code:Int) : TeacherBean {
        if(name.isNullOrBlank()){
            throw Exception("Name missing")
        }
        else if(code !in 1..10){
            throw Exception("Code incorrecte")
        }
        val teacher = TeacherBean(null, name, code)

        teacherRepository.save(teacher)

        return teacher
    }

    fun getAll() = teacherRepository.findAll()
    fun getByName(name:String) = teacherRepository.findByNameEquals(name)

}