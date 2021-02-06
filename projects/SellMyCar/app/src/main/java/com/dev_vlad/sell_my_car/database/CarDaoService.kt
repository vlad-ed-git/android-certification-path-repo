package com.dev_vlad.sell_my_car.database

import com.dev_vlad.sell_my_car.data_sources.models.Car


/* a layer inserted between room and local data source
*** allows us to run unit tests which cannot access the room
* as room is part of android framework
*  so CarDaoService allows us to fake it in a unit test
* while use android's room in actual app
 */
interface CarDaoService {
    fun insertCar(car: Car): Long

    fun deleteCar(car: Car): Int

    fun deleteCars(carList: List<Car>): Int

    fun updateCar(car: Car): Int

    fun searchCars(query: String, filterAndOrder: String, page: Int): List<Car>

    fun searchCarById(carId: String): Car

    fun getNumCars(): Int

    fun insertCars(carsList: List<Car>): LongArray
}