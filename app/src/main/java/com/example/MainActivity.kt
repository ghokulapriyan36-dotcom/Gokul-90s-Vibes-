package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Toys
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LiveTv
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material.icons.outlined.SmartDisplay
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.material.icons.outlined.Toys
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.data.l10n.AppLanguage
import com.example.data.l10n.LocalizedContentProvider
import com.example.data.l10n.LocalizedUiLabels
import com.example.model.NostalgiaDetailItem
import com.example.navigation.CartoonsRoute
import com.example.navigation.GamesRoute
import com.example.navigation.HomeRoute
import com.example.navigation.ToysVaultRoute
import com.example.navigation.TriviaRoute
import com.example.navigation.TvShowsRoute
import com.example.ui.components.MiniThemePlayerBar
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.NostalgiaDetailBottomSheet
import com.example.ui.screens.ShowsAndHeroesScreen
import com.example.ui.screens.ShowsSectionMode
import com.example.ui.screens.ToysAndVaultScreen
import com.example.ui.screens.TriviaAndHeroesScreen
import com.example.ui.screens.VideoGamesScreen
import com.example.ui.theme.LocalRetroExtras
import com.example.ui.theme.NinetiesRewindTheme
import com.example.viewmodel.NostalgiaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: NostalgiaViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            NinetiesRewindTheme(darkTheme = uiState.crtDarkMode) {
                NinetiesRewindApp(viewModel = viewModel)
            }
        }
    }
}

private enum class TopLevelTab(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val testTag: String
) {
    CARTOONS(Icons.Filled.SmartDisplay, Icons.Outlined.SmartDisplay, "nav_cartoons"),
    TV_SHOWS(Icons.Filled.LiveTv, Icons.Outlined.LiveTv, "nav_tv_shows"),
    GAMES(Icons.Filled.SportsEsports, Icons.Outlined.SportsEsports, "nav_games"),
    TOYS(Icons.Filled.Toys, Icons.Outlined.Toys, "nav_toys"),
    TRIVIA(Icons.Filled.Quiz, Icons.Outlined.Quiz, "nav_trivia")
}

private fun TopLevelTab.localizedLabel(labels: LocalizedUiLabels): String = when (this) {
    TopLevelTab.CARTOONS -> labels.navCartoons
    TopLevelTab.TV_SHOWS -> labels.navTvShows
    TopLevelTab.GAMES -> labels.navGames
    TopLevelTab.TOYS -> labels.navToys
    TopLevelTab.TRIVIA -> labels.navTrivia
}

