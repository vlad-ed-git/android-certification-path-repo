package com.dev_vlad.sell_my_car.data_sources.local

import com.dev_vlad.sell_my_car.data_sources.models.Car
import com.dev_vlad.sell_my_car.database.CarDaoService

interface CarCacheInterface {

    suspend fun insertCar(carDaoService : CarDaoService, car: Car) : Long

    suspend fun deleteCar( carDaoService :  CarDaoService,  car: Car): Int

    suspend fun deleteCars( carDaoService :  CarDaoService,  carList: List<Car>): Int

    suspend fun updateCar( carDaoService :  CarDaoService,  car: Car): Int

    suspend fun searchCars( carDaoService :  CarDaoService,  
        query: String,
        filterAndOrder: String,
        page: Int
    ): List<Car>

    suspend fun searchCarById( carDaoService :  CarDaoService,  
        carId: String
    ): Car

    suspend fun getNumCars( carDaoService :  CarDaoService ): Int

    //just testing
    suspend fun insertCars( carDaoService :  CarDaoService,  carsList: List<Car>): LongArray


}