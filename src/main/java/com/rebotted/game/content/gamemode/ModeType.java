package com.rebotted.game.content.gamemode;

import com.rebotted.game.content.skills.SkillConstants;

public enum ModeType {
    STANDARD,
    IRON_MAN,
    ULTIMATE_IRON_MAN,
    HC_IRON_MAN,
    HARDCORE;

    public boolean isStandardRate(SkillConstants skill) {
        switch (this) {
            case STANDARD:
            case IRON_MAN:
            case ULTIMATE_IRON_MAN:
            case HC_IRON_MAN:
                return true;
            default:
                return false;
        }
    }
    public String getFormattedName() {
        switch (this) {
            case STANDARD:
                return "Standard";
            case IRON_MAN:
                return "Ironman";
            case ULTIMATE_IRON_MAN:
                return "Ultimate Ironman";
            case HC_IRON_MAN:
                return "Hardcore Ironman";
            case HARDCORE:
                return "Hardcore";
            default:
                throw new IllegalStateException("No format option for: " + this);
        }
    }
}
