package io.kurosz.employee

import io.kurosz.driver.*
import org.apache.logging.log4j.LogManager.getLogger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/employees")
class EmployeeController(private val hireEmployeeUseCase: HireEmployeeUseCase, private val getHiredUsers: GetHiredUsers) {

    companion object {
        private val logger = getLogger(EmployeeController::class.java)
    }

    @PostMapping
    fun hireUser(@RequestBody hireRequest: HireUserRequest): ResponseEntity<HireResponse> {
        return when (val response = hireEmployeeUseCase.hire(hireRequest)) {
            is HireResponse.Hired -> ResponseEntity(response, HttpStatus.CREATED)
            is HireResponse.CannotHireError -> ResponseEntity(response, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun getUsers(): List<EmployeeDto> {
        return getHiredUsers.getAll()
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: String): ResponseEntity<EmployeeDto> {
        getHiredUsers.getById(id)?.let {
            return ResponseEntity(it, HttpStatus.OK)
        } ?: run {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

    }

}