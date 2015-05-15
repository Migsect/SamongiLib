package net.samongi.SamongiLib.Utilities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LoreUtilities
{
	/**Sets the lore line number of the item to the wanted String
	 *    Will create neccessary lines to be able to add.
	 *    
	 * @oaram ItemStack -> Item to be changed.
	 * @param Int -> The line number being set
	 * @param String -> The text to be inserted (Should be pre-formatted)
	 * @return Void
	 */
	public static void setLoreLine(ItemStack item, int line, String text)
	{
		ItemMeta im = item.getItemMeta();
		List<String> lore = im.getLore();
		if(lore == null) lore = new ArrayList<String>();
		
		int lines_to_make = (line + 1) - lore.size();
		if(lines_to_make > 0) for(int i = 0; i < lines_to_make; i++) lore.add("");
		lore.set(line, text);
		
		im.setLore(lore);
		item.setItemMeta(im);
	}
	/**Inserts a line into the lore at the specified line (-1 places it at the beginning)
	 * If the item has no lore to be inserted to, it will create empty lines.
	 *    
	 * @param ItemStack -> Item to be changed.
	 * @param Int -> The line after which the line to be inserted (-1 is at front)
	 * @param String -> Text to be inserted.
	 * @return Void
	 */
	public static void insertLoreLine(ItemStack item, int after_line, String text)
	{
		ItemMeta im = item.getItemMeta();
		List<String> lore = im.getLore();
		if(lore == null) lore = new ArrayList<String>();
		
		if(after_line == lore.size() - 1) lore.add(text);
		
		im.setLore(lore);
		item.setItemMeta(im);
	}
	/*Returns the item meta.  If there is no item meta it will make the item meta.
	 *    
	 * @param ItemStack -> The Itemstack to get the Item meta from
	 * @return ItemMeta -> The new or current item meta
	 */
	/*
  private static ItemMeta getItemMeta(ItemStack item)
	{
		ItemMeta im = item.getItemMeta();
		return im;
		
	}*/
	/*Gets the item's lore, if it doesn't exist is make an empty list and return that.
	 *    
	 * @param ItemMeta -> Itemmeta to get lore from
	 * @return List<String> -> Lore, will be empty if the item had no lore.
	 */
	/*
	private static List<String> getItemLore(ItemMeta meta)
	{
		
	} */
}
