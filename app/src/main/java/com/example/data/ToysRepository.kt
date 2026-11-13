package com.example.data

import com.example.model.ToyCategory
import com.example.model.ToyItem

object ToysRepository {

    val allToys: List<ToyItem> = listOf(
        // --- ICONIC 90s TAMIL TOYS, STREET GAMES & PETTI KADAI TREASURES ---
        ToyItem(
            id = "toy_pambaram",
            name = "Pambaram (பம்பரம் - Wooden Spinning Top)",
            category = ToyCategory.STREET_GAMES,
            eraOrPrice = "All 90s • ₹5–₹15 at street shops",
            howWePlayed = "Wrap cotton 'saatai' string tightly from the metal nail upward around the painted wooden top, flick your wrist, yank back—and scoop the roaring Pambaram right into your palm!",
            nostalgiaStory = "Nothing earned more street respect in Tamil Nadu than catching a spinning Pambaram in your hand—or delivering a fierce 'Aappu' chip to your friend's trapped top inside the chalk circle!",
            funFact = "True pros filed the iron nail tip sharper so it spun longer and sounded like a buzzing bee.",
            isTamilClassic = true
        ),
        ToyItem(
            id = "toy_goli",
            name = "Goli Gundu (கோலி குண்டு - Glass Marbles)",
            category = ToyCategory.STREET_GAMES,
            eraOrPrice = "All 90s • 50p / ₹1 for a handful",
            howWePlayed = "Aim and flick your shooter marble ('Soda Goli' or 'Bada Goli') using your thumb and middle finger to knock opponents' marbles out of the circle or into the dirt 'Kuzhi' pit!",
            nostalgiaStory = "Coming home from school with your half-pant pockets heavy and clinking with green cat-eye 'Goli' trophies!",
            funFact = "The biggest, heaviest marble salvaged from old soda bottles ('Goli Soda') was the ultimate boss shooter.",
            isTamilClassic = true
        ),
        ToyItem(
            id = "toy_nungu_vandi",
            name = "Nungu Vandi (நுங்கு வண்டி - Palm Fruit Cart)",
            category = ToyCategory.STREET_GAMES,
            eraOrPrice = "Summer Vacations • ₹0 Handmade!",
            howWePlayed = "After eating sweet summer Nungu (ice apple), push a bamboo stick axle through two round outer shells and attach a Y-shaped neem or coconut-frond handle to roll your own 2-wheeler!",
            nostalgiaStory = "Every May summer holiday in Tamil Nadu towns and villages had kids racing Nungu Vandi carts down the street until dusk!",
            funFact = "Zero plastic, zero batteries—just pure Tamil rural engineering passed down through generations.",
            isTamilClassic = true
        ),
        ToyItem(
            id = "toy_kittipullu",
            name = "Kittipullu (கிட்டிப்புள் / Gilli Danda)",
            category = ToyCategory.STREET_GAMES,
            eraOrPrice = "All 90s • Handmade wood",
            howWePlayed = "Balance the short tapered 'Pullu' across a small ground groove, flip it skyward with the long 'Kitti' stick, and whack it mid-air as far down the lane as possible!",
            nostalgiaStory = "The pure panic when someone smashed the Pullu straight onto a neighbor's tiled roof and everyone vanished in 2 seconds!",
            funFact = "Known as Kittipullu across Tamil Nadu and Gilli Danda across North India.",
            isTamilClassic = true
        ),
        ToyItem(
            id = "toy_pallanguzhi",
            name = "Pallanguzhi, Thaayam & Aadu Puli Aattam",
            category = ToyCategory.STREET_GAMES,
            eraOrPrice = "All 90s • Wooden board & Tamarind seeds",
            howWePlayed = "Play Pallanguzhi with 14 carved wooden pits and shiny tamarind seeds ('Puliangottai') or cowrie shells ('Sozhi'), or roll brass Dhayakattai dice on floor-chalk Thaayam grids!",
            nostalgiaStory = "Afternoon summer power-cuts with Paati & cousins sitting on a cool cement red-oxide floor playing Pallanguzhi and Thaayam for hours!",
            funFact = "Kids polished tamarind seeds until they shone like brown pearls, keeping lucky sets in matchboxes.",
            isTamilClassic = true
        ),
        ToyItem(
            id = "toy_ezhukal",
            name = "Ezhu Kal / Lagori (ஏழு கல் - Seven Stones)",
            category = ToyCategory.STREET_GAMES,
            eraOrPrice = "All 90s • 7 flat stones + tennis ball",
            howWePlayed = "Stack 7 flat stones in a pyramid. Team A throws a tennis ball to knock them down, then tries to rebuild the stack while Team B hunts them with the ball!",
            nostalgiaStory = "Playing Ezhu Kal barefoot in the colony lane until the streetlamps flickered on at 6:30 PM.",
            funFact = "Rebuilding the 7-stone tower while dodging throws from three sides was peak 90s teamwork!",
            isTamilClassic = true
        ),
        ToyItem(
            id = "toy_tamil_mittai",
            name = "90s Petti Kadai Mittai (தேன் மிட்டாய், ஜவ்வு, கமர்கட்டு)",
            category = ToyCategory.CANDY_SCHOOL,
            eraOrPrice = "All 90s • 25p – ₹1 at corner Petti Kadai",
            howWePlayed = "Spend ₹1 pocket money on Thaen Mittai (sugar-coated honey drops), sticky coconut Kamarkattu, stretchy pink Javvu Mittai (shaped into watches on your wrist!), or frozen ₹1 Sip-Up Pepsi tubes!",
            nostalgiaStory = "Getting a Javvu Mittai 'watch' wrapped around your wrist by the street vendor, plus saving Boomer tattoos and ₹1 Poppins!",
            funFact = "In 90s Tamil schools, sharing a ₹1 Sip-Up ice stick meant snapping it cleanly in half on the edge of the desk!",
            isTamilClassic = true
        ),
        ToyItem(
            id = "toy_walkman_cassette",
            name = "Walkman & Ilaiyaraaja / Rahman Cassettes",
            category = ToyCategory.RETRO_TECH,
            eraOrPrice = "1990–1999 • Pyramid / Melody / Star Music",
            howWePlayed = "Pop in Roja, Kadhalan, Baasha, Padayappa, or Ilaiyaraaja Greatest Hits cassettes, slip on orange-foam headphones, and rewind tangled tape with a Natraj pencil!",
            nostalgiaStory = "Checking the folded lyrics inlay card inside Pyramid & Star Music cassettes and recording your favorite Tamil film songs off radio!",
            funFact = "A hexagonal Natraj or Apsara pencil fit inside a cassette spool with 100% precision.",
            isTamilClassic = true
        ),
        ToyItem(
            id = "toy_tazos",
            name = "Uncle Chipps & Lays Tazos",
            category = ToyCategory.COLLECTIBLES,
            eraOrPrice = "1996–1999 • Free inside ₹5/₹10 chip packets!",
            howWePlayed = "Stack circular notched Tazo discs and slam a thick 'Slammer' down—every Tazo that flipped face-up became yours!",
            nostalgiaStory = "Pressing the outside of chip packets in the neighborhood shop to feel for a Tazo disc before buying!",
            funFact = "The notched edges let you interlock Tazos into 3D spinning tops and castles.",
            isTamilClassic = true
        ),
        ToyItem(
            id = "toy_trump_cards",
            name = "WWF & Cricket Trump Cards",
            category = ToyCategory.COLLECTIBLES,
            eraOrPrice = "1992–1999 • ₹5–₹10 per deck",
            howWePlayed = "Call out a stat—Rank, Height, Weight, or Sachin's ODI Runs—and the highest stat wins the opponent's card!",
            nostalgiaStory = "Undertaker Rank #1, Bret Hart, Yokozuna's Weight, and Tendulkar's Runs were pure school-bus currency.",
            funFact = "When two cards tied, both went into a center pot for a thrilling tiebreaker!",
            isTamilClassic = true
        ),
        ToyItem(
            id = "toy_vhs_vcr",
            name = "VHS Video Cassette & VCR Rental",
            category = ToyCategory.RETRO_TECH,
            eraOrPrice = "1990–1998 • ₹10–₹20 weekend Video Shop rental",
            howWePlayed = "Rent a VCR/VCP and 3 Tamil movie VHS cassettes from the local Video Library, adjust 'Tracking' snow lines, and ALWAYS rewind before returning!",
            nostalgiaStory = "Renting a VCR for Sunday family movie marathons with 15 relatives sitting on pai (mats) in the hall!",
            funFact = "Every Tamil town had a Video Library with hand-written registers tracking cassette rewinds.",
            isTamilClassic = true
        ),
        ToyItem(
            id = "toy_water_ring",
            name = "Water Ring Toss Handheld",
            category = ToyCategory.DESK_TOYS,
            eraOrPrice = "1993–1999 • ₹20–₹40 at temple fairs",
            howWePlayed = "Press twin rubber buttons on the water-filled TV-shaped frame to jet air bubbles and float neon rings onto the pegs.",
            nostalgiaStory = "Buying one at a Thiruvizha / temple festival stall and trying for hours to hook the last stubborn ring!",
            funFact = "Zero batteries needed—pure water pressure and bubble physics."
        ),
        ToyItem(
            id = "toy_yoyo",
            name = "Coca-Cola / Fanta Spinner Yo-Yo",
            category = ToyCategory.DESK_TOYS,
            eraOrPrice = "1996–1999 • Bottle-cap redemption",
            howWePlayed = "Trade plastic soda bottle liners + cash for a genuine Russell Spinner Yo-Yo and master 'Walk the Dog' and 'Around the World'!",
            nostalgiaStory = "Showing up to school with a genuine Coca-Cola Championship Yo-Yo looped on your finger made you an instant star.",
            funFact = "Yo-yo demo teams even toured Indian schools for live trick shows in the late 90s!"
        ),
        ToyItem(
            id = "toy_viewmaster",
            name = "View-Master 3D Slide Viewer",
            category = ToyCategory.DESK_TOYS,
            eraOrPrice = "1991–1998 • Red plastic 3D viewer",
            howWePlayed = "Slot a cardboard 7-pair photo reel, point it at sunlight or a tubelight, and click the lever for magical 3D scenes!",
            nostalgiaStory = "Peering at 3D dinosaurs, Mahabalipuram, or Jungle Book reels felt like real virtual reality in 1994.",
            funFact = "Each circular reel held 14 tiny Kodachrome film transparencies."
        ),
        ToyItem(
            id = "toy_slambook",
            name = "Autograph & Slam Books",
            category = ToyCategory.CANDY_SCHOOL,
            eraOrPrice = "1994–1999 • ₹25–₹50 padded hardbound",
            howWePlayed = "Pass a colorful Slam Book around on the last day of school for friends to fill out Nicknames, Favorite Actor (Rajini/Kamal/Vijay/Ajith!), and Best Memory!",
            nostalgiaStory = "Writing 'Rose is red, sky is blue, don't forget me when I'm far from you' in glitter pens!",
            funFact = "Your Slam Book WAS your 90s offline social network!"
        ),
        ToyItem(
            id = "toy_flames_book_cricket",
            name = "FLAMES, Book Cricket & Pen Fight",
            category = ToyCategory.STREET_GAMES,
            eraOrPrice = "All 90s • School bench classic",
            howWePlayed = "Flip any thick textbook blindly—last digit of the page number equals runs (2, 4, 6; 8 = 1 run; 0 = OUT!). Or flick Reynolds 045 pens across the desk in Pen Fight!",
            nostalgiaStory = "Turning a heavy Tamil or Science textbook into an intense 10-wicket match during substitution periods!",
            funFact = "Adding extra rubber grips onto a Reynolds 045 pen was top-secret Pen Fight engineering.",
            isTamilClassic = true
        )
    )
}
