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

import java.util.ArrayList;
import java.util.List;

public class Home implements CommandExecutor {
    private Genesis genesis;
    public Home(Genesis genesis) {
        this.genesis = genesis;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = Bukkit.getPlayer(commandSender.getName());
        if(args.length == 0) {
            if(this.genesis.getConfig().getLocation("homes." + player.getName()) == null) {
                player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix()  + "&c You have no homes. &aDo &d/sethome &ato set one at your location."));
            } else {
                List<String> homes = new ArrayList<String>();
                homes = this.genesis.getConfig().getStringList("homes." + player.getName());
                if(homes.size() > 1) {
                    player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &cPlease specify which home to teleport to."));
                    if(homes.contains(args[0])) {
                        player.teleport((Location) this.genesis.getConfig().get("homes." + player.getName() + "." + args[0]));
                        player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix()  + "&e You have been teleported to your home."));
                    } else {
                        player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix()  + "&c You have no homes by this name."));
                    }
                } else {
                    player.teleport((Location) this.genesis.getConfig().get("homes." + player.getName()));
                }
            }
        }
        if(args.length == 1) {
            player.teleport((Location) this.genesis.getConfig().get("homes." + player.getName() + "." + args[0]));
            player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix()  + "&e You have been teleported to your home."));
        }

        return true;
    }
}
