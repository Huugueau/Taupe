package com.huugueau;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class TaupeGame extends BukkitRunnable {

    private Player player;
    private int score;
    private Inventory inventory;
    private int timer = 60;
    private int totalTaupe = 0;
    private Taupe instance;


    public TaupeGame(Player player, Taupe instance) {
        this.player = player;
        this.score = 0;
        this.inventory = Bukkit.createInventory(null, 27, "§6Jeu de la taupe");
        this.instance = instance;
    }

    public void drawGui() {
        clearInventory();
        getPlayer().openInventory(getInventory());
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Player getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int toAdd) {
        this.score += toAdd;
    }

    @Override
    public void run() {

        if(timer == 0) {
            getPlayer().sendMessage("§6Vous avez fait un score de: §3" + getScore() + " §6/ §3" + getTotalTaupe() + " (" + calculatePercentage() + "%)");
            getInstance().getGames().remove(getPlayer());
            getPlayer().closeInventory();
            cancel();
        }

        clearInventory();

        Random random = new Random();
        for(int i = 0; i < random.nextInt(4) + 1; i++) {
            int toChange = random.nextInt(27);
            getInventory().setItem(toChange, new ItemStack(Material.RED_STAINED_GLASS_PANE));
            totalTaupe++;
        }


        timer--;
    }

    private void clearInventory() {
        for(int i = 0; i < getInventory().getSize(); i++) {
            getInventory().setItem(i, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
        }
    }

    public int calculatePercentage() {
        return (getScore() * 100) / totalTaupe;
    }

    public int getTotalTaupe() {
        return totalTaupe;
    }

    public Taupe getInstance() {
        return instance;
    }
}
