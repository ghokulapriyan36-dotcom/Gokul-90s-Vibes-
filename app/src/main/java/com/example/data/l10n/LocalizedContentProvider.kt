package com.example.data.l10n

import com.example.model.GamePlatformType
import com.example.model.ShowCategory
import com.example.model.ToyCategory
import com.example.model.ToyItem
import com.example.model.TriviaQuestion
import com.example.model.TvChannel
import com.example.model.TvShowItem
import com.example.model.VideoGameItem

enum class AppLanguage(val code: String, val chipLabel: String) {
    TAMIL("ta", "தமிழ்"),
    ENGLISH("en", "EN")
}

data class ShowTranslation(
    val title: String,
    val tagline: String,
    val yearsAired: String,
    val typicalSlot: String,
    val synopsis: String,
    val desiMemory: String,
    val catchphrase: String,
    val heroAlias: String? = null,
    val archNemesis: String? = null,
    val triviaFacts: List<String> = emptyList(),
    val themeTitle: String? = null,
    val themeLyricCue: String? = null
)

data class GameTranslation(
    val title: String,
    val genre: String,
    val players: String,
    val gameplayDetails: String,
    val controlsAndTips: String,
    val cheatCodesOrSecrets: String,
    val desiMemory: String
)

data class ToyTranslation(
    val name: String,
    val eraOrPrice: String,
    val howWePlayed: String,
    val nostalgiaStory: String,
    val funFact: String
)

data class TriviaTranslation(
    val showTitle: String,
    val channelTag: String,
    val question: String,
    val options: List<String>,
    val explanation: String
)

data class LocalizedUiLabels(
    val appName: String,
    val appSubtitle: String,
    val navHome: String,
    val navCartoons: String,
    val navTvShows: String,
    val navGames: String,
    val navToys: String,
    val navTrivia: String,
    val cartoonsHeader: String,
    val cartoonsSubtitle: String,
    val tvShowsHeader: String,
    val tvShowsSubtitle: String,
    val searchPlaceholder: String,
    val heroBadge: String,
    val heroHeadline: String,
    val heroSubtitle: String,
    val filterAll: String,
    val filterTamil90s: String,
    val filterShows: String,
    val filterGames: String,
    val filterToys: String,
    val triviaQuizCardTitle: String,
    val savedVaultCardTitle: String,
    val pinnedSuffix: String,
    val channelDialTitle: String,
    val channelDialSubtitle: String,
    val spotlightsTitle: String,
    val spotlightsSubtitle: String,
    val spotlightHeroTitle: String,
    val spotlightHeroDesc: String,
    val spotlightGameTitle: String,
    val spotlightGameDesc: String,
    val spotlightToyTitle: String,
    val spotlightToyDesc: String,
    val subtabAllShows: String,
    val subtabSuperheroes: String,
    val subtabTrivia: String,
    val allChannels: String,
    val tamilOnlyChip: String,
    val allTypesChip: String,
    val tvGuideHeader: String,
    val tvGuideSubtitle: String,
    val superheroBannerHeadline: String,
    val superheroBannerSubtitle: String,
    val startQuizCta: String,
    val playClip: String,
    val stopClip: String,
    val showTriviaLabel: String,
    val hideTriviaLabel: String,
    val secretIdentityPrefix: String,
    val archNemesisPrefix: String,
    val quizHeader: String,
    val quizSubtitle: String,
    val quizCompleteTitle: String,
    val quizRestartBtn: String,
    val nextQuestionBtn: String,
    val seeFinalScoreBtn: String,
    val gamesBannerHeadline: String,
    val gamesBannerSubtitle: String,
    val gamesHeader: String,
    val controlsTipPrefix: String,
    val unlockCheatBtn: String,
    val hideCheatBtn: String,
    val toysBannerHeadline: String,
    val toysBannerSubtitle: String,
    val allToysTab: String,
    val savedVaultTab: String,
    val toysHeader: String,
    val toysSubtitle: String,
    val crtSettingsTitle: String,
    val crtScanlinesTitle: String,
    val crtScanlinesDesc: String,
    val crtDarkModeTitle: String,
    val crtDarkModeDesc: String,
    val emptyVaultTitle: String,
    val emptyVaultHint: String,
    val emptySearchTitle: String,
    val emptySearchHint: String,
    val detailMemoryLabel: String,
    val detailTriviaLabel: String,
    val detailGameplayLabel: String,
    val detailControlsLabel: String,
    val detailCheatsLabel: String,
    val detailGamingMemoryLabel: String,
    val detailHowPlayedLabel: String,
    val detailFunFactLabel: String
)

