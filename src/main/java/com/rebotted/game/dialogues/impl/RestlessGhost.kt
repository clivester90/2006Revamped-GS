package com.rebotted.game.dialogues.impl

import com.rebotted.game.dialogues.DialogueBuilder
import com.rebotted.game.dialogues.DialogueOption
import com.rebotted.game.items.ItemConstants
import com.rebotted.game.players.Player

class RestlessGhost(player: Player?) : DialogueBuilder(player) {

    init {
        if (player != null) {
            if (player.restGhost == 2) {
                if(player.playerEquipment[ItemConstants.AMULET] == 552) {
                    setNpcId(457)
                        .player("Hello ghost, how are you?")
                        .npc("Not very good actually.")
                        .player("What's the problem then?")
                        .npc("Did you just understand what I said???")
                        .continueAction { withGhostspeak() }
                }
                else {
                    setNpcId(457)
                        .player("Hello ghost, how are you?")
                        .npc("Wooo wooo wooooo!")
                        .player("Sorry, I don't speak ghost.")
                }
            }
            else {
                setNpcId(457)
                    .player("Hello ghost, how are you?")
                    .npc("How are you doing finding my skull?")
                    .player("Sorry, I can't find it at the moment.")
                    .npc("Ah well. Keep on looking. I'm pretty sure it's somewhere in",
                        "the tower south-west from here. There's a lot of levels to the tower,",
                        "though. I suppose it might take a little while to find.")
            }

        }
    }

    private fun withGhostspeak() {
                player.start(DialogueBuilder(player).setNpcId(457)
            .option(DialogueOption("Yep, now tell me what the problem is.") {
                    whatsTheProblem()
            },
                DialogueOption("No, you sound like you're speaking nonsense to me.") {
                    speakingNonsense()
                },
                DialogueOption("Wow, this amulet works!") {
                    wowThisAmuletWorks()
                }))
    }

    private fun whatsTheProblem() {
        player.start(DialogueBuilder(player).setNpcId(457)
            .player("Hello ghost, how are you?")
            .npc("WOW! This is INCREDIBLE! I didn't expect anyone",
                "to ever understand me again!")
            .player("Ok, Ok, I can understand you!")
            .npc("Well, to be honest... I'm not sure.")
            .player("I've been told a certain task may need to be completed",
                "so you can rest in peace.")
            .npc(" I should think it is probably because a warlock has come",
                "along and stolen my skull. If you look inside my coffin there,",
                "you'll find my corpse without a head on it.")
            .player("Do you know where this warlock might be now?")
            .npc("I think it was one of the warlocks who lives in the",
                "big tower by the sea south-west from here.")
            .player("Ok. I will try and get the skull back for you,",
                "then you can rest in peace.")
            .npc("Ooh, thank you. That would be such a great relief!",
                "It is so dull being a ghost...")
            .continueAction {
                player.restGhost = 3
            })
    }

    private fun speakingNonsense() {
        player.start(DialogueBuilder(player).setNpcId(457)
            .player("No, you sound like you're speaking nonsense to me.")
            .npc("Oh that's a pity. You got my hopes up there.")
            .player("Yeah, it is a pity. Sorry about that.")
            .npc("Hang on a second... you CAN understand me!")

            .option(DialogueOption("No I can't.") {
                player.start(DialogueBuilder(player).setNpcId(457)
                    .player("No I can't.")
                    .npc("Great. The first person I can speak to in ages...",
                        "and they're a moron.")
                )
            },

                DialogueOption("Yep, clever aren't I?") {
                    stopMeBeingAGhost()
                })

        )
    }

    private fun stopMeBeingAGhost() {
        player.start(DialogueBuilder(player).setNpcId(457)
            .player("Yep, clever aren't I?")
            .npc("I'm impressed. You must be very powerful.",
                "I don't suppose you can stop me being a ghost?")

            .option(DialogueOption("Yes, ok. Do you know WHY you're a ghost?") {
               whyAreYouAGhost()
            },

                DialogueOption("No, you're scary!") {
                    noYoureScary()
                })
        )
    }

    private fun whyAreYouAGhost() {
        player.start(DialogueBuilder(player).setNpcId(457)
            .player("Yes, ok. Do you know WHY you're a ghost?")
            .npc("Nope. I just know I can't do much of anything", "like this!"))
    }

    private fun noYoureScary() {
        player.start(DialogueBuilder(player).setNpcId(457)
            .player("Yep, clever aren't I?")
            .npc("No, you're scary!",
                "Great. The first person I can speak to in ages...",
                "and they're an idiot."))
    }

    private fun wowThisAmuletWorks() {
        player.start(DialogueBuilder(player).setNpcId(457)
            .player("Wow, this amulet works!")
            .npc("Oh! It's your amulet that's doing it! I did wonder.",
                "I don't suppose you can help me? I don't like",
                "being a ghost.").continueAction { withGhostspeak() })
    }


}