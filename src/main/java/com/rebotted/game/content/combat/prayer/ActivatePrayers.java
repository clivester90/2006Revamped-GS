package com.rebotted.game.content.combat.prayer;

import com.rebotted.game.content.music.sound.SoundList;
import com.rebotted.game.players.Player;

public class ActivatePrayers {

	public static void activatePrayer(Player player, int i) {
		if (player.duelRule[7]) {
			for (int p = 0; p < player.getPrayer().PRAYER.length; p++) { // reset
																	// prayer
																	// glows
				player.getPrayer().prayerActive[p] = false;
				player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[p],
						0);
			}
			player.getPacketSender().sendMessage(
					"Prayer has been disabled in this duel!");
			return;
		}
		if (i == 24 && player.playerLevel[1] < 65) {
			player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[i], 0);
			player.getPacketSender().sendMessage(
					"You may not use this prayer yet.");
			return;
		}
		if (i == 25 && player.playerLevel[1] < 70) {
			player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[i], 0);
			player.getPacketSender().sendMessage(
					"You may not use this prayer yet.");
			return;
		}
		int[] defencePrayer = { 0, 5, 13, 24, 25 };
		int[] strengthPrayer = { 1, 6, 14, 24, 25 };
		int[] attackPrayer = { 2, 7, 15, 24, 25 };
		int[] rangePrayer = { 3, 11, 19 };
		int[] magePrayer = { 4, 12, 20 };

		if (player.playerLevel[5] > 0) {
			if (player.getPlayerAssistant().getLevelForXP(player.playerXP[5]) >= player
					.getPrayer().PRAYER_LEVEL_REQUIRED[i]) {
				boolean headIcon = false;
				switch (i) {
				case 0:
				case 5:
				case 13:
					if (!player.getPrayer().prayerActive[i]) {
						for (int k : defencePrayer) {
							if (k != i) {
								player.getPrayer().prayerActive[k] = false;
								player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[k], 0);
							}
						}
					}
					break;

				case 1:
				case 6:
				case 14:
					if (!player.getPrayer().prayerActive[i]) {
						for (int item : strengthPrayer) {
							if (item != i) {
								player.getPrayer().prayerActive[item] = false;
								player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[item], 0);
							}
						}
						for (int value : rangePrayer) {
							player.getPrayer().prayerActive[value] = false;
							player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[value], 0);
						}
						for (int k : magePrayer) {
							player.getPrayer().prayerActive[k] = false;
							player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[k], 0);
						}
					}
					break;

				case 2:
				case 7:
				case 15:
					if (!player.getPrayer().prayerActive[i]) {
						for (int item : attackPrayer) {
							if (item != i) {
								player.getPrayer().prayerActive[item] = false;
								player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[item], 0);
							}
						}
						for (int value : rangePrayer) {
							player.getPrayer().prayerActive[value] = false;
							player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[value], 0);
						}
						for (int k : magePrayer) {
							player.getPrayer().prayerActive[k] = false;
							player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[k], 0);
						}
					}
					break;

				case 3:// range prays
				case 11:
				case 19:
					if (!player.getPrayer().prayerActive[i]) {
						for (int element : attackPrayer) {
							player.getPrayer().prayerActive[element] = false;
							player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[element], 0);
						}
						for (int item : strengthPrayer) {
							player.getPrayer().prayerActive[item] = false;
							player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[item], 0);
						}
						for (int value : rangePrayer) {
							if (value != i) {
								player.getPrayer().prayerActive[value] = false;
								player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[value], 0);
							}
						}
						for (int k : magePrayer) {
							player.getPrayer().prayerActive[k] = false;
							player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[k], 0);
						}
					}
					break;
				case 4:
				case 12:
				case 20:
					if (!player.getPrayer().prayerActive[i]) {
						for (int element : attackPrayer) {
							player.getPrayer().prayerActive[element] = false;
							player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[element], 0);
						}
						for (int item : strengthPrayer) {
							player.getPrayer().prayerActive[item] = false;
							player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[item], 0);
						}
						for (int value : rangePrayer) {
							player.getPrayer().prayerActive[value] = false;
							player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[value], 0);
						}
						for (int k : magePrayer) {
							if (k != i) {
								player.getPrayer().prayerActive[k] = false;
								player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[k], 0);
							}
						}
					}
					break;
				case 10:
					player.lastProtItem = System.currentTimeMillis();
					break;

				case 16:
				case 17:
				case 18:
					if (System.currentTimeMillis()
							- player.getPrayer().stopPrayerDelay < 5000) {
						player.getPacketSender()
								.sendMessage(
										"You have been injured and can't use this prayer!");
						player.getPacketSender().sendConfig(
								player.getPrayer().PRAYER_GLOW[16], 0);
						player.getPacketSender().sendConfig(
								player.getPrayer().PRAYER_GLOW[17], 0);
						player.getPacketSender().sendConfig(
								player.getPrayer().PRAYER_GLOW[18], 0);
						return;
					}
					if (i == 16) {
						player.protMageDelay = System.currentTimeMillis();
					} else if (i == 17) {
						player.protRangeDelay = System.currentTimeMillis();
					} else {
						player.protMeleeDelay = System.currentTimeMillis();
					}
				case 21:
				case 22:
				case 23:
					headIcon = true;
					for (int p = 16; p < 24; p++) {
						if (i != p && p != 19 && p != 20) {
							player.getPrayer().prayerActive[p] = false;
							player.getPacketSender().sendConfig(
									player.getPrayer().PRAYER_GLOW[p], 0);
						}
					}
					break;
				case 24:
				case 25:
					if (!player.getPrayer().prayerActive[i]) {
						for (int i1 : attackPrayer) {
							if (i1 != i) {
								player.getPrayer().prayerActive[i1] = false;
								player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[i1], 0);
							}
						}
						for (int element : strengthPrayer) {
							if (element != i) {
								player.getPrayer().prayerActive[element] = false;
								player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[element], 0);
							}
						}
						for (int item : rangePrayer) {
							player.getPrayer().prayerActive[item] = false;
							player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[item], 0);
						}
						for (int value : magePrayer) {
							player.getPrayer().prayerActive[value] = false;
							player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[value], 0);
						}
						for (int k : defencePrayer) {
							if (k != i) {
								player.getPrayer().prayerActive[k] = false;
								player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[k], 0);
							}
						}
					}
					break;
				}

				if (!headIcon) {
					if (!player.getPrayer().prayerActive[i]) {
						player.getPrayer().prayerActive[i] = true;
						player.getPacketSender().sendConfig(
								player.getPrayer().PRAYER_GLOW[i], 1);
					} else {
						player.getPrayer().prayerActive[i] = false;
						player.getPacketSender().sendConfig(
								player.getPrayer().PRAYER_GLOW[i], 0);
					}
				} else {
					if (!player.getPrayer().prayerActive[i]) {
						player.getPrayer().prayerActive[i] = true;
						player.getPacketSender().sendConfig(
								player.getPrayer().PRAYER_GLOW[i], 1);
						player.headIcon = player.getPrayer().PRAYER_HEAD_ICONS[i];
						if (i == 16)
							player.getPacketSender().sendSound(SoundList.PROTECT_MAGIC, 100, 0);
						else if (i == 17)
							player.getPacketSender().sendSound(SoundList.PROTECT_RANGE, 100, 0);
						else if (i == 18)
							player.getPacketSender().sendSound(SoundList.PROTECT_MELEE, 100, 0);
						player.getPlayerAssistant().requestUpdates();
					} else {
						player.getPrayer().prayerActive[i] = false;
						player.getPacketSender().sendConfig(
								player.getPrayer().PRAYER_GLOW[i], 0);
						player.headIcon = -1;
						player.getPacketSender().sendSound(SoundList.NO_PRAY, 100, 0);
						player.getPlayerAssistant().requestUpdates();
					}
				}
			} else {
				player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[i],
						0);
				player.getPacketSender().sendString(
						"You need a @blu@Prayer level of "
								+ player.getPrayer().PRAYER_LEVEL_REQUIRED[i]
								+ " to use " + player.getPrayer().PRAYER_NAME[i]
								+ ".", 357);
				player.getPacketSender().sendString("Click here to continue", 358);
				player.getPacketSender().sendChatInterface(356);
			}
		} else {
			player.getPacketSender().sendConfig(player.getPrayer().PRAYER_GLOW[i], 0);
			player.getPacketSender().sendMessage("You have run out of prayer points!");
			player.getPacketSender().sendSound(SoundList.NO_PRAY, 100, 0);
		}

	}

}
