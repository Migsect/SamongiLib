package net.samongi.SamongiLib.Shapes;

public class Coordinate
{
  private final double x;
  private final double y;
  
  public Coordinate(double x, double y)
  {
    this.x = x;
    this.y = y;
  }
  
  /**Gets the x (horizontal) component of the coordinate
   * 
   * @return
   */
  public double getX(){return this.x;}
  /**Gets the y (vertical) component of the coordinate
   * 
   * @return
   */
  public double getY(){return this.y;}
  /**Adds two coordinates together.
   * 
   * @param other
   * @return
   */
  public Coordinate add(Coordinate other){return new Coordinate(this.x + other.x, this.y + other.y);}
  /**Returns the negated value of this coordinate
   * for example if we have (x, y) this will return (-x, -y)
   * 
   * @return
   */
  public Coordinate opposite(){return new Coordinate(-this.x, -this.y);}
  /**Returns the distance squared between both coordinates
   * 
   * @param other
   * @return
   */
  public double distanceSquared(Coordinate other){return this.add(other.opposite()).cardinalitySquared();}
  /**Returns the distance between two coordinates
   * 
   * @param other
   * @return
   */
  public double distance(Coordinate other){return Math.sqrt(this.distanceSquared(other));}
  /**Returns the cardinality of this coordinate as if it was a 2d vector
   * This is the square of the cardinality
   * 
   * @return
   */
  public double cardinalitySquared(){return this.x * this.x + this.y * this.y;}
  /**Returns the cardinality of this coordinate
   * 
   * @return
   */
  public double cardinality(){return Math.sqrt(this.cardinalitySquared());}
}
