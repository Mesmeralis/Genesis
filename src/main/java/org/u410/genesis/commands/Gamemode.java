package org.u410.genesis.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.u410.genesis.Genesis;
import org.u410.genesis.utils.ColourUtils;

public class Gamemode implements CommandExecutor  {
    private Genesis genesis;

    public Gamemode(Genesis genesis) {
        this.genesis = genesis;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = Bukkit.getPlayer(commandSender.getName());
        if(args.length == 0) {
            player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &cInvalid arguments. Try &d/gm [0,1,2,3]&c."));
        }
        if(args.length == 1) {
            switch(args[0]) {
                case "1":
                case "c":
                case "creative":
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aYour gamemode was set to &dCREATIVE&a."));
                    break;
                case "2":
                case "a":
                case "adventure":
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aYour gamemode was set to &dADVENTURE&a."));
                    break;
                case "0":
                case "s":
                case "survival":
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aYour gamemode was set to &dSURVIVAL&a."));
                    break;
                case "3":
                case "sp":
                case "spectator":
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aYour gamemode was set to &dSPECTATOR&a."));
                    break;
                default:
                    player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + "&c Invalid arguments."));
                    break;
            }
            }
        if(args.length == 2) {
            Player target = Bukkit.getPlayer(args[1]);
            if(target.isOnline()) {
                switch(args[0]) {
                    case "1":
                    case "c":
                    case "creative":
                        target.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aTheir gamemode has been changed to &dCREATIVE&a."));
                        target.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &eYour gamemode has been changed by &d" + player.getName() + "&e."));
                        break;
                    case "2":
                    case "a":
                    case "adventure":
                        target.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aTheir gamemode has been changed to &dADVENTURE&a."));
                        target.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &eYour gamemode has been changed by &d" + player.getName() + "&e."));
                        break;
                    case "0":
                    case "s":
                    case "survival":
                        target.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aTheir gamemode has been changed to &dSURVIVAL&a."));
                        target.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &eYour gamemode has been changed by &d" + player.getName() + "&e."));
                        break;
                    case "3":
                    case "sp":
                    case "spectator":
                        target.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &aTheir gamemode has been changed to &dSPECTATOR&a."));
                        target.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &eYour gamemode has been changed by &d" + player.getName() + "&e."));
                        break;
                    default:
                        target.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + "&c Invalid arguments."));
                        break;
                }
            } else {
                player.sendMessage(ColourUtils.colour(this.genesis.genesisPrefix() + " &cThat player is not online."));
            }
        }

        return true;
    }
}
