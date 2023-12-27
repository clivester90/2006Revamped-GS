package com.rebotted.game.dialogues.types;

import com.rebotted.game.dialogues.DialogueAction;
import com.rebotted.game.dialogues.DialogueBuilder;
import com.rebotted.game.dialogues.DialogueObject;
import com.rebotted.game.players.Player;

public class StatementDialogue extends DialogueObject {

    private String[] statement;

    public StatementDialogue(DialogueBuilder context, String...statement) {
        super(context, true);
        this.statement = statement;
    }

    @Override
    public void send(Player player) {
        switch (statement.length) {
            case 1:
                player.getPacketSender().sendString(statement[0], 357);
                player.getPacketSender().sendChatInterface(356);
                break;
            case 2:
                player.getPacketSender().sendString(statement[0], 360);
                player.getPacketSender().sendString(statement[1], 361);
                player.getPacketSender().sendChatInterface(359);
                break;
            case 3:
                player.getPacketSender().sendString(statement[0], 364);
                player.getPacketSender().sendString(statement[1], 365);
                player.getPacketSender().sendString(statement[2], 366);
                player.getPacketSender().sendChatInterface(363);
                break;
            case 4:
                player.getPacketSender().sendString(statement[0], 369);
                player.getPacketSender().sendString(statement[1], 370);
                player.getPacketSender().sendString(statement[2], 371);
                player.getPacketSender().sendString(statement[3], 372);
                player.getPacketSender().sendChatInterface(368);
                break;
            case 5:
                player.getPacketSender().sendString(statement[0], 375);
                player.getPacketSender().sendString(statement[1], 376);
                player.getPacketSender().sendString(statement[2], 377);
                player.getPacketSender().sendString(statement[3], 378);
                player.getPacketSender().sendString(statement[4], 379);
                player.getPacketSender().sendChatInterface(374);
                break;
            default:
                throw new IllegalArgumentException("Invalid length: " + statement.length);
        }
    }

    @Override
    public void handleAction(Player player, DialogueAction action) {
        if (action == DialogueAction.CLICK_TO_CONTINUE) {
            getContext().sendNextDialogue();
        }
    }
}
