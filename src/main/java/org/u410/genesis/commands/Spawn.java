package org.u410.genesis.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.u410.genesis.Genesis;
import org.u410.genesis.utils.ColourUtils;

public class Spawn implements CommandExecutor {

    private Genesis genesis;

    public Spawn (Genesis genesis) {
        this.genesis = genesis;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = Bukkit.getServer().getPlayer(commandSender.getName());
        if(this.genesis.getConfig().getString("spawn") == null) {
            player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aThe spawn has not been set."));
            player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aSet it by doing &d/setspawn&a."));
        } else {
            player.teleport((Location) this.genesis.getConfig().get("spawn"));
            player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &eYou have been teleported to the spawn."));
        }

        return true;
    }
}
