package net.samongi.SamongiLib.Effects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class Tether
{
  @SuppressWarnings("unused")
  private ParticleRenderer renderer;
  
  
  
  public interface Segment
  {
    public boolean hasNextSegment();
    public Segment getNextSegment();
    public double getSegmentLength();
    public double getSegmentDensity();

    public double getPitch();
    public void setPitch(double angle);
    public double getYaw();
    public void setYaw(double angle);
    public double getRoll();
    public void setRoll(double angle);
    
    public List<Location> chainRenderLocations(Location loc);
  }
  public class StaticSegment implements Segment
  {
    private final Segment next_segment;
    private final double length;
    private final double density;
    
    private double pitch;
    private double yaw;
    private double roll;
    
    private StaticSegment(Segment next_segment, double length, double density, double pitch, double yaw, double roll)
    {
      this.next_segment = next_segment;
      this.length = length;
      this.density = density;
      
      this.pitch = pitch;
      this.yaw = yaw;
      this.roll = roll;
      
    }
    
    @Override
    public boolean hasNextSegment(){return this.next_segment == null;}

    @Override
    public Segment getNextSegment(){return this.next_segment;}

    @Override
    public double getSegmentLength(){return this.length;}

    @Override
    public double getSegmentDensity(){return this.density;}
    
    @Override
    public double getPitch(){return this.pitch;}

    @Override
    public void setPitch(double angle)
    {
      // TODO Auto-generated method stub
      
    }

    @Override
    public double getYaw(){return this.yaw;}

    @Override
    public void setYaw(double angle)
    {
      // TODO Auto-generated method stub
      
    }

    @Override
    public double getRoll(){return this.roll;}

    @Override
    public void setRoll(double angle)
    {
      // TODO Auto-generated method stub
      
    }

    @Override
    public List<Location> chainRenderLocations(Location loc)
    {
      List<Location> locations = new ArrayList<>();
      @SuppressWarnings("unused")
      Vector direction = loc.getDirection();
      
      // TODO Auto-generated method stub
      List<Location> next_locations = new ArrayList<>();
      if(this.hasNextSegment()) next_locations = this.next_segment.chainRenderLocations(loc);
      locations.addAll(next_locations);
      return locations;
    }
    
  }
 
}
