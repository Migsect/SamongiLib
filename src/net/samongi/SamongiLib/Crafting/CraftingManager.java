package net.samongi.SamongiLib.Crafting;

import net.samongi.SamongiLib.SamongiLib;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class CraftingManager implements Listener {
    @EventHandler public void onCraftItem(CraftItemEvent event) {
        if (event.isCancelled()) {
            return;
        }

        Recipe recipe = event.getRecipe(); // getting the recipe
        ItemStack result = recipe.getResult(); // getting the result of the craft event

        SamongiLib.logger.fine("[CraftItemEvent]: Item Crafted Type: " + result.getType());
        Material resultMaterial = recipe.getResult().getType();

        // Calculating the ingredients for material-ingredient experience.
        ItemStack[] craftingMatrix = event.getInventory().getMatrix();
        List<Material> ingredients = new ArrayList<>();

        // Note that this result will not be the total number of items that were crafted.
        Player player = (Player) event.getWhoClicked();
        ItemStack[] currentContents = player.getInventory().getContents();
        ItemStack[] priorContents = new ItemStack[currentContents.length];
        // We're doing a copy since we can't be sure if the current contents are immutable
        for (int i = 0; i < priorContents.length; i++) {
            if (currentContents[i] == null) {
                continue;
            }
            priorContents[i] = currentContents[i].clone();
        }

        ItemCraftedEvent itemCraftedEvent = new ItemCraftedEvent(event, 1);

        // We are going to see how many times the item was actually crafted.
        if (event.isShiftClick()) {
            BukkitRunnable task = new BukkitRunnable() {

                @Override public void run() {
                    final ItemStack[] new_contents = player.getInventory().getContents();

                    // We are now going to count the number of items that the player has crafted.
                    int itemsCrafted = 0;
                    for (int i = 0; i < new_contents.length; i++) {
                        ItemStack newItem = new_contents[i];
                        ItemStack oldItem = priorContents[i];
                        if (newItem != oldItem) {
                            if (newItem.getType() != resultMaterial) {
                                continue;
                            }

                            SamongiLib.logger.finest("Found Inv Difference: " + newItem + " =/= " + oldItem);
                            if (oldItem == null || oldItem.getType().equals(Material.AIR)) {
                                // This occurs when the item is a new stack altogether.
                                itemsCrafted += newItem.getAmount();
                            } else {
                                // We are going to add the difference of the items.
                                itemsCrafted += newItem.getAmount() - oldItem.getAmount();
                            }
                        }
                    }
                    // The total times the item was crafted
                    int totalCrafts = itemsCrafted / result.getAmount();
                    SamongiLib.logger.finest("Items crafted: " + itemsCrafted + " result amount: " + result.getAmount() + " total crafts: " + totalCrafts);

                    itemCraftedEvent.setAmountCrafted(totalCrafts);
                    Bukkit.getPluginManager().callEvent(itemCraftedEvent);

                }
            };
            task.runTask(SamongiLib.getInstance()); // Running the task
        } else {
            Bukkit.getPluginManager().callEvent(itemCraftedEvent);
        }
    }
}
