package org.u410.genesis.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.u410.genesis.Genesis;
import org.u410.genesis.utils.ColourUtils;

public class JoinQuitListener implements Listener {
    private Genesis genesis;
    public JoinQuitListener(Genesis genesis) {
        this.genesis = genesis;
    }

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(ColourUtils.colour(genesis.getConfig().getString("joinMessage").replace("%p%", player.getName())));
        if(!player.hasPlayedBefore()){
            player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix()  + "&a This server is powered by Genesis, a custom core."));
            player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix()  + "&a Please contact &dU410 &aif any issues arise."));
        }
        if(this.genesis.getConfig().getBoolean("forcedSpawn") == true) {
            player.teleport(this.genesis.getConfig().getLocation("spawn"));
        }
    }

    @EventHandler
    public void onPlayerQuit (PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(ColourUtils.colour(genesis.getConfig().getString("quitMessage").replace("%p%", player.getName())));
    }
}