object LocalizedContentProvider {

    fun uiLabels(lang: AppLanguage): LocalizedUiLabels = when (lang) {
        AppLanguage.TAMIL -> LocalizedUiLabels(
            appName = "90ஸ் ரீவைண்ட்",
            appSubtitle = "90கள் தமிழ் நினைவகம் • சன் டிவி, பொதிகை",
            navHome = "முகப்பு",
            navCartoons = "கார்ட்டூன்",
            navTvShows = "டிவி ஷோ",
            navGames = "கேம்ஸ்",
            navToys = "பொம்மை",
            navTrivia = "வினாவிடை",
            cartoonsHeader = "90ஸ் கார்ட்டூன்கள் & அனிமே ஹீரோக்கள்",
            cartoonsSubtitle = "சேனல் மற்றும் தமிழ் 90ஸ் வாரியாகக் கார்ட்டூன்களைப் பாருங்கள்",
            tvShowsHeader = "90ஸ் தமிழ் & இந்திய டிவி தொடர்கள்",
            tvShowsSubtitle = "சன் டிவி, டிடி பொதிகை, ராஜ் டிவி & பழைய தொலைக்காட்சி நிகழ்ச்சிகள்",
            searchPlaceholder = "சன் டிவி, மர்மதேசம், பம்பரம், கான்ட்ரா தேடுக...",
            heroBadge = "▶ VHS PLAY • தமிழ் 90ஸ் • 1990–1999",
            heroHeadline = "90ஸ் தமிழ் ரீவைண்ட்",
            heroSubtitle = "சன் டிவி, டிடி பொதிகை, மர்மதேசம், பம்பரம் & 8-பிட் கேம்ஸ்",
            filterAll = "அனைத்தும்",
            filterTamil90s = "தமிழ் 90ஸ்",
            filterShows = "கார்ட்டூன் & டிவி",
            filterGames = "கேம்ஸ்",
            filterToys = "பொம்மைகள்",
            triviaQuizCardTitle = "வினாடி-வினா",
            savedVaultCardTitle = "சேமிப்புப் பெட்டி",
            pinnedSuffix = "சேமித்தது",
            channelDialTitle = "90ஸ் தமிழ் & இந்திய டிவி சேனல்கள்",
            channelDialSubtitle = "சன் டிவி, பொதிகை, ராஜ் டிவி, விஜய் டிவி & பழைய சேனல்கள்",
            spotlightsTitle = "கால இயந்திர சிறப்புப் பகுதிகள்",
            spotlightsSubtitle = "சூப்பர் ஹீரோக்கள், தீம் இசை, கேம்ஸ் & தமிழ் தெரு விளையாட்டுகள்",
            spotlightHeroTitle = "சூப்பர் ஹீரோக்கள், தகவல்கள் & தீம் இசை",
            spotlightHeroDesc = "8-பிட் சிந்த் தீம் இசையைக் கேட்டு 90ஸ் ஹீரோ வினாடி-வினாவில் பங்கேற்கவும்!",
            spotlightGameTitle = "அனைத்து 90ஸ் வீடியோ கேம்ஸ் & சீட் கோட்கள்",
            spotlightGameDesc = "8-பிட் கார்ட்ரிட்ஜ், ₹2 பார்லர், SEGA & DOS கணினி விளையாட்டுகள்.",
            spotlightToyTitle = "90ஸ் தமிழ் பொம்மைகள், மிட்டாய் & விளையாட்டுகள்",
            spotlightToyDesc = "தமிழ்நாட்டுத் தெருக்களும் பள்ளி பெஞ்ச் நினைவுகளும் நிறைந்த பொக்கிஷங்கள்.",
            subtabAllShows = "கார்ட்டூன் & டிவி",
            subtabSuperheroes = "ஹீரோக்கள் & இசை",
            subtabTrivia = "வினாடி-வினா",
            allChannels = "அனைத்து சேனல்கள்",
            tamilOnlyChip = "தமிழ் 90ஸ் மட்டும்",
            allTypesChip = "அனைத்து வகை",
            tvGuideHeader = "90கள் தமிழ் & இந்திய டிவி வழிகாட்டி",
            tvGuideSubtitle = "சேனல் மற்றும் நிகழ்ச்சி வகை வாரியாக வடிகட்டவும்",
            superheroBannerHeadline = "90ஸ் சூப்பர் ஹீரோக்கள்",
            superheroBannerSubtitle = "பழைய தீம் இசை கிளிப்களைக் கேட்டு ஹீரோ தகவல்களை அறியுங்கள்!",
            startQuizCta = "90ஸ் தமிழ் & ஹீரோ வினாடி-வினா தொடங்கு",
            playClip = "இசை கேள்",
            stopClip = "நிறுத்து",
            showTriviaLabel = "ஹீரோ சுவாரசிய தகவல்கள்",
            hideTriviaLabel = "தகவல்களை மறை",
            secretIdentityPrefix = "மறைமுக அடையாளம்",
            archNemesisPrefix = "முக்கிய எதிரி",
            quizHeader = "90ஸ் சூப்பர் ஹீரோ & டிவி வினாடி-வினா",
            quizSubtitle = "சன் டிவி, பொதிகை & கார்ட்டூன் நெட்வொர்க் நினைவுகளைச் சோதியுங்கள்!",
            quizCompleteTitle = "வினாடி-வினா நிறைவடைந்தது!",
            quizRestartBtn = "மீண்டும் விளையாடு",
            nextQuestionBtn = "அடுத்த கேள்வி",
            seeFinalScoreBtn = "இறுதி மதிப்பெண் பார்",
            gamesBannerHeadline = "90ஸ் வீடியோ கேம்ஸ்",
            gamesBannerSubtitle = "பிரிக் கேம், 8-பிட் கார்ட்ரிட்ஜ், ₹2 ஆர்கேட், SEGA & DOS கேம்ஸ்",
            gamesHeader = "90கள் வீடியோ கேம்ஸ் களஞ்சியம்",
            controlsTipPrefix = "கன்ட்ரோல் & டிப்ஸ்",
            unlockCheatBtn = "சீட் கோட் & ரகசியம் திற",
            hideCheatBtn = "சீட் கோட் மறை",
            toysBannerHeadline = "90ஸ் பொம்மைகள் & நினைவுகள்",
            toysBannerSubtitle = "பம்பரம், கோலி, நுங்கு வண்டி, வாக்மேன் & சேமித்த பொக்கிஷங்கள்",
            allToysTab = "பொம்மைகள் & விளையாட்டுகள்",
            savedVaultTab = "என் சேமிப்புப் பெட்டி",
            toysHeader = "பம்பரம், டாசோஸ் & 90கள் பொக்கிஷங்கள்",
            toysSubtitle = "விளையாடிய விதத்தையும் நினைவுகளையும் பார்க்க தட்டவும்",
            crtSettingsTitle = "ரெட்ரோ CRT டிவி அமைப்புகள்",
            crtScanlinesTitle = "CRT தொலைக்காட்சி ஸ்கேன்லைன்ஸ்",
            crtScanlinesDesc = "பழைய டிவி திரைக்கோடு தோற்றம்",
            crtDarkModeTitle = "இரவு நேர CRT டார்க் மோட்",
            crtDarkModeDesc = "இரவு வாசிப்பிற்கான ஆழ்நீல CRT தீம்",
            emptyVaultTitle = "உங்கள் 90ஸ் பெட்டி காலியாக உள்ளது!",
            emptyVaultHint = "பிடித்த கார்ட்டூன், ஹீரோ, கேம் அல்லது பொம்மையில் உள்ள புக்மார்க் ஐகானைத் தட்டிச் சேமிக்கவும்.",
            emptySearchTitle = "90ஸ் சிக்னல் கிடைக்கவில்லை!",
            emptySearchHint = "ஆன்டெனாவைத் திருப்பி சன் டிவி, பொதிகை, மாரியோ அல்லது பம்பரம் எனத் தேடுங்கள்.",
            detailMemoryLabel = "90கள் பசுமை நினைவு",
            detailTriviaLabel = "சுவாரசிய தகவல்கள் & பின்னணி",
            detailGameplayLabel = "விளையாட்டு விவரங்கள்",
            detailControlsLabel = "கட்டுப்பாடுகள் & குறிப்புகள்",
            detailCheatsLabel = "ரகசிய சீட் கோட்கள்",
            detailGamingMemoryLabel = "90ஸ் தமிழ்நாடு கேமிங் நினைவு",
            detailHowPlayedLabel = "விளையாடிய விதம்",
            detailFunFactLabel = "உங்களுக்குத் தெரியுமா?"
        )
        AppLanguage.ENGLISH -> LocalizedUiLabels(
            appName = "90s Rewind",
            appSubtitle = "Tamil 90s Archive • Sun TV, Podhigai & More",
            navHome = "Home",
            navCartoons = "Cartoons",
            navTvShows = "TV Shows",
            navGames = "Games",
            navToys = "Toys",
            navTrivia = "Trivia",
            cartoonsHeader = "90s Cartoons & Animated Heroes",
            cartoonsSubtitle = "Browse classic 90s cartoons & animated heroes by channel",
            tvShowsHeader = "90s Tamil & Indian TV Shows",
            tvShowsSubtitle = "Sun TV, DD Podhigai, Raj TV, DD National & cult serials",
            searchPlaceholder = "Search Sun TV, Marmadesam, Pambaram, Contra...",
            heroBadge = "▶ VHS PLAY • TAMIL 90s • 1990–1999",
            heroHeadline = "90s TAMIL REWIND",
            heroSubtitle = "Sun TV, DD Podhigai, Marmadesam, Pambaram & 8-Bit Carts",
            filterAll = "All",
            filterTamil90s = "Tamil 90s",
            filterShows = "Cartoons & TV",
            filterGames = "Games",
            filterToys = "Toys",
            triviaQuizCardTitle = "Trivia Quiz",
            savedVaultCardTitle = "Saved Vault",
            pinnedSuffix = "pinned",
            channelDialTitle = "90s Tamil & Indian Channel Dial",
            channelDialSubtitle = "Sun TV, DD Podhigai, Raj TV, Vijay TV & all classic cable channels",
            spotlightsTitle = "Time Machine Spotlights",
            spotlightsSubtitle = "Superheroes, theme clips, video games & Tamil street treasures",
            spotlightHeroTitle = "Superheroes, Trivia & Theme Music",
            spotlightHeroDesc = "Play 8-bit synth theme clips & test your Tamil & 90s superhero knowledge!",
            spotlightGameTitle = "All 90s Video Games & Cheat Codes",
            spotlightGameDesc = "Classic games across 8-bit cartridges, ₹2 parlours, SEGA & DOS PCs.",
            spotlightToyTitle = "90s Tamil Toys, Mittai & Street Games",
            spotlightToyDesc = "Childhood treasures from Tamil Nadu streets and school benches.",
            subtabAllShows = "Cartoons & TV",
            subtabSuperheroes = "Superheroes & Themes",
            subtabTrivia = "Trivia Quiz",
            allChannels = "All Channels",
            tamilOnlyChip = "Tamil 90s Only",
            allTypesChip = "All Types",
            tvGuideHeader = "90s Tamil & Indian TV Guide",
            tvGuideSubtitle = "Filter by 90s TV channel & show type",
            superheroBannerHeadline = "90s SUPERHEROES",
            superheroBannerSubtitle = "Play classic theme music clips & uncover secret trivia!",
            startQuizCta = "Start 90s Tamil & Hero Quiz",
            playClip = "Play Clip",
            stopClip = "Stop",
            showTriviaLabel = "Show Superhero Trivia",
            hideTriviaLabel = "Hide Trivia Facts",
            secretIdentityPrefix = "Secret Identity",
            archNemesisPrefix = "Arch-Nemesis",
            quizHeader = "90s Superhero & TV Trivia",
            quizSubtitle = "Prove you grew up in the golden decade of Sun TV, DD Podhigai & CN!",
            quizCompleteTitle = "QUIZ COMPLETE!",
            quizRestartBtn = "Play Shuffled Quiz Again",
            nextQuestionBtn = "Next Question",
            seeFinalScoreBtn = "See Final Score",
            gamesBannerHeadline = "90s VIDEO GAMES",
            gamesBannerSubtitle = "Brick Game, 8-Bit Carts, ₹2 Arcades, SEGA & DOS Cyber Cafes",
            gamesHeader = "90s Video Games Encyclopedia",
            controlsTipPrefix = "Controls & Tip",
            unlockCheatBtn = "Unlock Cheat Code & Secret",
            hideCheatBtn = "Hide Secret / Cheat Code",
            toysBannerHeadline = "90s TOYS & MEMORIES",
            toysBannerSubtitle = "Pambaram, Goli, Nungu Vandi, Walkman & Your Pinned Vault",
            allToysTab = "All Toys & Treasures",
            savedVaultTab = "My Saved Vault",
            toysHeader = "Pambaram, Tazos & 90s Treasures",
            toysSubtitle = "Tap any 90s treasure to relive how we played",
            crtSettingsTitle = "Retro TV Display Settings",
            crtScanlinesTitle = "CRT Television Scanlines",
            crtScanlinesDesc = "Classic phosphor scanline overlay on hero TV screens",
            crtDarkModeTitle = "Late-Night CRT Dark Mode",
            crtDarkModeDesc = "Deep indigo phosphor theme for night-time browsing",
            emptyVaultTitle = "Your 90s Vault is empty!",
            emptyVaultHint = "Tap the bookmark icon on any Cartoon, Superhero, Video Game, or Toy to pin your favorites here.",
            emptySearchTitle = "No 90s signal found!",
            emptySearchHint = "Try adjusting the antenna or searching Sun TV, Podhigai, Mario, or Pambaram.",
            detailMemoryLabel = "90s Childhood Memory",
            detailTriviaLabel = "Trivia & Behind the Scenes",
            detailGameplayLabel = "Gameplay & Full Details",
            detailControlsLabel = "Controls & Pro Tips",
            detailCheatsLabel = "Secret & Cheat Codes",
            detailGamingMemoryLabel = "90s Tamil Nadu Gaming Memory",
            detailHowPlayedLabel = "How We Played",
            detailFunFactLabel = "Did You Know?"
        )
    }

