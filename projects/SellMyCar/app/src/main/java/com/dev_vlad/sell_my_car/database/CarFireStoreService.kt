package com.dev_vlad.sell_my_car.database

import com.dev_vlad.sell_my_car.data_sources.models.Car

interface CarFireStoreService {
    fun insertOrUpdateCar(car: Car) 


    fun deleteCar(carId: String) 


    fun reInsertDeletedCar(car: Car) 


    fun deleteCacheDeletedCar(car: Car) 


    fun getDeletedCars(): List<Car> 


    fun deleteAllCars() 


    fun searchCar(car: Car): Car? 


    fun getAllCars(): List<Car> 


    //testing
    fun insertOrUpdateCars(cars: List<Car>) 



    fun reInsertDeletedCars(cars: List<Car>) 


}