package net.samongi.SamongiLib.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.samongi.SamongiLib.Configuration.ConfigAccessor;

import org.bukkit.Material;
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
	  Material material = Material.getMaterial(config.getConfig().getString(path+"material","GRASS"));
	  // Getting the amount.  Defaults to 1.
	  int amount = config.getConfig().getInt(path+"amount",1);
	  // Getting the durability
	  short durability = (short)config.getConfig().getInt(path+"durability", 0);
	  // Getting the display name and formatting it.  If it is NONE, then it will not set display.  Defualt is NONE.
	  String display = StringUtilities.formatString(config.getConfig().getString(path+"display-name","NONE"));
	  // Getting the lore.
	  List<String> lore = StringUtilities.formatString(config.getConfig().getStringList(path+"lore"));
	  // Getting the enchantments
	  Map<Enchantment, Integer> enchants = new HashMap<>(); 
	  List<String> enchant_keys = new ArrayList<String>(config.getConfig().getConfigurationSection(path+"enchantments").getKeys(false));
	  for(String key : enchant_keys)
	  {
	    Enchantment ench = Enchantment.getByName(key);
	    if(ench == null) continue;
	    int ench_level = config.getConfig().getInt(path+"enchantments.key",1);
	    enchants.put(ench, ench_level);
	  }
	  ItemStack itemstack = new ItemStack(material, amount, durability);
	  ItemMeta im = itemstack.getItemMeta();
	  if(!display.equals("NONE"))im.setDisplayName(display);
	  im.setLore(lore);
	  for(Enchantment e : enchants.keySet())
	  {
	    im.addEnchant(e, enchants.get(e), true);
	  }
	  itemstack.setItemMeta(im);
	  
	  return itemstack;
	}
	
}