    fun channelName(channel: TvChannel, lang: AppLanguage): String =
        if (lang == AppLanguage.ENGLISH) channel.displayName else when (channel) {
            TvChannel.SUN_TV -> "சன் டிவி"
            TvChannel.DD_PODHIGAI -> "டிடி பொதிகை (DD-5)"
            TvChannel.RAJ_TV -> "ராஜ் டிவி"
            TvChannel.VIJAY_TV -> "விஜய் டிவி"
            TvChannel.DD_NATIONAL -> "டிடி நேஷனல் (DD1)"
            TvChannel.CARTOON_NETWORK -> "கார்ட்டூன் நெட்வொர்க்"
            TvChannel.DD_METRO -> "டிடி மெட்ரோ (DD2)"
            TvChannel.STAR_PLUS -> "ஸ்டார் பிளஸ்"
            TvChannel.ZEE_TV -> "ஜீ டிவி"
            TvChannel.SONY_TV -> "சோனி டிவி"
        }

    fun channelEra(channel: TvChannel, lang: AppLanguage): String =
        if (lang == AppLanguage.ENGLISH) channel.eraNote else when (channel) {
            TvChannel.SUN_TV -> "90ஸ் தமிழ் குடும்பங்களின் ராஜா — மர்மதேசம் & உங்கள் சாய்ஸ்"
            TvChannel.DD_PODHIGAI -> "வெள்ளி ஒளியும் ஒலியும் & ஞாயிறு தமிழ் நிகழ்ச்சிகள்"
            TvChannel.RAJ_TV -> "பழைய தமிழ் படங்கள், கோப்பியம் & கார்ட்டூன்கள்"
            TvChannel.VIJAY_TV -> "90ஸ் தமிழ் இளையோர் நிகழ்ச்சிகள் & தொடர்கள்"
            TvChannel.DD_NATIONAL -> "ஞாயிறு 9 மணி மௌக்லி, சக்திமான் & மால்குடி டேஸ்"
            TvChannel.CARTOON_NETWORK -> "1995 முதல் 24 மணி நேர கார்ட்டூன் கொண்டாட்டம்"
            TvChannel.DD_METRO -> "90ஸ் நகைச்சுவை & மாலை நேர தொடர்கள்"
            TvChannel.STAR_PLUS -> "டிஸ்னி ஹவர், ஸ்பைடர்-மேன் & பவர் ரேஞ்சர்ஸ்"
            TvChannel.ZEE_TV -> "ஹம் பாஞ்ச் & ஹிப் ஹிப் ஹுரே"
            TvChannel.SONY_TV -> "ஆஹட் திகில் & பூகி வூகி நடனம்"
        }

