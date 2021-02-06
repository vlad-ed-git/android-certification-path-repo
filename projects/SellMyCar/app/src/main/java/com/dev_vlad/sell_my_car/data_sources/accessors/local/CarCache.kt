package com.dev_vlad.sell_my_car.data_sources.accessors.local

import com.dev_vlad.sell_my_car.data_sources.accessors.models.Car
import com.dev_vlad.sell_my_car.data_sources.database.room.CarDaoService

object CarCache : CarCacheInterface {

    override suspend fun insertCar(carDaoService : CarDaoService, car: Car): Long =
        carDaoService.insertCar(car)


    override suspend fun deleteCar(carDaoService : CarDaoService, car: Car): Int =
        carDaoService.deleteCar(car)


    override suspend fun deleteCars(carDaoService : CarDaoService, carList: List<Car>): Int =
        carDaoService.deleteCars(carList)


    override suspend fun updateCar(carDaoService : CarDaoService, car: Car): Int =
        carDaoService.updateCar(car)


    override suspend fun searchCars(carDaoService : CarDaoService, query: String, filterAndOrder: String, page: Int): List<Car> =
        carDaoService.searchCars(query, filterAndOrder, page)


    override suspend fun searchCarById(carDaoService : CarDaoService, carId: String): Car =
        carDaoService.searchCarById(carId)


    override suspend fun getNumCars( carDaoService : CarDaoService): Int =
        carDaoService.getNumCars()


    override suspend fun insertCars(carDaoService : CarDaoService, carsList: List<Car>): LongArray =
        carDaoService.insertCars(carsList)


}