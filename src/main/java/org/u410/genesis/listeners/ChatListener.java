package org.u410.genesis.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.u410.genesis.Genesis;
import org.u410.genesis.utils.ColourUtils;

public class ChatListener implements Listener {
    private Genesis genesis;
    public ChatListener(Genesis genesis) {
        this.genesis = genesis;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        event.setFormat(ColourUtils.colour(this.genesis.getConfig().getString("chatFormat")
                .replace("%prefix%", this.genesis.getPrefix(player))
                .replace("%p%", player.getName()).replace("%msg%", event.getMessage())));
    }
}
