package com.dev_vlad.sell_my_car.data_sources.database.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dev_vlad.sell_my_car.data_sources.accessors.models.CarCondition

@Entity(tableName = "cars")
class CarCacheEntity (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String,
    
    var title :String,
    
    var extra_details :String,
    
    var year: String,
    
    var make: String,
    
    var model: String,
    
    var color: String,
    
    var condition: String,
    
    var mileage: String,
    
    var has_been_in_accident : Boolean,
    
    var has_flood_damage : Boolean,
    
    var has_flame_damage : Boolean,
    
    var has_issues_on_dashboard : Boolean,
    
    var has_broken_or_replaced_odometer : Boolean,
    
    var no_of_tires_to_replace : Int,
    
    var has_customizations : Boolean,
    
    var updated_at : String,
    
    var created_at: String
    
){

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false

        other as CarCacheEntity
        when {
            this.id != other.id -> return false
            this.title != other.title -> return false
            this.extra_details != other.extra_details -> return false
            this.year!= other.year -> return false
            this.make!= other.make -> return false
            this.model!= other.model -> return false
            this.mileage!= other.mileage -> return false
            this.color!= other.color -> return false
            this.condition!= other.condition -> return false
            this.has_been_in_accident!= other.has_been_in_accident -> return false
            this.has_broken_or_replaced_odometer!= other.has_broken_or_replaced_odometer -> return false
            this.has_customizations!= other.has_customizations -> return false
            this.has_flame_damage!= other.has_flame_damage -> return false
            this.has_flood_damage!= other.has_flood_damage -> return false
            this.has_issues_on_dashboard!= other.has_issues_on_dashboard -> return false
            this.no_of_tires_to_replace!= other.no_of_tires_to_replace -> return false
            this.created_at!= other.created_at -> return false
            else -> return true
        }
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + extra_details.hashCode()
        result = 31 * result + year.hashCode()
        result = 31 * result + make.hashCode()
        result = 31 * result + model.hashCode()
        result = 31 * result + color.hashCode()
        result = 31 * result + condition.hashCode()
        result = 31 * result + mileage.hashCode()
        result = 31 * result + has_been_in_accident.hashCode()
        result = 31 * result + has_flood_damage.hashCode()
        result = 31 * result + has_flame_damage.hashCode()
        result = 31 * result + has_issues_on_dashboard.hashCode()
        result = 31 * result + has_broken_or_replaced_odometer.hashCode()
        result = 31 * result + no_of_tires_to_replace
        result = 31 * result + has_customizations.hashCode()
        result = 31 * result + updated_at.hashCode()
        result = 31 * result + created_at.hashCode()
        return result
    }
}