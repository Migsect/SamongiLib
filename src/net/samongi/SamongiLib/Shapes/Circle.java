package net.samongi.SamongiLib.Shapes;

public class Circle implements Shape
{
  private final double radius;
  private final Coordinate origin;
  
  public Circle(double radius, Coordinate origin)
  {
    this.radius = radius;
    this.origin = origin;
  }
  
  public Coordinate getOrigin(){return this.origin;}
  public double getRadius(){return this.radius;}
  public double getRadiusSquared(){return this.radius * this.radius;}
  
  @Override public double getPerimeter(){return this.radius * 2 * Math.PI;}
  @Override public double getArea(){return this.radius * this.radius * Math.PI;}

  /**Gets the intersections of this circle with another circle
   * Returns the two coordinates for their intersection.
   * Returns one coordinate if the intersect at one coordinate
   * returns null if no intersections were found
   * 
   * @param other
   * @return
   */
  /*
  public Coordinate[] intersections(Circle othr)
  {
    // Checking to see if they will ever intersect
    double sum_radius = this.getRadius() + othr.getRadius();
    double origin_dist = this.getOrigin().distance(othr.getOrigin());
    if(origin_dist > sum_radius) return null;
    
    double t_radius_sq = this.getRadiusSquared();
    double o_radius_sq = othr.getRadiusSquared();
    
  }
  */
}
