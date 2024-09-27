package com.rebotted;

import lombok.Getter;

public class GameConstants {

	@Getter
	private static final ServerState serverState = ServerState.DEBUG;
	public final static boolean SERVER_DEBUG = true;
	public static boolean DISABLE_DISCORD_MESSAGING = false;

	public final static String SERVER_NAME = "Runescape";
	
	public final static double TEST_VERSION = 2.3;
	
	public final static int ITEM_LIMIT = 15000, MAXITEM_AMOUNT = Integer.MAX_VALUE, CLIENT_VERSION = 999999,
			WORLD = 1, IPS_ALLOWED = 250, CONNECTION_DELAY = 100,
			MESSAGE_DELAY = 6000, MAX_PLAYERS = 200, REQ_AMOUNT = 150;
	
	public final static boolean SOUND = true,
			GUILDS = true,
			WORLD_LIST_FIX = false,
			PARTY_ROOM_DISABLED = true,
			PRINT_OBJECT_ID = false,
			EXPERIMENTS = false;
	
	public static int[] SIDEBARS = { 2423, 3917, 638, 3213, 1644, 5608, 1151,
			18128, 5065, 5715, 2449, 904, 147, 962 };
	
	public static boolean TUTORIAL_ISLAND = false, 
			MEMBERS_ONLY = false,
			sendServerPackets = false,
			CLUES_ENABLED = false;

	public final static int[] FUN_WEAPONS = { 2460, 2461, 2462, 2463, 2464,
			2465, 2466, 2467, 2468, 2469, 2470, 2471, 2471, 2473, 2474, 2475,
			2476, 2477 }; // fun weapons for dueling

	public static boolean ADMIN_CAN_TRADE = false; // can admins trade?
	
	public final static boolean ADMIN_DROP_ITEMS = false;
	
	public final static boolean ADMIN_CAN_SELL_ITEMS = false;
	
	public final static int RESPAWN_X = 3222; // when dead respawn here
	
	public final static int RESPAWN_Y = 3218;
	
	public final static int DUELING_RESPAWN_X = 3362;
	
	public final static int DUELING_RESPAWN_Y = 3263;
	
	public final static int NO_TELEPORT_WILD_LEVEL = 20;
	
	public final static boolean ITEM_REQUIREMENTS = true;
	
	public final static int CASTLE_WARS_X = 2439;
	
	public final static int CASTLE_WARS_Y = 3087;
	
	public static double XP_RATE = 1;
	
	public final static int SAVE_TIMER = 120; // save every 2 minute
	
	public final static int NPC_RANDOM_WALK_DISTANCE = 5;
	
	public final static int NPC_FOLLOW_DISTANCE = 10;
	
	public final static String[] UNDEAD = {
		"armoured zombie", "ankous", "banshee", "crawling hand", "dried zombie", "ghost", "ghostly warrior", "ghast",
		"mummy", "mighty banshee", "reventant imp", "reventant goblin",  "reventant icefiend",  "reventant pyrefiend",
		"reventant hobgoblin",  "reventant vampyre",  "reventant werewolf", "reventant cyclops", "reventant darkbeast",
		"reventant demon", "reventant ork",  "reventant hellhound", "reventant knight", "reventant dragon",
		"shade", "skeleton", "skeleton brute", "skeleton thug", "skeleton warload", "summoned zombie",
		"skorge", "tortured soul", "undead chicken", "undead cow", "undead one", "undead troll", "zombie", "zombie rat", "zogre"
	};
	
	public final static int TIMEOUT = 60;
	
	public final static int CYCLE_TIME = 600;
	
	public final static int BUFFER_SIZE = 10000;
}
