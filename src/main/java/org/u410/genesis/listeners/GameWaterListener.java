package org.u410.genesis.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;
import org.u410.genesis.Genesis;
import org.u410.genesis.managers.GameManager;

public class GameWaterListener implements Listener {
    private Genesis genesis;
    private GameManager manager;
    public GameWaterListener(Genesis genesis, GameManager manager) {
        this.genesis = genesis;
        this.manager = manager;
    }

    @EventHandler
    public void onWater(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(manager.gameRunning && manager.gameName.equalsIgnoreCase("water")) {
            if(manager.inGame.contains(player)) {
                if(player.getLocation().getBlock().getType() == Material.WATER) {
                    this.manager.winner = player;
                    this.manager.gameEnding.run();
                }
            }
        }
    }
}
