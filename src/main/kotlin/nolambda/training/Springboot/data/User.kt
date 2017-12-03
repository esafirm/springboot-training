package nolambda.training.Springboot.data

import javax.persistence.*

@Entity
data class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val userId: Long? = null,
        val username: String? = null,
        @OneToMany(mappedBy = "car_id")
        val car: List<Car>? = null
)