package com.huugueau;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;


public class TaupeListener implements Listener {

    private final Taupe instance;

    public TaupeListener(Taupe instance) {
        this.instance = instance;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if(getInstance().getGames().containsKey(player)) {
            event.setCancelled(true);

            if(event.getCurrentItem() == null) return;
            ItemStack clicked = event.getCurrentItem();

            TaupeGame game = getInstance().getGames().get(player);

            if(clicked.getType() == Material.RED_STAINED_GLASS_PANE) {
                game.getInventory().setItem(event.getSlot(), new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
                game.addScore(1);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if(getInstance().getGames().containsKey(player)) {

            TaupeGame game = getInstance().getGames().get(player);

            player.sendMessage("§cPartie annulé ! §6Vous avez fait un score de: §3" + game.getScore() + " §6/ §3" + game.getTotalTaupe() + " (" + game.calculatePercentage() + "%)");
            game.cancel();

            getInstance().getGames().remove(player);
        }
    }

    public Taupe getInstance() {
        return instance;
    }
}
