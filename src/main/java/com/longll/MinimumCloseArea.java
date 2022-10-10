package com.longll;

import com.longll.util.CaculateUtil;

import java.util.*;

/**
 * @author dragon
 * @create 2022-09-21 16:01
 * @description
 */
public class MinimumCloseArea {

    public static void main(String[] args) {
        //List<Line> lines = getLines1();
        List<Line> lines = getLines2();
        List<List<Line>> lists = calculateMiniumClosedArea(lines);
        System.out.println(lists.size());
        System.out.println(lists);
    }

    private static List<Line> getLines2() {
        Line line1 = new Line(new Point(0, 0), new Point(4, 0));
        Line line2 = new Line(new Point(4, 0), new Point(4, 4));
        Line line3 = new Line(new Point(4, 4), new Point(0, 4));
        Line line4 = new Line(new Point(0, 4), new Point(0, 0));
        Line line5 = new Line(new Point(5, 0), new Point(10, 0));
        Line line6 = new Line(new Point(10, 0), new Point(10, 5));
        Line line7 = new Line(new Point(10, 5), new Point(5, 5));
        Line line8 = new Line(new Point(5, 5), new Point(5, 0));
        List<Line> lines = new ArrayList<>();
        Collections.addAll(lines, line1, line2, line3, line4, line5, line6, line7, line8);
        return lines;
    }

    private static List<Line> getLines1() {
        Line line1 = new Line(new Point(0, 0), new Point(4, 0));
        Line line2 = new Line(new Point(4, 0), new Point(4, 4));
        Line line3 = new Line(new Point(4, 4), new Point(0, 4));
        Line line4 = new Line(new Point(0, 4), new Point(0, 0));
        Line line5 = new Line(new Point(6, 2), new Point(6, 6));
        Line line6 = new Line(new Point(6, 6), new Point(2, 6));
        Line line7 = new Line(new Point(2, 6), new Point(2, 2));
        Line line8 = new Line(new Point(2, 2), new Point(6, 2));
        Line line9 = new Line(new Point(5, 8), new Point(5, 5));
        Line line10 = new Line(new Point(8, 8), new Point(10, 8));
        List<Line> lines = new ArrayList<>();
        Collections.addAll(lines, line1, line2, line3, line4, line5, line6, line7, line8, line9, line10);
        return lines;
    }

    /*-------------------程序思路------------------------*/
/*
	①：得到线段集合S（线段的集合为line和polyline）

	②：移除孤立的线段（两端的点都是孤立的）；

	③：拆分所有的线段（就是将所有的相交线线段拆分开,得到新的线段集合S）

	④：移除一个端点孤立的线段（一个端点孤立的线段是不构成多边形的）

	⑤：计算最小闭合区域

	⑥：去除不合理的区域*/


    public static List<List<Line>> calculateMiniumClosedArea(List<Line> allLines) {
        //移除孤立的线段（两端的点都是孤立的）
        List<Line> nonIsolatedLine = remove_isolated_line(allLines);
        //拆分所有的线段（将所有的相交线段拆分开，得到新的线段集合是）
        List<Line> breakLine = breakLines(nonIsolatedLine);
        //移除一个端点孤立的线段
        List<Line> noneIsolatedLines = removeNoneFieldLines(breakLine);
        //获取所有交点
        List<Point> allCrossPoint = getAllCrossPoint(noneIsolatedLines);
        //获取所有有向线段
        List<Line> directedLine = getDirectedLine(noneIsolatedLines);
        //设置交点与线段关联
        List<AssociatedLine> associatedLine = getAssociatedLine(directedLine, allCrossPoint);
        //获取闭合区域
        List<List<Line>> closedArea = getCloseAreas(directedLine, associatedLine);
        //移除包含所有交点的闭合区域
        List<List<Line>> removeBigClosedArea = removeBigClosedArea(closedArea, allCrossPoint);
        //去除闭合区域内还含有其他交点的区域
        List<List<Line>> removePointInArea = removePointInArea(removeBigClosedArea, allCrossPoint);
        //去除重叠区域
        List<List<Line>> removeOverlapArea = removeOverlapArea(removePointInArea);
        //去除内部仍然包含有图形的区域
        List<List<Line>> removeContainArea = removeContainArea(removeOverlapArea);
        return removeContainArea;
    }


    //②：移除孤立的线段（两端的点都是孤立的）；
    public static List<Line> remove_isolated_line(List<Line> lines) {
        List<Line> output = new ArrayList<>();
        for (Line line1 : lines) {
            for (Line line2 : lines) {
                if (CaculateUtil.doselinecross(line1, line2)) {
                    output.add(line1);
                    break;
                }
            }
        }
        return output;
    }

