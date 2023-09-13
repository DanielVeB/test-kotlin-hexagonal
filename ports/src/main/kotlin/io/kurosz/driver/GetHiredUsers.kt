package io.kurosz.driver

interface GetHiredUsers {

    fun getById(id : String): EmployeeDto?

    fun getAll(): List<EmployeeDto>
}

data class EmployeeDto(
    val id: String,
    val firstname: String,
    val lastname: String,
    val age: Int
)

