package org.u410.genesis.utils;

import net.md_5.bungee.api.ChatColor;

public class ColourUtils {
    public static String colour (String message)
    {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
