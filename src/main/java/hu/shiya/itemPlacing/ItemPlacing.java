package hu.shiya.itemPlacing;

import hu.shiya.itemPlacing.commands.HatPlaceCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemPlacing extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("hat").setExecutor( new HatPlaceCommand( this ));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
