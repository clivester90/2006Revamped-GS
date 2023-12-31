package com.rebotted.game.content.skills.crafting;

import com.rebotted.event.CycleEvent;
import com.rebotted.event.CycleEventContainer;
import com.rebotted.event.CycleEventHandler;
import com.rebotted.game.content.skills.SkillData;
import com.rebotted.game.items.ItemAssistant;
import com.rebotted.game.players.Player;

/**
 * @author Tom
 */

public class Pottery {

	public static int unFire = 896;
	public static int Fire = 899;
	public static int softClay = 1761;

	public static void showUnfire(Player c) {
		c.getPacketSender().sendChatInterface(8938);
		c.getPacketSender().sendString("What would you like to make?", 8879);
		c.getPacketSender().sendInterfaceModel(8941, 120, 1787); // first
		c.getPacketSender().sendInterfaceModel(8942, 150, 1789); // second
		c.getPacketSender().sendInterfaceModel(8943, 150, 1791); // third
		c.getPacketSender().sendInterfaceModel(8944, 120, 5352); // 4th
		c.getPacketSender().sendInterfaceModel(8945, 150, 4438); // 5th
		c.getPacketSender().sendString("Pot", 8949);
		c.getPacketSender().sendString("Pie Dish", 8953);
		c.getPacketSender().sendString("Bowl", 8957);
		c.getPacketSender().sendString("Plant pot", 8961);
		c.getPacketSender().sendString("Pot lid", 8965);
		c.showedUnfire = true;
	}

	public static void showFire(Player c) {
		c.getPacketSender().sendChatInterface(8938);
		c.getPacketSender().sendString("What would you like to make?", 8879);
		c.getPacketSender().sendInterfaceModel(8941, 120, 1931); // first
		c.getPacketSender().sendInterfaceModel(8942, 150, 2313); // second
		c.getPacketSender().sendInterfaceModel(8943, 150, 1923); // third
		c.getPacketSender().sendInterfaceModel(8944, 120, 5350); // 4th
		c.getPacketSender().sendInterfaceModel(8945, 150, 4440); // 5th
		c.getPacketSender().sendString("Pot", 8949);
		c.getPacketSender().sendString("Pie Dish", 8953);
		c.getPacketSender().sendString("Bowl", 8957);
		c.getPacketSender().sendString("Plant pot", 8961);
		c.getPacketSender().sendString("Pot lid", 8965);
		c.showedFire = true;
	}

	public static void makeUnfire(final Player c, final int id,
			final double xp, final int level, int amount) {
		c.getPacketSender().closeAllWindows();
		c.doAmount = amount;
		c.isPotCrafting = true;
		if (c.getItemAssistant().playerHasItem(softClay)
				&& c.playerLevel[12] >= level && c.isPotCrafting) {
			c.startAnimation(unFire);
			c.getItemAssistant().deleteItem(softClay, 1);
			c.getItemAssistant().addItem(id, 1);
			c.getPacketSender().sendMessage(
					"You make the soft clay into a "
							+ ItemAssistant.getItemName(id) + ".");
			c.getPlayerAssistant().addSkillXP(xp, SkillData.CRAFTING.getId());
			c.doAmount--;
		}
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {

			@Override
			public void execute(CycleEventContainer container) {
				if (c.getItemAssistant().playerHasItem(softClay)
						&& c.playerLevel[12] >= level && !(c.doAmount <= 0)
						&& c.isPotCrafting) {
					c.startAnimation(unFire);
					c.getItemAssistant().deleteItem(softClay, 1);
					c.getItemAssistant().addItem(id, 1);
					c.getPacketSender().sendMessage("You make the soft clay into a " + ItemAssistant.getItemName(id) + ".");
					c.getPlayerAssistant().addSkillXP(xp, SkillData.CRAFTING.getId());
					c.doAmount--;
				}

				if (c.playerLevel[12] < level) {
					container.stop();
					c.getPacketSender().sendMessage("You need a crafting level of " + level + " to make this.");
				}

				if (!c.getItemAssistant().playerHasItem(softClay)) {
					container.stop();
					c.getPacketSender().sendMessage("You need soft clay to do this.");
				}

				if (!c.isPotCrafting) {
					container.stop();
				}

				if (c.doAmount <= 0) {
					container.stop();
				}

			}

			@Override
			public void stop() {
				c.isPotCrafting = false;
				c.startAnimation(65535);
			}
		}, 3);
	}

	public static void makeFire(final Player player, final int startId,
			final int finishId, final int level, final double xp, int amount) {
		player.getPacketSender().closeAllWindows();
		player.doAmount = amount;
		player.isPotCrafting = true;
		if (player.getItemAssistant().playerHasItem(startId)
				&& player.playerLevel[12] >= level && player.isPotCrafting) {
			player.getItemAssistant().deleteItem(startId, 1);
			player.getItemAssistant().addItem(finishId, 1);
			player.startAnimation(Fire);
			player.getPacketSender().sendSound(469, 100, 0);
			player.getPacketSender().sendMessage("You put a " + ItemAssistant.getItemName(startId) + " into the oven.");
			player.getPacketSender().sendMessage("You retrieve the " + ItemAssistant.getItemName(finishId) + " from the oven.");
			player.getPlayerAssistant().addSkillXP(xp, SkillData.CRAFTING.getId());
			player.doAmount--;
		}

		if (player.playerLevel[12] < level) {
			player.getPacketSender().sendMessage("You need a crafting level of " + level + " to make this.");
		}

		if (!player.getItemAssistant().playerHasItem(startId) && player.playerLevel[12] >= level) {
			player.getPacketSender().sendMessage("You need an " + ItemAssistant.getItemName(startId) + " to do this.");
		}

		CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {

			@Override
			public void execute(CycleEventContainer container) {
				if (player.getItemAssistant().playerHasItem(startId) && player.playerLevel[12] >= level && player.isPotCrafting && !(player.doAmount <= 0)) {
					player.getItemAssistant().deleteItem(startId, 1);
					player.getItemAssistant().addItem(finishId, 1);
					player.startAnimation(Fire);
					player.getPacketSender().sendSound(469, 100, 0);
					player.getPacketSender().sendMessage("You put a " + ItemAssistant.getItemName(startId) + " into the oven.");
					player.getPacketSender().sendMessage("You retrieve the " + ItemAssistant.getItemName(finishId) + " from the oven.");
					player.getPlayerAssistant().addSkillXP(xp, SkillData.CRAFTING.getId());
					player.doAmount--;
				}

				if (!player.isPotCrafting
						|| !player.getItemAssistant().playerHasItem(startId)
						|| player.playerLevel[12] < level) {
					container.stop();
				}

				if (player.doAmount <= 0) {
					container.stop();
				}

			}

			@Override
			public void stop() {
				player.isPotCrafting = false;
				player.startAnimation(65535);
			}
		}, 5);
	}

}
