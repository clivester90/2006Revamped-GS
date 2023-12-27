package com.rebotted.game.dialogues;

import com.rebotted.game.npcs.Npc;
import com.rebotted.game.npcs.NpcHandler;
import com.rebotted.game.players.Player;
import com.rebotted.net.packets.PacketType;

/**
 * Dialogue
 **/

public class Dialogue implements PacketType {

    @Override
    public void processPacket(Player c, int packetType, int packetSize) {
        if (c.getDialogueBuilder() != null) {
            if (c.getDialogueBuilder().getCurrent().isContinuable()) {
                if (c.getDialogueBuilder().hasNext()) {
                    if (c.npcClickIndex > 0) {
                        Npc npc = NpcHandler.npcs[c.npcClickIndex];
                        if (npc != null) {
                            npc.facePlayer(c.playerIndex);
                            c.turnPlayerTo(npc.getX(), npc.getY());
                        }
                    }
                }
                c.getDialogueBuilder().sendNextDialogue();
            }
        } else {

            if (c.nextChat > 0) {
                c.getDialogueHandler().sendDialogues(c.nextChat, c.talkingNpc);
            } else {
                c.getDialogueHandler().sendDialogues(0, -1);
            }
        }
    }
}
