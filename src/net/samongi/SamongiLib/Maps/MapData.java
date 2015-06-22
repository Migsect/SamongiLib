package net.samongi.SamongiLib.Maps;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.samongi.SamongiLib.SamongiLib;
import net.samongi.SamongiLib.Configuration.ConfigFile;
import net.samongi.SamongiLib.Maps.Spawn.LocationSpawn;
import net.samongi.SamongiLib.Maps.Spawn.Spawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public final class MapData
{
  private final String tag;
  private final File world_folder;
  private final ConfigFile config;
  private final World world;
  
  private String display_name;
  private Material display_material;
  private short display_damage;
  private List<String> display_lore = null;
  
  private Location center;
  private double radius;
  private double bottom;
  
  private int min_players = -1;
  private int max_players = -1;
  private Map<String, Integer> mapped_min_players = new HashMap<>();
  private Map<String, Integer> mapped_max_players = new HashMap<>();
  
  private List<String> supported_gametypes = null;
  
  private List<Spawn> spawns = new ArrayList<>();
  private Map<String, List<Spawn>> mapped_spawns = new HashMap<>();
  
  public MapData(ConfigFile config, String tag)
  {
    this.tag = tag;
    this.config = config;
    this.world_folder = config.getFile().getParentFile();
    
    this.world = this.loadWorld();

    if(this.getConfig() == null) SamongiLib.debugLog(tag + " found 'Config' to be null");
    String center_string = this.getConfig().getString("center");
    SamongiLib.debugLog(tag + " found 'center' to be: " + center_string);
    this.center = getLocation(world, center_string);
    this.radius = this.getConfig().getDouble("radius", -1);
    SamongiLib.debugLog(tag + " found 'radius' to be: " + radius);
    this.bottom = this.getConfig().getDouble("bottom", 0);
    SamongiLib.debugLog(tag + " found 'bottom' to be: " + bottom);
    
    if(this.getConfig().getKeys(false).contains("player-amounts"))
    {
      this.min_players = this.getConfig().getInt("player-amounts.default.min", -1);
      this.max_players = this.getConfig().getInt("player-amounts.default.max", -1);
      Set<String> keys = this.getConfig().getConfigurationSection("player-amounts").getKeys(false);
      for(String k : keys)
      {
        int min_p = this.getConfig().getInt("player-amounts."+k+".min", -1);
        int max_p = this.getConfig().getInt("player-amounts."+k+".max", -1);
        this.mapped_min_players.put(k, min_p);
        this.mapped_max_players.put(k, max_p);
      }
    }
    
    this.display_name = this.getConfig().getString("display.name", "NO_NAME_SUPPLIED");
    this.display_material = Material.getMaterial(this.getConfig().getString("display.material", "GRASS"));
    if(this.display_material == null) this.display_material = Material.GRASS;
    this.display_damage = (short) this.getConfig().getInt("display.damage", 0);
    this.display_lore = this.getConfig().getStringList("display.lore");
    if(this.display_lore == null) this.display_lore = new ArrayList<>();
    
    this.supported_gametypes = this.getConfig().getStringList("gametypes");
    if(this.supported_gametypes == null) this.supported_gametypes = new ArrayList<>();
    
    // Doing the spawns
    if(this.getConfig().getKeys(false).contains("spawns"))
    {
      List<String> spawns_string = this.getConfig().getStringList("spawns.default");
      if(spawns_string == null) spawns_string = new ArrayList<>();
      for(String s : spawns_string)
      {
        Location l = MapData.getLocation(world, s);
        if(l == null) continue;
        Spawn spawn = new LocationSpawn(l);
        spawns.add(spawn);
      }
      Set<String> keys = this.getConfig().getConfigurationSection("spawns").getKeys(false);
      for(String k : keys)
      {
        List<String> spawns_string_k = this.getConfig().getStringList("spawns."+k);
        if(spawns_string_k == null) continue;
        List<Spawn> spawns_k = new ArrayList<>();
        for(String s : spawns_string_k)
        {
          Location l = MapData.getLocation(world, s);
          if(l == null) continue;
          Spawn spawn = new LocationSpawn(l);
          spawns_k.add(spawn);
        }
        this.mapped_spawns.put(k, spawns_k);
      }
    }
    
  }
  
  public static List<MapData> getMapData(File file)
  {
    if(!file.isFile()) return null;
    ConfigFile config_file = new ConfigFile(file);
    if(!config_file.getConfig().getKeys(false).contains("maps")) return null;
    ConfigurationSection maps = config_file.getConfig().getConfigurationSection("maps");
    Set<String> keys = maps.getKeys(false);
    List<MapData> map_data = new ArrayList<>();
    for(String k : keys)
    {
      map_data.add(new MapData(config_file, k));
    }
    return map_data;
  }
  
  public static List<MapData> getAllMapData()
  {
    // Get the folder that contains all the world folders.
    File world_container = Bukkit.getServer().getWorldContainer().getAbsoluteFile();
    SamongiLib.debugLog("World Container is: " + world_container.getAbsolutePath());
    // Getting all the files that are in the folder.
    File[] world_files = world_container.listFiles();
    List<MapData> all_map_data = new ArrayList<>();
    for(File f : world_files)
    {
      // Check to make sure the file is a directory
      if(!f.isDirectory()) continue;
      String[] sub_files = f.list(); // getting a list of all the files.
      String map_data_file = null;
      for(String s : sub_files) if(s.equals("map_data.yml")) map_data_file = s;
      if(map_data_file == null) continue; // if it is still null then we didn't find it.
      // get all the map data;
      File config_file = new File(f, map_data_file);
      SamongiLib.debugLog("Getting map config from: " + config_file.getAbsolutePath());
      List<MapData> map_data = getMapData(config_file);
      all_map_data.addAll(map_data);
    }
    return all_map_data;
  }
  
  public World loadWorld()
  {
    String world_name = world_folder.getName();
    SamongiLib.debugLog("Loading World: " + world_name);
    if(Bukkit.getServer().getWorlds().contains(world_name)) 
    {
      SamongiLib.debugLog("Found world to already be loaded: " + world_name);
      return Bukkit.getServer().getWorld(world_name);
    }
    World world = Bukkit.getServer().createWorld(new WorldCreator(world_name));
    SamongiLib.debugLog("World Loaded: " + world_name);
    return world;
  }
  
  public ConfigurationSection getConfig()
  {
    ConfigurationSection config_section = this.config.getConfig().getConfigurationSection("maps." + tag);
    return config_section;
  }
  
  public static Location getLocation(World world, String loc)
  {
    String[] split_loc = loc.split(" ");
    if(split_loc.length < 3) return null;
    Location location = null;
    try
    {
      double x = Double.parseDouble(split_loc[0]);
      double y = Double.parseDouble(split_loc[1]);
      double z = Double.parseDouble(split_loc[2]);
      location = new Location(world, x,y,z);
      if(split_loc.length >= 6)
      {
        double dir_x = Double.parseDouble(split_loc[3]);
        double dir_y = Double.parseDouble(split_loc[4]);
        double dir_z = Double.parseDouble(split_loc[5]);
        Vector dir = new Vector(dir_x, dir_y, dir_z);
        location.setDirection(dir);
      }
    }
    catch(NumberFormatException e){}
    return location;
  }
  
  public String getDisplayName(){return this.display_name;}
  public ItemStack getDisplayItem()
  {
    ItemStack item = new ItemStack(this.display_material, 1, this.display_damage);
    
    ItemMeta im = item.getItemMeta();
    im.setLore(this.display_lore);
    im.setDisplayName(this.display_name);
    item.setItemMeta(im);
    
    return item;
  }
  public String getTag(){return this.tag;}
  
  public boolean hasCenter(){return this.center == null;}
  public Location getCenter(){return this.center;}
  public boolean hasBottom(){return this.bottom > -1;}
  public double getBottom(){return this.bottom;}
  public boolean hasRadius(){return this.radius > -1;}
  public double getRadius(){return this.radius;}

  public boolean hasMinPlayer(){return this.min_players > -1;}
  public boolean hasMaxPlayer(){return this.max_players > -1;}
  public boolean hasMinPlayer(String key){return this.mapped_min_players.containsKey(key);}
  public boolean hasMaxPlayer(String key){return this.mapped_max_players.containsKey(key);}
  public int getMinPlayers(){return this.min_players;}
  public int getMaxPlayers(){return this.max_players;}
  public int getMinPlayers(String key){return this.mapped_min_players.get(key);}
  public int getMaxPlayers(String key){return this.mapped_max_players.get(key);}
  
  public List<String> getSupportedGameTypes(){return this.supported_gametypes;}
  
  public List<Spawn> getSpawns(){return this.spawns;}
  public List<Spawn> getSpawns(String key)
  {
    if(!this.mapped_spawns.containsKey(key)) return new ArrayList<Spawn>();
    return this.mapped_spawns.get(key);
  }
}
