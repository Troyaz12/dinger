package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface RecommendationUserSpotifyThemeTrackAlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(album: RecommendationUserSpotifyThemeTrackAlbumEntity)

    @Query("SELECT * from RecommendationUserSpotifyThemeTrackAlbumEntity WHERE id=:id")
    fun selectAlbumById(id: String)
            : List<RecommendationUserSpotifyThemeTrackAlbumWithRelatives>
}
