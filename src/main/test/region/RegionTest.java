package region;

import com.longll.MinimumCloseArea;
import com.longll.Point;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author dragon
 * @create 2022-10-12 09:49
 * @description
 */
public class RegionTest {

    @Test
    public void testInPolygon(){
        List<Point> list = new ArrayList<>();
        Point point1 = new Point(0,0);
        Point point2 = new Point(7,1.5);
        Point point3 = new Point(0,3);
        Point point4 = new Point(3,1.5);
        Collections.addAll(list,point1,point2,point3,point4);
        boolean pointInPolygon = MinimumCloseArea.isPointInPolygon1(point4, list);
        System.out.println(pointInPolygon);
    }
}