@Composable
fun NinetiesRewindApp(
    viewModel: NostalgiaViewModel
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val playbackState by viewModel.playbackState.collectAsStateWithLifecycle()
    val triviaState by viewModel.triviaState.collectAsStateWithLifecycle()

    val lang = uiState.appLanguage
    val labels = remember(lang) { LocalizedContentProvider.uiLabels(lang) }

    val localizedShows = remember(lang, viewModel.allShows) {
        viewModel.allShows.map { LocalizedContentProvider.localizeShow(it, lang) }
    }
    val localizedGames = remember(lang, viewModel.allGames) {
        viewModel.allGames.map { LocalizedContentProvider.localizeGame(it, lang) }
    }
    val localizedToys = remember(lang, viewModel.allToys) {
        viewModel.allToys.map { LocalizedContentProvider.localizeToy(it, lang) }
    }

    val isOnHome = currentDestination?.hasRoute<HomeRoute>() == true || currentDestination == null
    val activeTab: TopLevelTab? = when {
        currentDestination?.hasRoute<CartoonsRoute>() == true -> TopLevelTab.CARTOONS
        currentDestination?.hasRoute<TvShowsRoute>() == true -> TopLevelTab.TV_SHOWS
        currentDestination?.hasRoute<GamesRoute>() == true -> TopLevelTab.GAMES
        currentDestination?.hasRoute<ToysVaultRoute>() == true -> TopLevelTab.TOYS
        currentDestination?.hasRoute<TriviaRoute>() == true -> TopLevelTab.TRIVIA
        else -> null
    }

    fun navigateTopLevel(route: Any) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToCartoons() {
        viewModel.applyCartoonsTab()
        navController.navigate(CartoonsRoute) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = false
            }
            launchSingleTop = true
        }
    }

    fun navigateToTvShows(channelName: String? = null) {
        viewModel.applyTvShowsArgs(channelName = channelName)
        navController.navigate(TvShowsRoute(initialChannelName = channelName)) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = false
            }
            launchSingleTop = true
        }
    }

    fun navigateToTrivia(subTab: Int = 0) {
        viewModel.applyTriviaTab(subTab)
        navController.navigate(TriviaRoute(initialTab = subTab)) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = false
            }
            launchSingleTop = true
        }
    }

    fun handleTabClick(tab: TopLevelTab) {
        when (tab) {
            TopLevelTab.CARTOONS -> navigateToCartoons()
            TopLevelTab.TV_SHOWS -> navigateToTvShows(null)
            TopLevelTab.GAMES -> navigateTopLevel(GamesRoute)
            TopLevelTab.TOYS -> {
                viewModel.setShowVaultOnly(false)
                navigateTopLevel(ToysVaultRoute)
            }
            TopLevelTab.TRIVIA -> navigateToTrivia(0)
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val useRail = maxWidth >= 600.dp

        Scaffold(
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                RetroTopBar(
                    labels = labels,
                    appLanguage = lang,
                    isOnHome = isOnHome,
                    isDarkMode = uiState.crtDarkMode,
                    onHomeClick = { navigateTopLevel(HomeRoute) },
                    onToggleLanguage = viewModel::toggleAppLanguage,
                    onToggleDarkMode = { viewModel.setCrtDarkMode(!uiState.crtDarkMode) }
                )
            },
            bottomBar = {
                if (!useRail) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .navigationBarsPadding()
                    ) {
                        if (playbackState.activeClip != null) {
                            MiniThemePlayerBar(
                                playbackState = playbackState,
                                onTogglePlay = {
                                    playbackState.activeClip?.let(viewModel::toggleThemeClip)
                                },
                                onDismiss = viewModel::dismissThemePlayer,
                                lang = lang
                            )
                        }
                        HorizontalDivider(thickness = 2.dp, color = LocalRetroExtras.current.inkBorder)
                        NavigationBar(
                            windowInsets = WindowInsets(0, 0, 0, 0),
                            containerColor = MaterialTheme.colorScheme.surface,
                            tonalElevation = 3.dp,
                            modifier = Modifier.testTag("bottom_navigation_bar")
                        ) {
                            TopLevelTab.entries.forEach { tab ->
                                val selected = activeTab == tab
                                val tabLabel = tab.localizedLabel(labels)
                                NavigationBarItem(
                                    selected = selected,
                                    onClick = { handleTabClick(tab) },
                                    alwaysShowLabel = true,
                                    icon = {
                                        Icon(
                                            imageVector = if (selected) tab.selectedIcon else tab.unselectedIcon,
                                            contentDescription = tabLabel
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = tabLabel,
                                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                                            maxLines = 1,
                                            softWrap = false,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    },
                                    modifier = Modifier.testTag(tab.testTag),
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = Color.White,
                                        selectedTextColor = MaterialTheme.colorScheme.primary,
                                        indicatorColor = MaterialTheme.colorScheme.primary,
                                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                )
                            }
                        }
                    }
                }
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (useRail) {
                    NavigationRail(
                        containerColor = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .fillMaxHeight()
                            .testTag("navigation_rail")
                    ) {
                        Spacer(modifier = Modifier.weight(0.1f))
                        NavigationRailItem(
                            selected = isOnHome,
                            onClick = { navigateTopLevel(HomeRoute) },
                            icon = {
                                Icon(
                                    imageVector = if (isOnHome) Icons.Filled.Home else Icons.Outlined.Home,
                                    contentDescription = labels.navHome
                                )
                            },
                            label = {
                                Text(
                                    text = labels.navHome,
                                    style = MaterialTheme.typography.labelSmall,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            modifier = Modifier.testTag("rail_nav_home"),
                            colors = NavigationRailItemDefaults.colors(
                                selectedIconColor = Color.White,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                indicatorColor = MaterialTheme.colorScheme.primary
                            )
                        )
                        TopLevelTab.entries.forEach { tab ->
                            val selected = activeTab == tab
                            val tabLabel = tab.localizedLabel(labels)
                            NavigationRailItem(
                                selected = selected,
                                onClick = { handleTabClick(tab) },
                                icon = {
                                    Icon(
                                        imageVector = if (selected) tab.selectedIcon else tab.unselectedIcon,
                                        contentDescription = tabLabel
                                    )
                                },
                                label = {
                                    Text(
                                        text = tabLabel,
                                        style = MaterialTheme.typography.labelSmall,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                },
                                modifier = Modifier.testTag("rail_${tab.testTag}"),
                                colors = NavigationRailItemDefaults.colors(
                                    selectedIconColor = Color.White,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    indicatorColor = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }

                Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                    NavHost(
                        navController = navController,
                        startDestination = HomeRoute,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        composable<HomeRoute> {
                            HomeScreen(
                                uiState = uiState,
                                allShows = localizedShows,
                                allGames = localizedGames,
                                allToys = localizedToys,
                                onSearchChange = viewModel::updateSearchQuery,
                                onSearchFilterChange = viewModel::selectHomeSearchFilter,
                                onToggleFavorite = viewModel::toggleFavorite,
                                onChannelSelected = { channel ->
                                    navigateToTvShows(channelName = channel.name)
                                },
                                onNavigateToSuperheroes = {
                                    navigateToTrivia(subTab = 1)
                                },
                                onNavigateToTrivia = {
                                    navigateToTrivia(subTab = 0)
                                },
                                onNavigateToGames = { navigateTopLevel(GamesRoute) },
                                onNavigateToToys = {
                                    viewModel.setShowVaultOnly(true)
                                    navigateTopLevel(ToysVaultRoute)
                                },
                                onOpenDetail = viewModel::openDetail
                            )
                        }

                        composable<CartoonsRoute> {
                            ShowsAndHeroesScreen(
                                mode = ShowsSectionMode.CARTOONS,
                                uiState = uiState,
                                playbackState = playbackState,
                                allShows = localizedShows,
                                onChannelSelected = viewModel::selectChannel,
                                onCategorySelected = viewModel::selectShowCategory,
                                onToggleTamilOnly = viewModel::setTamilOnly,
                                onToggleFavorite = viewModel::toggleFavorite,
                                onToggleThemeClip = viewModel::toggleThemeClip,
                                onOpenDetail = viewModel::openDetail
                            )
                        }

                        composable<TvShowsRoute> { entry ->
                            val args = entry.toRoute<TvShowsRoute>()
                            LaunchedEffect(args.initialChannelName) {
                                if (args.initialChannelName != null) {
                                    viewModel.applyTvShowsArgs(channelName = args.initialChannelName)
                                }
                            }
                            ShowsAndHeroesScreen(
                                mode = ShowsSectionMode.TV_SHOWS,
                                uiState = uiState,
                                playbackState = playbackState,
                                allShows = localizedShows,
                                onChannelSelected = viewModel::selectChannel,
                                onCategorySelected = viewModel::selectShowCategory,
                                onToggleTamilOnly = viewModel::setTamilOnly,
                                onToggleFavorite = viewModel::toggleFavorite,
                                onToggleThemeClip = viewModel::toggleThemeClip,
                                onOpenDetail = viewModel::openDetail
                            )
                        }

                        composable<GamesRoute> {
                            VideoGamesScreen(
                                uiState = uiState,
                                allGames = localizedGames,
                                onPlatformSelected = viewModel::selectPlatform,
                                onToggleCheat = viewModel::toggleCheatReveal,
                                onToggleFavorite = viewModel::toggleFavorite,
                                onOpenDetail = viewModel::openDetail
                            )
                        }

                        composable<ToysVaultRoute> {
                            ToysAndVaultScreen(
                                uiState = uiState,
                                allToys = localizedToys,
                                allShows = localizedShows,
                                allGames = localizedGames,
                                onSelectToyCategory = viewModel::selectToyCategory,
                                onToggleTamilOnly = viewModel::setTamilOnly,
                                onToggleVaultOnly = viewModel::setShowVaultOnly,
                                onToggleFavorite = viewModel::toggleFavorite,
                                onToggleScanlines = viewModel::setCrtScanlines,
                                onToggleDarkMode = viewModel::setCrtDarkMode,
                                onOpenDetail = viewModel::openDetail
                            )
                        }

                        composable<TriviaRoute> { entry ->
                            val args = entry.toRoute<TriviaRoute>()
                            LaunchedEffect(args.initialTab) {
                                viewModel.applyTriviaTab(args.initialTab)
                            }
                            TriviaAndHeroesScreen(
                                uiState = uiState,
                                playbackState = playbackState,
                                triviaState = triviaState,
                                allShows = localizedShows,
                                onSubTabSelected = viewModel::setTriviaSubTab,
                                onToggleFavorite = viewModel::toggleFavorite,
                                onToggleThemeClip = viewModel::toggleThemeClip,
                                onToggleHeroTrivia = viewModel::toggleHeroTriviaExpand,
                                onOpenDetail = viewModel::openDetail,
                                onTriviaAnswer = viewModel::submitTriviaAnswer,
                                onTriviaNext = viewModel::nextTriviaQuestion,
                                onTriviaRestart = viewModel::restartTrivia
                            )
                        }
                    }

                    if (useRail && playbackState.activeClip != null) {
                        MiniThemePlayerBar(
                            playbackState = playbackState,
                            onTogglePlay = {
                                playbackState.activeClip?.let(viewModel::toggleThemeClip)
                            },
                            onDismiss = viewModel::dismissThemePlayer,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .navigationBarsPadding(),
                            lang = lang
                        )
                    }
                }
            }
        }
    }

    val activeDetail = uiState.activeDetail
    if (activeDetail != null) {
        val localizedDetail = remember(activeDetail, lang) {
            when (activeDetail) {
                is NostalgiaDetailItem.Show -> NostalgiaDetailItem.Show(
                    LocalizedContentProvider.localizeShow(activeDetail.item, lang)
                )
                is NostalgiaDetailItem.Game -> NostalgiaDetailItem.Game(
                    LocalizedContentProvider.localizeGame(activeDetail.item, lang)
                )
                is NostalgiaDetailItem.Toy -> NostalgiaDetailItem.Toy(
                    LocalizedContentProvider.localizeToy(activeDetail.item, lang)
                )
            }
        }
        NostalgiaDetailBottomSheet(
            detail = localizedDetail,
            favoriteIds = uiState.favoriteIds,
            playbackState = playbackState,
            appLanguage = lang,
            onToggleFavorite = viewModel::toggleFavorite,
            onToggleThemeClip = viewModel::toggleThemeClip,
            onDismiss = viewModel::closeDetail
        )
    }
}

@Composable
private fun RetroTopBar(
    labels: LocalizedUiLabels,
    appLanguage: AppLanguage,
    isOnHome: Boolean,
    isDarkMode: Boolean,
    onHomeClick: () -> Unit,
    onToggleLanguage: () -> Unit,
    onToggleDarkMode: () -> Unit
) {
    val extras = LocalRetroExtras.current
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    onClick = onHomeClick,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(2.dp, extras.inkBorder),
                    modifier = Modifier.minimumInteractiveComponentSize()
                ) {
                    Text(
                        text = "90s",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = labels.appName,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = labels.appSubtitle,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                if (!isOnHome) {
                    IconButton(
                        onClick = onHomeClick,
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("topbar_home_button")
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = labels.navHome,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                Surface(
                    onClick = onToggleLanguage,
                    color = if (appLanguage == AppLanguage.TAMIL) {
                        Color(0xFFD84315)
                    } else {
                        MaterialTheme.colorScheme.secondary
                    },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.5.dp, extras.inkBorder),
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .testTag("topbar_language_toggle")
                ) {
                    Text(
                        text = if (appLanguage == AppLanguage.TAMIL) "தமிழ்" else "EN",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                IconButton(
                    onClick = onToggleDarkMode,
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .testTag("topbar_theme_toggle")
                ) {
                    Icon(
                        imageVector = if (isDarkMode) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                        contentDescription = if (isDarkMode) "Switch to Retro Newsprint Light Mode" else "Switch to CRT Dark Mode",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            HorizontalDivider(thickness = 2.dp, color = extras.inkBorder)
        }
    }
}
