package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(indices = arrayOf(Index("id")))
internal open class RecommendationUserCommonConnectionEntity(
        @PrimaryKey
        var id: String,
        var name: String,
        var degree: String) {
    companion object {
        val NONE = RecommendationUserCommonConnectionEntity(id = "", name = "", degree = "")
    }
}
