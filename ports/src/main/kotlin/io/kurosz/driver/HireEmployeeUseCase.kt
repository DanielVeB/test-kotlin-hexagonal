package io.kurosz.driver

interface HireEmployeeUseCase {

    fun hire(request: HireUserRequest): HireResponse
}


data class HireUserRequest(
    val firstname: String,
    val lastname: String,
    val age: Int
)

sealed class HireResponse {

    data class Hired(val id: String) : HireResponse()

    data class CannotHireError(val reason: String) : HireResponse()
}


