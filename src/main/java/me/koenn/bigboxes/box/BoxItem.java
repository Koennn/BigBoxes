package me.koenn.bigboxes.box;

import me.koenn.bigboxes.BigBoxes;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BoxItem implements Listener {

    private final ItemStack item;

    private BoxItem(BoxColor color) {
        this.item = new ItemStack(color.box);

        Bukkit.getPluginManager().registerEvents(this, BigBoxes.getInstance());
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getItemInHand().isSimilar(this.item)) {
            new Box(this, event.getBlockPlaced().getLocation());
        }
    }

    public ItemStack getItem() {
        return item;
    }

    public static class Builder {

        public BoxItem build() {
            return new BoxItem(BoxColor.CYAN);
        }
    }
}