    fun showCategoryLabel(category: ShowCategory, lang: AppLanguage): String =
        if (lang == AppLanguage.ENGLISH) category.label else when (category) {
            ShowCategory.CARTOON -> "கார்ட்டூன்கள்"
            ShowCategory.TV_SERIAL -> "டிவி தொடர்கள்"
            ShowCategory.SUPERHERO -> "சூப்பர் ஹீரோக்கள்"
        }

    fun platformShortLabel(platform: GamePlatformType, lang: AppLanguage): String =
        if (lang == AppLanguage.ENGLISH) platform.shortTag else when (platform) {
            GamePlatformType.HANDHELD_BRICK -> "9999-IN-1"
            GamePlatformType.EIGHT_BIT_CARTRIDGE -> "8-பிட் கார்ட்"
            GamePlatformType.ARCADE_COIN -> "₹2 பார்லர்"
            GamePlatformType.SIXTEEN_BIT_SEGA -> "16-பிட்"
            GamePlatformType.PC_DOS_WIN95 -> "PC / DOS"
            GamePlatformType.PLAYSTATION_1 -> "PS1"
        }

    fun platformDisplayLabel(platform: GamePlatformType, lang: AppLanguage): String =
        if (lang == AppLanguage.ENGLISH) platform.displayName else when (platform) {
            GamePlatformType.HANDHELD_BRICK -> "பிரிக் கேம் கையடக்க LCD"
            GamePlatformType.EIGHT_BIT_CARTRIDGE -> "8-பிட் கார்ட்ரிட்ஜ் (சமுராய்)"
            GamePlatformType.ARCADE_COIN -> "₹1 / ₹2 காயின் கேம் பார்லர்"
            GamePlatformType.SIXTEEN_BIT_SEGA -> "16-பிட் SEGA மெகா டிரைவ்"
            GamePlatformType.PC_DOS_WIN95 -> "MS-DOS / Windows 95-98 PC"
            GamePlatformType.PLAYSTATION_1 -> "சோனி பிளேஸ்டேஷன் (PS1)"
        }

