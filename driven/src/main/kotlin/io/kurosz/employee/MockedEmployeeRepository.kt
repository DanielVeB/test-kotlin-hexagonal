package io.kurosz.employee

import io.kurosz.driven.EmployeeEntity
import io.kurosz.driven.EmployeeRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class MockedEmployeeRepository : EmployeeRepository {

    private val employees = HashMap<UUID, EmployeeEntity>()
    override fun save(employee: EmployeeEntity): UUID {
        val id = UUID.randomUUID()
        employee.id = id
        employees[id] = employee
        return id
    }

    override fun getById(id: UUID): EmployeeEntity? {
        return employees[id]
    }

    override fun getAll(): List<EmployeeEntity> {
        return employees.values.toList()
    }
}