package com.dev_vlad.sell_my_car.data_sources.local

import com.dev_vlad.sell_my_car.data_sources.NoteDaoService
import com.dev_vlad.sell_my_car.data_sources.models.Car

object CarCache : CarCacheInterface {



    override suspend fun insertCar(car: Car): Long =
        NoteDaoService.insertCar(car)


    override suspend fun deleteCar(car: Car): Int =
        NoteDaoService.deleteCar(car)


    override suspend fun deleteCars(carList: List<Car>): Int =
        NoteDaoService.deleteCars(carList)


    override suspend fun updateCar(car: Car): Int =
        NoteDaoService.updateCar(car)


    override suspend fun searchCars(query: String, filterAndOrder: String, page: Int): List<Car> =
        NoteDaoService.searchCars(query, filterAndOrder, page)


    override suspend fun searchCarById(carId: String): Car =
        NoteDaoService.searchCarById(carId)


    override suspend fun getNumCars(): Int =
        NoteDaoService.getNumCars()


    override suspend fun insertCars(carsList: List<Car>): LongArray =
        NoteDaoService.insertCars(carsList)


}