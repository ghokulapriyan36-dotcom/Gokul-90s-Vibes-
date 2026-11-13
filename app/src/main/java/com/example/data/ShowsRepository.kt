package com.example.data

import com.example.model.ShowCategory
import com.example.model.TvChannel
import com.example.model.TvShowItem

object ShowsRepository {

    val cartoonsAndSerials: List<TvShowItem> = listOf(
        // --- 90s TAMIL CLASSICS FIRST ---
        TvShowItem(
            id = "show_marmadesam",
            title = "Marmadesam (Ragasiyam & Vidathu Karuppu)",
            taglineOrHindiTitle = "மர்மதேசம்: விடாது கருப்பு • Sun TV Mystery",
            primaryChannel = TvChannel.SUN_TV,
            yearsAired = "1995–1999",
            typicalSlot = "Wednesdays / Nights 9:30 PM IST",
            category = ShowCategory.TV_SERIAL,
            isTamilClassic = true,
            synopsis = "Directed by Naga and written by Indra Soundar Rajan, this legendary Tamil psychological-folklore thriller blended village deities like Karuppusamy, mysterious bells, and investigative suspense.",
            desiMemory = "Listening to that haunting 'Vidathu Karuppu' background score gave every 90s Tamil kid chills—sleeping with the bedroom light ON was mandatory!",
            catchphrase = "Karuppu varaar... Vidathu Karuppu!",
            triviaFacts = listOf(
                "Its haunting title track and village suspense made it one of the highest-rated Tamil series of the 1990s.",
                "Featured Chetan, Devadarshini, and Poovilangu Mohan in unforgettable roles."
            )
        ),
        TvShowItem(
            id = "show_oliyum_oliyum",
            title = "Oliyum Oliyum",
            taglineOrHindiTitle = "ஒளியும் ஒலியும் • Friday 7:30 PM DD Podhigai",
            primaryChannel = TvChannel.DD_PODHIGAI,
            yearsAired = "1980s–1999",
            typicalSlot = "Fridays 7:30 PM IST",
            category = ShowCategory.TV_SERIAL,
            isTamilClassic = true,
            synopsis = "Doordarshan Chennai's iconic half-hour Tamil film song countdown—the entire week's homework and street cricket ended early every Friday just for those 5–6 songs!",
            desiMemory = "Sprinting home at 7:25 PM, adjusting the roof antenna for clear DD-5 signal, and praying a Rajini, Kamal, or A.R. Rahman hit played!",
            catchphrase = "Vellikkizhamai maaiyam 7:30 manikku Oliyum Oliyum!",
            triviaFacts = listOf(
                "Before cable TV arrived, Oliyum Oliyum was practically the only place to watch Tamil cinema songs on TV.",
                "Even power cuts on Friday nights triggered collective groans across entire Tamil Nadu neighborhoods."
            )
        ),
        TvShowItem(
            id = "show_pepsi_ungal_choice",
            title = "Pepsi Ungal Choice",
            taglineOrHindiTitle = "பெப்சி உங்கள் சாய்ஸ் • Live Landline Requests",
            primaryChannel = TvChannel.SUN_TV,
            yearsAired = "1995–1999+",
            typicalSlot = "Weekday Evenings • Sun TV",
            category = ShowCategory.TV_SERIAL,
            isTamilClassic = true,
            synopsis = "Hosted by the ever-smiling Uma Padmanabhan (and later Mahesh), viewers dialed the Chennai studio landline live to chat, dedicate wishes, and request Tamil hit songs.",
            desiMemory = "Repeatedly dialing the landline number until your fingers hurt, dreaming of hearing your own name and city announced on Sun TV!",
            catchphrase = "Vanakkam! Pepsi Ungal Choice-la yaar pesuringa?",
            triviaFacts = listOf(
                "Became a cultural phenomenon across Tamil Nadu and Tamil households worldwide.",
                "Callers always thanked 'amma, appa, thatha, paati, and all my school friends' in one breath!"
            )
        ),
        TvShowItem(
            id = "show_ramany_vs_ramany",
            title = "Ramany vs Ramany",
            taglineOrHindiTitle = "ரமணி vs ரமணி • Naga's Cult Tamil Sitcom",
            primaryChannel = TvChannel.SUN_TV,
            otherChannels = listOf(TvChannel.RAJ_TV),
            yearsAired = "1998–1999+",
            typicalSlot = "Weekly Comedy Prime Time",
            category = ShowCategory.TV_SERIAL,
            isTamilClassic = true,
            synopsis = "Created by K. Balachander's Min Bimbangal and directed by Naga, starring Ramji and Vasuki as a newlywed middle-class Chennai couple caught in everyday hilarious mishaps.",
            desiMemory = "Pure Tamil household comedy without melodrama—watching Ramany get caught in innocent white lies made whole families laugh together.",
            catchphrase = "Ennanga Ramany... idhellam oru vishayama?",
            triviaFacts = listOf(
                "Directed by Naga (who also directed Marmadesam!), proving his versatility across horror and comedy.",
                "Waiters, neighbors, and Mr. Ramany's boss added classic 90s Madras charm."
            )
        ),
        TvShowItem(
            id = "show_kadhal_pagadai",
            title = "Kadhal Pagadai & Kaiyalavu Manasu",
            taglineOrHindiTitle = "காதல் பகடை • K. Balachander's Sun TV Hits",
            primaryChannel = TvChannel.SUN_TV,
            yearsAired = "1995–1998",
            typicalSlot = "Weeknight Prime Time 8:30 PM",
            category = ShowCategory.TV_SERIAL,
            isTamilClassic = true,
            synopsis = "Legendary filmmaker K. Balachander brought crisp cinema-grade storytelling to 90s Tamil television with heartfelt family relationships and memorable title tracks.",
            desiMemory = "Humming 'Kadhal Pagadai...' while finishing dinner plates in front of the living room CRT TV.",
            catchphrase = "K. Balachander's Min Bimbangal presents...",
            triviaFacts = listOf(
                "Launched actors like Prakash Raj, Renuka, and Venu Arvind into Tamil living rooms before mega-serials."
            )
        ),
        TvShowItem(
            id = "show_thillana_thillana",
            title = "Thillana Thillana",
            taglineOrHindiTitle = "தில்லானா தில்லானா • 90s Tamil Dance Show",
            primaryChannel = TvChannel.SUN_TV,
            yearsAired = "1997–1999",
            typicalSlot = "Weekends • Sun TV",
            category = ShowCategory.TV_SERIAL,
            isTamilClassic = true,
            synopsis = "Tamil Nadu's original high-energy dance competition show—kids and youth across Chennai, Madurai, and Coimbatore recreated Prabhu Deva and A.R. Rahman steps!",
            desiMemory = "Moving the tea-table aside in the hall to copy the contestants' 'Muqabla' and 'Urvasi' dance steps!",
            catchphrase = "Thillana Thillana... Aadalam Vaanga!",
            triviaFacts = listOf(
                "Featured famous choreographers and film stars as judges long before modern reality TV."
            )
        ),
        TvShowItem(
            id = "show_saptaswarangal",
            title = "Saptaswarangal",
            taglineOrHindiTitle = "சப்தஸ்வரங்கள் • Classic Tamil Music Stage",
            primaryChannel = TvChannel.SUN_TV,
            yearsAired = "1994–1999+",
            typicalSlot = "Sundays • Sun TV",
            category = ShowCategory.TV_SERIAL,
            isTamilClassic = true,
            synopsis = "Hosted by A.V. Ramanan and Uma Ramanan, Saptaswarangal discovered young playback singing talent across Tamil Nadu through classic Ilaiyaraaja and MSV melodies.",
            desiMemory = "Listening to young singers nail tricky Ilaiyaraaja songs while Sunday lunch sambar was served.",
            catchphrase = "Ramanan Sir-oda Saptaswarangal!",
            triviaFacts = listOf(
                "Several future Tamil playback singers first stood under studio lights on Saptaswarangal."
            )
        ),
        TvShowItem(
            id = "show_jenma_natchathiram",
            title = "Nizhalgal & Vijay / Raj TV Classics",
            taglineOrHindiTitle = "நிழல்கள் & ராஜ் டிவி 90s Cartoons & Thrillers",
            primaryChannel = TvChannel.RAJ_TV,
            otherChannels = listOf(TvChannel.VIJAY_TV, TvChannel.SUN_TV),
            yearsAired = "1994–1999",
            typicalSlot = "Evenings & Weekends",
            category = ShowCategory.TV_SERIAL,
            isTamilClassic = true,
            synopsis = "Raj TV and Vijay TV brought fresh Tamil youth quizzes, comedy skits, classic cinema retrospectives, and dubbed animated adventures into cable-connected Tamil homes.",
            desiMemory = "Flipping between Sun TV, Raj TV, and Vijay TV with a plastic remote wrapped inside a transparent polythene cover!",
            catchphrase = "Remote-ah kudu da, channel maathanum!",
            triviaFacts = listOf(
                "Raj TV launched in 1994 as one of the earliest private Tamil satellite channels after Sun TV."
            )
        ),
        TvShowItem(
            id = "show_jungle_book",
            title = "The Jungle Book (Mowgli Tamil & Hindi)",
            taglineOrHindiTitle = "ஜங்கிள் புக் • Sunday 9 AM DD Podhigai / DD1",
            primaryChannel = TvChannel.DD_NATIONAL,
            otherChannels = listOf(TvChannel.DD_PODHIGAI),
            yearsAired = "1993–1998",
            typicalSlot = "Sundays 9:00 AM IST",
            category = ShowCategory.CARTOON,
            isTamilClassic = true,
            synopsis = "Mowgli, Bagheera, Baloo, and Sher Khan captured every Sunday morning across Tamil Nadu and India—whether watched in Tamil on DD Podhigai or Hindi on DD National!",
            desiMemory = "Waking up early on Sunday with hot idli/dosai or pongal on your plate, singing along to Mowgli's jungle adventures before cricket started!",
            catchphrase = "Jungle jungle baat chali hai / Mowgli & Sher Khan!",
            triviaFacts = listOf(
                "Aired on Sunday mornings on Doordarshan across Tamil Nadu and all of India.",
                "The anime ran for 52 episodes following Mowgli from wolf cub adoption to growing up."
            )
        ),
        TvShowItem(
            id = "show_malgudi_days",
            title = "Malgudi Days",
            taglineOrHindiTitle = "மால்குடி டேஸ் • South Indian Childhood Classic",
            primaryChannel = TvChannel.DD_NATIONAL,
            otherChannels = listOf(TvChannel.DD_PODHIGAI),
            yearsAired = "1987–1998 (90s Reruns)",
            typicalSlot = "Weekday Evenings / Sundays",
            category = ShowCategory.TV_SERIAL,
            isTamilClassic = true,
            synopsis = "Directed by Shankar Nag from R.K. Narayan's beloved South Indian stories, following young Swami, Mani, and Rajam through school exams, cricket clubs, and summer holidays.",
            desiMemory = "Hearing the iconic 'Ta-na-na-na-na-na' flute tune instantly feels like summer holidays in Madurai, Trichy, or Mylapore!",
            catchphrase = "Swami and Friends — MCC Cricket Club!",
            triviaFacts = listOf(
                "R.K. Laxman (R.K. Narayan's brother) drew the iconic sketches in the title credits.",
                "Master Manjunath as Swami became an unforgettable childhood face across South India."
            )
        ),
        TvShowItem(
            id = "show_ducktales",
            title = "DuckTales (Tamil & English/Hindi)",
            taglineOrHindiTitle = "டக்டேல்ஸ் • Scrooge McDuck & Money Bin",
            primaryChannel = TvChannel.DD_NATIONAL,
            otherChannels = listOf(TvChannel.DD_PODHIGAI, TvChannel.STAR_PLUS),
            yearsAired = "1991–1998",
            typicalSlot = "Weekdays / Sundays 5:00 PM",
            category = ShowCategory.CARTOON,
            isTamilClassic = true,
            synopsis = "Billionaire Scrooge McDuck dives into his Money Bin and travels the globe with Huey, Dewey, Louie, Webby, and bumbling pilot Launchpad McQuack.",
            desiMemory = "Watching Scrooge dive into gold coins in Tamil/English after evening homework and wishing our piggy bank held that much!",
            catchphrase = "DuckTales, Woo-oo!",
            triviaFacts = listOf(
                "One of the earliest Disney shows aired across Doordarshan and Star Plus in Tamil Nadu.",
                "Launchpad McQuack could crash any plane and walk away without a scratch."
            )
        ),
        TvShowItem(
            id = "show_talespin",
            title = "TaleSpin",
            taglineOrHindiTitle = "டேல்ஸ்பின் • Baloo's Yellow Sea Duck",
            primaryChannel = TvChannel.DD_NATIONAL,
            otherChannels = listOf(TvChannel.STAR_PLUS, TvChannel.DD_PODHIGAI),
            yearsAired = "1992–1999",
            typicalSlot = "Evening Disney Slot",
            category = ShowCategory.CARTOON,
            isTamilClassic = true,
            synopsis = "Bear pilot Baloo flies his yellow Sea Duck cargo plane through Cape Suzette, dodging air pirates led by Don Karnage while Kit Cloudkicker surfs the clouds.",
            desiMemory = "Every 90s kid wanted Kit's folding metal cloud-surfing foil!",
            catchphrase = "Ooh-ee-ay! TaleSpin! Ooh-ee-oh! TaleSpin!",
            triviaFacts = listOf(
                "Reimagined Baloo, Louie, and Shere Khan in a 1930s aviation adventure world."
            )
        ),
        // --- ALL-INDIA & CABLE TV CLASSICS ---
        TvShowItem(
            id = "show_tom_jerry",
            title = "Tom & Jerry",
            taglineOrHindiTitle = "டாம் & ஜெர்ரி • Cartoon Network Favorite",
            primaryChannel = TvChannel.CARTOON_NETWORK,
            otherChannels = listOf(TvChannel.SUN_TV),
            yearsAired = "1995–1999+",
            typicalSlot = "All-day Marathon Slots",
            category = ShowCategory.CARTOON,
            isTamilClassic = true,
            synopsis = "The timeless cat-and-mouse rivalry featuring frying pans, mousetraps, pianos, and Spike the Bulldog—loved equally by grandparents and kids across Tamil Nadu.",
            desiMemory = "No Tamil dub needed—Thatha, Paati, Amma, and kids all laughed together at Tom getting flattened like a dosa!",
            catchphrase = "(Iconic Tom scream & Jerry tip-toe piano notes!)",
            triviaFacts = listOf(
                "Classic Hanna-Barbera shorts won 7 Academy Awards for Animated Short Film."
            )
        ),
        TvShowItem(
            id = "show_swat_kats",
            title = "SWAT Kats: The Radical Squadron",
            taglineOrHindiTitle = "ஸ்வாட் கேட்ஸ் • Turbokat Rock Intro",
            primaryChannel = TvChannel.CARTOON_NETWORK,
            yearsAired = "1995–1999",
            typicalSlot = "5:00 PM Action Block",
            category = ShowCategory.CARTOON,
            synopsis = "Two fired Enforcer mechanics, Chance (T-Bone) and Jake (Razor), build the supersonic Turbokat jet in a junkyard to defend Megakat City from Dark Kat and Metallicats.",
            desiMemory = "That screaming electric guitar intro on Cartoon Network made every 90s kid drop their school bag immediately!",
            catchphrase = "Megakat City needs the SWAT Kats!",
            triviaFacts = listOf(
                "Dr. Viper, Pastmaster, and Dark Kat were among the coolest 90s animated villains."
            )
        ),
        TvShowItem(
            id = "show_dexter",
            title = "Dexter's Laboratory",
            taglineOrHindiTitle = "டெக்ஸ்டர்ஸ் லேப் • Omelette du Fromage",
            primaryChannel = TvChannel.CARTOON_NETWORK,
            yearsAired = "1996–1999+",
            typicalSlot = "6:00 PM Cartoon Cartoons",
            category = ShowCategory.CARTOON,
            synopsis = "Boy genius Dexter hides a massive high-tech laboratory behind his bedroom bookshelf, constantly sabotaged by his carefree older sister Dee Dee.",
            desiMemory = "Every younger brother related deeply when a sibling pressed the giant red 'DO NOT PUSH' button!",
            catchphrase = "Dee Dee, get out of my laboratory!",
            triviaFacts = listOf(
                "Included bonus mini-segments 'Dial M for Monkey' and 'The Justice Friends'."
            )
        ),
        TvShowItem(
            id = "show_scooby_doo",
            title = "Scooby-Doo, Where Are You!",
            taglineOrHindiTitle = "ஸ்கூபி-டூ • Mystery Machine",
            primaryChannel = TvChannel.CARTOON_NETWORK,
            otherChannels = listOf(TvChannel.DD_NATIONAL),
            yearsAired = "1995–1999+",
            typicalSlot = "4:30 PM & Weekend Mornings",
            category = ShowCategory.CARTOON,
            synopsis = "Fred, Daphne, Velma, Shaggy, and cowardly Great Dane Scooby-Doo unmask greedy crooks disguised as ghosts in spooky mansions.",
            desiMemory = "Learning the golden 90s rule: the ghost is ALWAYS the grumpy old caretaker!",
            catchphrase = "Scooby-Dooby-Doo!",
            triviaFacts = listOf(
                "Velma losing her glasses happened in nearly every classic mystery."
            )
        ),
        TvShowItem(
            id = "show_johnny_bravo",
            title = "Johnny Bravo",
            taglineOrHindiTitle = "ஜானி பிராவோ • Whoa Mama!",
            primaryChannel = TvChannel.CARTOON_NETWORK,
            yearsAired = "1997–1999+",
            typicalSlot = "6:30 PM Evening Slot",
            category = ShowCategory.CARTOON,
            synopsis = "Pompadour-haired, sunglasses-wearing mama's boy who constantly tries—and hilariously fails—to impress women with karate poses.",
            desiMemory = "Practicing the 'Hoo-ha-huh!' karate pose in front of the mirror after school!",
            catchphrase = "Whoa Mama! Man, I'm pretty!",
            triviaFacts = listOf(
                "Created by Van Partible; Seth MacFarlane and Butch Hartman wrote early episodes."
            )
        ),
        TvShowItem(
            id = "show_powerpuff",
            title = "The Powerpuff Girls",
            taglineOrHindiTitle = "பவர்பஃப் கேர்ள்ஸ் • Chemical X",
            primaryChannel = TvChannel.CARTOON_NETWORK,
            yearsAired = "1998–1999+",
            typicalSlot = "5:30 PM Cartoon Network",
            category = ShowCategory.CARTOON,
            synopsis = "Professor Utonium accidentally adds Chemical X, creating Blossom, Bubbles, and Buttercup to save Townsville from Mojo Jojo and HIM.",
            desiMemory = "Debating in school which Powerpuff Girl you were—leader Blossom, sweet Bubbles, or fierce Buttercup.",
            catchphrase = "So once again, the day is saved, thanks to the Powerpuff Girls!",
            triviaFacts = listOf(
                "Mojo Jojo's overly repetitive speeches were legendary among 90s viewers."
            )
        ),
        TvShowItem(
            id = "show_popeye",
            title = "Popeye the Sailor Man",
            taglineOrHindiTitle = "பாப்பாய் • Keerai / Spinach Power!",
            primaryChannel = TvChannel.CARTOON_NETWORK,
            otherChannels = listOf(TvChannel.DD_METRO),
            yearsAired = "1993–1999",
            typicalSlot = "Afternoon / Morning Reruns",
            category = ShowCategory.CARTOON,
            synopsis = "Squinty-eyed sailor Popeye battles giant rival Bluto to protect Olive Oyl, squeezing open a can of spinach whenever trouble hits.",
            desiMemory = "Tamil moms pointing at Popeye during lunch to convince us to finish our Arai Keerai & Mulakkeerai!",
            catchphrase = "I'm strong to the finich, 'cause I eats me spinach!",
            triviaFacts = listOf(
                "Popeye single-handedly convinced a generation of 90s kids to eat spinach!"
            )
        ),
        TvShowItem(
            id = "show_flintstones",
            title = "The Flintstones",
            taglineOrHindiTitle = "பிளிண்ட்ஸ்டோன்ஸ் • Yabba Dabba Doo!",
            primaryChannel = TvChannel.CARTOON_NETWORK,
            otherChannels = listOf(TvChannel.DD_METRO),
            yearsAired = "1994–1999",
            typicalSlot = "Afternoon Family Block",
            category = ShowCategory.CARTOON,
            synopsis = "Stone Age family Fred, Wilma, Pebbles, Barney, and Betty live modern suburban life using foot-powered cars and prehistoric animal appliances.",
            desiMemory = "Trying to pedal a cycle like Fred's stone car, and loving Dino the pet dinosaur.",
            catchphrase = "Yabba Dabba Doo!!",
            triviaFacts = listOf(
                "The Flintstones was the first animated series nominated for a Primetime Emmy."
            )
        ),
        TvShowItem(
            id = "show_hum_paanch",
            title = "Hum Paanch",
            taglineOrHindiTitle = "Anand Mathur & His 5 Daughters • Zee TV",
            primaryChannel = TvChannel.ZEE_TV,
            yearsAired = "1995–1999",
            typicalSlot = "Fridays 8:30 PM IST",
            category = ShowCategory.TV_SERIAL,
            synopsis = "Middle-class Anand Mathur navigates hilarious daily chaos caused by his five distinct daughters—Meenakshi, Radhika, Sweety, Kajal Bhai, and Chhoti.",
            desiMemory = "Waiting for Kajal Bhai and the talking portrait frame!",
            catchphrase = "Hum Paanch, paanchon ke paanchon kamaal!",
            triviaFacts = listOf(
                "Vidya Balan played Radhika in Season 1 before her film debut."
            )
        ),
        TvShowItem(
            id = "show_aahat",
            title = "Aahat",
            taglineOrHindiTitle = "90s Friday Night Chills • Sony TV",
            primaryChannel = TvChannel.SONY_TV,
            yearsAired = "1995–1999+",
            typicalSlot = "Fridays 9:30 PM",
            category = ShowCategory.TV_SERIAL,
            synopsis = "Sony TV's pioneering horror-thriller anthology featuring haunted havelis, spooky mirrors, and eerie whistling winds.",
            desiMemory = "Watching Aahat with all lights off and getting scared to walk down the hallway afterwards!",
            catchphrase = "(Creepy creaking door + howling wind!)",
            triviaFacts = listOf(
                "Created by B.P. Singh, the mastermind behind C.I.D."
            )
        ),
        TvShowItem(
            id = "show_boogie_woogie",
            title = "Boogie Woogie",
            taglineOrHindiTitle = "Javed Jaffrey, Naved & Ravi Behl • Sony TV",
            primaryChannel = TvChannel.SONY_TV,
            yearsAired = "1996–1999+",
            typicalSlot = "Saturdays 8:00 PM",
            category = ShowCategory.TV_SERIAL,
            synopsis = "India's pioneering dance reality show hosted by Javed Jaffrey, Naved Jaffrey, and Ravi Behl.",
            desiMemory = "Practicing breakdance moves in the living room!",
            catchphrase = "Boogie Woogie! Mamu!",
            triviaFacts = listOf(
                "India's first-ever television dance reality show, running for nearly 18 years."
            )
        )
    )

    val allShows: List<TvShowItem> by lazy {
        cartoonsAndSerials + SuperheroesData.superheroShows
    }
}
