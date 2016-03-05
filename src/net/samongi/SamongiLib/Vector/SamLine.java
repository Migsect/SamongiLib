package net.samongi.SamongiLib.Vector;

public class SamLine
{
  private SamVector origin;
  private SamVector direction;
  
  SamLine(SamVector origin, SamVector direction)
  {
    this.origin = origin;
    this.direction = direction;
  }
  
  /**Returns the origin of the line
   * 
   * @return
   */
  public SamVector getOrigin(){return this.origin;}
  /**Returns the direction of the line
   * 
   * @return
   */
  public SamVector getDirection(){return this.direction;}
  /**Returns the segment end of the line given the line
   * is being treated as a segment.
   * 
   * @return
   */
  public SamVector getSegmentEnd(){return this.origin.add(this.direction);}
  /**Will normalize the line such that its direction vector has a magnitude of 1.
   * This effectively makes the segment have a length of 1
   * 
   * @return
   */
  public SamLine normalize(){return new SamLine(this.origin, this.direction.normalize());}
}
