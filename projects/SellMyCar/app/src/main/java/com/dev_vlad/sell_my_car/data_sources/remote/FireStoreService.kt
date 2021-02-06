package com.dev_vlad.sell_my_car.data_sources.remote

import com.dev_vlad.sell_my_car.data_sources.models.Car

object FireStoreService {
    fun insertOrUpdateCar(car: Car) {
        TODO("Not yet implemented")


    }

    fun deleteCar(carId: String) {
        TODO("Not yet implemented")
    }

    fun reInsertDeletedCar(car: Car) {
        TODO("Not yet implemented")
    }

    fun deleteCacheDeletedCar(car: Car) {
        TODO("Not yet implemented")
    }

    fun getDeletedCars(): List<Car> {
        TODO("Not yet implemented")
    }

    fun deleteAllCars() {
        TODO("Not yet implemented")
    }

    fun searchCar(car: Car): Car? {
        TODO("Not yet implemented")
    }

    fun getAllCars(): List<Car> {
        TODO("Not yet implemented")
    }

    //testing
    fun insertOrUpdateCars(cars: List<Car>) {
        TODO("Not yet implemented")
    }


    fun reInsertDeletedCars(cars: List<Car>) {
        TODO("Not yet implemented")
    }

}