    /*-------------程序思路③：拆分所有的线段（就是将所有的相交线线段拆分开，得到新的线段集合S）--------------*/
    //分割所有的线段，得到新的线段数组
    public static List<Line> breakLines(List<Line> lines) {
        List<Line> output = new ArrayList<>();
        for (Line line1 : lines) {
            List<Point> crossPointList = new ArrayList<>();
            for (Line line2 : lines) {
                if (CaculateUtil.doselinecross(line1, line2)) {
                    //交点不是line的起点和终点
                    Point crossPoint;
                    crossPoint = line1.getCrossPoint(line2);
                    if (!crossPoint.isEqualTo(line1.getStart()) && !crossPoint.isEqualTo(line1.getEnd())) {
                        crossPointList.add(crossPoint);
                    }
                }
            }
            crossPointList.add(0, line1.getStart());
            crossPointList.add(line1.getEnd());
            //计算每一个交点与line的起点距离，并增序排序
            for (int m = 0; m < crossPointList.size() - 1; m++) {
                for (int n = 0; n < crossPointList.size() - m - 1; n++) {
                    double distM = line1.getStart().distance(crossPointList.get(n));
                    double distN = line1.getStart().distance(crossPointList.get(n + 1));
                    if (distM > distN) {
                        swap(crossPointList, n, n + 1);
                    }
                }
            }
            //将line1所截断的线段放入output中
            for (int k = 0; k < crossPointList.size() - 1; k++) {
                Line lineSegment = new Line();
                lineSegment.setStart(crossPointList.get(k));
                lineSegment.setEnd(crossPointList.get(k + 1));
                output.add(lineSegment);
            }
        }
        return output;
    }

