package com.longll;

import com.longll.util.CaculateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dragon
 * @create 2022-09-21 16:14
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Line {
    private Point start;
    private Point mid;
    private Point end;

    public Line(Point start, Point end) {
        double midX = start.getX() + (end.getX() - start.getX()) / 2;
        double midY = start.getY() + (end.getY() - start.getY()) / 2;
        this.start = start;
        this.mid = new Point(midX, midY);
        this.end = end;
    }

    public Point getCrossPoint(Line line2) {
        double x1 = this.getStart().getX();
        double y1 = this.getStart().getY();
        double x2 = this.getEnd().getX();
        double y2 = this.getEnd().getY();

        double x3 = line2.getStart().getX();
        double y3 = line2.getStart().getY();
        double x4 = line2.getEnd().getX();
        double y4 = line2.getEnd().getY();

        Point point = new Point(0, 0);

        if (!CaculateUtil.doselinecross(this, line2)) {//判断两条线是否相交
            return point;
        }
        //L1的起点与L2的起点或端点相交
        if (this.getStart().isEqualTo(line2.getStart()) || this.getStart().isEqualTo(line2.getEnd())) {
            return this.getStart();
        }
        //L1的终点与L2的起点或终点相交
        if (this.getEnd().isEqualTo(line2.getStart()) || this.getEnd().isEqualTo(line2.getEnd())) {
            return this.getEnd();
        }
        //计算交点
        double x = ((x1 - x2) * (x3 * y4 - x4 * y3) - (x3 - x4) * (x1 * y2 - x2 * y1)) / ((x3 - x4) * (y1 - y2) - (x1 - x2) * (y3 - y4));
        double y = ((y1 - y2) * (x3 * y4 - x4 * y3) - (x1 * y2 - x2 * y1) * (y3 - y4)) / ((y1 - y2) * (x3 - x4) - (x1 - x2) * (y3 - y4));
        point.setX(x);
        point.setY(y);

        //Q点同时在L1和L2上
        if (CaculateUtil.isOnLine(this.getStart(), this.getEnd(), point) && CaculateUtil.isOnLine(line2.getStart(), line2.getEnd(), point)) {
            return point;
        } else {
            return point;
        }
    }

    public Line reverse() {
        return new Line(this.end, this.mid, this.start);
    }

    public Vector toVector() {
        return new Vector(this.end.getX() - this.start.getX(), this.end.getY() - this.start.getY());
    }

    public boolean isReverse(Line line) {
        return this.getStart().getX() == line.getEnd().getX() && this.getStart().getY() == line.getEnd().getY()
                && this.getEnd().getX() == line.getStart().getX() && this.getEnd().getY() == line.getStart().getY();
    }

    public boolean isEqual(Line line) {
        return this.getStart().getX() == line.getStart().getX() && this.getStart().getY() == line.getStart().getY()
                && this.getEnd().getX() == line.getEnd().getX() && this.getEnd().getY() == line.getEnd().getY();
    }
}
