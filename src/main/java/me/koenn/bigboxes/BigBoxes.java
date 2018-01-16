package me.koenn.bigboxes;

import me.koenn.bigboxes.box.BoxItem;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BigBoxes extends JavaPlugin {

    private static BigBoxes instance;

    public static BigBoxes getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getOnlinePlayers().forEach(player -> player.getInventory().addItem(new BoxItem.Builder().build().getItem()));
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }
}
