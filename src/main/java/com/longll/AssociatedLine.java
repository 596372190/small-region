package com.longll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dragon
 * @create 2022-09-30 16:24
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociatedLine {

    private Point point;
    private List<Line> lines;
}
