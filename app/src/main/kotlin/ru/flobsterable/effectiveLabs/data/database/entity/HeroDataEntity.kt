package ru.flobsterable.effectiveLabs.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heroes_table")
class HeroDataEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    @ColumnInfo(name = "uri_image")
    val uriImage: String
)
