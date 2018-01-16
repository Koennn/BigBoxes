package me.koenn.bigboxes.box;

import me.koenn.bigboxes.BigBoxes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Box implements Listener {

    private final BigBox bigBox;
    private final Block box;

    public Box(BoxItem item, Location location) {
        this.bigBox = new BigBox(location.clone().add(0, 10, 0), location, BoxColor.CYAN);
        this.box = location.getBlock();

        BigBox.REGISTRY.put(location, this.bigBox);

        Bukkit.getPluginManager().registerEvents(this, BigBoxes.getInstance());
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        Location location = event.getClickedBlock().getLocation();
        if (location.equals(this.box.getLocation())) {
            this.bigBox.enter(event.getPlayer());
            event.setCancelled(true);
        }

        if (location.equals(this.bigBox.getLocation())) {
            this.bigBox.exit(event.getPlayer());
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent event) {
    }
}
