package com.huugueau;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Taupe extends JavaPlugin implements CommandExecutor {


    private HashMap<Player, TaupeGame> games = new HashMap<>();





    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new TaupeListener(this), this);
        getCommand("taupe").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(!getGames().containsKey(player)) {
                TaupeGame game = new TaupeGame(player, this);
                getGames().put(player, game);
                game.drawGui();
                game.runTaskTimer(this, 20L, 20L);
            }else {
                player.sendMessage("§cPartie déjà en cours !");
            }
        }
        return false;
    }

    public HashMap<Player, TaupeGame> getGames() {
        return games;
    }

    @Override
    public void onDisable() {
        
    }




}
