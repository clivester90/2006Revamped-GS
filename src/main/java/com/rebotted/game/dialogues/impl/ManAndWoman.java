package com.rebotted.game.dialogues.impl;

import com.rebotted.game.dialogues.DialogueBuilder;
import com.rebotted.game.dialogues.DialogueExpression;
import com.rebotted.game.players.Player;

public class ManAndWoman extends DialogueBuilder {
    public ManAndWoman(Player player) {
        super(player);

        setNpcId(1)
                .npc(DialogueExpression.SAD, "Please leave me alone.")
                .player(DialogueExpression.DISTRESSED, "I'm so sorry, I didn't meant to interrupt.")
                .npc("Well no problem then, best of luck with your day,", "Adventurer!");
    }
}
