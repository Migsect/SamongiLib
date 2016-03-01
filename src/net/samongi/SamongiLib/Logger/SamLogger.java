package net.samongi.SamongiLib.Logger;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.configuration.ConfigurationSection;

public class SamLogger
{ 
  private static final String ERROR = "ERROR";
  private static final String DEBUG = "DEBUG";
  
  private final Logger logger;
  
  public Set<String> active_channels = new HashSet<>();
  
  public boolean always_show = false; // Will show everything that is sent to be logged
  
  public boolean always_show_error = true;
  public boolean always_show_info = true;
  public boolean always_show_debug = false;
  
  public SamLogger(Logger logger)
  {
    this.logger = logger;
  }
  
  public void info(String channel, String message)
  {
    if(!active_channels.contains(channel) && !always_show_info && !always_show) return;
    this.logger.info(channel + ": " + message);
  }
  public void error(String channel, String message)
  {
    if(!active_channels.contains(channel) && !always_show_error && !always_show) return;
    this.logger.info(channel + "-" + ERROR + ": " + message);
  }
  public void debug(String channel, String message)
  {
    if(!active_channels.contains(channel) && !always_show_debug && !always_show) return;
    this.logger.info(channel + "-" + DEBUG + ": " + message);
  }
  
  /**Will active the channel to have it display info or debug messages on
   * the logger channels.
   * 
   * @param channel
   */
  public void activateChannel(String channel){this.active_channels.add(channel);}
  /**Will deactivate the channel
   * 
   * @param channel
   */
  public void deactivateChannel(String channel){this.active_channels.remove(channel);}
  /**Activates all channels in the list
   * 
   * @param channels
   */
  public void activateChannels(String[] channels){for(String c : channels) this.activateChannel(c);}
  /**Will deactivate the channels
   * 
   * @param channels
   */
  public void deactivateChannels(String[] channels){for(String c : channels) this.deactivateChannel(c);}
  /**Will set the logger to show all channels
   * 
   * @param show
   */
  public void setAlwaysShow(boolean show){this.always_show = show;}
  /**Will set the logger to always show error messages
   * 
   * @param show
   */
  public void setAlwaysShowError(boolean show){this.always_show = show;}
  /**Will set the logger to always show info messages
   * 
   * @param show
   */
  public void setAlwaysShowInfo(boolean show){this.always_show = show;}
  /**Will set the logger to always show debug messages
   * 
   * @param show
   */
  public void setAlwaysShowDebug(boolean show){this.always_show = show;}
  
  /**Updates the logger based on the configuration section
   * This assumes it is at the top level key.
   * 
   * @param section
   */
  public void parseConfiguration(ConfigurationSection section)
  {
    String active_channels = section.getString("active_channels", "");
    this.activateChannels(active_channels.split(" "));
    this.setAlwaysShow(section.getBoolean("always_show", false));
    this.setAlwaysShowInfo(section.getBoolean("always_show_info", true));
    this.setAlwaysShowError(section.getBoolean("always_show_error", true));
    this.setAlwaysShowDebug(section.getBoolean("always_show_debug", false));
  }
  
}
