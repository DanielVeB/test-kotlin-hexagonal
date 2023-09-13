package io.kurosz

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["io.kurosz.employee"])

class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}