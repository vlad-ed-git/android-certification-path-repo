package com.dev_vlad.sell_my_car.data_sources.local

import com.dev_vlad.sell_my_car.data_sources.models.Car

object CarCache : CarCacheInterface {



    override suspend fun insertCar(car: Car): Long =
        CarDaoService.insertCar(car)


    override suspend fun deleteCar(car: Car): Int =
        CarDaoService.deleteCar(car)


    override suspend fun deleteCars(carList: List<Car>): Int =
        CarDaoService.deleteCars(carList)


    override suspend fun updateCar(car: Car): Int =
        CarDaoService.updateCar(car)


    override suspend fun searchCars(query: String, filterAndOrder: String, page: Int): List<Car> =
        CarDaoService.searchCars(query, filterAndOrder, page)


    override suspend fun searchCarById(carId: String): Car =
        CarDaoService.searchCarById(carId)


    override suspend fun getNumCars(): Int =
        CarDaoService.getNumCars()


    override suspend fun insertCars(carsList: List<Car>): LongArray =
        CarDaoService.insertCars(carsList)


}