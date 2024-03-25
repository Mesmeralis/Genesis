package org.u410.genesis.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.u410.genesis.Genesis;
import org.u410.genesis.utils.ColourUtils;

import java.util.Objects;

public class Fly implements CommandExecutor {
    private final Genesis genesis;
    public Fly(Genesis genesis) {
        this.genesis = genesis;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = Bukkit.getServer().getPlayer(commandSender.getName());
        if(args.length == 0) {
            assert player != null;
            if(!player.getAllowFlight()) {
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
            if(!Objects.requireNonNull(Bukkit.getServer().getPlayer(args[0])).isOnline()) {
                assert player != null;
                player.sendMessage(this.genesis.genesisPrefix() + " &cPlayer &d" + args[0] + " &cis not online.");
            } else {
                if(!Objects.requireNonNull(Bukkit.getServer().getPlayer(args[0])).isFlying()) {
                    Objects.requireNonNull(Bukkit.getServer().getPlayer(args[0])).setAllowFlight(true);
                    Objects.requireNonNull(Bukkit.getServer().getPlayer(args[0])).setFlying(true);
                    assert player != null;
                    player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aYou have enabled flight for &d" + args[0] + "&a."));
                    Objects.requireNonNull(Bukkit.getServer().getPlayer(args[0])).sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + "&aFlight has been enabled for you."));
                } else {
                    Objects.requireNonNull(Bukkit.getServer().getPlayer(args[0])).setAllowFlight(false);
                    Objects.requireNonNull(Bukkit.getServer().getPlayer(args[0])).setFlying(false);
                    assert player != null;
                    player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &cYou have disabled flight for &d" + args[0] + "&a."));
                    Objects.requireNonNull(Bukkit.getServer().getPlayer(args[0])).sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + "&cFlight has been disabled for you."));
                }
            }
        }

        return true;
    }
}
