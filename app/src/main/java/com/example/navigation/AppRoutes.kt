package com.example.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
object CartoonsRoute

@Serializable
data class TvShowsRoute(
    val initialChannelName: String? = null
)

@Serializable
object GamesRoute

@Serializable
object ToysVaultRoute

@Serializable
data class TriviaRoute(
    val initialTab: Int = 0
)
