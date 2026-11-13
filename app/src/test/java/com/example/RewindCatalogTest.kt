package com.example

import com.example.data.GamesRepository
import com.example.data.ShowsRepository
import com.example.data.SuperheroesData
import com.example.data.ToysRepository
import com.example.data.TriviaRepository
import com.example.data.l10n.AppLanguage
import com.example.data.l10n.LocalizedContentProvider
import com.example.data.l10n.TamilGamesTranslations
import com.example.data.l10n.TamilShowsTranslations
import com.example.data.l10n.TamilToysAndTriviaTranslations
import com.example.model.GamePlatformType
import com.example.model.ShowCategory
import com.example.model.ToyCategory
import com.example.model.TvChannel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class RewindCatalogTest {

    @Test
    fun allSixIndianChannelsHaveShows() {
        TvChannel.entries.forEach { channel ->
            val count = ShowsRepository.allShows.count {
                it.primaryChannel == channel || channel in it.otherChannels
            }
            assertTrue("Channel ${channel.displayName} should have at least 1 show", count > 0)
        }
    }

    @Test
    fun allSuperheroShowsHaveTriviaAndThemeClips() {
        val heroes = ShowsRepository.allShows.filter { it.category == ShowCategory.SUPERHERO }
        assertTrue(heroes.size >= 8)
        heroes.forEach { hero ->
            assertTrue("${hero.title} must have trivia", hero.triviaFacts.isNotEmpty())
            val clip = hero.themeClip
            assertTrue("${hero.title} must have a theme clip", clip != null && clip.notes.isNotEmpty())
        }
        assertEquals(SuperheroesData.superheroShows.size, heroes.size)
    }

    @Test
    fun allVideoGamePlatformsAreCovered() {
        GamePlatformType.entries.forEach { platform ->
            val count = GamesRepository.allGames.count { it.platform == platform }
            assertTrue("Platform ${platform.displayName} should have games", count > 0)
        }
    }

    @Test
    fun toysAndTriviaHaveRichContent() {
        assertTrue(ToysRepository.allToys.size >= 15)
        assertTrue(TriviaRepository.questions.size >= 10)
        assertTrue(ShowsRepository.allShows.count { it.isTamilClassic } >= 10)
        assertTrue(ToysRepository.allToys.count { it.isTamilClassic } >= 8)
        TriviaRepository.questions.forEach { q ->
            assertTrue(q.correctIndex in q.options.indices)
        }
    }

    @Test
    fun allShowsGamesToysAndTriviaHaveCompleteTamilTranslations() {
        val tamilRegex = Regex(".*[\u0B80-\u0BFF]+.*")

        ShowsRepository.allShows.forEach { show ->
            val tr = TamilShowsTranslations.map[show.id]
            assertNotNull("Missing Tamil translation for show id=${show.id}", tr)
            assertTrue("Show title not Tamil for ${show.id}", tr!!.title.isNotBlank())
            assertTrue("Show synopsis not Tamil for ${show.id}", tamilRegex.matches(tr.synopsis))
            assertTrue("Show memory not Tamil for ${show.id}", tamilRegex.matches(tr.desiMemory))
            if (show.triviaFacts.isNotEmpty()) {
                assertEquals(
                    "Show trivia count mismatch for ${show.id}",
                    show.triviaFacts.size,
                    tr.triviaFacts.size
                )
                tr.triviaFacts.forEach { fact ->
                    assertTrue("Trivia fact not Tamil for ${show.id}", tamilRegex.matches(fact))
                }
            }
            val localized = LocalizedContentProvider.localizeShow(show, AppLanguage.TAMIL)
            assertEquals(tr.title, localized.title)
        }

        GamesRepository.allGames.forEach { game ->
            val tr = TamilGamesTranslations.map[game.id]
            assertNotNull("Missing Tamil translation for game id=${game.id}", tr)
            assertTrue("Game title blank for ${game.id}", tr!!.title.isNotBlank())
            assertTrue("Game details not Tamil for ${game.id}", tamilRegex.matches(tr.gameplayDetails))
            assertTrue("Game controls not Tamil for ${game.id}", tamilRegex.matches(tr.controlsAndTips))
            assertTrue("Game cheats not Tamil for ${game.id}", tamilRegex.matches(tr.cheatCodesOrSecrets))
            val localized = LocalizedContentProvider.localizeGame(game, AppLanguage.TAMIL)
            assertEquals(tr.title, localized.title)
        }

        ToysRepository.allToys.forEach { toy ->
            val tr = TamilToysAndTriviaTranslations.toyMap[toy.id]
            assertNotNull("Missing Tamil translation for toy id=${toy.id}", tr)
            assertTrue("Toy name not Tamil for ${toy.id}", tamilRegex.matches(tr!!.name))
            assertTrue("Toy howWePlayed not Tamil for ${toy.id}", tamilRegex.matches(tr.howWePlayed))
            val localized = LocalizedContentProvider.localizeToy(toy, AppLanguage.TAMIL)
            assertEquals(tr.name, localized.name)
        }

        TriviaRepository.questions.forEach { q ->
            val tr = TamilToysAndTriviaTranslations.triviaMap[q.id]
            assertNotNull("Missing Tamil translation for trivia id=${q.id}", tr)
            assertTrue("Trivia question not Tamil for ${q.id}", tamilRegex.matches(tr!!.question))
            assertEquals("Trivia options size mismatch for ${q.id}", q.options.size, tr.options.size)
            tr.options.forEach { opt ->
                assertTrue("Trivia option blank for ${q.id}", opt.isNotBlank())
            }
            assertTrue("Trivia explanation not Tamil for ${q.id}", tamilRegex.matches(tr.explanation))
            val localized = LocalizedContentProvider.localizeTrivia(q, AppLanguage.TAMIL)
            assertEquals(tr.question, localized.question)
        }

        TvChannel.entries.forEach { ch ->
            assertTrue(tamilRegex.matches(LocalizedContentProvider.channelName(ch, AppLanguage.TAMIL)))
            assertTrue(tamilRegex.matches(LocalizedContentProvider.channelEra(ch, AppLanguage.TAMIL)))
        }
        ShowCategory.entries.forEach { cat ->
            assertTrue(tamilRegex.matches(LocalizedContentProvider.showCategoryLabel(cat, AppLanguage.TAMIL)))
        }
        GamePlatformType.entries.forEach { plat ->
            assertTrue(LocalizedContentProvider.platformDisplayLabel(plat, AppLanguage.TAMIL).isNotBlank())
        }
        ToyCategory.entries.forEach { cat ->
            assertTrue(tamilRegex.matches(LocalizedContentProvider.toyCategoryLabel(cat, AppLanguage.TAMIL)))
        }
    }
}
