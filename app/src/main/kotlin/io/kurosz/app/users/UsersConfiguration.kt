package io.kurosz.app.users

import io.kurosz.driver.HireEmployeeUseCase
import io.kurosz.users.EmployeeService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UsersConfiguration {

    @Bean
    fun hireUserUseCase(): HireEmployeeUseCase{
        return EmployeeService()
    }
}