    fun toyCategoryLabel(category: ToyCategory, lang: AppLanguage): String =
        if (lang == AppLanguage.ENGLISH) category.displayName else when (category) {
            ToyCategory.STREET_GAMES -> "தமிழ் தெரு விளையாட்டு"
            ToyCategory.COLLECTIBLES -> "டாசோஸ் & கார்ட்ஸ்"
            ToyCategory.RETRO_TECH -> "90ஸ் டேப் & வாக்மேன்"
            ToyCategory.CANDY_SCHOOL -> "மிட்டாய் & பள்ளிப் பை"
            ToyCategory.DESK_TOYS -> "கைவிளையாட்டுப் பொருட்கள்"
        }

    fun localizeShow(show: TvShowItem, lang: AppLanguage): TvShowItem {
        if (lang == AppLanguage.ENGLISH) return show
        val tr = TamilShowsTranslations.map[show.id] ?: return show
        return show.copy(
            title = tr.title,
            taglineOrHindiTitle = tr.tagline,
            yearsAired = tr.yearsAired,
            typicalSlot = tr.typicalSlot,
            synopsis = tr.synopsis,
            desiMemory = tr.desiMemory,
            catchphrase = tr.catchphrase,
            heroAlias = tr.heroAlias ?: show.heroAlias,
            archNemesis = tr.archNemesis ?: show.archNemesis,
            triviaFacts = if (tr.triviaFacts.isNotEmpty()) tr.triviaFacts else show.triviaFacts,
            themeClip = show.themeClip?.copy(
                title = tr.themeTitle ?: show.themeClip.title,
                showTitle = tr.title,
                lyricCue = tr.themeLyricCue ?: show.themeClip.lyricCue
            )
        )
    }

