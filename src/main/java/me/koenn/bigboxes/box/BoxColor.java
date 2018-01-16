package me.koenn.bigboxes.box;

import org.bukkit.Material;

public enum BoxColor {

    CYAN(9, Material.CYAN_SHULKER_BOX);

    public final int meta;
    public final Material box;

    BoxColor(int meta, Material box) {
        this.meta = meta;
        this.box = box;
    }
}
