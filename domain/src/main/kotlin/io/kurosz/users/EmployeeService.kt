package io.kurosz.users

import io.kurosz.driver.HireResponse
import io.kurosz.driver.HireUserRequest
import io.kurosz.driver.HireEmployeeUseCase
import org.apache.logging.log4j.LogManager

open class EmployeeService : HireEmployeeUseCase {

    companion object {
        private val logger = LogManager.getLogger(EmployeeService::class.java)
    }

    override fun hire(request: HireUserRequest): HireResponse {

        if (request.age < 60) {
            return HireResponse.Success
        } else {
            return HireResponse.CannotHireError("Employee is too old")
        }

    }
}