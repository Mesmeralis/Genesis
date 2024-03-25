package org.u410.genesis.listeners;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.u410.genesis.Genesis;
import org.u410.genesis.managers.GameManager;
import org.u410.genesis.utils.ColourUtils;

public class GameWaterListener implements Listener {
    private final Genesis genesis;
    private final GameManager manager;
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
                        this.manager.gameRunning = false;
                        this.manager.inGame.clear();
                        Bukkit.getServer().broadcastMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &eThe &d" + this.manager.gameName + "&e game has ended."));
                        Bukkit.getServer().broadcastMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &eThe winner is &a" + this.manager.winner.getName() + "&e!"));
                        Genesis.getEconomy().depositPlayer(player, 100);
                        this.manager.winner.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &eYou have earned &a100 &ecoins! Your total is now: &a" +
                                this.genesis.getEconomy().getBalance(player)));
                    }
            }
        }
    }
}
