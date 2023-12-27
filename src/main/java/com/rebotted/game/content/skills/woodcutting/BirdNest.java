package com.rebotted.game.content.skills.woodcutting;

import com.rebotted.GameEngine;
import com.rebotted.game.items.ItemAssistant;
import com.rebotted.game.players.Player;
import com.rebotted.util.Misc;

/**
 * Aug 31, 2017 : 3:02:33 AM
 * BirdsNest.java
 * @author Andrew (Mr Extremez)
 */
public class BirdNest {
	
	
	private final static int[][] TREE_CHANCE = {
			{5312, 0, 203}, {5283, 204, 349}, {5313, 350, 492}, 
			{5284, 493, 599}, {5285, 600, 679}, {5286, 680, 751},
			{5314, 752, 810}, {5287, 811, 864},  {5288, 865, 907},
			{5289, 908, 939}, {5315, 940, 963}, {5290, 964, 985},
			{5316, 986, 996}, {5317, 996, 1000},
	};
	
	private final static int[][] RING_CHANCE = {
			{1637, 0, 398}, {1635, 399, 775}, 
			{1639, 776, 901}, {1641, 902, 992}, 
			{1643, 993, 1001}
	};
	
	private final static int[][] WYSON_CHANCE = {
			{5318, 14, 0, 1739}, {5320, 3, 1740, 2763}, 
			{5322, 6, 2764, 3708}, {5324, 9, 3709, 4559},
			{5319, 11, 4560, 5368}, {5100, 2, 5369, 6166},
			{5321, 2, 6167, 6958}, {5323, 3, 6959, 7693},
			{5312, 1, 7694, 7965}, {5295, 1, 7966, 8151},
			{5313, 1, 8152, 8317}, {5314, 1, 8318, 8415},
			{5315, 1, 8416, 8481}, {5317, 1, 8482, 8491},
			{5316, 1, 8492, 8499}
	};
	
	public static void handleBirdNest(Player player, int itemId, int nestType) {
		player.getPacketSender().sendMessage("You search the nest...");
		player.getItemAssistant().deleteItem(itemId, 1);
		player.getItemAssistant().addItem(5075, 1);
		switch (nestType) {
		case 0:
			int randomTree = Misc.random(1000);
            for (int[] item : TREE_CHANCE) {
                if (randomTree >= item[1] && randomTree <= item[2]) {
                    player.getItemAssistant().addItem(item[0], 1);
                    player.getPacketSender().sendMessage("You find a " + ItemAssistant.getItemName(item[0]) + ".");
                }
            }
			break;
		case 1:
			int randomRing = Misc.random(1001);
            for (int[] value : RING_CHANCE) {
                if (randomRing >= value[1] && randomRing <= value[2]) {
                    player.getItemAssistant().addItem(value[0], 1);
                    player.getPacketSender().sendMessage("You find a " + ItemAssistant.getItemName(value[0]) + ".");
                }
            }
			break;
		case 2:
			int randomWyson = Misc.random(8499);
            for (int[] ints : WYSON_CHANCE) {
                if (randomWyson >= ints[2] && randomWyson <= ints[3]) {
                    player.getItemAssistant().addItem(ints[0], ints[1]);
                    player.getPacketSender().sendMessage("You find a " + ItemAssistant.getItemName(ints[0]) + ".");
                }
            }
			break;
		}
	}
	
	public static void birdNests(Player player) {
		if (Misc.random(256) == 69 && player.tutorialProgress >= 36) {
			player.getPacketSender().sendMessage("A birds nest falls from the branches.");
			dropNest(player);
		}
	}

	public static void dropNest(Player player) {
		GameEngine.itemHandler.createGroundItem(player, 5070 + Misc.random(4), player.getX(), player.getY(), 1, player.getId());
	}

}