    fun localizeGame(game: VideoGameItem, lang: AppLanguage): VideoGameItem {
        if (lang == AppLanguage.ENGLISH) return game
        val tr = TamilGamesTranslations.map[game.id] ?: return game
        return game.copy(
            title = tr.title,
            genre = tr.genre,
            players = tr.players,
            gameplayDetails = tr.gameplayDetails,
            controlsAndTips = tr.controlsAndTips,
            cheatCodesOrSecrets = tr.cheatCodesOrSecrets,
            desiMemory = tr.desiMemory
        )
    }

    fun localizeToy(toy: ToyItem, lang: AppLanguage): ToyItem {
        if (lang == AppLanguage.ENGLISH) return toy
        val tr = TamilToysAndTriviaTranslations.toyMap[toy.id] ?: return toy
        return toy.copy(
            name = tr.name,
            eraOrPrice = tr.eraOrPrice,
            howWePlayed = tr.howWePlayed,
            nostalgiaStory = tr.nostalgiaStory,
            funFact = tr.funFact
        )
    }

    fun localizeTrivia(question: TriviaQuestion, lang: AppLanguage): TriviaQuestion {
        if (lang == AppLanguage.ENGLISH) return question
        val tr = TamilToysAndTriviaTranslations.triviaMap[question.id] ?: return question
        return question.copy(
            showTitle = tr.showTitle,
            channelTag = tr.channelTag,
            question = tr.question,
            options = tr.options,
            explanation = tr.explanation
        )
    }

