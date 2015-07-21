package net.samongi.SamongiLib.Menu.Listener;

import net.samongi.SamongiLib.SamongiLib;
import net.samongi.SamongiLib.Menu.InventoryMenu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;

public class MenuListener implements Listener
{
  private static void debugLog(String message){SamongiLib.debugLog("[MenuListener] " + message);}
  
  @EventHandler
  public void onInventoryClick(InventoryClickEvent event)
  {
    Inventory inventory = event.getClickedInventory();
    Inventory top_inventory = event.getInventory();
    if(inventory == null) return;
    if(top_inventory == null) return;
    MenuListener.debugLog("Clicked Inv: " + inventory.getTitle());
    MenuListener.debugLog("Top Inv: " + top_inventory.getTitle());
    Player player = (Player)event.getWhoClicked();
    /* If the menu being clicked is not a inventory menu, then it will return.
     * However if the bottom menu is shift clicked and the top menu is not a menu
     */
    if(event.isShiftClick() && !InventoryMenu.isMenu(top_inventory)) return;
    if(!event.isShiftClick() && !InventoryMenu.isMenu(inventory)) return;
    InventoryMenu menu = InventoryMenu.getMenu(player, top_inventory);
    menu.onInventoryClickEvent(event);
    event.setCancelled(true);
  }
  /**This will detect when an inventory menu is closed by a player and
   * remove it from the list that listening for any event relating to it
   * This is to optimize and regulate any possible memory leaks.
   * 
   * @param event The event by which this happens.
   */
  @EventHandler
  public void onInventoryClose(InventoryCloseEvent event)
  {
    Inventory inventory = event.getInventory();
    if(inventory == null) return;
    if(!InventoryMenu.isMenu(inventory)) return;
    Player player = (Player)event.getPlayer();
    InventoryMenu menu = InventoryMenu.getMenu(player, inventory);
    InventoryMenu.removeMenu(player, menu);
    
  }
  /**This will simply cancel the drag event, we currently have no implementation
   * to handle drag events or add functionality to the interface
   * as such we will simply be ignoring it if it happens.
   * 
   * @param event The event by which this happens.
   */
  @EventHandler
  public void onInventoryDrag(InventoryDragEvent event)
  {
    Inventory inventory = event.getInventory();
    if(inventory == null) return;
    if(!InventoryMenu.isMenu(inventory)) return;
    event.setCancelled(true);
  }
  
  @EventHandler
  public void onInventoryMoveItem(InventoryMoveItemEvent event)
  {
    Inventory inventory = event.getDestination();
    if(inventory == null) return;
    if(!InventoryMenu.isMenu(inventory)) return;
    event.setCancelled(true);
  }
}
