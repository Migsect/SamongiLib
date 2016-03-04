package net.samongi.SamongiLib.Vector;

import org.bukkit.util.Vector;

public class VectorUtil
{ 
  public static Vector rotateX(Vector vec, double angle)
  {
    double x = vec.getX();
    double y = (vec.getY() * Math.cos(Math.toRadians(angle))) - (vec.getZ() * Math.sin(angle));
    double z = (vec.getY() * Math.sin(Math.toRadians(angle))) + (vec.getZ() * Math.cos(angle));
    return new Vector(x,y,z);
  }
  public static Vector rotateY(Vector vec, double angle)
  {
    double x = (vec.getX() * Math.cos(Math.toRadians(angle))) + (vec.getZ() * Math.sin(angle));
    double y = vec.getY();
    double z = -(vec.getX() * Math.sin(Math.toRadians(angle))) + (vec.getZ() * Math.cos(angle));
    return new Vector(x,y,z);
  }
  public static Vector rotateZ(Vector vec, double angle)
  {
    double x = (vec.getX() * Math.cos(Math.toRadians(angle))) - (vec.getY() * Math.sin(angle));
    double y = (vec.getX() * Math.sin(Math.toRadians(angle))) + (vec.getY() * Math.cos(angle));
    double z = vec.getZ();
    return new Vector(x,y,z);
  }
}
