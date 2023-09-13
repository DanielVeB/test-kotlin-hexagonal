package io.kurosz.app.controllers

import io.kurosz.driver.HireEmployeeUseCase
import io.kurosz.driver.HireResponse
import io.kurosz.driver.HireUserRequest
import org.apache.logging.log4j.LogManager.getLogger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val hireEmployeeUseCase: HireEmployeeUseCase) {

    companion object {
        private val logger = getLogger(UserController::class.java)
    }

    @PostMapping
    fun hireUser(@RequestBody hireRequest: HireUserRequest) : ResponseEntity<HireResponse>{
        return when (val response =  hireEmployeeUseCase.hire(hireRequest)){
            is HireResponse.Success -> ResponseEntity(response, HttpStatus.CREATED)
            is HireResponse.CannotHireError -> ResponseEntity(response, HttpStatus.BAD_REQUEST)
        }

    }


}