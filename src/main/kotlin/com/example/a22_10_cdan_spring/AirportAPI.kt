package com.example.a22_10_cdan_spring

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/airport")
class AirportAPI {

    var tab = Array<PlaneBean?>(5) { null }

    //Méthode qui permet de réinitialiser les données entre 2 tests
    //http://localhost:8080/airport/reset
    @GetMapping("/reset")
    fun reset() {
        tab = Array(5) { null }
    }

    //http://localhost:8080/airport/nextplace
    @GetMapping("/nextplace")
    fun nextplace(): Int {
        println("/nextplace")

        return tab.indexOf(null)
    }

    //http://localhost:8080/airport/park?position=1
    //json = { "name=:"toto", "id":"5" }
    @PostMapping("/park")
    fun park(@RequestBody plane: PlaneBean, position: Int?): Int {
        println("/park plane=$plane")

        if (plane.name.isNullOrBlank() || plane.id.isNullOrBlank()) {
            return 214
        }
        else if (position == null || position < 0 || position >= tab.size) {
            return 216
        }
        else if (tab[position] != null) {
            return 215
        }

        tab[position] = plane
        return 200
    }

    //http://localhost:8080/airport/takeoff ?position=1
    //json = { "name=:"toto", "id":"5" }
    @GetMapping("/takeoff")
    fun takeoff (position: Int?): Int {
        println("/takeoff  position=$position")

        if (position == null || position < 0 || position >= tab.size) {
            return 216
        }
        else if (tab[position] == null) {
            return 215
        }

        tab[position] = null
        return 200
    }

    //http://localhost:8080/airport/state
    @GetMapping("/state")
    fun state(): Array<PlaneBean?> {
        println("/state")

        return tab
    }

}