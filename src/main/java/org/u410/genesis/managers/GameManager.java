package org.u410.genesis.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.u410.genesis.Genesis;
import org.u410.genesis.utils.ColourUtils;

import java.util.ArrayList;

public class GameManager {
    private final Genesis genesis;
    public String gameName;
    public boolean gameRunning;
    public ArrayList<Player> inGame = new ArrayList<>();
    public Player winner;

    public GameManager(Genesis genesis) {
        this.genesis = genesis;
    }

    public void runGame(String gameName, int delay) {
        if(gameName.equalsIgnoreCase("water")) {
          if(!this.isGameRunning()) {
              Bukkit.getServer().broadcastMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &eThe &d" + gameName + "&e game is starting soon!"));
              Bukkit.getServer().broadcastMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &eYou have &a" + delay + "&e seconds to join. Do &d/joingame &eto join!"));
              gameRunning = true;
              Bukkit.getScheduler().scheduleAsyncDelayedTask(this.genesis, () -> {
                  Bukkit.getServer().broadcastMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &eThe &d" + gameName + "&e game has begun."));
                  Bukkit.getServer().broadcastMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &eFirst to find water &aWINS&e!"));
              }, delay*20L);
          }
        } else {
            Bukkit.getServer().broadcastMessage(ColourUtils.colour(this.genesis.genesisPrefix() + "&cThe game could not be started as it had an incorrect name."));
        }
    }

    public Boolean isGameRunning() {
        return this.gameRunning;
    }

}
