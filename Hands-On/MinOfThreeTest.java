import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class MinOfThreeTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
   
   @Test public void min1Test()
   {
      int values = MinOfThree.min1(2, 2, 2);
      int target = 2;
      assertEquals(target, values);
   }
   
   @Test public void min1Test2()
   {
      int values = MinOfThree.min1(2, 2, 1);
      int target = 1;
      assertEquals(target, values);
   }
   
   @Test public void min1Test3()
   {
      int values = MinOfThree.min1(2, 2, 3);
      int target = 2;
      assertEquals(target, values);
   }
   
   @Test public void min2Test1()
   {
      int values = MinOfThree.min1(2, 2, 1);
      int target = 1;
      assertEquals(target, values);
   }
   
   @Test public void min2Test2()
   {
      int values = MinOfThree.min1(2, 2, 1);
      int target = 1;
      assertEquals(target, values);
   }
   
}
