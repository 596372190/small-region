package com.longll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dragon
 * @create 2022-10-11 13:52
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Border {
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;

    public boolean contains(Border subBorder) {
        if (xMin <= subBorder.getXMin() && xMax >= subBorder.getXMax() &&
                yMin <= subBorder.getYMin() && yMax >= subBorder.getYMax()) {
            return true;
        }
        return false;
    }

    /**
     * 区域是否嵌套在borderI内
     *
     * @param borderI
     * @return
     */
    public boolean isWithin(Border borderI) {
        if (xMin > borderI.getXMin() && xMax < borderI.getXMax() &&
                yMin > borderI.getYMin() && yMax < borderI.getYMax()) {
            return true;
        }
        return false;
    }

    /**
     * 边界在borderI 外
     *
     * @param borderI
     * @return
     */
    public boolean isOutSide(Border borderI) {
        if (xMin >= borderI.xMax ||
                yMin >= borderI.yMax ||
                xMax <= borderI.xMin ||
                yMax <= borderI.yMin
        ) {
            return true;
        }
        return false;
    }
}
