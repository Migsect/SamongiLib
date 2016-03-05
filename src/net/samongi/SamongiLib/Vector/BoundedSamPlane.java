package net.samongi.SamongiLib.Vector;

public class BoundedSamPlane extends SamPlane
{ 
  private SamVector bounds_1;
  private SamVector bounds_2;
  
  public BoundedSamPlane(SamVector origin, SamVector bounds_1, SamVector bounds_2)
  {
    super(origin, bounds_1, bounds_2);
    this.bounds_1 = bounds_1;
    this.bounds_2 = bounds_2;
  }
  @Deprecated
  @Override public boolean intersects(SamVector vector){return this.intersects(new SamLine(new SamVector(0,0,0),vector));}
  @Deprecated
  @Override public boolean intersects(SamLine line)
  {
    SamVector intersection = super.intersection(line);
    if(intersection == null) return false;
    
    SamVector position = intersection.subtract(this.getOrigin());
    
    // bad and no good
    double bounds_1_projection = position.dot(bounds_1);
    double bounds_2_projection = position.dot(bounds_2);
    if(bounds_1_projection < 0 || bounds_1_projection > 1) return false;
    if(bounds_2_projection < 0 || bounds_2_projection > 1) return false;
    return true;
  }
  @Deprecated
  @Override public boolean intersectsSegment(SamLine line)
  {
    if(!this.intersects(line)) return false;
    return super.intersectsSegment(line);
  }
  @Deprecated
  @Override public SamVector intersection(SamLine line)
  {
    if(!this.intersects(line)) return null;
    return super.intersection(line);
  }
}
