package com.rebotted.game.players.right;

import com.rebotted.game.content.gamemode.Mode;
import com.rebotted.game.content.gamemode.ModeType;
import com.rebotted.util.Misc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * The rights of a player determines their authority. Every right can be viewed with a name and a value. The value is used to separate each right from one another.
 * 
 * @author Jason MacK
 * @date January 22, 2015, 5:23:49 PM
 */
public enum Right implements Comparator<Right> {
	PLAYER(0, "000000"),
	MODERATOR(1, "#0000ff"),
	ADMINISTRATOR(2, "F5FF0F", MODERATOR),
	HARDCORE(3, "437100"),
	IRONMAN(4, "3A3A3A"),
	ULTIMATE_IRONMAN(5, "717070"),
	HC_IRONMAN(6, "60201f");
	private static final Logger logger = LoggerFactory.getLogger(Right.class);

	/**
	 * Display groups. You can have one displayed right from the first group,
	 * the second group you can have as many until you reach 2 displayed groups.
	 */
	public static final EnumSet[] DISPLAY_GROUPS = {
			EnumSet.of(MODERATOR, ADMINISTRATOR),
			EnumSet.of(IRONMAN, ULTIMATE_IRONMAN, HC_IRONMAN, HARDCORE)
	};

	public static final EnumSet<Right> IRONMAN_SET = EnumSet.of(IRONMAN, HC_IRONMAN, ULTIMATE_IRONMAN);

	/**
	 * An array of {@link Right} objects that represent the order in which some rights should be prioritized over others. The index at which a {@link Right} object exists
	 * determines it's priority. The lower the index the less priority that {@link Right} has over another. The list is ordered from lowest priority to highest priority.
	 * <p>
	 * An example of this would be comparing a {@link #MODERATOR} to a {@link #ADMINISTRATOR}. An {@link #ADMINISTRATOR} can be seen as more 'powerful' when compared to a
	 * {@link #MODERATOR} because they have more power within the community.
	 * </p>
	 */
	public static final Right[] PRIORITY = { PLAYER, MODERATOR, ADMINISTRATOR, HARDCORE, HC_IRONMAN, IRONMAN, ULTIMATE_IRONMAN};


	/**
	 * The level of rights that define this
	 */
	private final int right;

	/**
	 * The rights inherited by this right
	 */
	private final List<Right> inherited;

	/**
	 * The color associated with the right
	 */
	private final String color;

	/**
	 * Creates a new right with a value to differentiate it between the others
	 * 
	 * @param right the right required
	 * @param color a color thats used to represent the players name when displayed
	 * @param inherited the right or rights inherited with this level of right
	 */
	Right(int right, String color, Right... inherited) {
		this.right = right;
		this.inherited = Arrays.asList(inherited);
		this.color = color;
	}

	public String getFormattedName() {
		return Misc.capitalizeEveryWord(name().toLowerCase().replace("_", " "));
	}

	public Mode getMode() {
		switch (this) {
			case IRONMAN:
				return Mode.forType(ModeType.IRON_MAN);
			case ULTIMATE_IRONMAN:
				return Mode.forType(ModeType.ULTIMATE_IRON_MAN);
			case HC_IRONMAN:
				return Mode.forType(ModeType.HC_IRON_MAN);
			case HARDCORE:
				return Mode.forType(ModeType.HARDCORE);
		}

		return Mode.forType(ModeType.STANDARD);
	}

	/**
	 * The rights of this enumeration
	 * 
	 * @return the rights
	 */
	public int getValue() {
		return right;
	}

	/**
	 * Returns a {@link Right} object for the value.
	 * 
	 * @param value the right level
	 * @return the rights object
	 */
	public static Right get(int value) {
		return RIGHTS.stream().filter(element -> element.right == value).findFirst().orElse(PLAYER);
	}

	/**
	 * A {@link Set} of all {@link Right} elements that cannot be directly modified.
	 */
	private static final Set<Right> RIGHTS = Collections.unmodifiableSet(EnumSet.allOf(Right.class));

	/**
	 * The color associated with the right
	 * 
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Determines if this level of rights inherited another level of rights
	 * 
	 * @param right the level of rights we're looking to determine is inherited
	 * @return {@code true} if the rights are inherited, otherwise {@code false}
	 */
	public boolean isOrInherits(Right right) {
		/*if (this == right) 
			return true;
		for (int i = 0; i < inherited.size(); i++) {
			if (this == inherited.get(i))
				return true;
		}
		System.out.println("inherited.size: "+inherited.size());
		return false;*/
		return this == right || !inherited.isEmpty() && inherited.stream().anyMatch(r -> r.isOrInherits(right));
	}
	
	/**
	 * Determines if the players rights equal that of {@link Right#MODERATOR}
	 * @return	true if they are of type moderator
	 */
	public boolean isModerator() {
		return equals(MODERATOR);
	}
	
	/**
	 * Determines if the players rights equal that of {@link Right#ADMINISTRATOR}
	 * @return	true if they are of type administrator
	 */
	public boolean isAdministrator() {
		return equals(ADMINISTRATOR);
	}

	public boolean isStaff() {
		return isModerator() || isAdministrator();
	}

	@Override
	public String toString() {
		return Misc.capitalize(name().replaceAll("_", " "));
	}

	@Override
	public int compare(Right arg0, Right arg1) {
		return 0;
	}

}
