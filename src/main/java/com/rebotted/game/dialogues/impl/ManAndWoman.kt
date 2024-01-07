package com.rebotted.game.dialogues.impl;

import com.rebotted.game.content.combat.npcs.NpcCombat
import com.rebotted.game.dialogues.DialogueBuilder
import com.rebotted.game.dialogues.DialogueExpression
import com.rebotted.game.dialogues.DialogueOption
import com.rebotted.game.npcs.Npc
import com.rebotted.game.players.Player
import com.rebotted.util.Misc

class ManAndWoman(player: Player?, npc: Npc) : DialogueBuilder(player) {

    init {
        when (Misc.random(0, 11)) {
            0 -> {
                setNpcId(npc.npcId)
                    .player(DialogueExpression.HAPPY, "Hello, how's it going?")
                    .npc(
                        DialogueExpression.CALM_TALK,
                        "Not too bad, but I'm a little worried about",
                        "the increase of goblins these days."
                    )
                    .player(DialogueExpression.HAPPY, "Don't worry, I'll kill them.")
            }

            1 -> {
                setNpcId(npc.npcId)
                    .player(DialogueExpression.HAPPY, "Hello, how's it going?")
                    .npc(DialogueExpression.CALM_TALK, "How can I help you?")
                    .option(
                        DialogueOption("Do you want to trade?") {
                            player(DialogueExpression.HAPPY, "Do you want to trade?")
                                .npc(
                                    DialogueExpression.CALM_TALK,
                                    "No, I have nothing I wish to get rid of. If you want to do some trading, " +
                                            "there are plenty of shops",
                                    "and market stalls around though."
                                )
                        },
                        DialogueOption("I'm in search of a quest.") {
                            player(DialogueExpression.HAPPY, "I'm in search of a quest.")
                                .npc(DialogueExpression.CALM_TALK, "I'm sorry I can't help you there.")
                        },
                        DialogueOption("I'm in search of enemies to kill.") {
                            player(DialogueExpression.HAPPY, "I'm in search of enemies to kill.")
                                .npc(
                                    DialogueExpression.CALM_TALK,
                                    "I've heard there are many fearsome creatures that",
                                    "dwell under the ground..."
                                )
                        })
            }

            2 -> {
                setNpcId(npc.npcId)
                    .player(DialogueExpression.HAPPY, "Hello, how's it going?")
                    .npc(DialogueExpression.ANGER_2, "Get out of my way, I'm in a hurry!")
            }

            3 -> {
                setNpcId(npc.npcId)
                    .player(DialogueExpression.HAPPY, "Hello, how's it going?")
                    .npc(DialogueExpression.HAPPY, "I'm fine, how are you?")
                    .player(DialogueExpression.HAPPY, "Very well thank you.")
            }

            4 -> {
                setNpcId(npc.npcId)
                    .player(DialogueExpression.HAPPY, "Hello, how's it going?")
                    .npc(DialogueExpression.HAPPY, "Hello there! Nice weather we've been having.")
            }

            5 -> {
                setNpcId(npc.npcId)
                    .player(DialogueExpression.HAPPY, "Hello, how's it going?")
                    .npc(DialogueExpression.HAPPY, "I'm very well thank you.")
            }

            6 -> {
                setNpcId(npc.npcId)
                    .player(DialogueExpression.HAPPY, "Hello, how's it going?")
                    .npc(DialogueExpression.HAPPY, "Who are you?")
                    .player(DialogueExpression.HAPPY, "I'm a bold adventurer.")
                    .npc(DialogueExpression.HAPPY, "Ah, a very noble profession.")
            }

            7 -> {
                setNpcId(npc.npcId)
                    .player(DialogueExpression.HAPPY, "Hello, how's it going?")
                    .npc(DialogueExpression.DISTRESSED, "Do I know you? I'm in a hurry!")
            }

            8 -> {
                setNpcId(npc.npcId)
                    .player(DialogueExpression.HAPPY, "Hello, how's it going?")
                    .npc(DialogueExpression.CALM, "I think we need a new king. The one we've got isn't very good.")
            }

            9 -> {
                setNpcId(npc.npcId)
                    .player(DialogueExpression.HAPPY, "Hello, how's it going?")
                    .npc(DialogueExpression.DISTRESSED, "Not too bad thanks.")
            }

            10 -> {
                setNpcId(npc.npcId)
                    .player(DialogueExpression.HAPPY, "Hello, how's it going?")
                    .npc(DialogueExpression.DISTRESSED, "Are you asking for a fight?")
                    .exit { NpcCombat.attackPlayer(getPlayer(), npc.npcId) }
            }
        }


    }

}
