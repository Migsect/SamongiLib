package net.samongi.SamongiLib.Utilities;

import org.bukkit.Material;

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
	
	
}
