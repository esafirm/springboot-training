package nolambda.training.Springboot.data

import javax.persistence.*

@Entity
data class Car(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val carId: Long? = null,
        val brand: String? = null,
        @ManyToOne @JoinColumn(name = "user_id") val user: User? = null
)