package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Toys
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.GamesRepository
import com.example.data.ShowsRepository
import com.example.data.ToysRepository
import com.example.data.TriviaRepository
import com.example.data.l10n.AppLanguage
import com.example.data.l10n.LocalizedContentProvider
import com.example.model.NostalgiaDetailItem
import com.example.model.ToyItem
import com.example.model.TvChannel
import com.example.model.TvShowItem
import com.example.model.VideoGameItem
import com.example.ui.components.ChannelBadge
import com.example.ui.components.CrtBannerFrame
import com.example.ui.components.RetroCard
import com.example.ui.components.RetroEmptyState
import com.example.ui.components.RetroSectionHeader
import com.example.ui.components.RetroTagPill
import com.example.ui.theme.LocalRetroExtras
import androidx.compose.material3.HorizontalDivider
import com.example.ui.components.FavoriteIconButton
import com.example.ui.components.RetroFilterChip
import com.example.ui.components.RetroHorizontalChipsRow
import com.example.viewmodel.HomeSearchFilter
import com.example.viewmodel.NostalgiaUiState

@Composable
fun HomeScreen(
    uiState: NostalgiaUiState,
    allShows: List<TvShowItem>,
    allGames: List<VideoGameItem>,
    allToys: List<ToyItem>,
    onSearchChange: (String) -> Unit,
    onSearchFilterChange: (HomeSearchFilter) -> Unit,
    onToggleFavorite: (String) -> Unit,
    onChannelSelected: (TvChannel) -> Unit,
    onNavigateToSuperheroes: () -> Unit,
    onNavigateToTrivia: () -> Unit,
    onNavigateToGames: () -> Unit,
    onNavigateToToys: () -> Unit,
    onOpenDetail: (NostalgiaDetailItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val lang = uiState.appLanguage
    val labels = remember(lang) { LocalizedContentProvider.uiLabels(lang) }
    val query = uiState.searchQuery.trim()
    val activeFilter = uiState.homeSearchFilter
    val isFiltering = query.isNotBlank() || activeFilter != HomeSearchFilter.ALL

    val rawShowMap = remember { ShowsRepository.allShows.associateBy { it.id } }
    val rawGameMap = remember { GamesRepository.allGames.associateBy { it.id } }
    val rawToyMap = remember { ToysRepository.allToys.associateBy { it.id } }

    val channelShowCounts = remember(allShows) {
        TvChannel.entries.associateWith { ch ->
            allShows.count { it.primaryChannel == ch || ch in it.otherChannels }
        }
    }

    val matchingShows = remember(query, activeFilter, allShows, lang) {
        val allowed = activeFilter == HomeSearchFilter.ALL ||
            activeFilter == HomeSearchFilter.TAMIL ||
            activeFilter == HomeSearchFilter.SHOWS
        if (!isFiltering || !allowed) {
            emptyList()
        } else {
            val pool = if (activeFilter == HomeSearchFilter.TAMIL) {
                allShows.filter { it.isTamilClassic }
            } else {
                allShows
            }
            if (query.isBlank()) {
                pool
            } else {
                pool.filter { show ->
                    val raw = rawShowMap[show.id]
                    show.matchesShowQuery(query, lang) || (raw != null && raw.matchesShowQuery(query, AppLanguage.ENGLISH))
                }
            }
        }
    }

    val matchingGames = remember(query, activeFilter, allGames, lang) {
        val allowed = activeFilter == HomeSearchFilter.ALL ||
            activeFilter == HomeSearchFilter.GAMES
        if (!isFiltering || !allowed) {
            emptyList()
        } else if (query.isBlank()) {
            allGames
        } else {
            allGames.filter { game ->
                val raw = rawGameMap[game.id]
                game.matchesGameQuery(query, lang) || (raw != null && raw.matchesGameQuery(query, AppLanguage.ENGLISH))
            }
        }
    }

    val matchingToys = remember(query, activeFilter, allToys, lang) {
        val allowed = activeFilter == HomeSearchFilter.ALL ||
            activeFilter == HomeSearchFilter.TAMIL ||
            activeFilter == HomeSearchFilter.TOYS
        if (!isFiltering || !allowed) {
            emptyList()
        } else {
            val pool = if (activeFilter == HomeSearchFilter.TAMIL) {
                allToys.filter { it.isTamilClassic }
            } else {
                allToys
            }
            if (query.isBlank()) {
                pool
            } else {
                pool.filter { toy ->
                    val raw = rawToyMap[toy.id]
                    toy.matchesToyQuery(query, lang) || (raw != null && raw.matchesToyQuery(query, AppLanguage.ENGLISH))
                }
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Pinned Search Bar & Quick Category Filter at the Top of the Home Screen
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 840.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = uiState.searchQuery,
                    onValueChange = onSearchChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("home_search_input"),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = labels.searchPlaceholder,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search 90s archive"
                        )
                    },
                    trailingIcon = {
                        if (uiState.searchQuery.isNotEmpty() || activeFilter != HomeSearchFilter.ALL) {
                            IconButton(
                                onClick = {
                                    onSearchChange("")
                                    onSearchFilterChange(HomeSearchFilter.ALL)
                                },
                                modifier = Modifier
                                    .minimumInteractiveComponentSize()
                                    .testTag("home_search_clear")
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Clear search and filters"
                                )
                            }
                        }
                    },
                    shape = MaterialTheme.shapes.medium,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = LocalRetroExtras.current.inkBorder
                    )
                )

                RetroHorizontalChipsRow {
                    HomeSearchFilter.entries.forEach { filter ->
                        val filterLabel = when (filter) {
                            HomeSearchFilter.ALL -> labels.filterAll
                            HomeSearchFilter.TAMIL -> labels.filterTamil90s
                            HomeSearchFilter.SHOWS -> labels.filterShows
                            HomeSearchFilter.GAMES -> labels.filterGames
                            HomeSearchFilter.TOYS -> labels.filterToys
                        }
                        RetroFilterChip(
                            label = filterLabel,
                            selected = activeFilter == filter,
                            onClick = { onSearchFilterChange(filter) },
                            testTag = "home_filter_${filter.name.lowercase()}",
                            accentColor = when (filter) {
                                HomeSearchFilter.ALL -> MaterialTheme.colorScheme.primary
                                HomeSearchFilter.TAMIL -> Color(0xFFD84315)
                                HomeSearchFilter.SHOWS -> MaterialTheme.colorScheme.tertiary
                                HomeSearchFilter.GAMES -> MaterialTheme.colorScheme.secondary
                                HomeSearchFilter.TOYS -> MaterialTheme.colorScheme.primary
                            }
                        )
                    }
                }
            }
        }

        HorizontalDivider(thickness = 1.5.dp, color = LocalRetroExtras.current.inkBorder.copy(alpha = 0.25f))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .widthIn(max = 840.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            if (!isFiltering) {
                item {
                    CrtBannerFrame(
                        imageRes = R.drawable.img_hero_retro_tv,
                        contentDescription = "Retro 90s Tamil childhood room with CRT television and game cartridge",
                        channelBadge = labels.heroBadge,
                        headline = labels.heroHeadline,
                        subtitle = labels.heroSubtitle,
                        scanlinesEnabled = uiState.crtScanlinesEnabled
                    )
                }
            }

            if (isFiltering) {
                val totalHits = matchingShows.size + matchingGames.size + matchingToys.size
                val activeFilterLabel = when (activeFilter) {
                    HomeSearchFilter.ALL -> labels.filterAll
                    HomeSearchFilter.TAMIL -> labels.filterTamil90s
                    HomeSearchFilter.SHOWS -> labels.filterShows
                    HomeSearchFilter.GAMES -> labels.filterGames
                    HomeSearchFilter.TOYS -> labels.filterToys
                }
                item {
                    RetroSectionHeader(
                        title = LocalizedContentProvider.searchResultsTitle(totalHits, lang),
                        subtitle = LocalizedContentProvider.searchResultsSubtitle(activeFilterLabel, query, lang)
                    )
                }

                if (totalHits == 0) {
                    item {
                        RetroEmptyState(
                            title = labels.emptySearchTitle,
                            hint = labels.emptySearchHint
                        )
                    }
                } else {
                    items(matchingShows, key = { "search_${it.id}" }) { show ->
                        SearchShowResultCard(
                            show = show,
                            lang = lang,
                            isFavorite = show.id in uiState.favoriteIds,
                            onToggleFavorite = { onToggleFavorite(show.id) },
                            onClick = { onOpenDetail(NostalgiaDetailItem.Show(show)) }
                        )
                    }
                    items(matchingGames, key = { "search_${it.id}" }) { game ->
                        SearchGameResultCard(
                            game = game,
                            lang = lang,
                            isFavorite = game.id in uiState.favoriteIds,
                            onToggleFavorite = { onToggleFavorite(game.id) },
                            onClick = { onOpenDetail(NostalgiaDetailItem.Game(game)) }
                        )
                    }
                    items(matchingToys, key = { "search_${it.id}" }) { toy ->
                        SearchToyResultCard(
                            toy = toy,
                            lang = lang,
                            isFavorite = toy.id in uiState.favoriteIds,
                            onToggleFavorite = { onToggleFavorite(toy.id) },
                            onClick = { onOpenDetail(NostalgiaDetailItem.Toy(toy)) }
                        )
                    }
                }
            } else {
                // Quick Stats & Trivia / Vault Shortcuts
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        RetroCard(
                            modifier = Modifier
                                .weight(1f)
                                .testTag("home_trivia_card"),
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            onClick = onNavigateToTrivia
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.EmojiEvents,
                                    contentDescription = labels.triviaQuizCardTitle,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(28.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = labels.triviaQuizCardTitle,
                                        style = MaterialTheme.typography.titleSmall,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                    Text(
                                        text = LocalizedContentProvider.bestScoreLabel(
                                            best = uiState.bestTriviaScore,
                                            total = TriviaRepository.questions.size,
                                            lang = lang
                                        ),
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }

                        RetroCard(
                            modifier = Modifier
                                .weight(1f)
                                .testTag("home_vault_card"),
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            onClick = onNavigateToToys
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Bookmark,
                                    contentDescription = labels.savedVaultCardTitle,
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.size(28.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = labels.savedVaultCardTitle,
                                        style = MaterialTheme.typography.titleSmall,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                    Text(
                                        text = "${uiState.favoriteIds.size} ${labels.pinnedSuffix}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }

                // 90s Tamil & Indian TV Channel Dial
                item {
                    RetroSectionHeader(
                        title = labels.channelDialTitle,
                        subtitle = labels.channelDialSubtitle
                    )
                }

                item {
                    val channels = TvChannel.entries
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        channels.chunked(2).forEach { pair ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                pair.forEach { channel ->
                                    ChannelDialCard(
                                        channel = channel,
                                        lang = lang,
                                        showCount = channelShowCounts[channel] ?: 0,
                                        onClick = { onChannelSelected(channel) },
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    }
                }

                // 3 Illustrated Category Spotlights
                item {
                    RetroSectionHeader(
                        title = labels.spotlightsTitle,
                        subtitle = labels.spotlightsSubtitle
                    )
                }

                item {
                    CategorySpotlightCard(
                        imageRes = R.drawable.img_superhero_spotlight,
                        badge = "SHAKTIMAAN • CAPTAIN VYOM • MAYAVI • BATMAN",
                        title = labels.spotlightHeroTitle,
                        description = labels.spotlightHeroDesc,
                        icon = Icons.Filled.MusicNote,
                        accentColor = MaterialTheme.colorScheme.tertiary,
                        onClick = onNavigateToSuperheroes,
                        testTag = "spotlight_superheroes"
                    )
                }

                item {
                    CategorySpotlightCard(
                        imageRes = R.drawable.img_retro_gaming,
                        badge = "BRICK GAME • CONTRA • MUSTAPHA • ROAD RASH",
                        title = labels.spotlightGameTitle,
                        description = LocalizedContentProvider.spotlightGamesDescription(allGames.size, lang),
                        icon = Icons.Filled.SportsEsports,
                        accentColor = MaterialTheme.colorScheme.secondary,
                        onClick = onNavigateToGames,
                        testTag = "spotlight_games"
                    )
                }

                item {
                    CategorySpotlightCard(
                        imageRes = R.drawable.img_toys_nostalgia,
                        badge = "PAMBARAM • NUNGU VANDI • GOLI • TAZOS",
                        title = labels.spotlightToyTitle,
                        description = LocalizedContentProvider.spotlightToysDescription(allToys.size, lang),
                        icon = Icons.Filled.Toys,
                        accentColor = MaterialTheme.colorScheme.primary,
                        onClick = onNavigateToToys,
                        testTag = "spotlight_toys"
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }
        }
    }
}

private fun TvShowItem.matchesShowQuery(query: String, lang: AppLanguage): Boolean {
    return title.contains(query, ignoreCase = true) ||
        taglineOrHindiTitle.contains(query, ignoreCase = true) ||
        primaryChannel.displayName.contains(query, ignoreCase = true) ||
        LocalizedContentProvider.channelName(primaryChannel, lang).contains(query, ignoreCase = true) ||
        catchphrase.contains(query, ignoreCase = true) ||
        synopsis.contains(query, ignoreCase = true) ||
        desiMemory.contains(query, ignoreCase = true) ||
        otherChannels.any { ch ->
            ch.displayName.contains(query, ignoreCase = true) ||
                LocalizedContentProvider.channelName(ch, lang).contains(query, ignoreCase = true)
        }
}

private fun VideoGameItem.matchesGameQuery(query: String, lang: AppLanguage): Boolean {
    return title.contains(query, ignoreCase = true) ||
        platform.displayName.contains(query, ignoreCase = true) ||
        LocalizedContentProvider.platformDisplayLabel(platform, lang).contains(query, ignoreCase = true) ||
        genre.contains(query, ignoreCase = true) ||
        gameplayDetails.contains(query, ignoreCase = true) ||
        cheatCodesOrSecrets.contains(query, ignoreCase = true) ||
        desiMemory.contains(query, ignoreCase = true)
}

private fun ToyItem.matchesToyQuery(query: String, lang: AppLanguage): Boolean {
    return name.contains(query, ignoreCase = true) ||
        category.displayName.contains(query, ignoreCase = true) ||
        LocalizedContentProvider.toyCategoryLabel(category, lang).contains(query, ignoreCase = true) ||
        howWePlayed.contains(query, ignoreCase = true) ||
        nostalgiaStory.contains(query, ignoreCase = true)
}

@Composable
private fun ChannelDialCard(
    channel: TvChannel,
    lang: AppLanguage,
    showCount: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val channelName = LocalizedContentProvider.channelName(channel, lang)
    val channelEra = LocalizedContentProvider.channelEra(channel, lang)
    val countLabel = LocalizedContentProvider.showsCountLabel(showCount, lang)

    RetroCard(
        modifier = modifier.testTag("channel_dial_${channel.name.lowercase()}"),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = channel.brandColor,
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(1.5.dp, LocalRetroExtras.current.inkBorder)
                ) {
                    Text(
                        text = channel.badgeCode,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                    )
                }
                Icon(
                    imageVector = Icons.Filled.LiveTv,
                    contentDescription = null,
                    tint = channel.brandColor,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = channelName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "$countLabel • $channelEra",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun CategorySpotlightCard(
    imageRes: Int,
    badge: String,
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    accentColor: Color,
    onClick: () -> Unit,
    testTag: String
) {
    RetroCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(testTag),
        onClick = onClick
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(126.dp)
        )
        Column(modifier = Modifier.padding(14.dp)) {
            RetroTagPill(
                text = badge,
                containerColor = accentColor,
                contentColor = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(accentColor)
                        .border(2.dp, LocalRetroExtras.current.inkBorder, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Open $title",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchShowResultCard(
    show: TvShowItem,
    lang: AppLanguage,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onClick: () -> Unit
) {
    RetroCard(
        modifier = Modifier.testTag("search_result_${show.id}"),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ChannelBadge(channel = show.primaryChannel, lang = lang)
                    RetroTagPill(text = LocalizedContentProvider.showCategoryLabel(show.category, lang))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = show.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = show.taglineOrHindiTitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            FavoriteIconButton(
                isFavorite = isFavorite,
                onToggle = onToggleFavorite,
                itemLabel = show.title,
                testTag = "search_fav_${show.id}"
            )
        }
    }
}

@Composable
private fun SearchGameResultCard(
    game: VideoGameItem,
    lang: AppLanguage,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onClick: () -> Unit
) {
    RetroCard(
        modifier = Modifier.testTag("search_result_${game.id}"),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                RetroTagPill(
                    text = LocalizedContentProvider.platformDisplayLabel(game.platform, lang),
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = game.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${game.genre} • ${game.releaseEra}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            FavoriteIconButton(
                isFavorite = isFavorite,
                onToggle = onToggleFavorite,
                itemLabel = game.title,
                testTag = "search_fav_${game.id}"
            )
        }
    }
}

@Composable
private fun SearchToyResultCard(
    toy: ToyItem,
    lang: AppLanguage,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onClick: () -> Unit
) {
    RetroCard(
        modifier = Modifier.testTag("search_result_${toy.id}"),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                RetroTagPill(
                    text = LocalizedContentProvider.toyCategoryLabel(toy.category, lang),
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = toy.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = toy.eraOrPrice,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            FavoriteIconButton(
                isFavorite = isFavorite,
                onToggle = onToggleFavorite,
                itemLabel = toy.name,
                testTag = "search_fav_${toy.id}"
            )
        }
    }
}
