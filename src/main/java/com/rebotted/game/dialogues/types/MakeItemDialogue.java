package com.rebotted.game.dialogues.types;

import com.google.common.base.Preconditions;
import com.rebotted.game.dialogues.DialogueAction;
import com.rebotted.game.dialogues.DialogueBuilder;
import com.rebotted.game.dialogues.DialogueObject;
import com.rebotted.game.items.ItemDefinition;
import com.rebotted.game.players.Player;

import java.util.function.Consumer;

public class MakeItemDialogue extends DialogueObject {

    public static class PlayerMakeItem {
        private final Player player;
        private final int itemId;
        private final int amount;

        public PlayerMakeItem(Player player, int itemId, int amount) {
            this.player = player;
            this.itemId = itemId;
            this.amount = amount;
        }

        public Player getPlayer() {
            return player;
        }

        public int getItemId() {
            return itemId;
        }

        public int getAmount() {
            return amount;
        }
    }

    public static class MakeItem {
        private final String itemHeader;
        private final int itemId;

        public MakeItem(int itemId) {
            this(ItemDefinition.getName(itemId), itemId);
        }

        public MakeItem(String itemHeader, int itemId) {
            this.itemHeader = itemHeader;
            this.itemId = itemId;
        }

        public String getItemHeader() {
            return itemHeader;
        }

        public int getItemId() {
            return itemId;
        }
    }

    private final int zoom;
    private final String dialogueHeader;
    private final Consumer<PlayerMakeItem> consumer;
    private final MakeItem[] makeItems;

    public MakeItemDialogue(DialogueBuilder context, int zoom, String dialogueHeader, Consumer<PlayerMakeItem> consumer, MakeItem...makeItems) {
        super(context, false);
        this.zoom = zoom;
        this.dialogueHeader = dialogueHeader;
        this.consumer = consumer;
        this.makeItems = makeItems;
    }

    @Override
    public void send(Player player) {
        switch (makeItems.length) {
            case 1:
                player.getPacketSender().sendString(makeItems[0].itemHeader, 2799);
                player.getPacketSender().sendInterfaceModel(1746, makeItems[0].itemId, zoom);
                player.getPacketSender().sendString(dialogueHeader, 2800);
                player.getPacketSender().sendChatInterface(4429);
                break;
            case 2:
                player.getPacketSender().sendInterfaceModel(8870, makeItems[1].getItemId(), zoom);
                player.getPacketSender().sendInterfaceModel(8869, makeItems[0].getItemId(), zoom);
                player.getPacketSender().sendString(makeItems[0].itemHeader, 8874);
                player.getPacketSender().sendString(makeItems[1].itemHeader, 8878);
                player.getPacketSender().sendChatInterface(8866);
                break;
            case 3:
                player.getPacketSender().sendInterfaceModel(8883, makeItems[0].getItemId(), zoom);
                player.getPacketSender().sendInterfaceModel(8884, makeItems[1].getItemId(), zoom);
                player.getPacketSender().sendInterfaceModel(8885, makeItems[2].getItemId(), zoom);
                for (int i = 0; i < 3; i++) {
                    player.getPacketSender().sendString(makeItems[i].itemHeader, 8889 + (i * 4));
                }
                player.getPacketSender().sendChatInterface(8880);
                break;
            case 4:
                player.getPacketSender().sendInterfaceModel(8902, makeItems[0].getItemId(), zoom);
                player.getPacketSender().sendInterfaceModel(8903, makeItems[1].getItemId(), zoom);
                player.getPacketSender().sendInterfaceModel(8904, makeItems[2].getItemId(), zoom);
                player.getPacketSender().sendInterfaceModel(8905, makeItems[3].getItemId(), zoom);
                for (int i = 0; i < 4; i++) {
                    player.getPacketSender().sendString(makeItems[i].itemHeader, 8909 + (i * 4));
                }
                player.getPacketSender().sendChatInterface(8899);
                break;
            case 5:
                player.getPacketSender().sendInterfaceModel(8941, makeItems[0].getItemId(), zoom);
                player.getPacketSender().sendInterfaceModel(8942, makeItems[1].getItemId(), zoom);
                player.getPacketSender().sendInterfaceModel(8943, makeItems[2].getItemId(), zoom);
                player.getPacketSender().sendInterfaceModel(8944, makeItems[3].getItemId(), zoom);
                player.getPacketSender().sendInterfaceModel(8945, makeItems[4].getItemId(), zoom);
                for (int i = 0; i < 5; i++) {
                    player.getPacketSender().sendString(makeItems[i].getItemHeader(), 8949 + (i * 4));
                }
                player.getPacketSender().sendChatInterface(8938);
                break;
            default:
                throw new IllegalArgumentException("Invalid amount of make items: " + makeItems.length);
        }
    }

