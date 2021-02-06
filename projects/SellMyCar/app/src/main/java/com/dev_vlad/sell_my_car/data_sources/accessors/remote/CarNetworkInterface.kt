package com.dev_vlad.sell_my_car.data_sources.accessors.remote

import com.dev_vlad.sell_my_car.data_sources.accessors.models.Car
import com.dev_vlad.sell_my_car.data_sources.database.CarFireStoreService

interface CarNetworkInterface {

    suspend fun insertOrUpdateCar(carFireStoreService : CarFireStoreService,car: Car)

    suspend fun deleteCar(carFireStoreService : CarFireStoreService, carId: String)

    suspend fun reInsertDeletedCar(carFireStoreService : CarFireStoreService, car: Car)


    suspend fun deleteCacheDeletedCar(carFireStoreService : CarFireStoreService, car: Car)

    suspend fun getDeletedCars( carFireStoreService : CarFireStoreService): List<Car>

    suspend fun deleteAllCars( carFireStoreService : CarFireStoreService)

    suspend fun searchCar( carFireStoreService : CarFireStoreService, car: Car): Car?

    suspend fun getAllCars( carFireStoreService : CarFireStoreService): List<Car>

    //for testing

    suspend fun insertOrUpdateCars( carFireStoreService : CarFireStoreService, cars: List<Car>)

    suspend fun reInsertDeletedCars( carFireStoreService : CarFireStoreService, cars: List<Car>)

}