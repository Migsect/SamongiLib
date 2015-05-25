package net.samongi.SamongiLib.Items;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.samongi.SamongiLib.SamongiLib;
import net.samongi.SamongiLib.Configuration.ConfigAccessor;
import net.samongi.SamongiLib.Utilities.StringUtilities;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtilities
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
	  // Getting the material. Defaults to grass.
	  if(SamongiLib.debugger) SamongiLib.logger.info("Parsing ItemStack for path: '" + path + "'");
	  Material material = Material.getMaterial(config.getConfig().getString(path+".material","GRASS"));
	  if(material == null) material = Material.GRASS;
	  if(SamongiLib.debugger) SamongiLib.logger.info("  Found material: " + material.toString());
	  // Getting the amount.  Defaults to 1.
	  int amount = config.getConfig().getInt(path+".amount",1);
	  if(SamongiLib.debugger) SamongiLib.logger.info("  Found amount: " + amount);
	  // Getting the durability
	  short durability = (short)config.getConfig().getInt(path+".durability", 0);
	  if(SamongiLib.debugger) SamongiLib.logger.info("  Found durability: " + durability);
	  
	  // making the first object:
	  ItemStack itemstack = new ItemStack(material, amount, durability);
    ItemMeta im = itemstack.getItemMeta();
	  
	  // Getting the display name and formatting it.  If it is NONE, then it will not set display.  Defualt is NONE.
	  String display = StringUtilities.formatString(config.getConfig().getString(path+".display-name","NONE"));
    if(SamongiLib.debugger) SamongiLib.logger.info("  Found display: " + display);
    if(!display.equals("NONE"))im.setDisplayName(display);
	  // Getting the lore.
	  List<String> lore = StringUtilities.formatString(config.getConfig().getStringList(path+".lore"));
	  if(lore != null)
	  {
  	  if(SamongiLib.debugger) SamongiLib.logger.info("  Found Lore:");
  	  if(SamongiLib.debugger) for(String l : lore)
  	  {
  	    SamongiLib.logger.info("   - " + l);
  	  }
  	  if(lore.size() != 0)im.setLore(lore);
	  }
	  // Getting the enchantments
	  if(config.getConfig().getConfigurationSection(path).getKeys(false).contains("enchantments")) //check to see if the config section exists.
	  {
  	  if(SamongiLib.debugger) SamongiLib.logger.info("  Found enchantments:");
  	  Map<Enchantment, Integer> enchants = new HashMap<>(); 
  	  List<String> enchant_keys = new ArrayList<String>(config.getConfig().getConfigurationSection(path+".enchantments").getKeys(false));
  	  for(String key : enchant_keys)
  	  {
  	    Enchantment ench = Enchantment.getByName(key);
  	    if(ench == null) continue;
  	    int ench_level = config.getConfig().getInt(path+".enchantments."+key,1);
  	    enchants.put(ench, ench_level);
  	    if(SamongiLib.debugger) SamongiLib.logger.info("   - " + ench.toString() + " : " + ench_level);
  	  }
      // setting the enchants.
  	  for(Enchantment e : enchants.keySet())
  	  {
  	    im.addEnchant(e, enchants.get(e), true);
  	  }
	  }
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
