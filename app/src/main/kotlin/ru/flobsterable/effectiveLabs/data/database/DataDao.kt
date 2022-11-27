package ru.flobsterable.effectiveLabs.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.flobsterable.effectiveLabs.data.database.entity.HeroDataEntity

@Dao
interface DataDao {
    @Query("SELECT * FROM heroes_table ORDER BY name ASC")
    fun getCharactersList(): Flow<List<HeroDataEntity>>
    @Query("SELECT * FROM heroes_table WHERE id = :id")
    fun getCharacterInfo(id: Int): Flow<HeroDataEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroesList(heroList: List<HeroDataEntity>)
    @Query("DELETE FROM heroes_table")
    suspend fun deleteAllHeroesList()
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHeroData(heroData: HeroDataEntity)
}
