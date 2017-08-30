package data.tinder.recommendation

import data.CollectibleDaoDelegate
import domain.recommendation.DomainRecommendationSpotifyArtist

internal class RecommendationSpotifyArtistDaoDelegate(
        private val artistDao: RecommendationSpotifyArtistDao,
        private val trackArtistDao: RecommendationSpotifyThemeTrack_ArtistDao)
    : CollectibleDaoDelegate<String, DomainRecommendationSpotifyArtist>() {
    override fun selectByPrimaryKey(primaryKey: String) =
            artistDao.selectArtistById(primaryKey).firstOrNull()?.let {
                return DomainRecommendationSpotifyArtist(
                        id = it.id,
                        name = it.name)
            } ?: DomainRecommendationSpotifyArtist.NONE

    override fun insertDomain(source: DomainRecommendationSpotifyArtist) =
            artistDao.insertArtist(RecommendationUserSpotifyThemeTrackArtistEntity(
                    id = source.id,
                    name = source.name))

    fun insertDomainForTrackId(
            trackId: String, artists: Iterable<DomainRecommendationSpotifyArtist>) {
        artists.forEach {
            insertDomain(it)
            trackArtistDao.insertSpotifyThemeTrack_Artist(
                    RecommendationUserSpotifyThemeTrackEntity_RecommendationUserSpotifyThemeTrackArtistEntity(
                            recommendationUserSpotifyThemeTrackArtistEntityId = trackId,
                            recommendationUserSpotifyThemeTrackEntityId = it.id))
        }
    }
}
