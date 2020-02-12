package net.samongi.SamongiLib.Configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigFile
{
  private final String plugin_file_name;
  private final JavaPlugin plugin;
  
  private File file = null;
  private FileConfiguration file_configuration = null;
  
  public ConfigFile(File file)
  {
    if(file == null) throw new IllegalArgumentException("file cannot be null");
    this.file = file;
    
    this.plugin_file_name = null;
    this.plugin = null;
  }
  public ConfigFile(JavaPlugin plugin, String file_name)
  {
    if (plugin == null) throw new IllegalArgumentException("plugin cannot be null");
    if (file_name == null) throw new IllegalArgumentException("file_name cannot be null");
    this.plugin = plugin;
    this.plugin_file_name = file_name;
    File dataFolder = plugin.getDataFolder();
    if (dataFolder == null) throw new IllegalStateException();
    this.file = new File(plugin.getDataFolder(), plugin_file_name);
  }
  public ConfigFile(File file, JavaPlugin plugin, String default_file_name)
  {
    if (plugin == null) throw new IllegalArgumentException("plugin cannot be null");
    if (default_file_name == null) throw new IllegalArgumentException("file_name cannot be null");
    this.plugin = plugin;
    this.plugin_file_name = default_file_name;
    this.file = file;
  }
  
  public final boolean reloadConfig()
  {
    file_configuration = YamlConfiguration.loadConfiguration(this.file);
    
    if(plugin_file_name == null || plugin == null) return false;
    try {
      InputStream defaultConfigStream = plugin.getResource(this.plugin_file_name);
      if (defaultConfigStream != null) {
        YamlConfiguration default_config = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultConfigStream));
        file_configuration.setDefaults(default_config);
      }
      defaultConfigStream.close();
    } catch (IOException exception)
    {
      return false;
    }
    return true;
  }
  public final FileConfiguration getConfig()
  {
    if(this.file_configuration == null) this.reloadConfig();
    return this.file_configuration;
  }
  public void saveConfig()
  {
    if(this.file_configuration == null || this.file == null) return;
    else
    {
      try
      {
        this.getConfig().save(file);
      }
      catch(IOException e){Logger.getLogger("Minecraft");}
    }
  }
  public final void saveDefaultConfig()
  {
    if(plugin_file_name == null || plugin == null) return;
    if(!file.exists()) this.plugin.saveResource(plugin_file_name, false);
  }
  public final File getFile()
  {
    return this.file;
  }
  
  public final static List<ConfigFile> getConfigFiles(File directory)
  {
    if(!directory.isDirectory()) return null;
    File[] files = directory.listFiles();
    List<ConfigFile> configs = new ArrayList<>();
    for(File f : files)
    {
      if(f.getAbsolutePath().endsWith(".yml") || f.getAbsolutePath().endsWith(".yaml")) continue;
      ConfigFile new_config = new ConfigFile(f);
      configs.add(new_config);
    }
    return configs;
  }
}
