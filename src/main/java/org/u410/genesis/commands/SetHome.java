package org.u410.genesis.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.u410.genesis.Genesis;
import org.u410.genesis.utils.ColourUtils;

public class SetHome implements CommandExecutor {
    private Genesis genesis;
    public SetHome(Genesis genesis) {
        this.genesis = genesis;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = Bukkit.getPlayer(commandSender.getName());
        if(args.length == 0) {
            this.genesis.getConfig().set("homes." + player.getName(), player.getLocation());
            player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aYour home was successfully set."));
            player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aDo &d/home &ato return to your home."));
            this.genesis.saveConfig();
        }
        if(args.length == 1) {
            this.genesis.getConfig().set("homes." + player.getName() + "." + args[0], player.getLocation());
            player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aYour home was successfully set with the name &d" + args[0] + "&a."));
            player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aDo &d/home " + args[0] + " &ato return to your home."));
            this.genesis.saveConfig();
        }

        return true;
    }
}
