package com.rebotted.game.content.gamemode;

import com.rebotted.game.content.skills.SkillConstants;

public enum ModeType {
    STANDARD,
    IRON_MAN,
    ULTIMATE_IRON_MAN,
    OSRS,
    HC_IRON_MAN,
    ROGUE,
    ROGUE_HARDCORE_IRONMAN,
    ROGUE_IRONMAN,
    GROUP_IRONMAN,
    EVENT_MAN;

    public boolean isStandardRate(SkillConstants skill) {
        switch (this) {
            case STANDARD:
            case IRON_MAN:
            case ULTIMATE_IRON_MAN:
            case HC_IRON_MAN:
            case GROUP_IRONMAN:
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
            case OSRS:
                return "OSRS";
            case HC_IRON_MAN:
                return "Hardcore Ironman";
            case ROGUE:
                return "Rogue";
            case ROGUE_HARDCORE_IRONMAN:
                return "Rogue Hardcore Ironman";
            case ROGUE_IRONMAN:
                return "Rogue Ironman";
            case GROUP_IRONMAN:
                return "Group Ironman";
            default:
                throw new IllegalStateException("No format option for: " + this);
        }
    }
}
