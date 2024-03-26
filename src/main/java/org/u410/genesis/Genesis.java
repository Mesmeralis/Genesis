package org.u410.genesis;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.u410.genesis.commands.*;
import org.u410.genesis.listeners.GameWaterListener;
import org.u410.genesis.listeners.JoinQuitListener;
import org.u410.genesis.managers.GameManager;


public final class Genesis extends JavaPlugin {

    public GameManager gameManager = new GameManager(this);
    private static Economy econ;
    @Override
    public void onEnable() {
        saveConfig();
        setupEconomy();
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
        Bukkit.getPluginManager().registerEvents(new GameWaterListener(this, this.gameManager), this);
    }

    public String genesisPrefix() {
        return this.getConfig().getString("genesisPrefix");
    }


    private void setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return;
        }
        econ = rsp.getProvider();
    }

    public static Economy getEconomy() {
        return econ;
    }
}
