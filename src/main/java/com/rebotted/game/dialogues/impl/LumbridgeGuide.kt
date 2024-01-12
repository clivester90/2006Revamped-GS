package com.rebotted.game.dialogues.impl;

import com.rebotted.GameConstants;
import com.rebotted.game.dialogues.DialogueBuilder;
import com.rebotted.game.dialogues.DialogueExpression;
import com.rebotted.game.players.Player;

class LumbridgeGuide(player: Player?) : DialogueBuilder(player) {
    init {
        player?.start(DialogueBuilder(player).setNpcId(2244).npc(DialogueExpression.HAPPY, "Greetings and welcome to " + GameConstants.SERVER_NAME + ", adventurer!"))
    }

}
