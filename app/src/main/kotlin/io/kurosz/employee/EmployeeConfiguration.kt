package io.kurosz.employee

import io.kurosz.driven.EmployeeRepository
import io.kurosz.employee.EmployeeService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EmployeeConfiguration(private val employeeRepository: EmployeeRepository) {

    @Bean
    fun employeeService(): EmployeeService{
        return EmployeeService(employeeRepository)
    }
}