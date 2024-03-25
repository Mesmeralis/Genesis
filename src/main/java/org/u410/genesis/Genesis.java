package org.u410.genesis;

import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.dataholder.OverloadedWorldHolder;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.u410.genesis.commands.*;
import org.u410.genesis.listeners.ChatListener;
import org.u410.genesis.listeners.GameWaterListener;
import org.u410.genesis.listeners.JoinQuitListener;
import org.u410.genesis.managers.GameManager;

import java.util.Arrays;
import java.util.List;

public final class Genesis extends JavaPlugin {

    public GroupManager groupManager;
    public GameManager gameManager = new GameManager(this);
    @Override
    public void onEnable() {
        saveConfig();
        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    public void registerCommands() {
        Bukkit.getPluginCommand("genesisreload").setExecutor(new GenesisReload(this));
        Bukkit.getPluginCommand("sethome").setExecutor(new SetHome(this));
        Bukkit.getPluginCommand("home").setExecutor(new Home(this));
        Bukkit.getPluginCommand("delhome").setExecutor(new DelHome(this));
        Bukkit.getPluginCommand("fly").setExecutor(new Fly(this));
        Bukkit.getPluginCommand("setspawn").setExecutor(new SetSpawn(this));
        Bukkit.getPluginCommand("spawn").setExecutor(new Spawn(this));
        Bukkit.getPluginCommand("forcespawn").setExecutor(new ForceSpawn(this));
        Bukkit.getPluginCommand("gamemode").setExecutor(new Gamemode(this));
        Bukkit.getPluginCommand("game").setExecutor(new GameCommand(this, this.gameManager));
        Bukkit.getPluginCommand("joingame").setExecutor(new JoinGame(this,this.gameManager));
    }
    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);
        Bukkit.getPluginManager().registerEvents(new GameWaterListener(this, this.gameManager), this);
    }

    public String genesisPrefix() {
        return this.getConfig().getString("genesisPrefix");
    }

    public boolean hasGroupManager() {

        if (groupManager != null) return true;

        final PluginManager pluginManager = this.getServer().getPluginManager();
        final Plugin GMplugin = pluginManager.getPlugin("GroupManager");

        if (GMplugin != null && GMplugin.isEnabled()) {
            groupManager = (GroupManager)GMplugin;
            return true;
        }
        return false;
    }

    public String getGroup(final Player player) {
        if (!hasGroupManager()) return null;

        final AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(player);
        if (handler == null) return null;

        return handler.getGroup(player.getName());
    }

    public boolean setGroup(final Player player, final String group) {
        if (!hasGroupManager()) return false;

        final OverloadedWorldHolder handler = groupManager.getWorldsHolder().getWorldData(player);
        if (handler == null) return false;

        handler.getUser(player.getName()).setGroup(handler.getGroup(group));
        return true;
    }

    public List<String> getGroups(final Player player) {
        if (!hasGroupManager()) return null;

        final AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(player);
        if (handler == null) return null;

        return Arrays.asList(handler.getGroups(player.getName()));
    }

    public String getPrefix(final Player player) {
        if (!hasGroupManager()) return null;

        final AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(player);
        if (handler == null) return null;

        return handler.getUserPrefix(player.getName());
    }

    public String getSuffix(final Player player) {
        if (!hasGroupManager()) return null;

        final AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(player);
        if (handler == null) return null;

        return handler.getUserSuffix(player.getName());
    }
}
