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
        val id = employeeRepository.save(employee.toEntity())
        return HireResponse.Hired(id.toString())

    }

    override fun getById(id: String): EmployeeDto? {
        return employeeRepository.getById(UUID.fromString(id))?.let {
            EmployeeDto(it.id.toString(), it.firstname, it.lastName, it.age)
        }
    }

    override fun getAll(): List<EmployeeDto> {
        return employeeRepository.getAll().map { EmployeeDto(it.id.toString(), it.firstname, it.lastName, it.age) }
    }

}