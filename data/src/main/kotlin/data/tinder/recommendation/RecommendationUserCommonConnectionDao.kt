package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction

@Dao
internal interface RecommendationUserCommonConnectionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCommonConnection(commonConnection: RecommendationUserCommonConnectionEntity)

    @Query("SELECT * from RecommendationUserCommonConnectionEntity WHERE id=:id")
    @Transaction
    fun selectCommonConnectionById(id: String)
            : List<RecommendationUserCommonConnectionWithRelatives>
}
