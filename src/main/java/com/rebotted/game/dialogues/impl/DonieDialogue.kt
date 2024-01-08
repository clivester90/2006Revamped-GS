package com.rebotted.game.dialogues.impl

import com.rebotted.game.dialogues.DialogueBuilder
import com.rebotted.game.dialogues.DialogueExpression
import com.rebotted.game.players.Player
import com.rebotted.util.Misc

class DonieDialogue(player: Player?) : DialogueBuilder(player) {

    init {
        setNpcId(2238)
            .player(DialogueExpression.HAPPY, "How's it going?")
            when(Misc.random(0, 1)) {
                0 -> {
                    player?.start(DialogueBuilder(player).setNpcId(2238).npc("I'm good, thank you for asking."))
                }
                1 -> {
                    player?.start(DialogueBuilder(player).setNpcId(2238).npc("I feel great, thanks for asking."))
                }
            }

    }
}