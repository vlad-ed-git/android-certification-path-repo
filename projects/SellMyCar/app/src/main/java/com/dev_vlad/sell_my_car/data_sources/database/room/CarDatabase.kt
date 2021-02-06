package com.dev_vlad.sell_my_car.data_sources.database.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [CarCacheEntity::class ], version = 1, exportSchema=false)
abstract class CarDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    companion object{
        val DATABASE_NAME: String = "sell_my_car-cars_db"
    }


}