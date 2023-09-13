package io.kurosz

import io.kurosz.driver.EmployeeDto
import io.kurosz.driver.GetHiredUsers
import io.kurosz.driver.HireEmployeeUseCase
import io.kurosz.employee.EmployeeController
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [EmployeeController::class])
class ApplicationKtTest(@Autowired val mockMvc: MockMvc) {


    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun hireEmployeeUseCase() = mockk<HireEmployeeUseCase>()

        @Bean
        fun getHiredUsers() = mockk<GetHiredUsers>()
    }

    @Autowired
    private lateinit var getHiredUsers: GetHiredUsers

    @Test
    fun shouldReturnListOfEmployees() {
        every { getHiredUsers.getAll() } returns listOf(EmployeeDto("1", "", "", 22))

        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"));
    }

    @Test
    fun shouldReturnEmptyListOfEmployees() {
        every { getHiredUsers.getAll() } returns listOf()

        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().string("[]"))
    }

}