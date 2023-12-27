package com.rebotted.game.dialogues.types;

import com.google.common.base.Preconditions;
import com.rebotted.game.dialogues.DialogueAction;
import com.rebotted.game.dialogues.DialogueBuilder;
import com.rebotted.game.dialogues.DialogueObject;
import com.rebotted.game.players.Player;

public class ItemStatementDialogue extends DialogueObject {

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

    private final int itemId;
    private final int itemZoom;
    private final String itemName;
    private final String[] statement;
    private final boolean closeOtherInterfaces;

    public ItemStatementDialogue(DialogueBuilder context, int itemId, int itemZoom, String itemName, String[] statement, boolean closeOtherInterfaces) {
        super(context, true);
        this.itemId = itemId;
        this.itemZoom = itemZoom;
        this.itemName = itemName;
        this.statement = statement;
        this.closeOtherInterfaces = closeOtherInterfaces;
        Preconditions.checkArgument(statement.length <= 2, "You can only have up to two lines of dialogue on an item statement.");
    }

    @Override
    public void send(Player player) {
        if (statement.length == 2) {
            player.getPacketSender().sendString(statement[0], 6232);
            player.getPacketSender().sendString(statement[1], 6233);
            player.getPacketSender().sendItemOnInterface(6235, itemZoom, 65_535);
            player.getPacketSender().sendItemOnInterface(6236, itemZoom, itemId);
            player.getPacketSender().sendChatInterface(6231);
        } else {
            player.getPacketSender().sendString(statement[0], 308);
            player.getPacketSender().sendItemOnInterface(307, itemZoom, itemId);
            player.getPacketSender().sendChatInterface(306);
        }
    }

    @Override
    public void handleAction(Player player, DialogueAction action) {
        if (action == DialogueAction.CLICK_TO_CONTINUE) {
            getContext().sendNextDialogue();
        }
    }
}
