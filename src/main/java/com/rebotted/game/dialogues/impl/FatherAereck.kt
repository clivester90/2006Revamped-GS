package com.rebotted.game.dialogues.impl

import com.rebotted.game.content.quests.QuestAssistant
import com.rebotted.game.dialogues.DialogueBuilder
import com.rebotted.game.dialogues.DialogueExpression
import com.rebotted.game.dialogues.DialogueOption
import com.rebotted.game.players.Player

class FatherAereck(player: Player?) : DialogueBuilder(player) {

    init {

        setNpcId(456)
            .npc(DialogueExpression.HAPPY, "Welcome to the church of holy Saradomin.")
            .option(DialogueOption("Who's Saradomin?") {
                whosSaradomin()
            },

                DialogueOption("Nice place you've got here.") {
                    nicePlaceYouGotHere()
                },

                DialogueOption("I'm looking for a quest!") {
                    if (player != null) {
                        if (player.restGhost == 5) {
                            restlessGhostFinished()
                        } else
                            restlessGhostStart()
                    }
                })


            .option(
                DialogueOption("Oh, THAT Saradomin...") {
                    ohThatSaradomin()
                },
                DialogueOption("Oh, sorry. I'm not from this world.") {
                    notFromThisWorld()
                },

                DialogueOption("You don't understand. This is an online game!") {
                    thisIsAnOnlineGame()
                },
                DialogueOption("I am - do you like my disguise?") {
                    doYouLikeMyDisguise()
                })

    }

    private fun whosSaradomin() {
        player.start(DialogueBuilder(player).setNpcId(456).player(DialogueExpression.CALM_TALK, "Who's Saradomin?")
            .npc(
                DialogueExpression.HAPPY,
                "He who creates the forces of goodness and purity in",
                "this world? I cannot believe your ignorance!"
            )
            .npc(
                DialogueExpression.CALM_TALK,
                "This is the god with more followers than any other!",
                "...At least in this part of the world."
            )
            .npc(
                DialogueExpression.CALM_TALK,
                "He who created this world along with his brothers", "Guthix and Zamorak?"
            ))
    }

    private fun ohThatSaradomin() {
        player.start(DialogueBuilder(player).setNpcId(456).player(DialogueExpression.LAUGH_2, "Oh, THAT Saradomin...")
            .npc(DialogueExpression.ANGER_1, "There... is only one Saradomin...")
            .player(DialogueExpression.DISTRESSED_2, "Yeah... I, uh, thought you said something else."))
    }

    private fun notFromThisWorld() {
        player.start(DialogueBuilder(player).setNpcId(456).player(DialogueExpression.LAUGH_2, "Oh, sorry. I'm not from this world.")
            .npc(DialogueExpression.DEFAULT, "...")
            .npc(DialogueExpression.DISTRESSED_2, "That's... strange.")
            .npc(
                DialogueExpression.CALM_TALK,
                "I thought things not from this world were all...",
                "You know. Slime and tentacles."
            ))
    }

    private fun thisIsAnOnlineGame() {
        player.start(DialogueBuilder(player).setNpcId(456).player(DialogueExpression.SPEAKING_CALMLY, "You don't understand. This is an online game!")
            .npc(DialogueExpression.ANGER_1, "I... beg your pardon?")
            .player(DialogueExpression.DISINTERESTED, "Never mind."))
    }

    private fun doYouLikeMyDisguise() {
        player.start(DialogueBuilder(player).setNpcId(456).player(DialogueExpression.LAUGH_1, "I am - do you like my disguise?")
            .npc(
                DialogueExpression.ANGER_1, "Aargh! Avaunt foul creature from another dimension!",
                "Avaunt! Begone in the name of Saradomin!"
            )
            .player(DialogueExpression.SPEAKING_CALMLY, "Ok, ok, I was only joking..."))
    }

    private fun nicePlaceYouGotHere() {
        player.start(DialogueBuilder(player).setNpcId(456).player(DialogueExpression.HAPPY, "Nice place you've got here.")
            .npc(DialogueExpression.HAPPY, "It is, isn't it? It was built over 230 years ago."))
    }

    private fun restlessGhostStart() {
        player.start(DialogueBuilder(player).setNpcId(456).player(DialogueExpression.HAPPY, "I'm looking for a quest!")
            .npc(DialogueExpression.HAPPY, "That's lucky, I need someone to do a quest for me.")

            .option(DialogueOption("Yes") {
                player.start(DialogueBuilder(player).setNpcId(456).player(DialogueExpression.HAPPY, "Okay, let me help  then.")
                .npc(
                    "Thank you. The problem is there's,",
                    "a ghost in the graveyard crypt just south of this church.",
                    "I would like you to get rid of it."
                )
                .npc(
                    DialogueExpression.SPEAKING_CALMLY, "You'll need the help of my friend, Father Urhney,",
                    "who is a bit of a ghost expert."
                )
                .npc(
                    DialogueExpression.SPEAKING_CALMLY, "He's currently living in a little shack to the south of,",
                    "the Lumbridge Swamp near the coast."
                )
                .npc(
                    DialogueExpression.SPEAKING_CALMLY, "My name is Father Aereck, by the way.",
                    "Pleased to meet you."
                )
                .player(DialogueExpression.HAPPY, "Likewise.")
                .npc(
                    DialogueExpression.SPEAKING_CALMLY, "Take care traveling through the swamps.",
                    "To get there just follow the path south,",
                    "through the graveyard."
                )
                .continueAction {
                    player.restGhost = 1
                    QuestAssistant.sendStages(player)
                }
                .player(DialogueExpression.HAPPY, "I will thanks."))
            },
                DialogueOption("No") {
                    player.start(DialogueBuilder(player).setNpcId(456).player(DialogueExpression.CALM_TALK, "Sorry, I don't have time right now.")
                            .npc(DialogueExpression.SAD, "Oh well. If you do have some spare time on your hands,",
                                "come back and talk to me."))
                }))


    }

    private fun restlessGhostFinished() {
        player.start(DialogueBuilder(player).setNpcId(456).option(DialogueOption("I'm looking for a quest!") {
                npc(
                    DialogueExpression.HAPPY, "Thank you for getting rid of that awful ghost for me!",
                    "May Saradomin always smile upon you!"
                )
                    .player(DialogueExpression.HAPPY, "I'm looking for a new quest!")
                    .npc(DialogueExpression.HAPPY, "Sorry, I only had the one quest.")


            }))
    }

}