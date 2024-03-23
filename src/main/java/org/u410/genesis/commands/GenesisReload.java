package org.u410.genesis.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.u410.genesis.Genesis;
import org.u410.genesis.utils.ColourUtils;

public class GenesisReload implements CommandExecutor {
    private Genesis genesis;
    public GenesisReload (Genesis genesis) {
        this.genesis = genesis;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length == 0) {
            genesis.reloadConfig();
            genesis.saveConfig();
            commandSender.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + "&a Genesis reloaded successfully."));
        } else {
            return false;
        }

        return true;
    }
}
