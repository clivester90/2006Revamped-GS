package com.rebotted.game.dialogues.impl

import com.rebotted.game.dialogues.DialogueBuilder
import com.rebotted.game.dialogues.DialogueExpression
import com.rebotted.game.dialogues.DialogueOption
import com.rebotted.game.items.Item
import com.rebotted.game.players.Player

class FatherUrhney(player: Player?) : DialogueBuilder(player) {

    init {
        if (player != null) {
            if (player.restGhost == 1) {
                setNpcId(458)
                    .npc(DialogueExpression.ANGER_2, "Go away! I'm meditating!")
                    .option(DialogueOption("Well, that's friendly.") {
                        player.start(DialogueBuilder(getPlayer()).setNpcId(458).player("Well, that's friendly.")
                            .continueAction {
                                sendMainQuestOptions()
                            })
                    },

                        DialogueOption("Father Aereck sent me to talk to you.") {
                                fatherAereckSentMe()
                        },

                        DialogueOption("I've come to repossess your house.") {
                            repossessHome()
                        })
            } else {
                meditatingBeforeQuest()
            }
        }
    }

    private fun fatherAereckSentMe() {
        player.start(DialogueBuilder(player).setNpcId(458)
                .player("Father Aereck sent me to talk to you.")
                .npc(DialogueExpression.CALM_TALK,
                    "I suppose I'd better talk to you then. What problems",
                    "has he got himself into this time?")
                .continueAction {
                    sendMainQuestOptions()
                })
    }

    private fun meditatingBeforeQuest() {
        setNpcId(458)
            .npc(DialogueExpression.ANGER_2, "Go away! I'm meditating!")
            .option(DialogueOption("Well, that's friendly.") {
                player.start(DialogueBuilder(player).setNpcId(458).player("Well, that's friendly.")
                    .npc(DialogueExpression.ANGER_3, "I SAID go AWAY.")
                    .player("Okay, okay... sheesh, what a grouch."))
            },

                DialogueOption("I've come to repossess your house.") {
                    repossessHome()
                })
    }

    private fun sendOptionsQuest() {
        player.start(DialogueBuilder(player).setNpcId(458)
            .npc(DialogueExpression.ANGER_2, "Go away! I'm meditating!")
            .option(DialogueOption("Well, that's friendly.") {
                player.start(DialogueBuilder(player).setNpcId(458).player("Well, that's friendly.")
                    .continueAction {
                        sendMainQuestOptions()
                    })
            }))
    }

    private fun sendMainQuestOptions() {
        player.start(
            DialogueBuilder(player).setNpcId(458).
        option(
            DialogueOption("He's got a ghost haunting his graveyard.") {
                player.start(
                    DialogueBuilder(player).setNpcId(458)
                        .player("He's got a ghost haunting his graveyard.")
                        .npc(DialogueExpression.LAUGH_1, "Oh, the silly fool.")
                        .npc("I leave town for just five months, and ALREADY", "He can't manage.")
                        .npc("(Sigh)")
                        .npc(
                            "Well, I can't go back and exorcise it. I vowed to not",
                            "leave this place. Until I had done a full two years", "of prayer and meditation."
                        )
                        .npc("Tell you what I can do though; take this amulet.").continueAction {
                            player.inventory.addItem(Item(552, 1))
                            player.restGhost = 2;
                        }.npc("It is an Amulet of Ghostspeak.")
                        .npc(
                            "It's called that because, when you wear it, you can,",
                            "speak to ghosts. Many ghosts are doomed to remain in this,",
                            "world because they have some important task left uncompleted."
                        )
                        .npc(
                            "Maybe if you know what this task is, you can get rid",
                            "of the ghost. I'm not making any guarantees mind you,",
                            "but it is the best I can do right now."
                        )
                        .player("Thank you, I'll  give it a try!")
                )
            },

            DialogueOption("You mean he gets himself into lots of problems?") {
                player.start(
                    DialogueBuilder(player).setNpcId(458)
                        .player("You mean he gets himself into lots of problems?")
                        .npc(
                            "Yeah. For example, when we were trainee priests he kept",
                            "on getting stuck up bell ropes.")
                        .npc("Anyway. I don't have time for a chitchat.", "What's his problem this time?")
                        .continueAction { sendMainQuestOptions() }
                )
            }))
    }

    private fun repossessHome() {
        player.start(
            DialogueBuilder(player).setNpcId(458).option(DialogueOption("I've come to repossess your house.") {
                player("I've come to repossess your house.")
                    .npc("Under what grounds???")
            })
                    .option(DialogueOption("Repeated failure on mortgage repayments.") {
                        player.start(DialogueBuilder(player)
                            .player("Repeated failure on mortgage repayments.")
                            .npc("What?")
                            .npc("But... I don't have a mortgage! I build this", "house myself!")
                            .player("Sorry. I must have got the wrong address. All the",
                                "houses look the same around here.")
                            .npc("What? What houses? What ARE you talking about???")
                            .player("Never mind."))
                    },
                        DialogueOption("I don't know, I just wanted this house.") {
                            player.start(DialogueBuilder(player)
                                .player("I don't know, I just wanted this house.")
                                .npc(DialogueExpression.ANGER_3, "Oh... go away and stop wasting my time!"))
                        }))
            }



}