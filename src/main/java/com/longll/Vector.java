package com.longll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dragon
 * @create 2022-09-30 16:35
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vector {
    private double x;
    private double y;

    public double getQuadrantAngle() {
        if (this.x == 0) {
            return this.y == 0 ? 0 : y < 0 ? 270 : 90;
        }
        if (this.y == 0) {
            return this.x == 0 ? 0 : x < 0 ? 180 : 0;
        }
        return Math.atan(y / x);
    }
}
