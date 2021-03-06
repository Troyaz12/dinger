package data.tinder.recommendation

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

internal class RecommendationUserCommonConnectionWithRelatives(
        @Embedded
        var recommendationUserCommonConnection: RecommendationUserCommonConnectionEntity,
        @Relation(parentColumn = "id",
                entityColumn = "recommendationUserCommonConnectionEntityId",
                entity = RecommendationUserCommonConnectionEntity_PhotoEntity::class,
                projection = arrayOf("recommendationUserCommonConnectionPhotoEntitySmall"))
        var photos: Set<String>) {
    constructor() : this(RecommendationUserCommonConnectionEntity.NONE, emptySet())
}
