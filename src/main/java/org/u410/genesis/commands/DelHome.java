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

public class DelHome implements CommandExecutor {
    private Genesis genesis;
    public DelHome(Genesis genesis) {
        this.genesis = genesis;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = Bukkit.getPlayer(commandSender.getName());
        if(args.length == 0) {
            if(this.genesis.getConfig().getLocation("homes." + player.getName() + ".") == null) {
                player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix()  + "&c You have no homes to delete."));
            } else {
                this.genesis.getConfig().set("homes." + player.getName(), null);
                player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aYour home was deleted."));
                this.genesis.saveConfig();
            }
        }
        if(args.length == 1) {
            if(this.genesis.getConfig().getLocation("homes." + player.getName() + "." + args[0]) == null) {
                player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix()  + "&c You have no homes by the name &d" + args[0] + "&c."));
            } else {
                this.genesis.getConfig().set("homes." + player.getName() + "." + args[0], null);
                player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aYour home by the name of &d" + args[0] + "&a was deleted."));
                this.genesis.saveConfig();
            }
        }

        return true;
    }
}
