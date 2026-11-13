package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.RetroSynthPlayer
import com.example.audio.SynthPlaybackState
import com.example.data.FavoritesPreferences
import com.example.data.GamesRepository
import com.example.data.ShowsRepository
import com.example.data.ToysRepository
import com.example.data.TriviaRepository
import com.example.data.l10n.AppLanguage
import com.example.model.GamePlatformType
import com.example.model.NostalgiaDetailItem
import com.example.model.ShowCategory
import com.example.model.ThemeMusicClip
import com.example.model.ToyCategory
import com.example.model.TriviaQuestion
import com.example.model.TvChannel
import com.example.model.TvShowItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TriviaQuizState(
    val questions: List<TriviaQuestion> = TriviaRepository.questions,
    val currentIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val score: Int = 0,
    val streak: Int = 0,
    val isFinished: Boolean = false
) {
    val currentQuestion: TriviaQuestion?
        get() = questions.getOrNull(currentIndex)
}

enum class HomeSearchFilter(val label: String) {
    ALL("All"),
    TAMIL("Tamil 90s"),
    SHOWS("Cartoons & TV"),
    GAMES("Games"),
    TOYS("Toys")
}

data class NostalgiaUiState(
    val searchQuery: String = "",
    val homeSearchFilter: HomeSearchFilter = HomeSearchFilter.ALL,
    val tamilOnly: Boolean = false,
    val selectedChannel: TvChannel? = null,
    val selectedShowCategory: ShowCategory? = null,
    val showsSubTab: Int = 0, // 0 = All Cartoons & TV, 1 = Superheroes & Themes, 2 = Trivia Quiz
    val triviaSubTab: Int = 0, // 0 = Trivia Quiz, 1 = Superheroes & Themes
    val selectedPlatform: GamePlatformType? = null,
    val selectedToyCategory: ToyCategory? = null,
    val showVaultOnly: Boolean = false,
    val revealedCheatGameIds: Set<String> = emptySet(),
    val expandedHeroTriviaIds: Set<String> = emptySet(),
    val favoriteIds: Set<String> = emptySet(),
    val crtScanlinesEnabled: Boolean = true,
    val crtDarkMode: Boolean = false,
    val bestTriviaScore: Int = 0,
    val appLanguage: AppLanguage = AppLanguage.TAMIL,
    val activeDetail: NostalgiaDetailItem? = null
)

private data class PrefsSnapshot(
    val favoriteIds: Set<String>,
    val crtScanlinesEnabled: Boolean,
    val crtDarkMode: Boolean,
    val bestTriviaScore: Int,
    val appLanguage: AppLanguage
)

class NostalgiaViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = FavoritesPreferences(application.applicationContext)
    private val synthPlayer = RetroSynthPlayer()

    val playbackState: StateFlow<SynthPlaybackState> = synthPlayer.state

    private val _localState = MutableStateFlow(NostalgiaUiState())

    private val prefsFlow = combine(
        prefs.favoriteIds,
        prefs.crtScanlinesEnabled,
        prefs.crtDarkMode,
        prefs.bestTriviaScore,
        prefs.appLanguage
    ) { favorites, scanlines, darkMode, bestScore, language ->
        PrefsSnapshot(
            favoriteIds = favorites,
            crtScanlinesEnabled = scanlines,
            crtDarkMode = darkMode,
            bestTriviaScore = bestScore,
            appLanguage = language
        )
    }

    val uiState: StateFlow<NostalgiaUiState> = combine(
        _localState,
        prefsFlow
    ) { local, snap ->
        local.copy(
            favoriteIds = snap.favoriteIds,
            crtScanlinesEnabled = snap.crtScanlinesEnabled,
            crtDarkMode = snap.crtDarkMode,
            bestTriviaScore = snap.bestTriviaScore,
            appLanguage = snap.appLanguage
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = NostalgiaUiState()
    )

    private val _triviaState = MutableStateFlow(TriviaQuizState())
    val triviaState: StateFlow<TriviaQuizState> = _triviaState.asStateFlow()

    val allShows: List<TvShowItem> = ShowsRepository.allShows
    val allGames = GamesRepository.allGames
    val allToys = ToysRepository.allToys

    fun updateSearchQuery(query: String) {
        _localState.update { it.copy(searchQuery = query) }
    }

    fun selectHomeSearchFilter(filter: HomeSearchFilter) {
        _localState.update { it.copy(homeSearchFilter = filter) }
    }

    fun setTamilOnly(enabled: Boolean) {
        _localState.update { it.copy(tamilOnly = enabled) }
    }

    fun selectChannel(channel: TvChannel?) {
        _localState.update { it.copy(selectedChannel = channel) }
    }

    fun selectShowCategory(category: ShowCategory?) {
        _localState.update { it.copy(selectedShowCategory = category) }
    }

    fun setShowsSubTab(tabIndex: Int) {
        _localState.update { it.copy(showsSubTab = tabIndex) }
    }

    fun setTriviaSubTab(tabIndex: Int) {
        _localState.update { it.copy(triviaSubTab = tabIndex) }
    }

    fun applyTvShowsArgs(channelName: String?) {
        val parsedChannel = channelName?.let { name ->
            TvChannel.entries.firstOrNull { it.name == name }
        }
        _localState.update {
            it.copy(
                selectedChannel = parsedChannel,
                selectedShowCategory = if (parsedChannel != null) null else ShowCategory.TV_SERIAL
            )
        }
    }

    fun applyCartoonsTab() {
        _localState.update {
            it.copy(
                selectedChannel = null,
                selectedShowCategory = ShowCategory.CARTOON
            )
        }
    }

    fun applyTriviaTab(tabIndex: Int) {
        _localState.update { it.copy(triviaSubTab = tabIndex) }
    }

    fun selectPlatform(platform: GamePlatformType?) {
        _localState.update { it.copy(selectedPlatform = platform) }
    }

    fun selectToyCategory(category: ToyCategory?) {
        _localState.update { it.copy(selectedToyCategory = category) }
    }

    fun setShowVaultOnly(vaultOnly: Boolean) {
        _localState.update { it.copy(showVaultOnly = vaultOnly) }
    }

    fun toggleCheatReveal(gameId: String) {
        _localState.update { state ->
            val updated = if (gameId in state.revealedCheatGameIds) {
                state.revealedCheatGameIds - gameId
            } else {
                state.revealedCheatGameIds + gameId
            }
            state.copy(revealedCheatGameIds = updated)
        }
    }

    fun toggleHeroTriviaExpand(showId: String) {
        _localState.update { state ->
            val updated = if (showId in state.expandedHeroTriviaIds) {
                state.expandedHeroTriviaIds - showId
            } else {
                state.expandedHeroTriviaIds + showId
            }
            state.copy(expandedHeroTriviaIds = updated)
        }
    }

    fun openDetail(item: NostalgiaDetailItem) {
        _localState.update { it.copy(activeDetail = item) }
    }

    fun closeDetail() {
        _localState.update { it.copy(activeDetail = null) }
    }

    fun toggleFavorite(itemId: String) {
        viewModelScope.launch {
            prefs.toggleFavorite(itemId)
        }
    }

    fun setCrtScanlines(enabled: Boolean) {
        viewModelScope.launch {
            prefs.setCrtScanlines(enabled)
        }
    }

    fun setCrtDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            prefs.setCrtDarkMode(enabled)
        }
    }

    fun setAppLanguage(language: AppLanguage) {
        viewModelScope.launch {
            prefs.setAppLanguage(language)
        }
    }

    fun toggleAppLanguage() {
        val next = if (uiState.value.appLanguage == AppLanguage.TAMIL) {
            AppLanguage.ENGLISH
        } else {
            AppLanguage.TAMIL
        }
        setAppLanguage(next)
    }

    fun toggleThemeClip(clip: ThemeMusicClip) {
        synthPlayer.togglePlay(clip)
    }

    fun stopThemeClip() {
        synthPlayer.stop()
    }

    fun dismissThemePlayer() {
        synthPlayer.dismiss()
    }

    fun submitTriviaAnswer(optionIndex: Int) {
        val current = _triviaState.value
        val question = current.currentQuestion ?: return
        if (current.selectedOptionIndex != null) return

        val isCorrect = optionIndex == question.correctIndex
        val newScore = if (isCorrect) current.score + 1 else current.score
        val newStreak = if (isCorrect) current.streak + 1 else 0

        _triviaState.value = current.copy(
            selectedOptionIndex = optionIndex,
            score = newScore,
            streak = newStreak
        )
        viewModelScope.launch {
            prefs.updateBestTriviaScore(newScore)
        }
    }

    fun nextTriviaQuestion() {
        val current = _triviaState.value
        val nextIdx = current.currentIndex + 1
        if (nextIdx >= current.questions.size) {
            _triviaState.value = current.copy(isFinished = true)
            viewModelScope.launch {
                prefs.updateBestTriviaScore(current.score)
            }
        } else {
            _triviaState.value = current.copy(
                currentIndex = nextIdx,
                selectedOptionIndex = null
            )
        }
    }

    fun restartTrivia() {
        _triviaState.value = TriviaQuizState(
            questions = TriviaRepository.questions.shuffled()
        )
    }

    override fun onCleared() {
        super.onCleared()
        synthPlayer.release()
    }
}
