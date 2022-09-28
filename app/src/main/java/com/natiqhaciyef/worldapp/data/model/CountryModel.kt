package com.natiqhaciyef.worldapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class CountryModel(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String,
    @ColumnInfo(name = "capital")
    @SerializedName("capital")
    var capital: String,
    @ColumnInfo(name = "region")
    @SerializedName("region")
    var region: String,
    @ColumnInfo(name = "currency")
    @SerializedName("currency")
    var currency: String,
    @ColumnInfo(name = "language")
    @SerializedName("language")
    var language: String,
    @ColumnInfo(name = "flagUrl")
    @SerializedName("flag")
    var flagUrl: String,
    @ColumnInfo(name = "religion")
    @SerializedName("religion")
    var religion: String
): Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}