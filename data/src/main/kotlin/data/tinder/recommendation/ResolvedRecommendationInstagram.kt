package data.tinder.recommendation

import java.util.Date

internal data class ResolvedRecommendationInstagram(
        val profilePictureUrl: String,
        val lastFetchTime: Date,
        val mediaCount: Int,
        val completedInitialFetch: Boolean,
        val username: String,
        val photos: Iterable<ResolvedRecommendationInstagramPhoto>) {
    companion object {
        val NONE = ResolvedRecommendationInstagram(
                profilePictureUrl = "",
                lastFetchTime = Date(),
                mediaCount = 0,
                completedInitialFetch = false,
                username = "",
                photos = emptySet())
    }
}
