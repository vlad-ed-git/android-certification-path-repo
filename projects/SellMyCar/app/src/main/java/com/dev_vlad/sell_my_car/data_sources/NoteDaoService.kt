package com.dev_vlad.sell_my_car.data_sources

import com.dev_vlad.sell_my_car.data_sources.models.Car


/* a layer inserted between room and local data source
*** allows us to run unit tests which cannot access the room
* as room is part of android framework
*  so NoteDaoService allows us to fake it in a unit test
* while use android's room in actual app
 */
object NoteDaoService {
    fun insertCar(car: Car): Long {
        TODO("Not yet implemented")
    }

    fun deleteCar(car: Car): Int {
        TODO("Not yet implemented")
    }

    fun deleteCars(carList: List<Car>): Int {
        TODO("Not yet implemented")
    }

    fun updateCar(car: Car): Int {
        TODO("Not yet implemented")
    }

    fun searchCars(query: String, filterAndOrder: String, page: Int): List<Car> {
        TODO("Not yet implemented")
    }

    fun searchCarById(carId: String): Car {
        TODO("Not yet implemented")
    }

    fun getNumCars(): Int {
        TODO("Not yet implemented")
    }

    fun insertCars(carsList: List<Car>): LongArray {
        TODO("Not yet implemented")
    }
}