package net.samongi.SamongiLib.Utilities;

public class ArrayUtil
{
  static public String arrayToString(Object[] a)
  {
    String ret = "[";
    for(Object o : a)
    {
      ret += o.toString() + ",";
    }
   return ret.substring(0, ret.length()) + "]";
  }
}
