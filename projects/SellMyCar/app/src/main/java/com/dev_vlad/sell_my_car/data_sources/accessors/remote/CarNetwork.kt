package com.dev_vlad.sell_my_car.data_sources.accessors.remote

import com.dev_vlad.sell_my_car.data_sources.accessors.models.Car
import com.dev_vlad.sell_my_car.data_sources.database.CarFireStoreService

object CarNetwork : CarNetworkInterface {
    
    override suspend fun insertOrUpdateCar( carFireStoreService : CarFireStoreService, car: Car) =
        carFireStoreService.insertOrUpdateCar(car)


    override suspend fun deleteCar( carFireStoreService : CarFireStoreService, carId: String) =
        carFireStoreService.deleteCar(carId)


    override suspend fun reInsertDeletedCar( carFireStoreService : CarFireStoreService, car: Car) =
        carFireStoreService.reInsertDeletedCar(car)

    override suspend fun deleteCacheDeletedCar( carFireStoreService : CarFireStoreService, car: Car) =
        carFireStoreService.deleteCacheDeletedCar(car)


    override suspend fun getDeletedCars( carFireStoreService : CarFireStoreService ): List<Car> =
        carFireStoreService.getDeletedCars()


    override suspend fun deleteAllCars( carFireStoreService : CarFireStoreService ) =
            carFireStoreService.deleteAllCars()


    override suspend fun searchCar( carFireStoreService : CarFireStoreService, car: Car): Car? =
        carFireStoreService.searchCar(car)


    override suspend fun getAllCars( carFireStoreService : CarFireStoreService ): List<Car> =
        carFireStoreService.getAllCars()


    //testing
    override suspend fun insertOrUpdateCars( carFireStoreService : CarFireStoreService, cars: List<Car>) =
        carFireStoreService.insertOrUpdateCars( cars)


    override suspend fun reInsertDeletedCars( carFireStoreService : CarFireStoreService, cars: List<Car>) =
            carFireStoreService.reInsertDeletedCars(cars)




}