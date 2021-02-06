package com.dev_vlad.sell_my_car.data_sources.remote

import com.dev_vlad.sell_my_car.data_sources.models.Car

object CarNetwork : CarNetworkInterface {
    
    override suspend fun insertOrUpdateCar(car: Car) =
        FireStoreService.insertOrUpdateCar(car)
   

    override suspend fun deleteCar(carId: String) =
        FireStoreService.deleteCar(carId)
   

    override suspend fun reInsertDeletedCar(car: Car) =
        FireStoreService.reInsertDeletedCar(car)

    override suspend fun deleteCacheDeletedCar(car: Car) =
        FireStoreService.deleteCacheDeletedCar(car)
   

    override suspend fun getDeletedCars(): List<Car> =
        FireStoreService.getDeletedCars()
   

    override suspend fun deleteAllCars() =
            FireStoreService.deleteAllCars()
   

    override suspend fun searchCar(car: Car): Car? =
        FireStoreService.searchCar(car)
   

    override suspend fun getAllCars(): List<Car> =
        FireStoreService.getAllCars()
   

    //testing
    override suspend fun insertOrUpdateCars(cars: List<Car>) =
        FireStoreService.insertOrUpdateCars(cars)


    override suspend fun reInsertDeletedCars(cars: List<Car>) =
            FireStoreService.reInsertDeletedCars(cars)




}