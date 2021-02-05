package com.dev_vlad.sell_my_car.data_sources.models

import com.dev_vlad.sell_my_car.utils.DateUtil
import java.util.*
import kotlin.collections.ArrayList
import com.dev_vlad.sell_my_car.data_sources.models.Car

object CarFactory {

    fun createSingleCar(
            id : String? = null,
            title:String,
            extra_details: String? = null,
            year: String,
            make: String,
            model: String,
            color: String,
            condition: CarCondition,
            mileage: String,
            has_been_in_accident : Boolean,
            has_flood_damage : Boolean,
            has_flame_damage : Boolean,
            has_issues_on_dashboard : Boolean,
            has_broken_or_replaced_odometer : Boolean,
            no_of_tires_to_replace : Int,
            has_customizations : Boolean,
    ): Car{
        return Car(
            id = id ?: UUID.randomUUID().toString(),
            title = title,
            extra_details = extra_details ?:  "",
            year = year,
            make = make,
            model = model,
            color = color,
            condition = condition,
            mileage = mileage,
            has_been_in_accident = has_been_in_accident,
            has_flood_damage = has_flood_damage,
            has_flame_damage = has_flame_damage,
            has_issues_on_dashboard = has_issues_on_dashboard,
            has_broken_or_replaced_odometer = has_broken_or_replaced_odometer,
            no_of_tires_to_replace = no_of_tires_to_replace,
            has_customizations = has_customizations,
            updated_at = DateUtil.getCurrentTimestampAsStr(),
            created_at = DateUtil.getCurrentTimestampAsStr()
        )
    }


    //only for testing
    fun createCarsList(numCars: Int) : List<Car>{
        val list = ArrayList<Car>()
        for (i in 0 until numCars){
            list.add(
                 createSingleCar(
                     id = null,
                    title = "Car $i",
                    extra_details = null,
                    year = "201 + $i",
                    make = "Car make $i" ,
                    model = "Car model $i",
                    color = "Car color black $i",
                    condition =  CarCondition.AVERAGE,
                    mileage = "Car mileage $i",
                    has_been_in_accident = false,
                    has_flood_damage = true,
                    has_flame_damage = false,
                    has_issues_on_dashboard = true,
                    has_broken_or_replaced_odometer =false,
                    no_of_tires_to_replace = 0,
                    has_customizations = true,
                 )
            )
        }
        return list
    }
}