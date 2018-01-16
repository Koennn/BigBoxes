package me.koenn.bigboxes.box;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class BigBox {

    public static final HashMap<Location, BigBox> REGISTRY = new HashMap<>();

    private final HashMap<UUID, Location> exitLocations = new HashMap<>();
    private final Location location;
    private final Location boxLocation;
    private final BoxColor color;

    public BigBox(Location location, Location boxLocation, BoxColor color) {
        this.location = location;
        this.boxLocation = boxLocation;
        this.color = color;

        if (location.getBlock().getType().equals(Material.AIR)) {
            this.generate();
        }
    }

    public void enter(Player player) {
        this.exitLocations.put(player.getUniqueId(), player.getLocation());
        Location loc = this.location.clone().add(0.5, 1.2, 0.5);
        Location pLoc = player.getLocation();
        player.teleport(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), pLoc.getYaw(), pLoc.getPitch()));
    }

    public void exit(Player player) {
        player.teleport(this.exitLocations.getOrDefault(player.getUniqueId(), this.boxLocation));
    }

    @SuppressWarnings("deprecation")
    private void generate() {
        this.location.getBlock().setType(this.color.box);
        this.location.getBlock().setData((byte) this.color.meta);

        int lx = this.location.getBlockX() - 3;
        int hx = this.location.getBlockX() + 3;
        int lz = this.location.getBlockZ() - 3;
        int hz = this.location.getBlockZ() + 3;
        int ly = this.location.getBlockY();
        int hy = this.location.getBlockY() + 6;

        for (int x = lx; x < hx + 1; x++) {
            for (int z = lz; z < hz + 1; z++) {
                for (int y = ly; y < hy + 1; y++) {
                    if (x == lx || x == hx || z == lz || z == hz || y == ly || y == hy) {
                        Location location = new Location(this.location.getWorld(), x, y, z);
                        if (location.getBlock().getType().equals(Material.AIR)) {
                            location.getBlock().setType(Material.CONCRETE);
                            location.getBlock().setData((byte) this.color.meta);
                        }
                    }
                }
            }
        }
    }

    public Location getLocation() {
        return location;
    }

    public Location getBoxLocation() {
        return boxLocation;
    }

    public BoxColor getColor() {
        return color;
    }
}
