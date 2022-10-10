package com.longll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.longll.util.CaculateUtil;

/**
 * @author dragon
 * @create 2022-09-21 16:14
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point {

    private double x;
    private double y;

    public boolean isEqualTo(Point point) {
        if (CaculateUtil.beEqualTo(this.x, point.getX()) && CaculateUtil.beEqualTo(this.y, point.getY())) {
            return true;
        } else {
            return false;
        }
    }

    public double distance(Point p){
        double X = this.x-p.getX();
        double Y = this.y-p.getY();
        return Math.sqrt(X*X+Y*Y);
    }

}
