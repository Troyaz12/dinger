package data.tinder.recommendation

import data.database.CollectibleDaoDelegate
import domain.recommendation.DomainRecommendationCommonConnection

internal class RecommendationCommonConnectionDaoDelegate(
        private val commonConnectionDao: RecommendationUserCommonConnectionDao,
        private val userCommonConnectionDelegate
        : RecommendationUser_RecommendationUserCommonConnectionDao,
        private val photoDaoDelegate: CommonConnectionPhotoDaoDelegate)
    : CollectibleDaoDelegate<String, DomainRecommendationCommonConnection>() {
    override fun selectByPrimaryKey(primaryKey: String) =
            commonConnectionDao.selectCommonConnectionById(primaryKey).firstOrNull()?.let {
                val photos = photoDaoDelegate.collectByPrimaryKeys(it.photos)
                it.recommendationUserCommonConnection.let {
                    return DomainRecommendationCommonConnection(
                            id = it.id,
                            name = it.name,
                            degree = it.degree,
                            photos = photos)
                }
            } ?: DomainRecommendationCommonConnection.NONE

    override fun insertResolved(source: DomainRecommendationCommonConnection) {
        source.photos?.let { photoDaoDelegate.insertResolvedForCommonConnectionId(source.id, it) }
        commonConnectionDao.insertCommonConnection(
                RecommendationUserCommonConnectionEntity(
                            id = source.id,
                            name = source.name,
                            degree = source.degree))
    }

    fun insertResolvedForUserId(
            userId: String, commonConnections: Iterable<DomainRecommendationCommonConnection>) =
            commonConnections.forEach {
                insertResolved(it)
                userCommonConnectionDelegate.insertUser_CommonConnection(
                        RecommendationUserEntity_RecommendationUserCommonConnectionEntity(
                                recommendationUserEntityId = userId,
                                recommendationUserCommonConnectionEntityId = it.id))
            }
}
