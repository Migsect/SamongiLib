package net.samongi.SamongiLib;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class SamongiLib extends JavaPlugin
{
	static final public Logger logger = Logger.getLogger("Minecraft"); // Logger is static to allow easy use across plugin;
	static private boolean debug;
	
	//Enabling
	public void onEnable()
	{
	  // Getting the debugger 
	  debug = this.getConfig().getBoolean("debug");
		
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
	
  static final public void log(String to_log)
  {
    logger.info(to_log);
  }
  static final public void debugLog(String to_log)
  {
    if(debug == true) logger.info(to_log);
  }
  static final public boolean debug(){return debug;}
}