    fun bestScoreLabel(best: Int, total: Int, lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) "சிறந்த மதிப்பெண்: $best/$total" else "Best: $best/$total"

    fun showsCountLabel(count: Int, lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) "$count நிகழ்ச்சிகள்" else "$count shows"

    fun searchResultsTitle(count: Int, lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) "தேடல் முடிவுகள் ($count)" else "Archive Results ($count)"

    fun searchResultsSubtitle(filterLabel: String, query: String, lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) {
            if (query.isNotBlank()) "\"$query\" — $filterLabel முடிவுகள்" else "$filterLabel — அனைத்துப் பதிவுகள்"
        } else {
            if (query.isNotBlank()) "Matching ${filterLabel.lowercase()} for \"$query\"" else "Filtering all $filterLabel"
        }

    fun spotlightGamesDescription(count: Int, lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) {
            "$count பழைய 8-பிட் கார்ட்ரிட்ஜ், ₹2 பார்லர், SEGA & DOS கணினி விளையாட்டுகள்."
        } else {
            "$count classic games across 8-bit cartridges, ₹2 parlours, SEGA & DOS PCs."
        }

    fun spotlightToysDescription(count: Int, lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) {
            "$count தமிழ்நாட்டுத் தெரு விளையாட்டுகள், மிட்டாய்கள் & பள்ளி நினைவுகள்."
        } else {
            "$count childhood treasures from Tamil Nadu streets and school benches."
        }

