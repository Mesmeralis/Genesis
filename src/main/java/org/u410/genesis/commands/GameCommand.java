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

public class GameCommand implements CommandExecutor {
    public Genesis genesis;
    public GameManager manager;
    public GameCommand(Genesis genesis, GameManager manager) {
        this.genesis = genesis;
        this.manager = manager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = Bukkit.getServer().getPlayer(commandSender.getName());
        int delay = Integer.parseInt(args[1]);
        int end = Integer.parseInt(args[2]);
        if(args.length == 0) {
            player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &eThe available games are: &bWater"));
        }
        if(args.length == 3) {
            if(args[0].equalsIgnoreCase("water") && (delay >= 10) && (end >= 10)) {
                this.manager.runGame("water", delay);
            } else {
                Bukkit.broadcastMessage(ColourUtils.colour(this.genesis.genesisPrefix() + "&c Game command error upon startup."));
            }
        } else {
            return false;
        }

        return true;
    }
}
