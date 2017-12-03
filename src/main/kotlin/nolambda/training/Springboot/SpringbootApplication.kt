package nolambda.training.Springboot

import nolambda.training.Springboot.data.Car
import nolambda.training.Springboot.data.CarRepository
import nolambda.training.Springboot.data.User
import nolambda.training.Springboot.data.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class SpringbootApplication

@Configuration
class AppConfiguration @Autowired constructor(val userRepo: UserRepository,
                                              val carRepository: CarRepository) {
    @Bean
    fun runner() = CommandLineRunner {

        val car = carRepository.save(Car(brand = "Yaris"))
        val yaris = listOf(car)

        userRepo.save(listOf(
                User(username = "Jajang", car = yaris),
                User(username = "Ujang", car = yaris),
                User(username = "Maicih", car = yaris),
                User(username = "Asep", car = yaris),
                User(username = "Gumasep", car = yaris)
        ))
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(SpringbootApplication::class.java, *args)
}

@RestController
class MainController @Autowired constructor(val userRepo: UserRepository) {

    @RequestMapping("/users")
    fun getUsers(): List<User> = userRepo.findAll().toList()

    @RequestMapping("/findUsers")
    fun findUsers(pageable: Pageable): Page<User> = userRepo.findAll(pageable)

    @RequestMapping("/users/{id}")
    fun getUser(@PathVariable("id") id: Long) = userRepo.findOne(id)

    @RequestMapping("/users/{id}", method = arrayOf(RequestMethod.DELETE))
    fun deleteUser(@PathVariable("id") id: Long) =
            userRepo.delete(id).also {
                mapOf(
                        "success" to true,
                        "users" to userRepo.findAll()
                )
            }

    @RequestMapping("/testa")
    fun get(): Any = mapOf("success" to false)
}
