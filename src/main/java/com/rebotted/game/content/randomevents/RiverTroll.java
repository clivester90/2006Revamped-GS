package com.rebotted.game.content.randomevents;

import com.rebotted.game.content.skills.SkillData;
import com.rebotted.game.npcs.NpcHandler;
import com.rebotted.game.players.Player;
import com.rebotted.util.Misc;

public class RiverTroll {
	
	public static boolean hasRiverTroll = false;

	private static int[][] riverTroll = { { 3, 10, 391, 19, 1 },
			{ 11, 20, 392, 40, 1 }, { 21, 40, 393, 80, 3 },
			{ 61, 90, 394, 105, 4 }, { 91, 110, 395, 120, 5 },
			{ 111, 138, 396, 150, 7 }, };

	public static void spawnRiverTroll(Player client) {
		for (int[] element : riverTroll) {
			if (!hasRiverTroll) {
			if (client.combatLevel >= element[0] && client.combatLevel <= element[1] && !hasRiverTroll) {
				NpcHandler.spawnNpc(client, element[2], client.absX + Misc.random(1), client.absY + Misc.random(1), client.heightLevel, 0, element[3], element[4], client.playerLevel[SkillData.ATTACK.getId()] * 2, client.playerLevel[SkillData.DEFENCE.getId()] * 2, true, false);
					client.randomActions = 0;
					hasRiverTroll = true;
					NpcHandler.npcs[element[2]].forceChat("Fishies be mine! Leave dem fishies!");
				}
			}
		}
	}
}