package net.samongi.SamongiLib;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class SamongiLib extends JavaPlugin
{
	static final public Logger logger = Logger.getLogger("Minecraft"); // Logger is static to allow easy use across plugin;
	static public boolean debugger;
	
	//Enabling
	public void onEnable()
	{
	  // Getting the debugger 
		debugger = this.getConfig().getBoolean("debug");
		
	  // Server Log Message
		PluginDescriptionFile pdf = this.getDescription();
    SamongiLib.logger.info(pdf.getName() + " has been enabled.");
	}
	
  //Disabling
	public void onDisable()
	{
	  // Server Log Message
		PluginDescriptionFile pdf = this.getDescription();
    SamongiLib.logger.info(pdf.getName() + " has been disabled");
	}
	
	
}
