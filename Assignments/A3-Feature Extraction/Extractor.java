import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author  Aaron Smith (abs0089@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2/23/2020
 *
 */
public class Extractor {
   
   /** raw data: all (x,y) points from source data. */
   private Point[] points;
   
   /** lines identified from raw data. */
   private SortedSet<Line> lines;
  
   /**
    * Builds an extractor based on the points in the file named by filename. 
    */
   public Extractor(String filename) 
   {
      try
      {
         Scanner scan = new Scanner(new File(filename));
         int size = scan.nextInt();
         points = new Point[size];
         int x;
         int y;
         for (int i = 0; i < size; i++)
         {
            x = scan.nextInt();
            y = scan.nextInt();
            points[i] = new Point(x, y);
         }
      }
      catch (java.io.IOException e)
      {
         System.err.println("File not found");
      }
      catch (Exception e)
      {
         System.err.println("An Error Occurred");
      }
   }
  
   /**
    * Builds an extractor based on the points in the Collection named by pcoll. 
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }
  
   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-force combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesBrute() {
      Line temp = new Line();
      lines = new TreeSet<Line>();
      for (int i = 0; i < points.length; i ++) 
      {
         for (int j = i + 1; j < points.length; j++) 
         {
            for (int k = j + 1; k < points.length; k++) 
            { 
               for (int l = k + 1; l < points.length; l++) 
               {
                  temp.add(points[i]);
                  temp.add(points[j]);
                  if (temp.add(points[k]) && temp.add(points[l]) && temp.length() == 4) { 
                     lines.add(temp);
                  }
                  temp = new Line();
               }
            }
         }       
      }
      return lines;
   }
  
   /**
    * Returns a sorted set of all line segments of at least four collinear
    * points. The line segments are maximal; that is, no sub-segments are
    * identified separately. A sort-and-scan strategy is used. Returns an empty
    * set if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesFast() 
   {
      Point[] sorted = Arrays.copyOf(points, points.length);
      lines = new TreeSet<Line>();
      Line temp = new Line(); 
      boolean able = true;
      for (int i = 0; i < points.length; i++) 
      { 
         Arrays.sort(sorted, points[i].slopeOrder);
         for (int j = 0; j < points.length; j++) 
         {
            temp.add(sorted[0]); 
            able = temp.add(sorted[j]); 
            if (!able) 
            {
               if (temp.length() >= 4) 
               { 
                  lines.add(temp);
               }
               temp = new Line();
               temp.add(sorted[j]);
            }
         }
      }
      return lines;
   }  
}