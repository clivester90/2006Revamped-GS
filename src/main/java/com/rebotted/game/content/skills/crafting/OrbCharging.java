package com.rebotted.game.content.skills.crafting;

import com.rebotted.event.CycleEvent;
import com.rebotted.event.CycleEventContainer;
import com.rebotted.event.CycleEventHandler;
import com.rebotted.game.items.ItemAssistant;
import com.rebotted.game.players.Player;

public class OrbCharging {

	public static void chargeOrbs(final Player player, final int spellId, final int objectId) {
		if (player.isCrafting) {
			return;
		}
		for (final Orb l : Orb.values()) {
			if (objectId == l.getObjectId(objectId)) {
				if (l.getSpell() == spellId) {
					if (!player.getItemAssistant().playerHasItem(567, 1)) {
						player.getPacketSender().sendMessage("You need 1 " + ItemAssistant.getItemName(567).toLowerCase() + " to make "
								+ ItemAssistant.getItemName(l.getProduct()).toLowerCase() + ".");
						player.getPacketSender().closeAllWindows();
						return;
					}
					player.getPacketSender().closeAllWindows();
					player.isCrafting = true;
					player.skillAmount = l.getAmount(objectId);
					CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer container) {
							if (player.isCrafting) {
								if (!player.getItemAssistant().playerHasItem(567, 1)) {
									player.getPacketSender().sendMessage("You have run out of unpowered orbs.");
									container.stop();
									return;
								}
								if (!player.getCombatAssistant().checkMagicReqs(l.getSpellConfig())) {
									container.stop();
									return;
								}
								if (player.skillAmount == 0) {
									container.stop();
									return;
								}
								player.startAnimation(726);
								player.gfx100(l.getOrbGfx());
								player.getItemAssistant().deleteItem(567, 1);
								player.getItemAssistant().addItem(l.getProduct(), 1);
								player.getPlayerAssistant().addSkillXP((int) l.getXP(), 6);
								player.skillAmount--;
								if (!player.getItemAssistant().playerHasItem(567, 1)) {
									container.stop();
									return;
								}
							} else {
								container.stop();
							}
						}

						@Override
						public void stop() {
							player.isCrafting = false;
						}
					}, 3);
				}
			}
		}
	}

	public enum Orb {
		AIR(new int[][] { { 2152, 28 } }, 1058, 567, 573, 66, 76, 150, 93),
		FIRE(new int[][] { { 2153, 28 }}, 1056, 567, 569, 62, 73, 152, 92),
		EARTH(new int[][] { { 2150, 28} }, 1054, 567, 575, 58, 70, 151, 91),
		WATER(new int[][] { { 2151, 28 } }, 1051, 567, 571, 54, 66, 149, 90);

		private final int[][] objectId;
		private final int spellId;
		private final int orb;
		private final int product;
		private final int level;
		private final int gfx;
		private final int spellConfig;
		private final double xp;

		Orb(final int[][] objectId, final int spellId, final int orb, final int product, final int level, final double xp, final int gfx, final int spellConfig) {
			this.objectId = objectId;
			this.spellId = spellId;
			this.orb = orb;
			this.product = product;
			this.level = level;
			this.xp = xp;
			this.gfx = gfx;
			this.spellConfig = spellConfig;
		}

		public int getObjectId(final int object) {
			for (int[] ints : objectId) {
				if (object == ints[0]) {
					return ints[0];
				}
			}
			return -1;
		}

		public int getAmount(final int object) {
			for (int[] ints : objectId) {
				if (object == ints[0]) {
					return ints[1];
				}
			}
			return -1;
		}

		public int getSpell() {
			return spellId;
		}

		public int getOrb() {
			return orb;
		}

		public int getProduct() {
			return product;
		}

		public int getLevel() {
			return level;
		}

		public double getXP() {
			return xp;
		}
		
		public int getOrbGfx() {
			return gfx;
		}

		public int getSpellConfig() {
			return spellConfig;
		}
	}
	
}