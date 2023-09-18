package world.leafmc.violett;

import org.bukkit.plugin.java.JavaPlugin;

public final class Violett extends JavaPlugin {

    private static ViolettAPI api;

    @Override
    public void onEnable() {
        // Plugin startup logic
        api = new ViolettAPI();
        api.initialize();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        api.deflate();
    }

    public ViolettAPI getAPI() {
        return api;
    }

}
