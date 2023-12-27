package com.rebotted.game.content.gamemode;

import com.rebotted.game.players.Player;

public class RogueMode extends Mode {

    /**
     * Creates a new mode for a specific type
     *
     * @param type the type of mode
     */
    public RogueMode(ModeType type) {
        super(type);
    }

    @Override
    public boolean isTradingPermitted(Player player, Player other) {
        return true;
    }

    @Override
    public boolean isStakingPermitted() {
        return true;
    }

    @Override
    public boolean isItemScavengingPermitted() {
        return true;
    }

    @Override
    public boolean isShopAccessible(int shopId) {
        return true;
    }

    @Override
    public boolean isItemPurchasable(int shopId, int itemId) {
        if (shopId == 171) {
            return itemId != 8866 && itemId != 8868;
        }
        return true;
    }

    @Override
    public boolean isItemSellable(int shopId, int itemId) {
        return true;
    }

    @Override
    public boolean isBankingPermitted() {
        return true;
    }

    @Override
    public boolean hasAccessToIronmanNpc() {
        return true;
    }
}
