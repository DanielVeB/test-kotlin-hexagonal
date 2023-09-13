package io.kurosz.employee

import io.kurosz.driven.EmployeeRepository
import io.kurosz.driver.*
import org.apache.logging.log4j.LogManager
import java.util.*

open class EmployeeService(private val employeeRepository: EmployeeRepository) : HireEmployeeUseCase, GetHiredUsers {

    companion object {
        private val logger = LogManager.getLogger(EmployeeService::class.java)
    }

    override fun hire(request: HireUserRequest): HireResponse {
        logger.info("Try to hire new employee")
        val employee = Employee.hire(request)


        return employee.fold({ HireResponse.CannotHireError(it.reason) },
            {
                val id = employeeRepository.save(it.toEntity())
                HireResponse.Hired(id.toString())
            })
    }
    override fun getById(id: String): EmployeeDto? {
        logger.info("Get employee with id $id")
        return employeeRepository.getById(UUID.fromString(id))?.let {
            EmployeeDto(it.id.toString(), it.firstname, it.lastName, it.age)
        }
    }

    override fun getAll(): List<EmployeeDto> {
        logger.info("Get all employees")
        return employeeRepository.getAll().map { EmployeeDto(it.id.toString(), it.firstname, it.lastName, it.age) }
    }

}