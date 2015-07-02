package net.samongi.SamongiLib.Items;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.samongi.SamongiLib.SamongiLib;
import net.samongi.SamongiLib.Configuration.ConfigAccessor;
import net.samongi.SamongiLib.Utilities.StringUtil;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ItemUtil
{
	/**Returns true if the input material is a hoe.
	 *    
	 *  @param Material -> Checked material.
	 *  @return Boolean -> Returned true if material is a hoe.
	 */
	public static boolean isHoe(Material mat)
	{
		if(mat.equals(Material.WOOD_HOE)) return true;
		if(mat.equals(Material.STONE_HOE)) return true;
		if(mat.equals(Material.IRON_HOE)) return true;
		if(mat.equals(Material.GOLD_HOE)) return true;
		if(mat.equals(Material.DIAMOND_HOE)) return true;
		return false;
	}
	/**Returns true if the input material is a hoe.
	 *    
	 * @param Material -> Checked material.
	 * @return Boolean -> Returned true if material is a hoe.
	 */
	public static boolean isSword(Material mat)
	{
		if(mat.equals(Material.WOOD_SWORD)) return true;
		if(mat.equals(Material.STONE_SWORD)) return true;
		if(mat.equals(Material.IRON_SWORD)) return true;
		if(mat.equals(Material.GOLD_SWORD)) return true;
		if(mat.equals(Material.DIAMOND_SWORD)) return true;
		return false;
	}
	/**Returns true if the input material is a Sword.
	 *    
	 *    
	 * @param Material -> Checked material.
	 * @return Boolean -> Returned true if material is a Sword.
	 */
	public static boolean isPickaxe(Material mat)
	{
		if(mat.equals(Material.WOOD_PICKAXE)) return true;
		if(mat.equals(Material.STONE_PICKAXE)) return true;
		if(mat.equals(Material.IRON_PICKAXE)) return true;
		if(mat.equals(Material.GOLD_PICKAXE)) return true;
		if(mat.equals(Material.DIAMOND_PICKAXE)) return true;
		return false;
	}
	/**Returns true if the input material is a Pickaxe.
	 *    
	 *  @param Material -> Checked material.
	 *  @return Boolean -> Returned true if material is a Pickaxe.
	 */
	public static boolean isAxe(Material mat)
	{
		if(mat.equals(Material.WOOD_AXE)) return true;
		if(mat.equals(Material.STONE_AXE)) return true;
		if(mat.equals(Material.IRON_AXE)) return true;
		if(mat.equals(Material.GOLD_AXE)) return true;
		if(mat.equals(Material.DIAMOND_AXE)) return true;
		return false;
	}
	/**Returns true if the input material is a Spade.
	 *    
	 *  @param Material -> Checked material.
	 *  @return Boolean -> Returned true if material is a Spade.
	 */
	public static boolean isSpade(Material mat)
	{
		if(mat.equals(Material.WOOD_SPADE)) return true;
		if(mat.equals(Material.STONE_SPADE)) return true;
		if(mat.equals(Material.IRON_SPADE)) return true;
		if(mat.equals(Material.GOLD_SPADE)) return true;
		if(mat.equals(Material.DIAMOND_SPADE)) return true;
		return false;
	}
	/**Grabs an ItemStack from the config that is not a bukkit serial based one but a custom setup one (more human readable imo)
	 * 
	 * @param config The config.
	 * @param path The path within the config to the itemstack
	 * @return The item stack represented by the path.
	 */
	public static ItemStack getConfigItemStack(ConfigAccessor config, String path)
	{
	  ConfigurationSection section = config.getConfig().getConfigurationSection(path);
	  return getConfigItemStack(section);
	}
	/**Grabs an ItemStack from the config that is not a bukkit serial based one but a custom setup one (more human readable imo)
   * 
   * @param config The config.
   * @param path The path within the config to the itemstack
   * @return The item stack represented by the path.
   */
  public static ItemStack getConfigItemStack(ConfigurationSection section)
  {
    // Getting the material. Defaults to grass.
    SamongiLib.debugLog("Parsing ItemStack for path: '" + section.getCurrentPath() + "'");
    Material material = Material.getMaterial(section.getString("material","GRASS"));
    if(material == null) material = Material.GRASS;
    SamongiLib.debugLog("  Found material: " + material.toString());
    
    // Getting the amount.  Defaults to 1.
    int amount = section.getInt("amount",1);
    SamongiLib.debugLog("  Found amount: " + amount);
    
    // Getting the durability
    short durability = (short)section.getInt("durability", 0);
    SamongiLib.debugLog("  Found durability: " + durability);
    
    // making the first object:
    ItemStack itemstack = new ItemStack(material, amount, durability);
    ItemMeta im = itemstack.getItemMeta();
    
    // Getting the display name and formatting it.  If it is NONE, then it will not set display.  Defualt is NONE.
    String display = StringUtil.formatString(section.getString("display-name","NONE"));
    SamongiLib.debugLog("  Found display: " + display);
    if(!display.equals("NONE"))im.setDisplayName(display);
    
    // Getting the lore.
    
    List<String> lore = null;
    if(section.getKeys(false).contains("lore")) lore = StringUtil.formatString(section.getStringList("lore"));
    if(lore != null)
    {
      SamongiLib.debugLog("  Found Lore:");
      if(SamongiLib.debug()) for(String l : lore)
      {
        SamongiLib.debugLog("   - " + l);
      }
      if(lore.size() != 0)im.setLore(lore);
    }
    
    // Getting if the item is unbreakable:
    boolean bool = section.getBoolean("unbreakable", false);
    im.spigot().setUnbreakable(bool);
    
    // Getting the enchantments
    if(section.getKeys(false).contains("enchantments")) //check to see if the config section exists.
    {
      SamongiLib.debugLog("  Found enchantments:");
      Map<Enchantment, Integer> enchants = new HashMap<>(); 
      ConfigurationSection enchantments = section.getConfigurationSection("enchantments");
      List<String> enchant_keys = new ArrayList<String>(enchantments.getKeys(false));
      for(String key : enchant_keys)
      {
        Enchantment ench = Enchantment.getByName(key);
        if(ench == null) continue;
        int ench_level = enchantments.getInt(key,1);
        enchants.put(ench, ench_level);
        SamongiLib.debugLog("   - " + ench.toString() + " : " + ench_level);
      }
      // setting the enchants.
      for(Enchantment e : enchants.keySet())
      {
        im.addEnchant(e, enchants.get(e), true);
      }
    }
    
    // Checking to see if the item is leather, if it is we will set the color.
    if(im instanceof LeatherArmorMeta)
    {
      // Getting RGB values
      int r = section.getInt("color.red", -1);
      int g = section.getInt("color.green", -1);
      int b = section.getInt("color.blue", -1);
      if(r > 0 && g > 0 && b > 0)
      {
        Color color = Color.fromRGB(r, g, b);
        ((LeatherArmorMeta)im).setColor(color);
      }
    }
    
    // Checking to see if the item is a potion, if it is we will do potion stuff.
    if(im instanceof PotionMeta)
    {
      PotionMeta pm = (PotionMeta)im;
      // Getting all the effects to be used:
      if(section.getKeys(false).contains("effects")) //check to see if the config section exists.
      {
        List<PotionEffect> effects = new ArrayList<>();
        ConfigurationSection potion_effects = section.getConfigurationSection("effects");
        List<String> effect_keys = new ArrayList<>(potion_effects.getKeys(false));
        for(String e_str : effect_keys)
        {
          SamongiLib.debugLog(" Reading Potion Types: ");
          String type = potion_effects.getString(e_str + ".type");
          SamongiLib.debugLog("    Got type: '" + type + "'");
          PotionEffectType potion_effect = PotionEffectType.getByName(type);
          if(potion_effect == null) continue;
          int strength = potion_effects.getInt(e_str + ".strength", 0);
          SamongiLib.debugLog("    Got strength: '" + strength + "'");
          int duration = potion_effects.getInt(e_str + ".duration", 0);
          SamongiLib.debugLog("    Got duration: '" + duration + "'");
          boolean ambient = potion_effects.getBoolean(e_str + ".ambient", true);
          SamongiLib.debugLog("    Got ambient: '" + ambient + "'");
          boolean particles = potion_effects.getBoolean(e_str + ".particles", true);
          SamongiLib.debugLog("    Got particles: '" + particles + "'");
          
          PotionEffect effect = new PotionEffect(potion_effect, duration, strength, ambient, particles);
          effects.add(effect);
        }
        
        if(effects.size() > 0) pm.setMainEffect(effects.get(0).getType());
        if(effects.size() > 0) for( PotionEffect e : effects ) pm.addCustomEffect(e, true);
      }
    }
    
    // Setting the itemmeta
    itemstack.setItemMeta(im);
    
    return itemstack;
  }
	
	/**Saves an itemstack as a file.
	 * 
	 * @param to_file The file to save to
	 * @param item The item to be saved
	 */
	public static void serializeItemStack(File to_file, ItemStack item)
	{
	  // Loads the config file to save to.
	  FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(to_file);
	  fileConfiguration.set("item", item);
	}
	/**Loads an itemstackf from a singualr item file.
	 * 
	 * @param from_file The file the itemstack is gotten from.
	 * @return The file being returned.
	 */
	public static ItemStack deserialItemStack(File from_file)
	{
	  if(!from_file.exists()) return null;
	  return YamlConfiguration.loadConfiguration(from_file).getItemStack("item");
	}
	
}
