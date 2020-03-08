package net.samongi.SamongiLib;

import net.samongi.SamongiLib.Crafting.CraftingManager;
import net.samongi.SamongiLib.Logger.BetterLogger;
import net.samongi.SamongiLib.Menu.MenuManager;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SamongiLib extends JavaPlugin {

    //----------------------------------------------------------------------------------------------------------------//
    private static SamongiLib m_instance;
    public static SamongiLib getInstance() {
        return m_instance;
    }

    public static BetterLogger logger;
    static private boolean debug;

    static final public void log(String to_log) {
        logger.info(to_log);
    }
    static final public void debugLog(String to_log) {
        if (debug == true)
            logger.info(to_log);
    }
    static final public boolean debug() {
        return debug;
    }
    //----------------------------------------------------------------------------------------------------------------//
    public SamongiLib() {
        SamongiLib.logger = new BetterLogger(this);
    }

    //----------------------------------------------------------------------------------------------------------------//
    public void onEnable() {
        // Getting the debugger
        debug = this.getConfig().getBoolean("debug");

        // Server Log Message
        PluginDescriptionFile pdf = this.getDescription();
        SamongiLib.logger.info(pdf.getName() + " has been enabled.");

        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new MenuManager(), this);
        pluginManager.registerEvents(new CraftingManager(), this);
    }

    public void onDisable() {
        // Server Log Message
        PluginDescriptionFile pdf = this.getDescription();
        SamongiLib.logger.info(pdf.getName() + " has been disabled");
    }

    //----------------------------------------------------------------------------------------------------------------//
}
