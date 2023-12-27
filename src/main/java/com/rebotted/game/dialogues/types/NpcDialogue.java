package com.rebotted.game.dialogues.types;

import com.rebotted.game.dialogues.DialogueAction;
import com.rebotted.game.dialogues.DialogueBuilder;
import com.rebotted.game.dialogues.DialogueExpression;
import com.rebotted.game.dialogues.DialogueObject;
import com.rebotted.game.npcs.NPCDefinition;
import com.rebotted.game.players.Player;

public class NpcDialogue extends DialogueObject {

    /**
     * This array contains the child id where the dialogue
     * statement starts for npc and item dialogues.
     */
    private static final int[] NPC_DIALOGUE_ID = {
            4885,
            4890,
            4896,
            4903
    };

    private final int npcId;
    private final String[] text;
    private final DialogueExpression expression;

    public NpcDialogue(DialogueBuilder context, int npcId, DialogueExpression expression, String[] text) {
        super(context, true);
        this.npcId = npcId;
        this.text = text;
        this.expression = expression;
    }

    @Override
    public void send(Player player) {
        int startDialogueChildId = NPC_DIALOGUE_ID[text.length - 1];
        int headChildId = startDialogueChildId - 2;
        player.getPacketSender().sendNPCDialogueHead(npcId, headChildId);
        player.getPacketSender().sendDialogueAnimation(headChildId, expression.getAnimation());
        NPCDefinition.forId(npcId);
        player.getPacketSender().sendString(NPCDefinition.forId(npcId).getName().replaceAll("_", " "), startDialogueChildId - 1);
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
