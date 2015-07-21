package net.samongi.SamongiLib.Menu.ButtomAction;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.samongi.SamongiLib.Menu.InventoryMenu;

/**This represents an Inventory Menu button that, when pressed, will
 * open a menu for the clicker.
 */
public class ButtonOpenMenu implements ButtonAction
{
  private final ItemStack display;
  private final InventoryMenu menu;
  private final JavaPlugin plugin;
  public ButtonOpenMenu(InventoryMenu menu, ItemStack display, JavaPlugin plugin)
  {
    this.display = display.clone();
    this.menu = menu;
    this.plugin = plugin;
  }
  
  public void register(InventoryMenu menu, int slot)
  {
    menu.setItem(slot, display);
    menu.addClickAction(slot, this);
  }
  
  @Override
  public void onButtonPress()
  {
    menu.getPlayer().closeInventory();
    BukkitRunnable task = new BukkitRunnable()
    {
      @Override
      public void run()
      {
        menu.openMenu();
      }
    };
    task.runTaskLater(plugin, 1);
    
  }
  
}
