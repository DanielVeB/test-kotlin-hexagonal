package io.kurosz.driven

import java.util.UUID

interface EmployeeRepository {

    fun save(employee: EmployeeEntity): UUID

    fun getById(id: UUID): EmployeeEntity?

    fun getAll(): List<EmployeeEntity>
}

class EmployeeEntity( val firstname: String, val lastName: String, val age: Int){
    lateinit var id: UUID

}