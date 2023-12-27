package com.rebotted.game.content.gamemode;

import com.rebotted.game.players.Player;

public class RegularMode extends Mode {

    /**
     * Creates a new default mode
     *
     * @param type the default mode
     */
    public RegularMode(ModeType type) {
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
        return shopId != 41;
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
        if (shopId == 113) {
            return itemId != 385 && itemId != 139 && itemId != 3026 && itemId != 6687;
        }
        return true;
    }

    @Override
    public boolean isBankingPermitted() {
        return true;
    }

    @Override
    public boolean hasAccessToIronmanNpc() {
        return false;
    }

}
