package com.rebotted.net.packets;

import com.rebotted.game.players.Player;

public interface PacketType {

	void processPacket(Player player, int packetType, int packetSize);
}
