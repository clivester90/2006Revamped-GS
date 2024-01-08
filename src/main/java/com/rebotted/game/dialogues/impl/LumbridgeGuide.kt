package com.rebotted.game.dialogues.impl;

import com.rebotted.GameConstants;
import com.rebotted.game.dialogues.DialogueBuilder;
import com.rebotted.game.dialogues.DialogueExpression;
import com.rebotted.game.players.Player;

public class LumbridgeGuide extends DialogueBuilder {
    public LumbridgeGuide(Player player) {
        super(player);
        setNpcId(2244).npc(DialogueExpression.HAPPY, "Greetings and welcome to " + GameConstants.SERVER_NAME + ", adventurer!");
    }
}
