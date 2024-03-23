package org.u410.genesis.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.u410.genesis.Genesis;
import org.u410.genesis.utils.ColourUtils;

public class SetSpawn implements CommandExecutor {
    private Genesis genesis;

    public SetSpawn (Genesis genesis) {
        this.genesis = genesis;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = Bukkit.getServer().getPlayer(commandSender.getName());
        player.getWorld().setSpawnLocation(player.getLocation());
        this.genesis.getConfig().set("spawn", player.getLocation());
        this.genesis.saveConfig();
        player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aThe spawn was set to your location."));

        return true;
    }
}
