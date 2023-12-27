package com.rebotted.game.dialogues.types;

import com.rebotted.game.dialogues.DialogueAction;
import com.rebotted.game.dialogues.DialogueBuilder;
import com.rebotted.game.dialogues.DialogueExpression;
import com.rebotted.game.dialogues.DialogueObject;
import com.rebotted.game.players.Player;

public class PlayerDialogue extends DialogueObject {

    /**
     * This array contains the child id where the dialogue
     * statement starts for player dialogues.
     */
    private static final int[] PLAYER_DIALOGUE_ID = {
            971,
            976,
            982,
            989
    };

    private final String[] text;
    private final DialogueExpression expression;

    public PlayerDialogue(DialogueBuilder context, DialogueExpression expression, String[] text) {
        super(context, true);
        this.text = text;
        this.expression = expression;
    }

    @Override
    public void send(Player player) {
        int startDialogueChildId = PLAYER_DIALOGUE_ID[text.length - 1];
        int headChildId = startDialogueChildId - 2;
        player.getPacketSender().sendPlayerDialogueHead(headChildId);
        player.getPacketSender().sendDialogueAnimation(headChildId, expression.getAnimation());
        player.getPacketSender().sendString(player.getPlayerName(), startDialogueChildId - 1);
        for (int i = 0; i < text.length; i++) {
            player.getPacketSender().sendString(text[i], startDialogueChildId + i);
        }
        player.getPacketSender().sendChatInterface(startDialogueChildId - 3);
    }

    @Override
    public void handleAction(Player player, DialogueAction action) {
        if (action == DialogueAction.CLICK_TO_CONTINUE) {
            getContext().sendNextDialogue();
        }
    }
}
