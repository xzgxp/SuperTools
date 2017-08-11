package top.danny.tools.test.measure.listener;


/**
 * @author huyuyang@lxfintech.com
 * @Title: StringUtil
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-07-02 11:01:09
 */
public abstract class AbstractMeasureListener implements MeasureListener{
    private double MILLI_SECOND_UNIT = 1000000.0D;
    private double SECOND_UNIT = 1000000000.0D;

    protected double getTps(long totalTime, int cycelNum) {
        return cycelNum/(totalTime/SECOND_UNIT);
    }

    protected double getTotalTimeMs(long totalTime) {
        return totalTime / MILLI_SECOND_UNIT;
    }

    protected double getAvgTimeMs(long totalTime, int cycleNum) {
        return (totalTime / cycleNum / MILLI_SECOND_UNIT);
    }
}
