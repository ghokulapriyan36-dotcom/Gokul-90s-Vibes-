package com.example.data

import com.example.model.TriviaQuestion

object TriviaRepository {

    val questions: List<TriviaQuestion> = listOf(
        TriviaQuestion(
            id = "q_tn1",
            showTitle = "Marmadesam: Vidathu Karuppu",
            channelTag = "Sun TV",
            question = "Which iconic Sun TV mystery thriller directed by Naga kept 90s Tamil kids terrified of horses and bells?",
            options = listOf(
                "Marmadesam: Vidathu Karuppu",
                "Chithi",
                "Ramany vs Ramany",
                "Metti Oli"
            ),
            correctIndex = 0,
            explanation = "Marmadesam (Ragasiyam & Vidathu Karuppu) blended Tamil folklore, Karuppusamy mysteries, and psychology every week on Sun TV!"
        ),
        TriviaQuestion(
            id = "q_tn2",
            showTitle = "Oliyum Oliyum",
            channelTag = "DD Podhigai",
            question = "Which Friday night Doordarshan Madras (DD Podhigai) show made entire Tamil streets go quiet at 7:30 PM?",
            options = listOf(
                "Pepsi Ungal Choice",
                "Oliyum Oliyum",
                "Saptaswarangal",
                "Thillana Thillana"
            ),
            correctIndex = 1,
            explanation = "Before cable TV, Oliyum Oliyum was the only time all week you could watch Tamil film songs on television!"
        ),
        TriviaQuestion(
            id = "q_tn3",
            showTitle = "Pepsi Ungal Choice",
            channelTag = "Sun TV",
            question = "In 90s Sun TV's 'Pepsi Ungal Choice', how did fans request their favorite Tamil movie songs live?",
            options = listOf(
                "By sending an SMS",
                "By dialing the studio landline phone live on TV",
                "Through WhatsApp voice notes",
                "By mailing postcards only"
            ),
            correctIndex = 1,
            explanation = "Viewers kept redialing the rotary/push-button landline phone hoping Uma or Mahesh would pick up live on Sun TV!"
        ),
        TriviaQuestion(
            id = "q_tn4",
            showTitle = "Nungu Vandi",
            channelTag = "Tamil Toys",
            question = "What natural summer fruit was carved by 90s Tamil kids to build a rolling 'Nungu Vandi' push-cart?",
            options = listOf(
                "Raw Mango",
                "Ice Apple (Nungu / Palm Fruit)",
                "Coconut Shell",
                "Guava"
            ),
            correctIndex = 1,
            explanation = "Two round Nungu (ice-apple) shells skewered on a thin stick with a Y-shaped handle made the coolest eco-friendly 90s Tamil toy!"
        ),
        TriviaQuestion(
            id = "q1",
            showTitle = "Shaktimaan",
            channelTag = "DD National / DD Podhigai",
            question = "What was Shaktimaan's bumbling alter-ego's full name?",
            options = listOf(
                "Pandit Gangadhar Vidyadhar Mayadhar Omkarnath Shastri",
                "Rameshwar Prasad Chaturvedi Shastri",
                "Gangadhar Devendra Prabhakar Tiwari",
                "Omkarnath Shastri Verma"
            ),
            correctIndex = 0,
            explanation = "Watched in Tamil and Hindi every Sunday afternoon—everyone memorized Pandit Gangadhar Vidyadhar Mayadhar Omkarnath Shastri!"
        ),
        TriviaQuestion(
            id = "q2",
            showTitle = "Shaktimaan",
            channelTag = "DD National / DD Podhigai",
            question = "Which iconic catchphrase did Shaktimaan's arch-enemy Tamraj Kilvish always say?",
            options = listOf(
                "Power is everything!",
                "Andhera Kayam Rahe!",
                "Shakti mere bas mein hai!",
                "Sab khatam ho jayega!"
            ),
            correctIndex = 1,
            explanation = "Tamraj Kilvish raised his hands from the darkness and declared: 'Andhera Kayam Rahe!'"
        ),
        TriviaQuestion(
            id = "q3",
            showTitle = "SWAT Kats",
            channelTag = "Cartoon Network",
            question = "What was the name of the supersonic jet flown by T-Bone and Razor in SWAT Kats?",
            options = listOf(
                "Sky Vault",
                "Turbokat",
                "Megazord",
                "Sea Duck"
            ),
            correctIndex = 1,
            explanation = "Chance 'T-Bone' Furlong and Jake 'Razor' Clawson built the legendary Turbokat jet in a Megakat City salvage yard!"
        ),
        TriviaQuestion(
            id = "q4",
            showTitle = "Captain Planet",
            channelTag = "Cartoon Network / DD",
            question = "Which 5 elemental ring powers combine to summon Captain Planet?",
            options = listOf(
                "Earth, Fire, Wind, Water, Heart",
                "Earth, Lightning, Fire, Ice, Soul",
                "Sun, Moon, Wind, Water, Earth",
                "Fire, Water, Metal, Wood, Spirit"
            ),
            correctIndex = 0,
            explanation = "Kwame (Earth), Wheeler (Fire), Linka (Wind), Gi (Water), and Ma-Ti (Heart) unite to summon Captain Planet!"
        ),
        TriviaQuestion(
            id = "q5",
            showTitle = "Contra (8-Bit)",
            channelTag = "Video Games",
            question = "What does the legendary Konami Code (↑ ↑ ↓ ↓ ← → ← → B A Start) unlock in Contra?",
            options = listOf(
                "Infinite Spread Gun",
                "Skip straight to the final boss",
                "30 Lives instead of 3",
                "Play as an alien robot"
            ),
            correctIndex = 2,
            explanation = "Entering the Konami Code on Contra's title screen bumped your lives from 3 to 30—essential for beating the game!"
        ),
        TriviaQuestion(
            id = "q6",
            showTitle = "Pambaram",
            channelTag = "Tamil Street Games",
            question = "In 90s Tamil street Pambaram games, what was 'Aappu'?",
            options = listOf(
                "Spinning two tops at once",
                "Chipping/cracking a trapped opponent's wooden top with your metal nail tip",
                "Painting the top in neon colors",
                "Catching the top on a cricket bat"
            ),
            correctIndex = 1,
            explanation = "If you failed to rescue your Pambaram from the chalk circle, winners got to throw their spinning top directly onto yours—'Aappu'!"
        ),
        TriviaQuestion(
            id = "q7",
            showTitle = "Captain Vyom",
            channelTag = "DD National / Podhigai",
            question = "Who starred as futuristic space superhero Captain Vyom in 1998?",
            options = listOf(
                "Mukesh Khanna",
                "Shahbaz Khan",
                "Milind Soman",
                "Pankaj Dheer"
            ),
            correctIndex = 2,
            explanation = "Milind Soman played Captain Vyom, piloting the spaceship Ulka in the year 2123 AD!"
        ),
        TriviaQuestion(
            id = "q8",
            showTitle = "Batman: The Animated Series",
            channelTag = "Cartoon Network",
            question = "Which famous DC villain was originally created specifically for Batman: TAS before entering comics?",
            options = listOf(
                "The Joker",
                "Harley Quinn",
                "Two-Face",
                "The Penguin"
            ),
            correctIndex = 1,
            explanation = "Paul Dini and Bruce Timm invented Harley Quinn for the 1992 animated series—and fans loved her so much she became a comic icon!"
        ),
        TriviaQuestion(
            id = "q10",
            showTitle = "He-Man",
            channelTag = "DD / Cartoon Network",
            question = "What is the name of He-Man's cowardly tiger before it transforms into Battle Cat?",
            options = listOf(
                "Cringer",
                "Panthor",
                "Bagheera",
                "Orko"
            ),
            correctIndex = 0,
            explanation = "Prince Adam's terrified green tiger Cringer turned into armored Battle Cat whenever He-Man shouted 'I have the power!'"
        ),
        TriviaQuestion(
            id = "q11",
            showTitle = "Malgudi Days",
            channelTag = "DD National / South India",
            question = "Who drew the iconic black-and-white illustrations seen in Malgudi Days' opening credits?",
            options = listOf(
                "Mario Miranda",
                "Pran Kumar Sharma",
                "R.K. Laxman",
                "Anant Pai"
            ),
            correctIndex = 2,
            explanation = "Legendary cartoonist R.K. Laxman illustrated the stories written by his brother R.K. Narayan!"
        )
    )
}