    fun noShowsOnFrequencyTitle(lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) "இந்த அலைவரிசையில் நிகழ்ச்சிகள் இல்லை!" else "No shows on this frequency!"

    fun noShowsOnFrequencyHint(lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) "மேலே உள்ள 'அனைத்து சேனல்கள்' அல்லது 'அனைத்து வகை' என்பதைத் தேர்ந்தெடுக்கவும்." else "Try selecting 'All Channels' or 'All Types' above."

    fun gamesShowingSubtitle(count: Int, lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) {
            "$count விளையாட்டுகள் • முழு விவரம் & கன்ட்ரோல்களுக்குத் தட்டவும்"
        } else {
            "Showing $count games • Tap any game for full controls & details"
        }

    fun quizScoreLabel(score: Int, total: Int, lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) "மதிப்பெண்: $score/$total" else "SCORE: $score/$total"

    fun quizAllTimeBestLabel(best: Int, total: Int, lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) "அதிகபட்ச மதிப்பெண்: $best/$total" else "All-Time Best: $best/$total"

    fun quizStreakLabel(streak: Int, lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) "தொடர் வெற்றி: $streak" else "Streak: $streak"

    fun quizResultScoreText(score: Int, total: Int, lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) "$total கேள்விகளில் $score மதிப்பெண்கள் பெற்றீர்கள்" else "You scored $score out of $total"

    fun quizRankLabel(score: Int, lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) {
            when {
                score >= 10 -> "பட்டம்: 90ஸ் சன் டிவி & சக்திமான் ஜாம்பவான்!"
                score >= 7 -> "பட்டம்: ஞாயிறு காலை கார்ட்டூன் நிபுணர்"
                else -> "பட்டம்: 90ஸ் கேபிள் டிவி ஆரம்ப வீரர் — மீண்டும் பாருங்கள்!"
            }
        } else {
            when {
                score >= 10 -> "Rank: Supreme 90s Legend (Shaktimaan Tier!)"
                score >= 7 -> "Rank: Sunday Morning Cartoon Pro"
                else -> "Rank: Cable TV Rookie — Time for a 90s Rewatch!"
            }
        }

    fun miniPlayerStatus(isPlaying: Boolean, noteLabel: String, lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) {
            if (isPlaying) "8-பிட் சிந்த் இசைகிறது [$noteLabel]" else "நிறுத்தப்பட்டது • மீண்டும் கேட்க தட்டவும்"
        } else {
            if (isPlaying) "Playing 8-Bit Synth [$noteLabel]" else "Paused • Tap play to replay theme"
        }

    fun crtSettingsSubtitle(lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) "உங்கள் 90ஸ் CRT திரைத் தோற்றத்தை அமைக்கவும்" else "Customize your 90s CRT look (saved automatically)"

    fun vaultSectionSubtitle(lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) "நீங்கள் சேமித்த நிகழ்ச்சிகள், கேம்ஸ் & பொம்மைகள்" else "All shows, video games & toys you bookmarked"

    fun tamilClassicBadge(lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) "தமிழ் 90ஸ்" else "TAMIL 90s"

    fun inlineThemeLabel(isPlaying: Boolean, lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) {
            if (isPlaying) "நிறுத்து" else "இசை"
        } else {
            if (isPlaying) "Stop" else "Theme"
        }

    fun sheetPlayThemeLabel(isPlaying: Boolean, clipTitle: String, lang: AppLanguage): String =
        if (lang == AppLanguage.TAMIL) {
            if (isPlaying) "நிறுத்து: $clipTitle" else "தீம் இசை கேள்: $clipTitle"
        } else {
            if (isPlaying) "Stop $clipTitle" else "Play Classic Theme: $clipTitle"
        }
}
