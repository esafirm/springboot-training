package nolambda.training.Springboot.data

import org.springframework.data.annotation.PersistenceConstructor
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "car")
data class Car @PersistenceConstructor constructor(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val carId: Long? = null,
        val brand: String? = null
)