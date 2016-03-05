package net.samongi.SamongiLib.Vector;

import java.util.Map;
import java.util.Random;

import org.bukkit.util.Vector;

public class SamVector extends Vector
{
  
  public static SamVector convert(Double[] array)
  {
    if(array.length != 3) return null;
    return new SamVector(array[0],array[1],array[2]);
  }
  public static SamVector convert(double[] array)
  {
    if(array.length != 3) return null;
    return new SamVector(array[0],array[1],array[2]);
  }
  public static SamVector convert(int[] array)
  {
    if(array.length != 3) return null;
    return new SamVector(array[0],array[1],array[2]);
  }
  public static SamVector convert(Integer[] array)
  {
    if(array.length != 3) return null;
    return new SamVector(array[0],array[1],array[2]);
  }
  public static SamVector convert(float[] array)
  {
    if(array.length != 3) return null;
    return new SamVector(array[0],array[1],array[2]);
  }
  public static SamVector convert(Float[] array)
  {
    if(array.length != 3) return null;
    return new SamVector(array[0],array[1],array[2]);
  }
  
  public SamVector(){super();}
  public SamVector(double x, double y, double z){super(x,y,z);}
  public SamVector(float x, float y, float z){super(x,y,z);}
  public SamVector(int x, int y, int z){super(x,y,z);}
  public SamVector(Vector v){super(v.getX(), v.getY(), v.getZ());}

  public SamVector rotateX(double angle)
  {
    double x = this.getX();
    double y = (this.getY() * Math.cos(angle)) - (this.getZ() * Math.sin(angle));
    double z = (this.getY() * Math.sin(angle)) + (this.getZ() * Math.cos(angle));
    return new SamVector(x,y,z);
  }
  public SamVector rotateY(double angle)
  {
    double x = (this.getX() * Math.cos(angle)) + (this.getZ() * Math.sin(angle));
    double y = this.getY();
    double z = -(this.getX() * Math.sin(angle)) + (this.getZ() * Math.cos(angle));
    return new SamVector(x,y,z);
  }
  public SamVector rotateZ(double angle)
  {
    double x = (this.getX() * Math.cos(Math.toRadians(angle))) - (this.getY() * Math.sin(angle));
    double y = (this.getX() * Math.sin(Math.toRadians(angle))) + (this.getY() * Math.cos(angle));
    double z = this.getZ();
    return new SamVector(x,y,z);
  }
  public SamVector rotate(double angle, Vector axis){return this.rotate(angle, new SamVector(axis));}
  
  public SamVector rotate(double angle, SamVector axis)
  {
    SamVector part1 = this.multiply(Math.cos(angle));
    SamVector part2 = axis.crossProduct(this).multiply(Math.sin(angle));
    SamVector part3 = axis.multiply(axis.dot(this)).multiply(1 - Math.cos(angle));
    return part1.add(part2).add(part3);
  }
  
  public boolean isPerpendicular(SamVector vec){return Math.abs(this.dot(vec)) <= SamVector.getEpsilon();}
  public boolean isParallel(SamVector vec){return this.normalize().equals(vec.normalize());}
  /**Projects the other vector onto this vector
   * @param vec The vector being projected onto this vector.
   * @return A projected vector onto this vector.
   */
  public SamVector project(SamVector vec){return this.normalize().multiply(this.dot(vec));}
  public SamVector getRandomPerpendicular()
  {
    Random rand = new Random();
    if(this.getX() != 0 && this.getY() != 0 && this.getZ() != 0)
    {
      double x = 1 - (2 * rand.nextDouble()); // get a number between -1 and 1
      double y = 1 - (2 * rand.nextDouble()); // get a number between -1 and 1
      double z = -x - y; // this negative is important.
      SamVector new_vec = new SamVector(x / this.getX(), y / this.getY(), z / this.getZ());
      return new_vec.normalize();
    }
    else if(this.getX() != 0 && this.getY() != 0)
    {
      double x = 1 - (2 * rand.nextDouble()); // get a number between -1 and 1
      double y = -x;
      double z = 1 - (2 * rand.nextDouble()); // get a number between -1 and 1
      SamVector new_vec = new SamVector(x / this.getX(), y / this.getY(), z);
      return new_vec.normalize();
    }
    else if(this.getX() != 0  && this.getZ() != 0)
    {
      double x = 1 - (2 * rand.nextDouble()); // get a number between -1 and 1
      double y = 1 - (2 * rand.nextDouble()); // get a number between -1 and 1
      double z = -x;
      SamVector new_vec = new SamVector(x / this.getX(), y, z / this.getZ());
      return new_vec.normalize();
    }
    else if(this.getY() != 0 && this.getZ() != 0)
    {
      double x = 1 - (2 * rand.nextDouble()); // get a number between -1 and 1
      double y = 1 - (2 * rand.nextDouble()); // get a number between -1 and 1
      double z = -y;
      SamVector new_vec = new SamVector(x, y / this.getY(), z / this.getZ());
      return new_vec.normalize();
    }
    else if(this.getX() != 0)
    {
      double y = 1 - (2 * rand.nextDouble()); // get a number between -1 and 1
      double z = 1 - (2 * rand.nextDouble()); // get a number between -1 and 1
      SamVector new_vec = new SamVector(0, y, z);
      return new_vec.normalize();
    }
    else if(this.getY() != 0)
    {
      double x = 1 - (2 * rand.nextDouble()); // get a number between -1 and 1
      double z = 1 - (2 * rand.nextDouble()); // get a number between -1 and 1
      SamVector new_vec = new SamVector(x, 0, z);
      return new_vec.normalize();
    }
    else if(this.getZ() != 0)
    {
      double x = 1 - (2 * rand.nextDouble()); // get a number between -1 and 1
      double y = 1 - (2 * rand.nextDouble()); // get a number between -1 and 1
      SamVector new_vec = new SamVector(x, y , 0);
      return new_vec.normalize();
    }
    else return this;
  }
  
