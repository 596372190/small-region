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
        //List<Line> lines = getLines0();//两个不封闭图形
        //List<Line> lines = getLines1();//两个长方形相交
        //List<Line> lines = getLines2();//两个独立的长方形
        //List<Line> lines = getLines3();//两个连接的三角形
        //List<Line> lines = getLines4();//两个连接的三角形加一个矩形
        //List<Line> lines = getLines5();//大矩形包含两个小矩形
        //List<Line> lines = getLines6();//两个长方形连在一起
        //List<Line> lines = getLines7();//左右俩个三角形相连
        List<Line> lines = getLines8();//三个三角形相连
        List<Area> lists = calculateMiniumClosedArea(lines);
        System.out.println(lists.size());
        for (Area area : lists) {
            System.out.println("[");
            System.out.println(area.isNested());
            for (Line line : area.getRegion()) {
                System.out.println(line.getStart() + "" + line.getEnd());
            }
            System.out.println("nestedSize:" + (area.getNestedRegions() == null ? 0 : area.getNestedRegions().size()));
            if (!Objects.isNull(area.getNestedRegions())) {
                for (List<Line> nestedRegion : area.getNestedRegions()) {
                    System.out.println("  {");
                    for (Line line : nestedRegion) {
                        System.out.println(line.getStart() + "" + line.getEnd());
                    }
                    System.out.println("  }");
                }
            }
            System.out.println("]");
        }

    }

    private static List<Line> getLines8() {
        Line line1 = new Line(new Point(0, 0), new Point(3, 1.5));
        Line line2 = new Line(new Point(3, 1.5), new Point(0, 3));
        Line line3 = new Line(new Point(0, 3), new Point(0, 0));
        Line line4 = new Line(new Point(0, 0), new Point(7, 1.5));
        Line line5 = new Line(new Point(7, 1.5), new Point(0, 3));
        Line line6 = new Line(new Point(5, 1.5), new Point(0, 3));
        Line line7 = new Line(new Point(0, 0), new Point(5, 1.5));
        List<Line> lines = new ArrayList<>();
        Collections.addAll(lines, line1, line2, line3, line4, line5, line6, line7);
        return lines;
    }

    private static List<Line> getLines7() {
        Line line1 = new Line(new Point(0, 0), new Point(4, 1.5));
        Line line2 = new Line(new Point(4, 1.5), new Point(0, 4));
        Line line3 = new Line(new Point(0, 0), new Point(-4, 1.5));
        Line line4 = new Line(new Point(-4, 1.5), new Point(0, 4));
        Line line5 = new Line(new Point(0, 0), new Point(0, 4));
        List<Line> lines = new ArrayList<>();
        Collections.addAll(lines, line1, line2, line3, line4, line5);
        return lines;
    }

    public static List<Line> getLines6() {
        Line line1 = new Line(new Point(0, 0), new Point(4, 0));
        Line line2 = new Line(new Point(4, 0), new Point(4, 4));
        Line line3 = new Line(new Point(4, 4), new Point(0, 4));
        Line line4 = new Line(new Point(0, 4), new Point(0, 0));
        Line line5 = new Line(new Point(4, 0), new Point(6, 0));
        Line line6 = new Line(new Point(6, 0), new Point(6, 4));
        Line line7 = new Line(new Point(6, 4), new Point(4, 4));
        Line line8 = new Line(new Point(4, 4), new Point(4, 0));
        List<Line> lines = new ArrayList<>();
        Collections.addAll(lines, line1, line2, line3, line4, line5, line6, line7, line8);
        return lines;
    }

    public static List<Line> getLines0() {
        Line line1 = new Line(new Point(0, 0), new Point(4, 0));
        Line line2 = new Line(new Point(4, 0), new Point(4, 4));
        Line line3 = new Line(new Point(4, 4), new Point(0, 4));
        Line line5 = new Line(new Point(-10, -10), new Point(-10, 10));
        Line line6 = new Line(new Point(-10, 10), new Point(10, 10));
        Line line7 = new Line(new Point(10, 10), new Point(10, -10));
        List<Line> lines = new ArrayList<>();
        Collections.addAll(lines, line1, line2, line3, line5, line6, line7);
        return lines;
    }

    public static List<Line> getLines5() {
        Line line1 = new Line(new Point(0, 0), new Point(20, 0));
        Line line2 = new Line(new Point(20, 0), new Point(20, 20));
        Line line3 = new Line(new Point(20, 20), new Point(0, 20));
        Line line4 = new Line(new Point(0, 20), new Point(0, 0));
        Line line5 = new Line(new Point(1, 1), new Point(2, 1));
        Line line6 = new Line(new Point(2, 1), new Point(2, 2));
        Line line7 = new Line(new Point(2, 2), new Point(1, 2));
        Line line8 = new Line(new Point(1, 2), new Point(1, 1));
        Line line9 = new Line(new Point(4, 4), new Point(6, 4));
        Line line10 = new Line(new Point(6, 4), new Point(6, 6));
        Line line11 = new Line(new Point(6, 6), new Point(4, 6));
        Line line12 = new Line(new Point(4, 6), new Point(4, 4));
        List<Line> lines = new ArrayList<>();
        Collections.addAll(lines, line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, line11, line12);
        return lines;
    }

    public static List<Line> getLines4() {
        List<Line> lines = new ArrayList<>();
        lines.addAll(getLines3());
        Line line1 = new Line(new Point(10, 0), new Point(15, 0));
        Line line2 = new Line(new Point(15, 0), new Point(15, 3));
        Line line3 = new Line(new Point(15, 3), new Point(10, 3));
        Line line4 = new Line(new Point(10, 3), new Point(10, 0));
        Collections.addAll(lines, line1, line2, line3, line4);
        return lines;
    }

    public static List<Line> getLines3() {
        Line line1 = new Line(new Point(0, 0), new Point(3, 1.1));
        Line line2 = new Line(new Point(3, 1.1), new Point(0, 3));
        Line line3 = new Line(new Point(0, 3), new Point(0, 0));
        Line line4 = new Line(new Point(0, 0), new Point(7, 1.5));
        Line line5 = new Line(new Point(7, 1.5), new Point(0, 3));
        List<Line> lines = new ArrayList<>();
        Collections.addAll(lines, line1, line2, line3, line4, line5);
        return lines;
    }

    public static List<Line> getLines2() {
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

    public static List<Line> getLines1() {
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


    public static List<Area> calculateMiniumClosedArea(List<Line> allLines) {
        //移除孤立的线段（两端的点都是孤立的）
        List<Line> nonIsolatedLine = remove_isolated_line(allLines);
        //拆分所有的线段（将所有的相交线段拆分开，得到新的线段集合是）
        List<Line> breakLine = breakLines(nonIsolatedLine);
        //移除一个端点孤立的线段
        List<Line> noneIsolatedLines = removeNoneFieldLines(breakLine);
        //+移除相同的线段
        List<Line> removeSamedLine = removeSamedLine(noneIsolatedLines);
        //获取所有交点
        List<Point> allCrossPoint = getAllCrossPoint(removeSamedLine);
        //获取所有有向线段
        List<Line> directedLine = getDirectedLine(removeSamedLine);
        //设置交点与线段关联
        List<AssociatedLine> associatedLine = getAssociatedLine(directedLine, allCrossPoint);
        //获取闭合区域
        List<List<Line>> closedArea = getCloseAreas(directedLine, associatedLine);
        //移除包含所有交点的闭合区域
        //List<List<Line>> removeBigClosedArea = removeBigClosedArea(closedArea, allCrossPoint);
        //去除闭合区域内还含有其他交点的区域
        //List<List<Line>> removePointInArea = removePointInArea(closedArea, allCrossPoint);
        //去除重叠区域
        List<List<Line>> removeOverlapArea = removeOverlapArea(closedArea);
        //去除内部仍然包含有图形的区域
        //List<List<Line>> removeContainArea = removeContainArea(removeOverlapArea);
        //+去除面积为0的区域
        List<List<Line>> removeZeroArea = removeZeroArea(removeOverlapArea);
        //+ 去除区域内有和其他区域超过一个交点的区域
        //List<List<Line>> removeInnerIntersectArea = removeInnerIntersectArea(removeZeroArea);
        //+ 变成Area类
        List<Area> areas = transformToArea(removeZeroArea);
        return areas;
    }

    private static List<Area> transformToArea(List<List<Line>> removeZeroArea) {
        Map<List<Line>, List<List<Line>>> map = new HashMap<>();//找出区域内对应的封闭区域
        for (int i = 0; i < removeZeroArea.size(); i++) {
            List<Line> areaI = removeZeroArea.get(i);
            List<List<Line>> list = new ArrayList<>();
            Border borderI = getBorderOfArea(areaI);
            for (int j = 0; j < removeZeroArea.size(); j++) {
                if (i == j) {
                    continue;
                }
                List<Line> areaJ = removeZeroArea.get(j);
                Border borderJ = getBorderOfArea(areaJ);
                if (list.size() > 0) {
                    for (int k = 0; k < list.size(); k++) {
                        List<Line> areaK = list.get(k);
                        Border borderK = getBorderOfArea(areaK);
                        if (borderJ.isWithin(borderI) && borderJ.isOutSide(borderK)) {
                            list.add(areaJ);
                            break;
                        }
                    }
                } else {
                    if (borderJ.isWithin(borderI)) {
                        list.add(areaJ);
                    }
                }
            }
            map.put(areaI, list);
        }
        List<Area> areas = new ArrayList<>();
        for (List<Line> lines : map.keySet()) {
            Area area = new Area();
            area.setRegion(lines);
            List<List<Line>> lists = map.get(lines);
            if (lists.size() > 0) {
                area.setNested(true);
                area.setNestedRegions(lists);
            }
            areas.add(area);
        }
        return areas;
    }


    public static List<List<Line>> removeInnerIntersectArea(List<List<Line>> removeZeroArea) {
        List<List<Line>> result = new ArrayList<>();
        //找出大区域的上下左右边界
        for (int i = 0; i < removeZeroArea.size(); i++) {
            List<Line> lines = removeZeroArea.get(i);
            Border border = getBorderOfArea(lines);
            //如果该区域存在在其内部的小区域，同时该小区域的交点与大区域的交点有一个重合的，该大区域去掉
            boolean isSave = true;
            for (int j = 0; j < removeZeroArea.size(); j++) {
                if (i == j) {
                    continue;
                }
                List<Line> subLines = removeZeroArea.get(j);
                Border subBorder = getBorderOfArea(subLines);
                if (border.contains(subBorder)) {
                    List<Point> crossPoints = getAllCrossPoint(lines);
                    List<Point> subCrossPoints = getAllCrossPoint(subLines);
                    if (hasPointInPolygon(subCrossPoints, crossPoints)) {
                        isSave = false;
                        break;
                    }
                }
            }
            if (isSave) {
                result.add(lines);
            }
        }
        return result;
    }

    /**
     * 点A集合内有点在封闭区域内
     *
     * @param pointsA
     * @param pointsB
     * @return
     */
    public static boolean hasPointInPolygon(List<Point> pointsA, List<Point> pointsB) {
        for (Point point : pointsA) {
            if (isPointInPolygon(point, pointsB)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取该封闭区域的上下左右边界
     *
     * @param lines
     * @return
     */
    public static Border getBorderOfArea(List<Line> lines) {
        double xMin = Double.MAX_VALUE;
        double xMax = Double.MIN_VALUE;
        double yMin = Double.MAX_VALUE;
        double yMax = Double.MIN_VALUE;
        for (Line line : lines) {
            xMin = Math.min(Math.min(line.getStart().getX(), line.getEnd().getX()), xMin);
            xMax = Math.max(Math.max(line.getStart().getX(), line.getEnd().getX()), xMax);
            yMin = Math.min(Math.min(line.getStart().getY(), line.getEnd().getY()), yMin);
            yMax = Math.max(Math.max(line.getStart().getY(), line.getEnd().getY()), yMax);
        }
        return new Border(xMin, xMax, yMin, yMax);
    }

    public static List<Line> removeSamedLine(List<Line> nonIsolatedLine) {
        List<Line> lines = new ArrayList<>();
        for (Line line : nonIsolatedLine) {
            if (lines.contains(line) || lines.contains(line.reverse())) {
                continue;
            }
            lines.add(line);
        }
        return lines;
    }

    public static List<List<Line>> removeZeroArea(List<List<Line>> removeOverlapArea) {
        List<List<Line>> result = new ArrayList<>();
        for (List<Line> lines : removeOverlapArea) {
            if (!(lines.size() == 2 && (lines.get(0).isEqual(lines.get(1)) || lines.get(0).isEqual(lines.get(1).reverse())))) {
                result.add(lines);
            }
        }
        return result;
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
    public static List<List<Line>> removeContainArea(List<List<Line>> areas) {
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
    public static boolean isContainPoints(List<Point> pointsA, List<Point> pointsB) {
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

    public static List<List<Line>> removeOverlapArea(List<List<Line>> closedArea) {
        int n = closedArea.size();
        if (n < 2) {
            return closedArea;
        }
        List<List<Line>> result = new ArrayList<>();
        for (List<Line> lines : closedArea) {
            if (result.size() == 0) {
                result.add(lines);
            } else {
                boolean isOverlap = false;
                for (List<Line> list : result) {
                    if (isOverlap(lines, list)) {
                        isOverlap = true;
                        break;
                    }
                }
                if (!isOverlap) {
                    result.add(lines);
                }
            }
        }
        /*for (int i = 0; i < n; i++) {
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
        return closedArea.subList(0, n);*/
        return result;
    }

    public static boolean isOverlap(List<Line> areaLeft, List<Line> areaRight) {
        if (areaLeft.size() != areaRight.size()) {
            return false;
        }
        int equalLineSize = 0;
        for (int i = 0; i < areaLeft.size(); i++) {
            Line lineLeft = areaLeft.get(i);
            for (int j = 0; j < areaRight.size(); j++) {
                if (lineLeft.isEqual(areaRight.get(j)) || lineLeft.isEqual(areaRight.get(j).reverse())) {
                    equalLineSize++;
                }
            }
        }
        return equalLineSize == areaRight.size();
    }

    public static List<List<Line>> removePointInArea(List<List<Line>> closedArea, List<Point> allCrossPoint) {
        List<List<Line>> resultArea = new ArrayList<>();
        if (closedArea.size() < 2) {
            return closedArea;
        }
        for (int i = 0; i < closedArea.size(); i++) {//遍历所有闭合区域
            List<Line> childArea = closedArea.get(i);//获取闭合区域A
            List<Point> crossPoints = getAllCrossPoint(childArea);
            //删除全部交点集合B中闭合区域A的顶点
            List<Point> afterMove = removePoints(crossPoints, allCrossPoint);
            boolean pointInArea = false;
            for (int j = 0; j < afterMove.size(); j++) {//遍历区域内所有的点
                //移除区域内有其他交点的图形
                if (isPointInPolygon1(afterMove.get(j), crossPoints)) {//遍历交点是否在闭合区域A内
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
    public static boolean isPointInPolygon1(Point point, List<Point> points) {
        if (points.size() == 0) {
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

    public static boolean isPointInPolygon2(Point point, List<Point> points) {
        int cnt = 0;
        int num = points.size();
        Point p1;
        Point p2;
        for (int i = 0; i < num; i++) {
            p1 = points.get(i);
            p2 = points.get((i + 1) % num);

            if (p1.getY() == p2.getY()) {
                continue;
            }

            if (point.getY() < Math.min(p1.getY(), p2.getY()) || point.getY() > Math.max(p1.getY(), p2.getY())) {
                continue;
            }

            double x = (point.getY() - p1.getY()) * (double) (p2.getX() - p1.getX()) / (double) (p2.getY() - p1.getY()) + p1.getX();
            if (x > point.getX()) {
                cnt++;
            }
        }
        return !(cnt % 2 == 0);
    }

    public static boolean isPointInPolygon3(Point checkPoint, List<Point> polygonPoints) {
        boolean inside = false;
        int pointCount = polygonPoints.size();
        Point p1, p2;
        for (int i = 0, j = pointCount - 1; i < pointCount; j = i, i++) {
            p1 = polygonPoints.get(i);
            p2 = polygonPoints.get(j);
            if (checkPoint.getY() < p2.getY()) {
                if (p1.getY() <= checkPoint.getY()) {
                    if ((checkPoint.getY() - p1.getY()) * (p2.getX() - p1.getX()) > (checkPoint.getX() - p1.getX()) * (p2.getY() - p1.getY())) {
                        inside = (!inside);
                    }
                }
            } else if (checkPoint.getY() < p1.getY()) {
                if ((checkPoint.getY() - p1.getY()) * (p2.getX() - p1.getX()) < (checkPoint.getX() - p1.getX()) * (p2.getY() - p1.getY())) {
                    inside = (!inside);
                }
            }
        }
        return inside;
    }

    public static boolean isPointInPolygon(Point pt, List<Point> plist) {
        int nCross = 0;    // 定义变量，统计目标点向右画射线与多边形相交次数

        for (int i = 0; i < plist.size(); i++) {   //遍历多边形每一个节点

            Point p1;
            Point p2;

            p1 = plist.get(i);
            p2 = plist.get((i + 1) % plist.size());  // p1是这个节点，p2是下一个节点，两点连线是多边形的一条边
// 以下算法是用是先以y轴坐标来判断的

            if (p1.getY() == p2.getY()) {
                continue;   //如果这条边是水平的，跳过
            }
            if (pt.getY() < Math.min(p1.getY(), p2.getY())) { //如果目标点低于这个线段，跳过
                continue;
            }
            if (pt.getY() >= Math.max(p1.getY(), p2.getY())) {//如果目标点高于这个线段，跳过
                continue;
            }
//那么下面的情况就是：如果过p1画水平线，过p2画水平线，目标点在这两条线中间
            double x = (double) (pt.getY() - p1.getY()) * (double) (p2.getX() - p1.getX()) / (double) (p2.getY() - p1.getY()) + p1.getX();
// 这段的几何意义是 过目标点，画一条水平线，x是这条线与多边形当前边的交点x坐标
            if (x > pt.getX()) {
                nCross++; //如果交点在右边，统计加一。这等于从目标点向右发一条射线（ray），与多边形各边的相交（crossing）次数
            }
        }

        if (nCross % 2 == 1) {

            return true; //如果是奇数，说明在多边形里
        } else {

            return false; //否则在多边形外 或 边上
        }
    }


    public static boolean onSegment(Point p, Point q, Point r) {
        if (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX())
                && q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY())) {
            return true;
        }
        return false;
    }

    public static int orientation(Point p, Point q, Point r) {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX()) - (q.getX() - p.getX()) * (r.getY() - q.getY());
        if (val == 0) {
            return 0;//colinear
        }
        return val > 0 ? 1 : 2;//clock or counterclock wise
    }

    public static boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
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
        if (o3 == 0 && onSegment(p2, p1, q2)) {
            return true;
        }
        //p2,q2 and q1 are colinear and q1 lies on segment p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) {
            return true;
        }
        return false;
    }

    public static List<Point> removePoints(List<Point> crossPoints, List<Point> allCrossPoint) {
        List<Point> list = new ArrayList<>(allCrossPoint);
        Iterator<Point> iterator = list.iterator();
        while (iterator.hasNext()) {
            Point next = iterator.next();
            crossPoints.forEach(p -> {
                if (p.isEqualTo(next)) {
                    iterator.remove();
                }
            });
        }
        return list;
    }

    //todo 移除包含所有顶点的区域（逻辑还需完善）
    public static List<List<Line>> removeBigClosedArea(List<List<Line>> closedArea, List<Point> allCrossPoint) {
        List<List<Line>> result = new ArrayList<>();
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

    public static Line getNextLine(Line currentLine, List<AssociatedLine> associatedLine) {
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
