package io.kurosz.employee

import arrow.core.Either
import io.kurosz.driven.EmployeeEntity
import io.kurosz.driver.HireUserRequest
import java.util.*

class Employee private constructor(private val firstname: String, private val lastname: String, private val age: Int) {

    internal lateinit var id: UUID

    companion object {
        fun hire(hireUserRequest: HireUserRequest): Either<EmployeeCannotBeHired, Employee> {
            with(hireUserRequest) {
                if (age > 60) {
                    return Either.Left(EmployeeCannotBeHired("Cannot hire employee older than 60 years"))
                } else if (age < 18) {
                    return Either.Left(EmployeeCannotBeHired("Cannot hire employee younger than 18 years"))

                }
                return Either.Right(Employee(firstname, lastname, age))
            }

        }
    }

    fun toEntity(): EmployeeEntity {
        return EmployeeEntity(firstname, lastname, age)
    }

}

data class EmployeeCannotBeHired(val reason: String)