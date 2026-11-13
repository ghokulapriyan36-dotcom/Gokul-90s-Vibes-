package com.example.data

import com.example.model.ShowCategory
import com.example.model.SynthNote
import com.example.model.SynthWaveform
import com.example.model.ThemeMusicClip
import com.example.model.TvChannel
import com.example.model.TvShowItem

object SuperheroesData {

    val superheroShows: List<TvShowItem> = listOf(
        TvShowItem(
            id = "hero_shaktimaan",
            title = "Shaktimaan (Tamil & Hindi)",
            taglineOrHindiTitle = "சக்திமான் • Sunday Noon DD Podhigai / DD1",
            primaryChannel = TvChannel.DD_NATIONAL,
            otherChannels = listOf(TvChannel.DD_PODHIGAI),
            yearsAired = "1997–2005 (Peak 90s Craze: 1997–99)",
            typicalSlot = "Sundays 12:00 PM IST",
            category = ShowCategory.SUPERHERO,
            isTamilClassic = true,
            synopsis = "India's first homegrown television superhero! Photoreporter Pandit Gangadhar Vidyadhar Mayadhar Omkarnath Shastri spins upward into Shaktimaan, powered by seven Kundalini chakras to battle Tamraj Kilvish.",
            desiMemory = "In Tamil Nadu homes too, kids spun around the living room shouting 'Shaktimaaaan!' every Sunday afternoon after lunch, waiting for 'Sorry Shaktimaan'!",
            catchphrase = "Andhera Kayam Rahe! / Sorry Shaktimaan!",
            heroAlias = "Pandit Gangadhar Shastri (Mukesh Khanna)",
            archNemesis = "Tamraj Kilvish & Dr. Jackal ('Power!')",
            triviaFacts = listOf(
                "Aired in Tamil dub on Doordarshan Podhigai as well as Hindi on DD National!",
                "Every episode ended with 'Chhoti Magar Moti Baatein' teaching kids safety and honesty.",
                "Gangadhar's full name was 'Pandit Gangadhar Vidyadhar Mayadhar Omkarnath Shastri'.",
                "Dr. Jackal's famous catchphrase was shouting 'Power!' while shaking his fists."
            ),
            themeClip = ThemeMusicClip(
                id = "clip_shaktimaan",
                title = "Shaktimaan Chakra Fanfare",
                showTitle = "Shaktimaan",
                waveform = SynthWaveform.SAWTOOTH,
                durationLabel = "0:09",
                tempoBpm = 132,
                lyricCue = "♪ Adbhut adamya sahas ki paribhasha hai... Shaktimaan! ♪",
                notes = listOf(
                    SynthNote(293.66f, 180, 146.83f, "D4"),
                    SynthNote(349.23f, 180, 174.61f, "F4"),
                    SynthNote(440.00f, 320, 220.00f, "A4"),
                    SynthNote(392.00f, 160, 196.00f, "G4"),
                    SynthNote(440.00f, 160, 220.00f, "A4"),
                    SynthNote(523.25f, 380, 261.63f, "C5"),
                    SynthNote(0f, 90, 0f, "Rest"),
                    SynthNote(587.33f, 220, 293.66f, "D5"),
                    SynthNote(523.25f, 180, 261.63f, "C5"),
                    SynthNote(440.00f, 220, 220.00f, "A4"),
                    SynthNote(392.00f, 200, 196.00f, "G4"),
                    SynthNote(440.00f, 420, 220.00f, "A4!"),
                    SynthNote(0f, 80, 0f, "Rest"),
                    SynthNote(587.33f, 260, 293.66f, "SHAK"),
                    SynthNote(659.25f, 260, 329.63f, "TI"),
                    SynthNote(587.33f, 520, 293.66f, "MAAN!")
                )
            )
        ),
        TvShowItem(
            id = "hero_captain_vyom",
            title = "Captain Vyom",
            taglineOrHindiTitle = "கேப்டன் வியோம் • Space Hero of 2123 AD",
            primaryChannel = TvChannel.DD_NATIONAL,
            otherChannels = listOf(TvChannel.DD_PODHIGAI),
            yearsAired = "1998–1999",
            typicalSlot = "Sundays 11:00 AM IST",
            category = ShowCategory.SUPERHERO,
            isTamilClassic = true,
            synopsis = "Set in 2123 AD, yogic space warrior Captain Vyom (Milind Soman) pilots spaceship Ulka with Maya, Paras, Sonic, and Surya to recapture 12 escaped super-criminals from Io prison.",
            desiMemory = "Sunday 11 AM right before Shaktimaan—Tamil & Indian kids watched Milind Soman battle Kaal and Maya in silver space armor!",
            catchphrase = "Vishwa Rakshak Captain Vyom!",
            heroAlias = "Vyom (Son of Vikrant)",
            archNemesis = "Kaal, Maya, Moha & Krodh (12 Space Criminals)",
            triviaFacts = listOf(
                "Directed by Ketan Mehta, it was India's most ambitious 90s sci-fi TV production.",
                "The 12 villains were named after human flaws: Kaal (Time), Maya (Illusion), Moha (Greed).",
                "Captain Vyom gained superhuman focus from ancient Himalayan yoga!"
            ),
            themeClip = ThemeMusicClip(
                id = "clip_captain_vyom",
                title = "Ulka Hyperdrive Theme",
                showTitle = "Captain Vyom",
                waveform = SynthWaveform.SQUARE,
                durationLabel = "0:08",
                tempoBpm = 140,
                lyricCue = "♪ Space-age laser synth across the solar system ♪",
                notes = listOf(
                    SynthNote(329.63f, 150, 164.81f, "E4"),
                    SynthNote(329.63f, 150, 164.81f, "E4"),
                    SynthNote(493.88f, 280, 246.94f, "B4"),
                    SynthNote(440.00f, 180, 220.00f, "A4"),
                    SynthNote(392.00f, 180, 196.00f, "G4"),
                    SynthNote(440.00f, 340, 220.00f, "A4"),
                    SynthNote(329.63f, 160, 164.81f, "E4"),
                    SynthNote(392.00f, 160, 196.00f, "G4"),
                    SynthNote(493.88f, 200, 246.94f, "B4"),
                    SynthNote(587.33f, 240, 293.66f, "D5"),
                    SynthNote(659.25f, 480, 329.63f, "E5!")
                )
            )
        ),
        TvShowItem(
            id = "hero_he_man",
            title = "He-Man & Masters of the Universe",
            taglineOrHindiTitle = "ஹீ-மேன் • By the Power of Grayskull!",
            primaryChannel = TvChannel.DD_PODHIGAI,
            otherChannels = listOf(TvChannel.DD_NATIONAL, TvChannel.CARTOON_NETWORK),
            yearsAired = "1990–1997",
            typicalSlot = "Sundays / Morning Cartoons",
            category = ShowCategory.SUPERHERO,
            isTamilClassic = true,
            synopsis = "Prince Adam raises his Magic Sword at Castle Grayskull to become He-Man, the strongest warrior in Eternia, turning his timid tiger Cringer into Battle Cat.",
            desiMemory = "One of the very first cartoons dubbed and loved in Tamil Nadu! Lifting a plastic cricket bat and shouting 'I HAVE THE POWER!'",
            catchphrase = "By the power of Grayskull... I HAVE THE POWER!",
            heroAlias = "Prince Adam of Eternia",
            archNemesis = "Skeletor",
            triviaFacts = listOf(
                "He-Man always delivered a short moral lesson to kids at the end of each episode.",
                "Skeletor's high-pitched cackle made him an instant favorite villain."
            ),
            themeClip = ThemeMusicClip(
                id = "clip_he_man",
                title = "Power of Grayskull Brass",
                showTitle = "He-Man",
                waveform = SynthWaveform.SQUARE,
                durationLabel = "0:08",
                tempoBpm = 136,
                lyricCue = "♪ By the power of Grayskull... I have the power! ♪",
                notes = listOf(
                    SynthNote(261.63f, 180, 130.81f, "C4"),
                    SynthNote(392.00f, 240, 196.00f, "G4"),
                    SynthNote(349.23f, 180, 174.61f, "F4"),
                    SynthNote(392.00f, 300, 196.00f, "G4"),
                    SynthNote(440.00f, 200, 220.00f, "A4"),
                    SynthNote(392.00f, 200, 196.00f, "G4"),
                    SynthNote(523.25f, 440, 261.63f, "C5!")
                )
            )
        ),
        TvShowItem(
            id = "hero_spiderman_1994",
            title = "Spider-Man: The Animated Series",
            taglineOrHindiTitle = "ஸ்பைடர்-மேன் • Star Plus / Cartoon Network",
            primaryChannel = TvChannel.STAR_PLUS,
            otherChannels = listOf(TvChannel.CARTOON_NETWORK),
            yearsAired = "1995–1999",
            typicalSlot = "Evening Kids Action Block",
            category = ShowCategory.SUPERHERO,
            isTamilClassic = true,
            synopsis = "College student Peter Parker battles Venom, Green Goblin, Doc Ock, and Kingpin in New York while protecting Aunt May.",
            desiMemory = "Drawing Spider-Man webs on the back of Tamil classwork notebooks after watching Venom on TV!",
            catchphrase = "My Spider-Sense is tingling!",
            heroAlias = "Peter Parker",
            archNemesis = "Venom, Green Goblin & Kingpin",
            triviaFacts = listOf(
                "The 3-part Black Symbiote / Venom arc was the biggest schoolyard topic of the late 90s.",
                "Due to 90s broadcast rules, Spider-Man webbed villains instead of throwing punches."
            ),
            themeClip = ThemeMusicClip(
                id = "clip_spiderman_94",
                title = "Radioactive Web Riff",
                showTitle = "Spider-Man (1994)",
                waveform = SynthWaveform.SAWTOOTH,
                durationLabel = "0:08",
                tempoBpm = 148,
                lyricCue = "♪ Spider-Man, Spider-Man, radioactive Spider-Man! ♪",
                notes = listOf(
                    SynthNote(293.66f, 160, 146.83f, "D4"),
                    SynthNote(349.23f, 160, 174.61f, "F4"),
                    SynthNote(440.00f, 280, 220.00f, "A4"),
                    SynthNote(415.30f, 180, 207.65f, "G#4"),
                    SynthNote(349.23f, 180, 174.61f, "F4"),
                    SynthNote(293.66f, 280, 146.83f, "D4"),
                    SynthNote(440.00f, 160, 220.00f, "A4"),
                    SynthNote(523.25f, 160, 261.63f, "C5"),
                    SynthNote(587.33f, 340, 293.66f, "D5"),
                    SynthNote(523.25f, 180, 261.63f, "C5"),
                    SynthNote(440.00f, 360, 220.00f, "A4!")
                )
            )
        ),
        TvShowItem(
            id = "hero_power_rangers",
            title = "Mighty Morphin Power Rangers",
            taglineOrHindiTitle = "பவர் ரேஞ்சர்ஸ் • It's Morphin Time!",
            primaryChannel = TvChannel.STAR_PLUS,
            otherChannels = listOf(TvChannel.DD_METRO, TvChannel.RAJ_TV),
            yearsAired = "1995–1999",
            typicalSlot = "5:30 PM Daily Action Slot",
            category = ShowCategory.SUPERHERO,
            isTamilClassic = true,
            synopsis = "Five teenagers chosen by Zordon morph into Red, Blue, Black, Yellow, and Pink Rangers—plus Tommy the Green/White Ranger—and summon Dinozords to form the Megazord!",
            desiMemory = "Fighting over who got to be Red Ranger or Green Ranger during school interval in Chennai, Madurai & Coimbatore!",
            catchphrase = "It's Morphin Time! Go Go Power Rangers!",
            heroAlias = "Jason, Tommy, Kimberly, Billy, Zack & Trini",
            archNemesis = "Rita Repulsa & Lord Zedd",
            triviaFacts = listOf(
                "Zordon was a floating head in a time warp inside the Command Center, assisted by Alpha 5.",
                "Action footage came from the Japanese Super Sentai series Zyuranger."
            ),
            themeClip = ThemeMusicClip(
                id = "clip_power_rangers",
                title = "Go Go Megazord Guitar",
                showTitle = "Power Rangers",
                waveform = SynthWaveform.SAWTOOTH,
                durationLabel = "0:08",
                tempoBpm = 156,
                lyricCue = "♪ Go Go Power Rangers! Mighty Morphin Power Rangers! ♪",
                notes = listOf(
                    SynthNote(329.63f, 150, 164.81f, "E4"),
                    SynthNote(329.63f, 150, 164.81f, "E4"),
                    SynthNote(293.66f, 150, 146.83f, "D4"),
                    SynthNote(329.63f, 220, 164.81f, "E4"),
                    SynthNote(392.00f, 200, 196.00f, "G4"),
                    SynthNote(329.63f, 340, 164.81f, "E4"),
                    SynthNote(0f, 80, 0f, "Rest"),
                    SynthNote(493.88f, 150, 246.94f, "B4"),
                    SynthNote(440.00f, 150, 220.00f, "A4"),
                    SynthNote(392.00f, 180, 196.00f, "G4"),
                    SynthNote(329.63f, 420, 164.81f, "E4!")
                )
            )
        ),
        TvShowItem(
            id = "hero_captain_planet",
            title = "Captain Planet & The Planeteers",
            taglineOrHindiTitle = "கேப்டன் பிளானட் • Earth! Fire! Wind! Water! Heart!",
            primaryChannel = TvChannel.CARTOON_NETWORK,
            otherChannels = listOf(TvChannel.DD_NATIONAL),
            yearsAired = "1993–1999",
            typicalSlot = "4:00 PM After-School Slot",
            category = ShowCategory.SUPERHERO,
            synopsis = "Gaia sends five magic rings to teens around the world who unite their elemental powers to summon eco-hero Captain Planet.",
            desiMemory = "Five friends pointing knuckles together in school ground shouting 'Earth, Fire, Wind, Water, Heart—Go Planet!'",
            catchphrase = "By your powers combined, I am Captain Planet!",
            heroAlias = "Spirit of Gaia",
            archNemesis = "Looten Plunder, Hoggish Greedly & Dr. Blight",
            triviaFacts = listOf(
                "Captain Planet's one weakness was toxic pollution—splashing sludge drained his power immediately.",
                "Wheeler, Kwame, Linka, Gi, and Ma-Ti represented five continents."
            ),
            themeClip = ThemeMusicClip(
                id = "clip_captain_planet",
                title = "Planeteers Ring Unite",
                showTitle = "Captain Planet",
                waveform = SynthWaveform.SQUARE,
                durationLabel = "0:08",
                tempoBpm = 128,
                lyricCue = "♪ Captain Planet, he's our hero, gonna take pollution down to zero! ♪",
                notes = listOf(
                    SynthNote(392.00f, 200, 196.00f, "G4"),
                    SynthNote(440.00f, 200, 220.00f, "A4"),
                    SynthNote(493.88f, 240, 246.94f, "B4"),
                    SynthNote(523.25f, 300, 261.63f, "C5"),
                    SynthNote(493.88f, 180, 246.94f, "B4"),
                    SynthNote(440.00f, 180, 220.00f, "A4"),
                    SynthNote(392.00f, 340, 196.00f, "G4"),
                    SynthNote(523.25f, 200, 261.63f, "C5"),
                    SynthNote(587.33f, 200, 293.66f, "D5"),
                    SynthNote(659.25f, 240, 329.63f, "E5"),
                    SynthNote(587.33f, 200, 293.66f, "D5"),
                    SynthNote(523.25f, 460, 261.63f, "C5!")
                )
            )
        ),
        TvShowItem(
            id = "hero_batman_tas",
            title = "Batman: The Animated Series",
            taglineOrHindiTitle = "பேட்மேன் • The Dark Knight of Gotham",
            primaryChannel = TvChannel.CARTOON_NETWORK,
            otherChannels = listOf(TvChannel.STAR_PLUS),
            yearsAired = "1995–1999",
            typicalSlot = "7:00 PM Action Hour",
            category = ShowCategory.SUPERHERO,
            synopsis = "Drawn on black paper for a brooding Dark Deco look, Bruce Wayne defends Gotham City against Joker, Two-Face, Mr. Freeze, and Harley Quinn.",
            desiMemory = "That lightning flash in the intro where Batman stands atop a skyscraper—pure goosebumps on 90s CRT TVs!",
            catchphrase = "I am vengeance! I am the night! I am Batman!",
            heroAlias = "Bruce Wayne",
            archNemesis = "The Joker (Mark Hamill)",
            triviaFacts = listOf(
                "Harley Quinn was invented for this 1992 animated series before ever appearing in DC Comics!",
                "Animators painted backgrounds on black paper instead of white for deep noir shadows."
            ),
            themeClip = ThemeMusicClip(
                id = "clip_batman_tas",
                title = "Gotham Shadow March",
                showTitle = "Batman: TAS",
                waveform = SynthWaveform.TRIANGLE,
                durationLabel = "0:09",
                tempoBpm = 112,
                lyricCue = "♪ Dark brass-style motifs echoing over Gotham rooftops ♪",
                notes = listOf(
                    SynthNote(220.00f, 320, 110.00f, "A3"),
                    SynthNote(246.94f, 180, 123.47f, "B3"),
                    SynthNote(261.63f, 320, 130.81f, "C4"),
                    SynthNote(246.94f, 220, 123.47f, "B3"),
                    SynthNote(220.00f, 460, 110.00f, "A3"),
                    SynthNote(0f, 120, 0f, "Rest"),
                    SynthNote(220.00f, 260, 110.00f, "A3"),
                    SynthNote(329.63f, 260, 164.81f, "E4"),
                    SynthNote(311.13f, 220, 155.56f, "D#4"),
                    SynthNote(329.63f, 520, 164.81f, "E4!")
                )
            )
        ),
        TvShowItem(
            id = "hero_phantom",
            title = "Phantom 2040 / The Phantom",
            taglineOrHindiTitle = "மாயாவி (Vetaal) • Muthu Comics & TV Legend",
            primaryChannel = TvChannel.DD_METRO,
            otherChannels = listOf(TvChannel.CARTOON_NETWORK, TvChannel.RAJ_TV),
            yearsAired = "1995–1998",
            typicalSlot = "Evening Animated Action",
            category = ShowCategory.SUPERHERO,
            isTamilClassic = true,
            synopsis = "Immortalized in Tamil Nadu as 'Mayavi' in Muthu Comics and Rani Comics, the purple-suited masked hero protects both the jungle and futuristic Metropia!",
            desiMemory = "90s Tamil kids already worshipped The Phantom as 'Mayavi' in ₹5 Muthu Comics—seeing him animated on TV was pure joy!",
            catchphrase = "The Ghost Who Walks!",
            heroAlias = "Kit Walker (24th Phantom)",
            archNemesis = "Rebecca Madison & Singh Brotherhood",
            triviaFacts = listOf(
                "In Tamil Muthu Comics (Sivakasi), Phantom was famously titled 'Mayavi'!",
                "His Skull Ring leaves a skull mark on the jaw of villains he punches!"
            ),
            themeClip = ThemeMusicClip(
                id = "clip_phantom",
                title = "Skull Cave Pulse",
                showTitle = "Phantom 2040",
                waveform = SynthWaveform.TRIANGLE,
                durationLabel = "0:07",
                tempoBpm = 124,
                lyricCue = "♪ Mysterious jungle-synth fanfare of the Ghost Who Walks ♪",
                notes = listOf(
                    SynthNote(246.94f, 220, 123.47f, "B3"),
                    SynthNote(293.66f, 220, 146.83f, "D4"),
                    SynthNote(369.99f, 320, 185.00f, "F#4"),
                    SynthNote(329.63f, 200, 164.81f, "E4"),
                    SynthNote(293.66f, 200, 146.83f, "D4"),
                    SynthNote(369.99f, 240, 185.00f, "F#4"),
                    SynthNote(493.88f, 460, 246.94f, "B4!")
                )
            )
        ),
        TvShowItem(
            id = "hero_mask",
            title = "The Mask: Animated Series",
            taglineOrHindiTitle = "தி மாஸ்க் • Ssssmokin'!",
            primaryChannel = TvChannel.CARTOON_NETWORK,
            yearsAired = "1996–1999",
            typicalSlot = "6:30 PM Cartoon Network",
            category = ShowCategory.SUPERHERO,
            synopsis = "Meek Edge City bank clerk Stanley Ipkiss puts on an ancient Loki mask and transforms into a green-faced, yellow-zoot-suited chaotic superhero alongside his dog Milo!",
            desiMemory = "Even Milo the dog putting on the Mask became an all-time cartoon moment!",
            catchphrase = "Somebody stop me!! Ssssmokin'!",
            heroAlias = "Stanley Ipkiss & Milo",
            archNemesis = "Pretorius & Walter",
            triviaFacts = listOf(
                "Followed the massive 1994 Jim Carrey film that 90s kids watched on cable & VHS."
            ),
            themeClip = ThemeMusicClip(
                id = "clip_mask",
                title = "Edge City Swing Riff",
                showTitle = "The Mask",
                waveform = SynthWaveform.SQUARE,
                durationLabel = "0:07",
                tempoBpm = 152,
                lyricCue = "♪ Fast cartoon swing brass & whirlwind bounce! ♪",
                notes = listOf(
                    SynthNote(392.00f, 140, 196.00f, "G4"),
                    SynthNote(466.16f, 140, 233.08f, "Bb4"),
                    SynthNote(523.25f, 180, 261.63f, "C5"),
                    SynthNote(554.37f, 140, 277.18f, "C#5"),
                    SynthNote(523.25f, 160, 261.63f, "C5"),
                    SynthNote(466.16f, 160, 233.08f, "Bb4"),
                    SynthNote(392.00f, 240, 196.00f, "G4"),
                    SynthNote(587.33f, 380, 293.66f, "D5!")
                )
            )
        ),
        TvShowItem(
            id = "hero_centurions",
            title = "The Centurions",
            taglineOrHindiTitle = "செஞ்சுரியன்ஸ் • Power Xtreme!",
            primaryChannel = TvChannel.CARTOON_NETWORK,
            yearsAired = "1995–1999",
            typicalSlot = "5:30 PM Action Block",
            category = ShowCategory.SUPERHERO,
            synopsis = "Ace McCloud (Air), Max Ray (Sea), and Jake Rockwell (Land) beam down modular exo-frame weapons from Sky Vault to stop cyborg villain Doc Terror.",
            desiMemory = "Wanting a glowing exo-suit so badly that you taped cardboard wings onto your school bag!",
            catchphrase = "Man and machine—Power Xtreme!!",
            heroAlias = "Ace McCloud, Max Ray & Jake Rockwell",
            archNemesis = "Doc Terror & Hacker",
            triviaFacts = listOf(
                "Comic legends Jack Kirby and Gil Kane worked on early concept designs!"
            ),
            themeClip = ThemeMusicClip(
                id = "clip_centurions",
                title = "Sky Vault Beam-Down",
                showTitle = "The Centurions",
                waveform = SynthWaveform.SAWTOOTH,
                durationLabel = "0:08",
                tempoBpm = 138,
                lyricCue = "♪ Man and machine... Power Xtreme! ♪",
                notes = listOf(
                    SynthNote(293.66f, 180, 146.83f, "D4"),
                    SynthNote(440.00f, 220, 220.00f, "A4"),
                    SynthNote(440.00f, 160, 220.00f, "A4"),
                    SynthNote(493.88f, 220, 246.94f, "B4"),
                    SynthNote(523.25f, 300, 261.63f, "C5"),
                    SynthNote(587.33f, 440, 293.66f, "D5!")
                )
            )
        )
    )
}
