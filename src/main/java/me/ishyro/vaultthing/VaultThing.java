package me.ishyro.vaultthing;

import me.ishyro.vaultthing.commands.BalanceCommand;
import me.ishyro.vaultthing.listeners.BlockBreakListener;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class VaultThing extends JavaPlugin {
    private static Economy econ = null;

    @Override
    public void onEnable() {
        if (!setupEconomy() ) {
            getLogger().severe(String.format(" - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getCommand("balance").setExecutor(new BalanceCommand());
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
