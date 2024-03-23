package org.u410.genesis.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.u410.genesis.Genesis;
import org.u410.genesis.utils.ColourUtils;

import java.util.ArrayList;

public class GameManager {
    private Genesis genesis;
    public String gameName;
    public boolean gameRunning = false;
    public ArrayList<Player> inGame = new ArrayList<>();
    public Player winner;

    public GameManager(Genesis genesis) {
        this.genesis = genesis;
    }

    public void runGame(String gameName, int delay) {
        if(gameName.equalsIgnoreCase("water")) {
            Bukkit.getServer().broadcastMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &eThe &d" + gameName + "&e game is starting soon!"));
            Bukkit.getServer().broadcastMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &eYou have &a" + delay + "&e seconds to join. Do &d/joingame &eto join!"));
            Bukkit.getScheduler().scheduleAsyncDelayedTask(this.genesis, () -> {
                gameRunning = true;
                Bukkit.getServer().broadcastMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &eThe &d" + gameName + "&e game has begun."));
            }, delay*20L);
        } else {
            Bukkit.getServer().broadcastMessage(ColourUtils.colour(this.genesis.genesisPrefix() + "&cThe game could not be started as it had an incorrect name."));
        }
    }

}
