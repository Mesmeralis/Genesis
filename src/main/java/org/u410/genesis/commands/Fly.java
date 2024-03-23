package org.u410.genesis.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.u410.genesis.Genesis;
import org.u410.genesis.utils.ColourUtils;

public class Fly implements CommandExecutor {
    private Genesis genesis;
    public Fly(Genesis genesis) {
        this.genesis = genesis;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = Bukkit.getServer().getPlayer(commandSender.getName());
        if(args.length == 0) {
            if(!player.getAllowFlight() == true) {
                player.setAllowFlight(true);
                player.setFlying(true);
                player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aYou have enabled flight."));
            } else {
                player.setAllowFlight(false);
                player.setFlying(false);
                player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &cYou have disabled flight."));
            }
        }
        if(args.length == 1) {
            if(!Bukkit.getServer().getPlayer(args[0]).isOnline()) {
                player.sendMessage(this.genesis.genesisPrefix() + " &cPlayer &d" + args[0] + " &cis not online.");
            } else {
                if(!Bukkit.getServer().getPlayer(args[0]).isFlying()) {
                    Bukkit.getServer().getPlayer(args[0]).setAllowFlight(true);
                    Bukkit.getServer().getPlayer(args[0]).setFlying(true);
                    player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aYou have enabled flight for &d" + args[0] + "&a."));
                    Bukkit.getServer().getPlayer(args[0]).sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + "&aFlight has been enabled for you."));
                } else {
                    Bukkit.getServer().getPlayer(args[0]).setAllowFlight(false);
                    Bukkit.getServer().getPlayer(args[0]).setFlying(false);
                    player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &cYou have disabled flight for &d" + args[0] + "&a."));
                    Bukkit.getServer().getPlayer(args[0]).sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + "&cFlight has been disabled for you."));
                }
            }
        }

        return true;
    }
}
