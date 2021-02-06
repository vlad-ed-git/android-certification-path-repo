package com.dev_vlad.sell_my_car.data_sources.remote

import com.dev_vlad.sell_my_car.data_sources.models.Car

interface CarNetworkInterface {

    suspend fun insertOrUpdateCar(car: Car)

    suspend fun deleteCar(carId: String)

    suspend fun reInsertDeletedCar(car: Car)


    suspend fun deleteCacheDeletedCar(car: Car)

    suspend fun getDeletedCars(): List<Car>

    suspend fun deleteAllCars()

    suspend fun searchCar(car: Car): Car?

    suspend fun getAllCars(): List<Car>

    //for testing

    suspend fun insertOrUpdateCars(cars: List<Car>)

    suspend fun reInsertDeletedCars(cars: List<Car>)

}