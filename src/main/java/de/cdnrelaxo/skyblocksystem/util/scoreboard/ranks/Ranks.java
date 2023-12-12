package de.cdnrelaxo.skyblocksystem.util.scoreboard.ranks;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

/**
       Rank Class coded for EnderNation
       Made by: Mxscha (+KeksGauner)

       How to add a rank:
           TEST_RANK("[Prefix], NamedTextColor.[WantedColor], "[permission]");

       How to add a rank with 2 colors:
           TEST_RANK("[Prefix1]","[Prefix2], NamedTextColor.[ColorForPrefix2], NamedTextColor.[ColorForPrefix1], "[permission]");
*/
public enum Ranks {

    OWNER("Inhaber", TextColor.fromHexString("#E63521"), "rang.owner", 1),
    ADMIN("Admin", NamedTextColor.RED, "rang.admin", 2),
    SRDEVELOPER("SrDeveloper", NamedTextColor.AQUA, "rang.srdeveloper", 3),
    DEVELOPER("Developer", NamedTextColor.AQUA, "rang.developer", 4),
    SRMODERATOR("Sr", "Moderator", TextColor.fromHexString("#41E768"), TextColor.fromHexString("#20A03F"), "rang.srmoderator", 5),
    MODERATOR("Moderator", TextColor.fromHexString("#20A03F"), "rang.moderator", 6),
    SUPPORTER("Supporter", TextColor.fromHexString("#744CF1"), "rang.supporter", 7),
    BUILDER("Builder", TextColor.fromHexString("#E2BF0F"), "rang.builder", 8),
    INFLUENCER_PLUS("Influencer", "+", NamedTextColor.LIGHT_PURPLE, NamedTextColor.DARK_PURPLE,"rang.youtuberplus", 9),
    INFLUENCER("Influencer", NamedTextColor.DARK_PURPLE, "rang.youtuber", 10),
    ENDERHACKER("Platz", "halter", NamedTextColor.AQUA, NamedTextColor.DARK_AQUA, "rang.platzhalter", 11),
    ENDERHERO("Platz", "halter", NamedTextColor.DARK_GREEN, NamedTextColor.DARK_AQUA, "rang.platzhalter", 12),
    ENDERKING("Platz", "halter", NamedTextColor.GOLD, NamedTextColor.DARK_AQUA, "rang.platzhalter", 13),
    PLAYER("Spieler", TextColor.fromHexString("#A5C9EA"), "rang.spieler", 14);

    private final String prePrefix;
    private final String prefix;
    private final TextColor color;
    private final TextColor secondColor;
    private final String permission;
    private final int tablistID;

    Ranks(String prePrefix, String prefix, TextColor color, TextColor secondColor, String permission, int tablistID) {
        this.prePrefix = prePrefix;
        this.prefix = prefix;
        this.color = color;
        this.secondColor = secondColor;
        this.permission = permission;
        this.tablistID = tablistID;
    }

    Ranks(String prefix, TextColor color, String permission, int tablistID) {
        this.prefix = prefix;
        this.color = color;
        this.permission = permission;
        secondColor = null;
        prePrefix = null;
        this.tablistID = tablistID;
    }

    public String getPrefix() {
        if (prePrefix == null) {
            return prefix;
        } else {
            return prePrefix+prefix;
        }
    }

    public Component getPrefixForScoreboard() {
        if (secondColor == null || prePrefix == null) {
            return Component.text(" §8» ")
                    .append(Component.text(prefix).color(color).decorate(TextDecoration.BOLD));
        } else {
            // prePrefix = Ender(secondColor), Prefix = Hero(color)
            return Component.text(" §8» ")
                    .append(Component.text(prePrefix).color(secondColor).decorate(TextDecoration.BOLD))
                    .append(Component.text(prefix).color(color).decorate(TextDecoration.BOLD));
        }
    }

    public String getPermission() {
        return permission;
    }

    public TextColor getColor() {
        return color;
    }

    public Component getPrefixForChat() {
        if (secondColor == null || prePrefix == null) {
            return Component.text(prefix).color(color).decorate(TextDecoration.BOLD)
                    .append(Component.text(" §8| "));
        } else {
            // prePrefix = Ender(secondColor), Prefix = Hero(color)
            return Component.text(prePrefix).color(secondColor).decorate(TextDecoration.BOLD)
                        .append(Component.text(prefix).color(color).decorate(TextDecoration.BOLD)
                                .append(Component.text(" §8| ")));
        }
    }

    public int getTablistID() {
        return tablistID;
    }

    public Component getPrefixForTablist() {
        if (secondColor == null || prePrefix == null) {
            return Component.text(prefix).color(color).decorate(TextDecoration.BOLD)
                    .append(Component.text(" §8| "));
        } else {
            // prePrefix = Ender(secondColor), Prefix = Hero(color)
            return Component.text(prePrefix).color(secondColor).decorate(TextDecoration.BOLD)
                    .append(Component.text(prefix).color(color).decorate(TextDecoration.BOLD)
                            .append(Component.text(" §8| ")));
        }
    }
}
