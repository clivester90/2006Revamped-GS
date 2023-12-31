package com.rebotted.game.content.skills.smithing;

import com.rebotted.event.CycleEvent;
import com.rebotted.event.CycleEventContainer;
import com.rebotted.event.CycleEventHandler;
import com.rebotted.game.content.skills.SkillData;
import com.rebotted.game.content.skills.SkillHandler;
import com.rebotted.game.items.ItemAssistant;
import com.rebotted.game.players.Player;
import com.rebotted.util.Misc;

/**
 * @author abysspartyy
 */

public class Smelting extends SkillHandler {

	private static int COPPER = 436, TIN = 438, IRON = 440, COAL = 453,
			MITH = 447, ADDY = 449, RUNE = 451, GOLD = 444, SILVER = 442;
	private final static int[] SMELT_FRAME = { 2405, 2406, 2407, 2409, 2410,
			2411, 2412, 2413 };
	private final static int[] SMELT_BARS = { 2349, 2351, 2355, 2353, 2357,
			2359, 2361, 2363 };

	/**
	 * Handles Smelting data.
	 */
	public static int[][] data = {
			// index ,lvl required, XP, primary item required, secondary item required, secondary item amount, output item(BAR)
			{ 1, 1, 6, COPPER, TIN, 1, 2349 }, // Bronze
			{ 2, 15, 12, IRON, -1, -1, 2351 }, // iron
			{ 3, 20, 17, IRON, COAL, 2, 2353 }, // Steel
			{ 4, 50, 30, MITH, COAL, 4, 2359 }, // Mith
			{ 5, 70, 37, ADDY, COAL, 6, 2361 }, // Addy
			{ 6, 85, 50, RUNE, COAL, 8, 2363 }, // Rune
			{ 7, 20, 13, SILVER, -1, -1, 2355 }, // Silver
			{ 8, 40, 22, GOLD, -1, -1, 2357 }, // GOLD
	};

	/**
	 * Sends the interface
	 * 
	 * @param c
	 */
	public static void startSmelting(Player c, int object) {
		for (int j = 0; j < SMELT_FRAME.length; j++) {
			c.getPacketSender().sendInterfaceModel(SMELT_FRAME[j], 150, SMELT_BARS[j]);
		}
		c.getPacketSender().sendChatInterface(2400);
		c.isSmelting = true;
	}

	/**
	 * Sets the amount of bars that can be smelted. (EG. 5,10,28 times)
	 * 
	 * @param c
	 * @param amount
	 */
	public static void doAmount(Player c, int amount, int bartype) {
		c.doAmount = amount;
		if (amount == 28) {
			c.smeltingItem = bartype;
			c.getOutStream().createFrame(27);
		} else {
			smeltBar(c, bartype);
		}
	}

