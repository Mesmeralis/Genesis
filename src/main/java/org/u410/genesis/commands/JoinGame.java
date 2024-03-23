package org.u410.genesis.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.u410.genesis.Genesis;
import org.u410.genesis.managers.GameManager;
import org.u410.genesis.utils.ColourUtils;

public class JoinGame implements CommandExecutor {
    private Genesis genesis;
    private GameManager manager;
    public JoinGame(Genesis genesis, GameManager manager) {
        this.genesis = genesis;
        this.manager = manager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] arg) {
        Player player = Bukkit.getServer().getPlayer(commandSender.getName());
        if(!manager.gameRunning ) {
            player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + "&c There is no game running."));
        } else if(manager.gameRunning && !manager.inGame.contains(player)) {
            manager.inGame.add(player);
            Bukkit.getServer().broadcastMessage(ColourUtils.colour(this.genesis.genesisPrefix() + "&a " + player.getName() + "&e joined the minigame."));
        } else if(manager.gameRunning && manager.inGame.contains(player)){
            player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + "&c You are already in this minigame."));
        }

        return true;
    }
}
