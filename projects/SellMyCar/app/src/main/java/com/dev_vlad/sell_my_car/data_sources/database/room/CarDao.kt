package com.dev_vlad.sell_my_car.data_sources.database.room

import androidx.room.*


const val CAR_ORDER_ASC: String = ""
const val CAR_ORDER_DESC: String = "-"
const val CAR_FILTER_TITLE = "title"
const val CAR_FILTER_DATE_CREATED = "created_at"

const val ORDER_BY_ASC_DATE_UPDATED = CAR_ORDER_ASC + CAR_FILTER_DATE_CREATED
const val ORDER_BY_DESC_DATE_UPDATED = CAR_ORDER_DESC + CAR_FILTER_DATE_CREATED
const val ORDER_BY_ASC_TITLE = CAR_ORDER_ASC + CAR_FILTER_TITLE
const val ORDER_BY_DESC_TITLE = CAR_ORDER_DESC + CAR_FILTER_TITLE

const val CAR_PAGINATION_PAGE_SIZE = 30



@Dao
interface CarDao {

    @Insert
    suspend fun insertCar(car: CarCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCars(cars: List<CarCacheEntity>): LongArray

    @Query("SELECT * FROM cars WHERE id = :id")
    suspend fun searchCarById(id: String): CarCacheEntity?

    @Query("DELETE FROM cars WHERE id IN (:ids)")
    suspend fun deleteCars(ids: List<String>): Int

    @Query("DELETE FROM cars")
    suspend fun deleteAllCars()

    @Query("SELECT * FROM cars")
    suspend fun getAllCars(): List<CarCacheEntity>

    @Update
    suspend fun updateCar(car: CarCacheEntity): Int

    @Delete
    suspend fun deleteCar(car: CarCacheEntity): Int

    @Query("SELECT * FROM cars")
    suspend fun searchCars(): List<CarCacheEntity>

    @Query("""
        SELECT * FROM cars 
        WHERE title LIKE '%' || :query || '%' 
        ORDER BY updated_at DESC LIMIT (:page * :pageSize)
        """)
    suspend fun searchCarsOrderByDateDESC(
            query: String,
            page: Int,
            pageSize: Int = CAR_PAGINATION_PAGE_SIZE
    ): List<CarCacheEntity>

    @Query("""
        SELECT * FROM cars 
        WHERE title LIKE '%' || :query || '%'
        ORDER BY updated_at ASC LIMIT (:page * :pageSize)
        """)
    suspend fun searchCarsOrderByDateASC(
            query: String,
            page: Int,
            pageSize: Int = CAR_PAGINATION_PAGE_SIZE
    ): List<CarCacheEntity>

    @Query("""
        SELECT * FROM cars 
        WHERE title LIKE '%' || :query || '%' 
        ORDER BY title DESC LIMIT (:page * :pageSize)
        """)
    suspend fun searchCarsOrderByTitleDESC(
            query: String,
            page: Int,
            pageSize: Int = CAR_PAGINATION_PAGE_SIZE
    ): List<CarCacheEntity>

    @Query("""
        SELECT * FROM cars 
        WHERE title LIKE '%' || :query || '%' 
        ORDER BY title ASC LIMIT (:page * :pageSize)
        """)
    suspend fun searchCarsOrderByTitleASC(
            query: String,
            page: Int,
            pageSize: Int = CAR_PAGINATION_PAGE_SIZE
    ): List<CarCacheEntity>


    @Query("SELECT COUNT(*) FROM cars")
    suspend fun getNumCars(): Int
}


suspend fun CarDao.returnOrderedQuery(
        query: String,
        filterAndOrder: String,
        page: Int
): List<CarCacheEntity> {

    when{

        filterAndOrder.contains(ORDER_BY_DESC_DATE_UPDATED) ->{
            return searchCarsOrderByDateDESC(
                    query = query,
                    page = page)
        }

        filterAndOrder.contains(ORDER_BY_ASC_DATE_UPDATED) ->{
            return searchCarsOrderByDateASC(
                    query = query,
                    page = page)
        }

        filterAndOrder.contains(ORDER_BY_DESC_TITLE) ->{
            return searchCarsOrderByTitleDESC(
                    query = query,
                    page = page)
        }

        filterAndOrder.contains(ORDER_BY_ASC_TITLE) ->{
            return searchCarsOrderByTitleASC(
                    query = query,
                    page = page)
        }
        else ->
            return searchCarsOrderByDateDESC(
                    query = query,
                    page = page
            )
    }
}