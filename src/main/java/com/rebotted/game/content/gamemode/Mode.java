package com.rebotted.game.content.gamemode;

import com.google.common.collect.ImmutableList;
import com.rebotted.game.players.Player;

import java.util.Arrays;
import java.util.List;

public abstract class Mode {

    /**
     * The type of mode
     */
    protected final ModeType type;

    /**
     * A list of all {@link Mode}'s that exist in the game.
     */
    private static final List<Mode> MODES = ImmutableList
            .copyOf(Arrays.asList(
                    new RegularMode(ModeType.STANDARD),
                    new HardcoreMode(ModeType.HARDCORE)
                    /*new IronmanMode(ModeType.IRON_MAN),
                    new IronmanMode(ModeType.HC_IRON_MAN),
                    new UltimateIronmanMode(ModeType.ULTIMATE_IRON_MAN)*/
            ));

    /**
     * Returns a particular {@link Mode} for the given {@link ModeType}.
     *
     * @param type the type of mode
     * @return the {@link Mode} for the type, or null if no mode exists for the type.
     */
    public static Mode forType(ModeType type) {
        return MODES.stream().filter(mode -> mode.getType().equals(type)).findFirst().orElse(null);
    }

    /**
     * Creates a new mode for a specific type
     *
     * @param type the type of mode
     */
    public Mode(ModeType type) {
        this.type = type;
    }

    public boolean isTradingPermitted() {
        return isTradingPermitted(null, null);
    }

    /**
     * Determines whether or not the player can access trading operations
     *
     * @return {@code true} if the player can access trading operations, otherwise {@code false}
     * @param player Local player (nullable)
     * @param other Other player (nullable)
     */
    public abstract boolean isTradingPermitted(Player player, Player other);

    /**
     * Determines whether or not the player can access staking operations
     *
     * @return {@code true} if the player can access staking operations, otherwise {@code false}
     */
    public abstract boolean isStakingPermitted();

    /**
     * Determines if the player is permitted to pickup items on the ground that is not theirs
     *
     * @return {@code true} if the player can pickup items that are not theirs, otherwise {@code false}
     */
    public abstract boolean isItemScavengingPermitted();

    /**
     * Determines if a particular shop, by identification, is accessible
     *
     * @param shopId the identification value of the shop
     * @return {@code true} if the shop can be accessed, otherwise {@code false}
     */
    public abstract boolean isShopAccessible(int shopId);

    /**
     * Determines if a particular item from a shop is purchasable
     *
     * @param shopId the shop identification value
     * @param itemId the item id attempting to be purchased
     * @return {@code true} if the item can be purchased from the specific shop, otherwise false
     */
    public abstract boolean isItemPurchasable(int shopId, int itemId);

    /**
     * Determines if a particular item can be sold to a shop
     *
     * @param shopId the shop id
     * @param itemId the item id
     * @return {@code true} if the item can be sold to a specific shop, otherwise {@code false}
     */
    public abstract boolean isItemSellable(int shopId, int itemId);

    /**
     * Determines if banking is permitted for this type of game mode.
     *
     * @return {@code true} if the mdoe is allowed or permitted to use the banking system.
     */
    public abstract boolean isBankingPermitted();

    public abstract boolean hasAccessToIronmanNpc();

    /**
     * The type of Mode this is
     *
     * @return the mode type
     */
    public ModeType getType() {
        return type;
    }

    public boolean is5x() {
        return type == ModeType.HARDCORE;
    }

}