    @Override
    public void handleAction(Player player, DialogueAction action) {
        Preconditions.checkState(consumer != null, new IllegalStateException());

        switch (action) {
            case MAKE_1_SLOT_1:
            case MAKE_1_SLOT_2:
            case MAKE_1_SLOT_3:
            case MAKE_1_SLOT_4:
            case MAKE_1_SLOT_5:
                handleMakeItem(player,
                        action == DialogueAction.MAKE_1_SLOT_1 ? 0
                        : action == DialogueAction.MAKE_1_SLOT_2 ? 1
                        : action == DialogueAction.MAKE_1_SLOT_3 ? 2
                        : action == DialogueAction.MAKE_1_SLOT_4 ? 3
                        : 4, 1);
                break;
            case MAKE_5_SLOT_1:
            case MAKE_5_SLOT_2:
            case MAKE_5_SLOT_3:
            case MAKE_5_SLOT_4:
            case MAKE_5_SLOT_5:
                handleMakeItem(player,
                        action == DialogueAction.MAKE_5_SLOT_1 ? 0
                                : action == DialogueAction.MAKE_5_SLOT_2 ? 1
                                : action == DialogueAction.MAKE_5_SLOT_3 ? 2
                                : action == DialogueAction.MAKE_5_SLOT_4 ? 3
                                : 4, 5);
                break;
            case MAKE_10_SLOT_1:
            case MAKE_10_SLOT_2:
            case MAKE_10_SLOT_3:
            case MAKE_10_SLOT_4:
            case MAKE_10_SLOT_5:
                handleMakeItem(player,
                        action == DialogueAction.MAKE_10_SLOT_1 ? 0
                                : action == DialogueAction.MAKE_10_SLOT_2 ? 1
                                : action == DialogueAction.MAKE_10_SLOT_3 ? 2
                                : action == DialogueAction.MAKE_10_SLOT_4 ? 3
                                : 4, 10);
                break;
            case MAKE_X_SLOT_1:
            case MAKE_X_SLOT_2:
            case MAKE_X_SLOT_3:
            case MAKE_X_SLOT_4:
            case MAKE_X_SLOT_5:
                handleMakeItem(player,
                        action == DialogueAction.MAKE_X_SLOT_1 ? 0
                                : action == DialogueAction.MAKE_X_SLOT_2 ? 1
                                : action == DialogueAction.MAKE_X_SLOT_3 ? 2
                                : action == DialogueAction.MAKE_X_SLOT_4 ? 3
                                : 4, -1);
                break;
            case MAKE_ALL_SLOT_1:
            case MAKE_ALL_SLOT_2:
            case MAKE_ALL_SLOT_3:
            case MAKE_ALL_SLOT_4:
            case MAKE_ALL_SLOT_5:
                handleMakeItem(player,
                        action == DialogueAction.MAKE_ALL_SLOT_1 ? 0
                                : action == DialogueAction.MAKE_ALL_SLOT_2 ? 1
                                : action == DialogueAction.MAKE_ALL_SLOT_3 ? 2
                                : action == DialogueAction.MAKE_ALL_SLOT_4 ? 3
                                : 4, Integer.MAX_VALUE);
                break;
            default:
                break;
        }
    }

    private void handleMakeItem(Player player, int slot, int amount) {
        /*if (amount == -1) {
            player.getPacketSender().sendEnterAmount(makeItems[0].getItemId());
            player.amountInputHandler = (player1, amount1) -> consumer.accept(new PlayerMakeItem(player1, makeItems[slot].getItemId(), amount1));
        } else {*/
            consumer.accept(new PlayerMakeItem(player, makeItems[slot].getItemId(), amount));
        //}
    }
}