  public Vector bukkit(){return new Vector(this.getX(), this.getY(), this.getZ());}
  
  public SamVector add(SamVector vec){return new SamVector(this.add(vec.bukkit().clone()));}
  public SamVector crossProduct(SamVector vec){return new SamVector(this.crossProduct(vec.bukkit().clone()));}
  public SamVector subtract(SamVector vec){return new SamVector(this.subtract(vec.bukkit().clone()));}
  public SamVector divide(SamVector vec){return new SamVector(this.divide(vec.bukkit().clone()));}
  public SamVector copy(SamVector vec){return new SamVector(this.copy(vec.bukkit().clone()));}
  
  public boolean isInAABB(SamVector min, SamVector max){return this.isInAABB(min.bukkit().clone(), max.bukkit().clone());}
  public boolean isInSphere(SamVector origin, double radius){return this.isInSphere(origin.bukkit().clone(), radius);}
  /**Checks to see if the vector is normal to the other vector
   * This uses an epsilon since we are working with doubles.
   * 
   * @param other
   * @return
   */
  public boolean isNormalTo(SamVector other){return Math.abs(this.dot(other)) < SamVector.getEpsilon();}
  
  
  public SamVector normalize(){return new SamVector(this.bukkit().clone().normalize());}
  public SamVector midpoint(SamVector vec){return new SamVector(this.midpoint(vec.bukkit().clone()));}
  public SamVector getMidpoint(SamVector vec){return new SamVector(this.getMidpoint(vec.bukkit().clone()));}
  static public SamVector getMaximum(SamVector v1, SamVector v2){return new SamVector(Vector.getMaximum(v1.bukkit().clone(), v2.bukkit().clone()));}
  static public SamVector getMinimum(SamVector v1, SamVector v2){return new SamVector(Vector.getMinimum(v1.bukkit().clone(), v2.bukkit().clone()));}
  
  static public SamVector getRandom(){return new SamVector(Vector.getRandom());}
  /**Returns a random vector that has a length equal to or less than the supplied length
   * 
   * @param length The length the vector will be at or less
   * @return The vector returned.
   */
  static public SamVector getRandomWithinLength(double length)
  {
    Random rand = new Random();
    double rand_length = length * rand.nextDouble();
    return SamVector.getRandom().normalize().multiply(rand_length);
    
  }
  /**Returns a random vector that has a length equal to the required length.
   * 
   * @param length The length that the vector will be at.
   * @return The vector returned.
   */
  static public SamVector getRandomAtLength(double length){return SamVector.getRandom().normalize();}
  
  public SamVector multiply(int m){return new SamVector(this.bukkit().clone().multiply(m));}
  public SamVector multiply(double m){return new SamVector(this.bukkit().clone().multiply(m));}
  public SamVector multiply(float m){return new SamVector(this.bukkit().clone().multiply(m));}
  public SamVector multiply(SamVector vec){return new SamVector(this.bukkit().clone().multiply(vec.bukkit()));}
  
  public SamVector setX(double m){return new SamVector(this.bukkit().clone().setX(m));}
  public SamVector setX(float m){return new SamVector(this.bukkit().clone().setX(m));}
  public SamVector setX(int m){return new SamVector(this.bukkit().clone().setX(m));}
  public SamVector setY(double m){return new SamVector(this.bukkit().clone().setY(m));}
  public SamVector setY(float m){return new SamVector(this.bukkit().clone().setY(m));}
  public SamVector setY(int m){return new SamVector(this.bukkit().clone().setY(m));}
  public SamVector setZ(double m){return new SamVector(this.bukkit().clone().setZ(m));}
  public SamVector setZ(float m){return new SamVector(this.bukkit().clone().setZ(m));}
  public SamVector setZ(int m){return new SamVector(this.bukkit().clone().setZ(m));}
  
  public SamVector zero(){return new SamVector(this.bukkit().clone().zero());}
  
  public static SamVector deserialize(Map<String,Object> args){return new SamVector(Vector.deserialize(args));}
  
  
  
}
