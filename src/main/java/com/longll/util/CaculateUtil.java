package com.longll.util;


import com.longll.Line;
import com.longll.Point;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author dragon
 * @create 2022-09-21 16:52
 * @description
 */
public class CaculateUtil {

    public static boolean beEqualTo(double a, double b) {
        if (((a - b) < 0.1) && ((a - b) > -0.1)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOnLine(Point p1, Point p2, Point q) {//判断q是否在line(p1,p2)上
        if (q.isEqualTo(p1) || q.isEqualTo(p2)) //点Q是线段的端点
        {
            return true;
        }
        if (CaculateUtil.beEqualTo(q.distance(p1) + q.distance(p2), p1.distance(p2)))//点Q在line的两端点之间
        {
            return true;
        }
        return false;
    }


    /**
     * 判断线段是否相交
     *
     * @param line1
     * @param line2
     * @return
     */
    public static boolean doselinecross(Line line1, Line line2) {
        double x1 = line1.getStart().getX();
        double y1 = line1.getStart().getY();
        double x2 = line1.getEnd().getX();
        double y2 = line1.getEnd().getY();

        double x3 = line2.getStart().getX();
        double y3 = line2.getStart().getY();
        double x4 = line2.getEnd().getX();
        double y4 = line2.getEnd().getY();
        //两直线重合
        if ((line1.getStart().isEqualTo(line2.getStart()) && line1.getEnd().isEqualTo(line2.getEnd()))
                || (line1.getStart().isEqualTo(line2.getEnd()) && line1.getEnd().isEqualTo(line2.getStart()))) {
            return false;
        }
        if (CaculateUtil.beEqualTo((x3 - x4) * (y1 - y2) - (x1 - x2) * (y3 - y4), 0.f) || CaculateUtil.beEqualTo((y1 - y2) * (x3 - x4) - (x1 - x2) * (y3 - y4), 0)) {//斜率相等
            if (line1.getStart().isEqualTo(line2.getStart()) || line1.getEnd().isEqualTo(line2.getEnd())
                    || line1.getStart().isEqualTo(line2.getEnd()) || line1.getEnd().isEqualTo(line2.getStart())) {
                return true;
            } else {
                return false;
            }
        }

        double X = ((x1 - x2) * (x3 * y4 - x4 * y3) - (x3 - x4) * (x1 * y2 - x2 * y1)) / ((x3 - x4) * (y1 - y2) - (x1 - x2) * (y3 - y4));
        double Y = ((y1 - y2) * (x3 * y4 - x4 * y3) - (x1 * y2 - x2 * y1) * (y3 - y4)) / ((y1 - y2) * (x3 - x4) - (x1 - x2) * (y3 - y4));
        Point point = new Point(X, Y);
        //判断交点是否同时在L1，L2上
        if (CaculateUtil.isOnLine(line1.getStart(), line1.getEnd(), point) && CaculateUtil.isOnLine(line2.getStart(), line2.getEnd(), point)) {
            return true;
        } else {//交点在延长线上
            return false;
        }
    }


}
