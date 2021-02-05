package com.dev_vlad.sell_my_car.data_sources.models

import android.os.Parcel
import android.os.Parcelable
import java.util.concurrent.locks.Condition

enum class CarCondition {
    OUTSTANDING,
    CLEAN,
    AVERAGE,
    ROUGH,
    DAMAGED
}

data class Car (
        val id: String,
        val title :String,
        val extra_details :String,
        val year: String,
        val make: String,
        val model: String,
        val color: String,
        val condition: CarCondition,
        val mileage: String,
        val has_been_in_accident : Boolean,
        val has_flood_damage : Boolean,
        val has_flame_damage : Boolean,
        val has_issues_on_dashboard : Boolean,
        val has_broken_or_replaced_odometer : Boolean,
        val no_of_tires_to_replace : Int,
        val has_customizations : Boolean,
        val updated_at : String,
        val created_at: String

): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        CarCondition.valueOf(parcel.readString()!!),
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(extra_details)
        parcel.writeString(year)
        parcel.writeString(make)
        parcel.writeString(model)
        parcel.writeString(color)
        parcel.writeString(condition.name)
        parcel.writeString(mileage)
        parcel.writeByte(if (has_been_in_accident) 1 else 0)
        parcel.writeByte(if (has_flood_damage) 1 else 0)
        parcel.writeByte(if (has_flame_damage) 1 else 0)
        parcel.writeByte(if (has_issues_on_dashboard) 1 else 0)
        parcel.writeByte(if (has_broken_or_replaced_odometer) 1 else 0)
        parcel.writeInt(no_of_tires_to_replace)
        parcel.writeByte(if (has_customizations) 1 else 0)
        parcel.writeString(updated_at)
        parcel.writeString(created_at)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Car> {
        override fun createFromParcel(parcel: Parcel): Car {
            return Car(parcel)
        }

        override fun newArray(size: Int): Array<Car?> {
            return arrayOfNulls(size)
        }
    }

}

