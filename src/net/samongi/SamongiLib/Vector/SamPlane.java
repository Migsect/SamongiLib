package net.samongi.SamongiLib.Vector;

public class SamPlane
{ 
  private SamVector origin;
  private SamVector normal;
  
  
  /**Constructs a plane that intersects the origin
   * and has all vectors in the plane normal the specified normal vector
   * 
   * @param origin
   * @param normal
   */
  public SamPlane(SamVector origin, SamVector normal)
  {
    this.origin = origin;
    this.normal = normal;
  }
  /**Constructs a plane that contains both the specified vectors
   * that also intersects the origin.
   * 
   * @param origin
   * @param member_1
   * @param member_2
   */
  public SamPlane(SamVector origin, SamVector member_1, SamVector member_2)
  {
    this.origin = origin;
    this.normal = member_1.crossProduct(member_2);
  }
  
  /**Checks to see if the vector will ever intersect with the plane
   * given the vector can be scaled by any number-n.
   * This will only return false if the dotproduct of the vector
   * and the plane's normal vector are 0.  This means that the vector
   * is parallel to the plane.
   * 
   * @param vector
   * @return
   */
  public boolean intersects(SamVector vector){return this.normal.isNormalTo(vector);}
  /**Checks to see if the line will interesect with the plane.
   * This merely checks to see if the line's direction and the plane's normal vector
   * have a dotproduct that isn't zero.
   * 
   * @param line
   * @return
   */
  public boolean intersects(SamLine line){return this.intersects(line.getDirection());}
  /**Checks to see if the line segment will interesect the plane.
   * A line segment is defined as when a line's direction scalar (known as t) is 0 or 1
   * 
   * @param line
   * @return
   */
  public boolean intersectsSegment(SamLine line)
  {
    if(!this.intersects(line)) return false;
    double scalar = (this.normal.dot(this.origin) + this.normal.dot(line.getOrigin())) / this.normal.dot(line.getDirection());
    if(scalar < 0 || scalar > 1) return false;
    return true;
  }
  /**Returns a vector of the intersection between the line and this
   * plane.  This will return null if the line will never intersect.
   * Intersection is determined by the intersects function.
   * 
   * @param line
   * @return
   */
  public SamVector intersection(SamLine line)
  {
    if(!this.intersects(line)) return null;
    SamVector new_direction = line.getDirection().multiply((this.normal.dot(this.origin) + this.normal.dot(line.getOrigin())) / this.normal.dot(line.getDirection()));
    return new_direction.add(line.getOrigin());
  }
  
  public SamVector getOrigin(){return this.origin;}
  public SamVector getNormal(){return this.normal;}
}
