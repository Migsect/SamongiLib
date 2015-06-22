package net.samongi.SamongiLib.Maps.Spawn;

import java.util.List;

import org.bukkit.entity.Player;

public interface Spawn
{
  /**Spawns the player at the spawn.
   * 
   * @param player
   */
  public void spawn(Player player);
  /**Gets a ratio that is a measure of how close the spawn is to the list of players.
   * 
   * @param players a list of players to check.
   * @return a ratio of closeness.
   */
  public double getDistanceRelation(List<Player> players);
  
  /**Returns true if the player's distance is below the range
   * Returns false if the player is outside the range.
   * 
   * @param players A list of players to check
   * @param range The range to check for
   * @return True if they are within range, otherwise false.
   */
  public boolean isWithinRange(List<Player> players, double range);
}