    //程序思路4 移除一个端点孤立的线段（一个端点孤立的线段是不构成多边形的）
    public static List<Line> removeNoneFieldLines(List<Line> lines) {
        List<Line> output = new ArrayList<>();
        int size = lines.size();
        for (int i = 0; i < size; i++) {
            List<Point> crossPointList = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                if (CaculateUtil.doselinecross(lines.get(i), lines.get(j))) {
                    crossPointList.add(lines.get(i).getCrossPoint(lines.get(j)));
                }
            }
            boolean bEqual = false;
            for (int m = 0; m < crossPointList.size(); m++) {
                if (lines.get(i).getStart().isEqualTo(crossPointList.get(m))) {
                    bEqual = true;
                    break;
                }
            }
            if (bEqual) {
                for (int n = 0; n < crossPointList.size(); n++) {
                    if (lines.get(i).getEnd().isEqualTo(crossPointList.get(n))) {
                        output.add(lines.get(i));
                        break;
                    }
                }
            }
        }
        return output;
    }


    /**
     * 移除大区域包含小区域的图形
     * 只有一种情况：大区域的交点包含小区域的所有的交点
     *
     * @param areas
     * @return
     */
    private static List<List<Line>> removeContainArea(List<List<Line>> areas) {
        List<List<Line>> result = new ArrayList<>();
        int size = areas.size();
        for (int i = 0; i < size; i++) {
            List<Line> areaA = areas.get(i);//获得区域A
            List<Point> aCrossPoints = getAllCrossPoint(areaA);
            boolean isContain = false;
            for (int j = 0; j < size; j++) {
                List<Line> areaB = areas.get(j);//获取区域B
                List<Point> bCrossPoints = getAllCrossPoint(areaB);
                //判断A中是否包含有其他区域B
                //判断方法：当A和B的顶点数不相同时，若区域A包含区域B的所有顶点，则包含，删除A
                if (isContainPoints(bCrossPoints, aCrossPoints)) {
                    isContain = true;
                    break;
                }
            }
            if (!isContain) {//区域A中没有包含其他区域
                result.add(areaA);
            }
        }
        return result;
    }

    /**
     * 判断点集合pointsA是否包含在点集合pointsB中，B的点数量大于A的点数量
     *
     * @param pointsA
     * @param pointsB
     * @return
     */
    private static boolean isContainPoints(List<Point> pointsA, List<Point> pointsB) {
        int sizeA = pointsA.size();
        int sizeB = pointsB.size();
        //若B中包含有A,B的顶点数量要大于A的顶点数量
        if (sizeA >= sizeB) {
            return false;
        }
        if (pointsB.containsAll(pointsA)) {
            return true;
        }
        return false;
    }

    private static List<List<Line>> removeOverlapArea(List<List<Line>> closedArea) {
        int n = closedArea.size();
        if (n < 2) {
            return closedArea;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (isOverlap(closedArea.get(i), closedArea.get(j))) {
                    n--;
                    for (int k = i; k < n; k++) {
                        closedArea.set(k, closedArea.get(k + 1));
                    }
                    i--;
                }
            }
        }
        return closedArea.subList(0, n);
    }

    private static boolean isOverlap(List<Line> areaLeft, List<Line> areaRight) {
        if (areaLeft.size() != areaRight.size()) {
            return false;
        }
        int equalLineSize = 0;
        for (int i = 0; i < areaLeft.size(); i++) {
            Line lineLeft = areaLeft.get(i);
            for (int j = 0; j < areaRight.size(); j++) {
                if (lineLeft.isEqual(areaRight.get(j))||lineLeft.isEqual(areaRight.get(j).reverse())) {
                    equalLineSize++;
                }
            }
        }
        return equalLineSize == areaRight.size();
    }

    private static List<List<Line>> removePointInArea(List<List<Line>> removeBigClosedArea, List<Point> allCrossPoint) {
        List<List<Line>> resultArea = new ArrayList<>();
        if (removeBigClosedArea.size() < 2) {
            return removeBigClosedArea;
        }
        for (int i = 0; i < removeBigClosedArea.size(); i++) {//遍历所有闭合区域
            List<Line> childArea = removeBigClosedArea.get(i);//获取闭合区域A
            List<Point> crossPoints = getAllCrossPoint(childArea);
            //删除全部交点集合B中闭合区域A的顶点
            List<Point> afterMove = removePoints(crossPoints, allCrossPoint);
            boolean pointInArea = false;
            for (int j = 0; j < afterMove.size(); j++) {//遍历区域内所有的点
                //移除区域内有其他交点的图形
                if (isPointInPolygon(afterMove.get(j), crossPoints)) {//遍历交点是否在闭合区域A内
                    pointInArea = true;
                    break;
                }
            }
            if (!pointInArea) {
                resultArea.add(childArea);
            }
        }
        return resultArea;
    }

    //判断点是否在闭合区域内
    private static boolean isPointInPolygon(Point point, List<Point> points) {
        if (points.size() < 3) {
            return false;
        }
        Point extreme = new Point(Integer.MAX_VALUE, point.getY());
        int count = 0;
        int i = 0;
        do {
            int next = (i + 1) % points.size();
            if (doIntersect(points.get(i), points.get(next), point, extreme)) {
                if (orientation(points.get(i), point, points.get(next)) == 0) {
                    return onSegment(points.get(i), point, points.get(next));
                }
                count++;
            }
            i = next;
        } while ((i != 0));
        return count % 2 == 1;//return true if count is odd,false otherwise
    }

    private static boolean onSegment(Point p, Point q, Point r) {
        if (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX())
                && q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY())) {
            return true;
        }
        return false;
    }

    private static int orientation(Point p, Point q, Point r) {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX()) - (q.getX() - p.getX()) * (r.getY() - q.getY());
        if (val == 0) {
            return 0;//colinear
        }
        return val > 0 ? 1 : 2;//clock or counterclock wise
    }

    private static boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);
        if (o1 != o2 && o3 != o4) {
            return true;
        }
        //p1,q1 and q2 are colinear and p2 lies on segment p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) {
            return true;
        }
        //p1,q1 and q2 are colinear and q2 lies on segment p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) {
            return true;
        }
        //p2,q2 and p1 are colinear and p1 lies on segment p2q2
        if (o3 == 0 && onSegment(p2, p2, q2)) {
            return true;
        }
        //p2,q2 and q1 are colinear and q1 lies on segment p2q2
        if (o4 == 0 && onSegment(p2, q2, q2)) {
            return true;
        }
        return false;
    }

    private static List<Point> removePoints(List<Point> crossPoints, List<Point> allCrossPoint) {
        Iterator<Point> iterator = allCrossPoint.iterator();
        while (iterator.hasNext()) {
            Point next = iterator.next();
            crossPoints.forEach(p -> {
                if (p.isEqualTo(next)) {
                    iterator.remove();
                }
            });
        }
        return allCrossPoint;
    }

    private static List<List<Line>> removeBigClosedArea(List<List<Line>> closedArea, List<Point> allCrossPoint) {
        List<List<Line>> result = new ArrayList<>();
        if (closedArea.size() < 2) {
            return closedArea;
        }
        for (List<Line> childArea : closedArea) {
            List<Point> crossPoints = getAllCrossPoint(childArea);
            if (crossPoints.size() != allCrossPoint.size()) {
                result.add(childArea);
            }
        }
        return result;
    }

    public static List<List<Line>> getCloseAreas(List<Line> directedLine, List<AssociatedLine> associatedLine) {
        List<List<Line>> allCloseAreas = new ArrayList<>();
        //提取任意一条有向线段作为提取封闭图形的第一条直线
        for (int i = 0; i < directedLine.size(); i++) {
            List<Line> closeArea = new ArrayList<>();
            closeArea.add(directedLine.get(i));
            Line nextLine = getNextLine(directedLine.get(i), associatedLine);
            if (Objects.isNull(nextLine)) {
                continue;
            }
            closeArea.add(nextLine);
            while (nextLine != null && !nextLine.getEnd().isEqualTo(directedLine.get(i).getStart())) {
                nextLine = getNextLine(nextLine, associatedLine);
                if (!Objects.isNull(nextLine)) {
                    closeArea.add(nextLine);
                }
            }
            allCloseAreas.add(closeArea);
        }
        return allCloseAreas;
    }

    private static Line getNextLine(Line currentLine, List<AssociatedLine> associatedLine) {
        for (int i = 0; i < associatedLine.size(); i++) {
            AssociatedLine associated = associatedLine.get(i);
            if (currentLine.getEnd().isEqualTo(associated.getPoint())) {//找到与当前线段终点相等的交点的关联对象
                for (int j = 0; j < associated.getLines().size(); j++) {//遍历交点point关联的线段
                    List<Line> aLines = associated.getLines();
                    if (aLines.get(j).isReverse(currentLine)) {
                        //从象限角排序数组中找到方向线段的下一个位置的线段
                        if (j == (aLines.size() - 1)) {
                            return aLines.get(0);
                        } else {
                            return aLines.get(j + 1);
                        }
                    }
                }
            }
        }
        return null;
    }

    public static List<AssociatedLine> getAssociatedLine(List<Line> directedLine, List<Point> allCrossPoint) {
        List<AssociatedLine> list = new ArrayList<>();
        //找到每一个交点的关联线段
        for (int i = 0; i < allCrossPoint.size(); i++) {
            AssociatedLine associatedLine = new AssociatedLine();
            associatedLine.setPoint(allCrossPoint.get(i));
            associatedLine.setLines(getAssociated(directedLine, allCrossPoint.get(i)));
            list.add(associatedLine);
        }
        return list;
    }

    public static List<Line> getAssociated(List<Line> directedLine, Point point) {
        List<Line> associatedLine = new ArrayList<>();
        //找到与交点crossPoint[i]关联的所有线段
        for (int j = 0; j < directedLine.size(); j++) {
            if (directedLine.get(j).getStart().isEqualTo(point)) {
                associatedLine.add(directedLine.get(j));
            }
        }
        //对关联线段根据象限角大小进行排序
        associatedLine = sortedLine(associatedLine);
        return associatedLine;
    }

    //对线段根据象限角大小进行排序（以水平方向为射线为基线）
    public static List<Line> sortedLine(List<Line> lines) {
        Vector baseVector = new Vector(1, 0);
        for (int i = 0; i < lines.size(); i++) {//插入排序
            int minIndex = i;
            for (int j = i + 1; j < lines.size(); j++) {
                Vector linei = lines.get(minIndex).toVector();
                Vector linej = lines.get(j).toVector();
                if (linej.getQuadrantAngle() < linei.getQuadrantAngle()) {
                    minIndex = j;
                }
            }
            Line tmp = lines.get(minIndex);
            lines.set(minIndex, lines.get(i));
            lines.set(i, tmp);
        }
        return lines;
    }

    public static List<Line> getDirectedLine(List<Line> noneIsolatedLines) {
        List<Line> allDirectedLine = new ArrayList<>();
        for (Line line : noneIsolatedLines) {
            allDirectedLine.add(line);
            allDirectedLine.add(line.reverse());
        }
        return allDirectedLine;
    }

    public static List<Point> getAllCrossPoint(List<Line> lines) {
        Set<Point> crossPoints = new HashSet<>();
        for (Line line : lines) {
            crossPoints.add(line.getStart());
            crossPoints.add(line.getEnd());
        }
        return new ArrayList<>(crossPoints);
    }

    public static void swap(List<Point> crossPointList, int n, int t) {
        Point tmp = crossPointList.get(n);
        crossPointList.set(n, crossPointList.get(t));
        crossPointList.set(t, tmp);
    }


}
