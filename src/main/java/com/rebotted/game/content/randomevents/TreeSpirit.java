package com.rebotted.game.content.randomevents;

import com.rebotted.game.content.skills.SkillData;
import com.rebotted.game.npcs.NpcHandler;
import com.rebotted.game.players.Player;
import com.rebotted.util.Misc;

public class TreeSpirit {

	private static int[][] treeSpirit = {
			// combat, combat 2, npcid, hitpoints, levels
			{ 3, 10, 438, 28, 1 }, { 11, 20, 439, 36, 1 },
			{ 21, 40, 440, 57, 3 }, { 61, 90, 441, 90, 4 },
			{ 91, 110, 442, 130, 5 }, { 111, 138, 443, 160, 7 }, };
	
	private static int checkStats(Player p) {
		return p.getPlayerAssistant().getLevelForXP(p.playerXP[SkillData.HITPOINTS.getId()]) * 2;
	}
	
	public static void spawnTreeSpirit(Player p) {
		for (int[] element : treeSpirit) {
			if (!p.treeSpiritSpawned) {
			if (p.combatLevel >= element[0] && p.combatLevel <= element[1]) {
				NpcHandler.spawnNpc(p, element[2], p.absX + Misc.random(1),
						p.absY + Misc.random(1), p.heightLevel, 0, element[3],
						element[4], checkStats(p), p.playerLevel[SkillData.DEFENCE.getId()] * 2, true,
						false);
				NpcHandler.npcs[element[2]]
						.forceChat("Leave these woods and never return!");
				p.treeSpiritSpawned = true;
				p.randomActions = 0;
				}
			}
		}
	}
}