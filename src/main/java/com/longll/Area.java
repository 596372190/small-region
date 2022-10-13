package com.longll;

import com.longll.Line;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dragon
 * @create 2022-10-12 15:44
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Area {
    private boolean isNested;
    private List<Line> region;
    private List<List<Line>> nestedRegions;
}
