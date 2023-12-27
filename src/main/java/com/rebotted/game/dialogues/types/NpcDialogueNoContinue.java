package com.rebotted.game.dialogues.types;

import com.rebotted.game.dialogues.DialogueAction;
import com.rebotted.game.dialogues.DialogueBuilder;
import com.rebotted.game.dialogues.DialogueExpression;
import com.rebotted.game.dialogues.DialogueObject;
import com.rebotted.game.npcs.NPCDefinition;
import com.rebotted.game.players.Player;

public class NpcDialogueNoContinue extends DialogueObject {

    private final int npcId;
    private final String[] text;
    private final DialogueExpression expression;

    public NpcDialogueNoContinue(DialogueBuilder context, int npcId, DialogueExpression expression, String[] text) {
        super(context, false);
        this.npcId = npcId;
        this.text = text;
        this.expression = expression;
    }

    @Override
    public void send(Player player) {
        switch (text.length) {
            case 2:
                player.getPacketSender().sendNPCDialogueHead(npcId, 12379);
                player.getPacketSender().sendDialogueAnimation(12379, expression.getAnimation());
                player.getPacketSender().sendString(NPCDefinition.forId(npcId).getName(), 12380);
                player.getPacketSender().sendString(text[0], 12381);
                player.getPacketSender().sendString(text[1], 12382);
                player.getPacketSender().sendChatInterface(12378);
                break;
            case 3:
                player.getPacketSender().sendNPCDialogueHead(npcId, 12384);
                player.getPacketSender().sendDialogueAnimation(12384, expression.getAnimation());
                player.getPacketSender().sendString(NPCDefinition.forId(npcId).getName(), 12385);
                player.getPacketSender().sendString(text[0], 12386);
                player.getPacketSender().sendString(text[1], 12387);
                player.getPacketSender().sendString(text[2], 12388);
                player.getPacketSender().sendChatInterface(12383);
                break;
            case 4:
                player.getPacketSender().sendNPCDialogueHead(npcId, 11892);
                player.getPacketSender().sendDialogueAnimation(11892, expression.getAnimation());
                player.getPacketSender().sendString(NPCDefinition.forId(npcId).getName(), 11893);
                player.getPacketSender().sendString(text[0], 11894);
                player.getPacketSender().sendString(text[1], 11895);
                player.getPacketSender().sendString(text[2], 11896);
                player.getPacketSender().sendString(text[3], 11897);
                player.getPacketSender().sendChatInterface(11891);
        }
    }

    @Override
    public void handleAction(Player player, DialogueAction action) {
        if (action == DialogueAction.CLICK_TO_CONTINUE) {
            getContext().sendNextDialogue();
        }
    }

}
