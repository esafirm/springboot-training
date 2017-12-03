package nolambda.training.Springboot.data

import org.springframework.data.jpa.repository.JpaRepository

interface CarRepository : JpaRepository<Car, Long>