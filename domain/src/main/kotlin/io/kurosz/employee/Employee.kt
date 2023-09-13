package io.kurosz.employee

import io.kurosz.driven.EmployeeEntity
import io.kurosz.driver.HireUserRequest
import java.util.UUID

class Employee private constructor(private val firstname: String, private val lastname: String, private val age: Int) {

    internal lateinit var id: UUID
    companion object {
        fun hire(hireUserRequest: HireUserRequest): Employee {
            with(hireUserRequest) {
                return Employee(firstname, lastname, age)
            }

        }
    }

    fun toEntity(): EmployeeEntity {
        return EmployeeEntity(firstname, lastname, age)
    }

}