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
 * @author  Justin Sprouse (jss0077@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2/23/20
 *
 */
public class Extractor {
   
   /** raw data: all (x,y) points from source data. */
   private Point[] points;
   
   /** lines identified from raw data. */
   private SortedSet<Line> lines;
   
   private Iterator iterator;
  
   /**
    * Builds an extractor based on the points in the file named by filename. 
    */
   public Extractor(String filename) {
      int i = 0;
      
      int x;
      
      int y;
      
      try {
         
         Scanner scan = new Scanner(new File(filename));
         
         int length = scan.nextInt();
         points = new Point[length];
         
         while (scan.hasNext()) {
         
            x = scan.nextInt();
            y = scan.nextInt();
            
            Point addPoint = new Point(x, y);
            points[i] = addPoint;
            
            i++;
         
         }
         
      } catch (Exception e) {
      
         System.out.println("Error reading file");
      
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
      lines = new TreeSet<Line>();
      
      Point[] tempArray = Arrays.copyOf(points, points.length);
      
      double s1;
      
      double s2;
      
      double s3;
      
      for (int i = 3; i < tempArray.length; i++) {
         
         for (int j = 2; j < i; j++) {
            
            for (int k = 1; k < j; k++) {
               
               for (int l = 0; l < k; l++) {
                  s1 = tempArray[i].slopeTo(tempArray[j]);
                  s2 = tempArray[i].slopeTo(tempArray[k]);
                  s3 = tempArray[i].slopeTo(tempArray[l]);
                  
                  if ((s1 == s2) && (s2 == s3)) {
                  
                     Line tempLine = new Line();
                     
                     tempLine.add(tempArray[i]);
                     tempLine.add(tempArray[j]);
                     tempLine.add(tempArray[k]);
                     tempLine.add(tempArray[l]);
                     lines.add(tempLine);
                  
                  }
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
   public SortedSet<Line> getLinesFast() {
      lines = new TreeSet<Line>();
      this.iterator = lines.iterator();
      int equal = 0;
      int k;
      
      Point [] tempArray = Arrays.copyOf(points, points.length);
      
      for (int i = 0; i < points.length; i++) {
         
         Arrays.sort(tempArray, points[i].slopeOrder);
         
         for (int j = 0; j < points.length - 1; j += equal) {
            
            equal = 0;
            k = 0;
            
            while ((j + k < points.length) && ((points[i].slopeOrder.compare(tempArray[j], tempArray[j + k]) == 0 ))) {
            
               k++;
               equal++;
            
            }
            
            if (equal > 2) {
            
               Line tempLine = new Line();
               tempLine.add(points[i]);
               
               for (int l = 0; l < equal; l++) {
                  
                  tempLine.add(tempArray[j + l]);
               
               }
               
               lines.add(tempLine);
            
            }
         }
         
      }
      
      return lines;
   }
   
}
