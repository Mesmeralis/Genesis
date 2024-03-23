package org.u410.genesis.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.u410.genesis.Genesis;
import org.u410.genesis.utils.ColourUtils;

public class ForceSpawn implements CommandExecutor {
    private Genesis genesis;
    public ForceSpawn(Genesis genesis) {
        this.genesis = genesis;
    }

    public static boolean forced = false;

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = Bukkit.getPlayer(commandSender.getName());
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("check")) {
                player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &eforced spawn is set to &d" + forced + "&e."));
            } else {
                return false;
            }
        }
        if(args.length == 0){
            if(forced == false) {
                this.genesis.getConfig().set("forcedSpawn", true);
                this.genesis.saveConfig();
                forced = true;
                player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + "&e forced spawn has been set to &dtrue&e.") );
            }
            else {
                this.genesis.getConfig().set("forcedSpawn", false);
                this.genesis.saveConfig();
                forced = false;
                player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + "&e forced spawn has been set to &dfalse&e.") );
            }
        }

        return true;
    }
}
