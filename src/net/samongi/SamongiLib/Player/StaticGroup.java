package net.samongi.SamongiLib.Player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class StaticGroup implements Group
{
	private List<OfflinePlayer> players;
	
	public StaticGroup(List<OfflinePlayer> players)
	{
		this.players = players;
	}

	@Override
  public int countPlayers()
  {
	  return players.size();
  }
	
	@Override
  public int countOnlinePlayers()
  {
	  int number = 0;
	  for(OfflinePlayer p : players)
	  {
	  	Player player = p.getPlayer();
	  	if(player == null) continue;
	  	number++;
	  }
	  return number;
  }

	@Override
  public List<Player> getPlayers()
  {
	  List<Player> normal_players = new ArrayList<>();
	  for(OfflinePlayer p : players)
	  {
	  	Player player = p.getPlayer();
	  	if(player == null) continue;
	  	normal_players.add(player);
	  }
	  return normal_players;
  }

	@Override
  public Group getStaticGroup()
  {
	  return this;
  }

	@Override
  public Group getSubSetGroup(Comparer compare)
  {
	  return new DynamicGroup(this, compare);
  }

	@Override
  public void performAction(Action action)
  {
	  for(Player p : this.getPlayers())
	  {
	  	action.perform(p);
	  }
  }


}
