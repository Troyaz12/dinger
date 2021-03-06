package data.tinder.recommendation

import data.database.CollectibleDaoDelegate
import domain.recommendation.DomainRecommendationCommonConnectionPhoto

internal class CommonConnectionPhotoDaoDelegate(
        private val photoDao: RecommendationUserCommonConnectionPhotoDao,
        private val commonConnectionPhotoDao: RecommendationUserCommonConnection_PhotoDao)
    : CollectibleDaoDelegate<String, DomainRecommendationCommonConnectionPhoto>() {
    override fun insertResolved(source: DomainRecommendationCommonConnectionPhoto) =
            photoDao.insertPhoto(RecommendationUserCommonConnectionPhotoEntity(
                    small = source.small, medium = source.medium, large = source.large))

    fun insertResolvedForCommonConnectionId(
            commonConnectionId: String,
            photos: Iterable<DomainRecommendationCommonConnectionPhoto>) = photos.forEach {
                insertResolved(it)
                commonConnectionPhotoDao.insertCommonConnection_Photo(
                        RecommendationUserCommonConnectionEntity_PhotoEntity(
                                recommendationUserCommonConnectionEntityId = commonConnectionId,
                                recommendationUserCommonConnectionPhotoEntitySmall = it.small))
            }
}

