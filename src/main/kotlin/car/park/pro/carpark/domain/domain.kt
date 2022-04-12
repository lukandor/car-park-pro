package car.park.pro.carpark.domain

class ParkingLot(
    val id: ParkingLotId,

)

data class ParkingLotId(
    val value: String
)

data class DomainException(override val message: String?) : RuntimeException(message)
