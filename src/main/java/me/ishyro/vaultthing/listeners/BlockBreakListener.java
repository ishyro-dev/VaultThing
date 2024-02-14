package me.ishyro.vaultthing.listeners;

import me.ishyro.vaultthing.VaultThing;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Economy eco = VaultThing.getEconomy();
        Player player = event.getPlayer();

        EconomyResponse response = eco.depositPlayer(player, 5.0);
        if(response.transactionSuccess()){
            player.sendMessage("You got" + eco.format(response.amount));
        }
    }
}