	/**
	 * Main method. Smelting
	 * 
	 * @param c
	 */
	public static void smeltBar(final Player c, int bartype) {
		for (int[] datum : data) {
			if (bartype == datum[0]) {
				// Check player has the correct smithing level
				if (c.playerLevel[SkillData.SMITHING.getId()] < datum[1]) { // Smithing level
					c.getDialogueHandler().sendStatement("You need a smithing level of at least " + datum[1] + " in order smelt this bar.");
					return;
				}

				// Check the player has all required items
				if (datum[4] <= 0) { // Bars with only a primary requirement
					if (!c.getItemAssistant().playerHasItem(datum[3])) {
						c.getPacketSender().sendMessage("You need " + ItemAssistant.getItemName(datum[3]).toLowerCase() + " to make this bar.");
						c.getPacketSender().closeAllWindows();
						return;
					}
				} else { // Bars with a secondary requirement
					if (!c.getItemAssistant().playerHasItem(datum[3]) || !c.getItemAssistant().playerHasItem(datum[4], datum[5])) {
						c.getPacketSender().sendMessage("You need 1 " + ItemAssistant.getItemName(datum[3]).toLowerCase() + " and " + datum[5] + " " + ItemAssistant.getItemName(datum[4]).toLowerCase() + " to make this bar.");
						c.getPacketSender().closeAllWindows();
						return;
					}
				}

				if (c.playerSkilling[13]) {
					return;
				}

				c.playerSkilling[13] = true;
				c.stopPlayerSkill = true;

				c.playerSkillProp[13][0] = datum[0];// index
				c.playerSkillProp[13][1] = datum[1];// Level required
				c.playerSkillProp[13][2] = datum[2];// XP
				c.playerSkillProp[13][3] = datum[3];// primary item required
				c.playerSkillProp[13][4] = datum[4];// secondary item required
				c.playerSkillProp[13][5] = datum[5];// secondary item amount
				c.playerSkillProp[13][6] = datum[6];// output item

				c.getPacketSender().closeAllWindows();
				c.startAnimation(899);
				c.getPacketSender().sendSound(352, 100, 1);
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {

					@Override
					public void execute(CycleEventContainer container) {
						deleteTime(c);
						// Remove primary item
						c.getItemAssistant().deleteItem(c.playerSkillProp[13][3], 1);
						// Check if required and remove secondary items
						if (c.playerSkillProp[13][4] > 0 && c.playerSkillProp[13][5] > 0) {
							c.getItemAssistant().deleteItem(c.playerSkillProp[13][4], c.playerSkillProp[13][5]);
						}

						if (c.playerSkillProp[13][3] == IRON && c.playerSkillProp[13][4] == -1) {
							// Ring of forging
							if (c.playerEquipment[c.playerRing] == 2568) {
								c.getPlayerAssistant().addSkillXP(c.playerSkillProp[13][2], SkillData.SMITHING.getId());
								c.getItemAssistant().addItem(c.playerSkillProp[13][6], 1);// item
								c.getPacketSender().sendMessage("You receive an " + ItemAssistant.getItemName(c.playerSkillProp[13][6]).toLowerCase() + ".");

							} else {
								if (Misc.random(100) >= 50) {
									c.getPlayerAssistant().addSkillXP(c.playerSkillProp[13][2], SkillData.SMITHING.getId());
									c.getItemAssistant().addItem(c.playerSkillProp[13][6], 1);// item
									c.getPacketSender().sendMessage("You receive an " + ItemAssistant.getItemName(c.playerSkillProp[13][6]).toLowerCase() + ".");
								} else {
									c.getPacketSender().sendMessage("You failed to smelt the iron bar.");
								}
							}
						} else if (c.playerSkillProp[13][3] == GOLD && c.playerEquipment[c.playerHands] == 776) {
							c.getPacketSender().sendMessage("You receive a " + ItemAssistant.getItemName(c.playerSkillProp[13][6]).toLowerCase() + ".");
							c.getPlayerAssistant().addSkillXP(56.2, SkillData.SMITHING.getId());
							c.getItemAssistant().addItem(c.playerSkillProp[13][6], 1);// item
						} else {
							c.getPacketSender().sendMessage("You receive a " + ItemAssistant.getItemName(c.playerSkillProp[13][6]).toLowerCase() + ".");
							c.getPlayerAssistant().addSkillXP(c.playerSkillProp[13][2], SkillData.SMITHING.getId());
							c.getItemAssistant().addItem(c.playerSkillProp[13][6], 1);// item
						}

						////////////////////// CHECKING //////////////////////
						if (c.doAmount <= 0) {
							resetSmelting(c);
							container.stop();
						}
						if (!c.playerSkilling[13]) {
							resetSmelting(c);
							container.stop();
						}
						if (!c.stopPlayerSkill) {
							resetSmelting(c);
							container.stop();
						}
						if (!c.getItemAssistant().playerHasItem(c.playerSkillProp[13][3], 1)) {
							c.getPacketSender().sendMessage("You don't have enough ores to continue smelting!");
							resetSmelting(c);
							container.stop();
						}
						if (c.playerSkillProp[13][4] > 0) {
							if (!c.getItemAssistant().playerHasItem(c.playerSkillProp[13][4], c.playerSkillProp[13][5])) {
								c.getPacketSender().sendMessage("You don't have enough ores to continue smelting!");
								resetSmelting(c);
								container.stop();
							}
						}

					}

					@Override
					public void stop() {

					}
				}, (int) 5.9);

				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {// animation

					@Override
					public void execute(CycleEventContainer container) {
						if (!c.playerSkilling[13]) {
							resetSmelting(c);
							container.stop();
							return;
						}
						c.startAnimation(899);
						c.getPacketSender().sendSound(352, 100, 1);
						if (!c.stopPlayerSkill) {
							container.stop();
						}
					}

					@Override
					public void stop() {

					}
				}, (int) 5.8);

			}
		}
	}

	/**
	 * Gets the index from DATA for which bar to smelt
	 */
	public static void getBar(Player c, int i) {
		switch (i) {
		case 15147: // bronze (1)
			doAmount(c, 1, 1);// (c,amount,Index in data)
			break;
		case 15146: // bronze (5)
			doAmount(c, 5, 1);
			break;
		case 10247: // bronze (10)
			doAmount(c, 10, 1);
			break;
		case 9110:// bronze (X)
			doAmount(c, 28, 1);
			break;

		case 15151: // iron (1)
			doAmount(c, 1, 2);
			break;
		case 15150: // iron (5)
			doAmount(c, 5, 2);
			break;
		case 15149: // iron (10)
			doAmount(c, 10, 2);
			break;
		case 15148:// Iron (X)
			doAmount(c, 28, 2);
			break;

		case 15159: // Steel (1)
			doAmount(c, 1, 3);
			break;
		case 15158: // Steel (5)
			doAmount(c, 5, 3);
			break;
		case 15157: // Steel (10)
			doAmount(c, 10, 3);
			break;
		case 15156:// Steel (X)
			doAmount(c, 28, 3);
			break;

		case 29017: // mith (1)
			doAmount(c, 1, 4);
			break;
		case 29016: // mith (5)
			doAmount(c, 5, 4);
			break;
		case 24253: // mith (10)
			doAmount(c, 10, 4);
			break;
		case 16062:// Mith (X)
			doAmount(c, 28, 4);
			break;

		case 29022: // Addy (1)
			doAmount(c, 1, 5);
			break;
		case 29020: // Addy (5)
			doAmount(c, 5, 5);
			break;
		case 29019: // Addy (10)
			doAmount(c, 10, 5);
			break;
		case 29018:// Addy (X)
			doAmount(c, 28, 5);
			break;

		case 29026: // RUNE (1)
			doAmount(c, 1, 6);
			break;
		case 29025: // RUNE (5)
			doAmount(c, 5, 6);
			break;
		case 29024: // RUNE (10)
			doAmount(c, 10, 6);
			break;
		case 29023:// Rune (X)
			doAmount(c, 28, 6);
			break;

		case 15155:// SILVER (1)
			doAmount(c, 1, 7);
			break;
		case 15154:// SILVER (5)
			doAmount(c, 5, 7);
			break;
		case 15153:// SILVER (10)
			doAmount(c, 10, 7);
			break;
		case 15152:// SILVER (28)
			doAmount(c, 28, 7);
			break;

		case 15163:// Gold (1)
			doAmount(c, 1, 8);
			break;
		case 15162:// Gold (5)
			doAmount(c, 5, 8);
			break;
		case 15161:// Gold (10)
			doAmount(c, 10, 8);
			break;
		case 15160:// Gold (28)
			doAmount(c, 28, 8);
			break;
		}

	}

	/**
	 * Resets Smelting
	 */
	public static void resetSmelting(Player player) {
		player.playerSkilling[13] = false;
		player.stopPlayerSkill = false;
		player.isSmelting = false;
		for (int i = 0; i < 7; i++) {
			player.playerSkillProp[13][i] = -1;
		}
	}

}