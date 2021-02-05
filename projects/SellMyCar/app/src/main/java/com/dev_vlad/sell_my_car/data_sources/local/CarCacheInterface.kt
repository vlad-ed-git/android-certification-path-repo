package com.dev_vlad.sell_my_car.data_sources.local

import com.dev_vlad.sell_my_car.data_sources.models.Car

interface CarCacheInterface {

    suspend fun insertCar(car: Car) : Long

    suspend fun deleteCar(car: Car): Int

    suspend fun deleteCars(carList: List<Car>): Int

    suspend fun updateCar(car: Car): Int

    suspend fun searchCars(
        query: String,
        filterAndOrder: String,
        page: Int
    ): List<Car>

    suspend fun searchCarById(
        carId: String
    ): Car

    suspend fun getNumCars(): Int

    //just testing
    suspend fun insertCars(carsList: List<Car>): LongArray


}