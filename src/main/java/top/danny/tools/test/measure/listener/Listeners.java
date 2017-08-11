package top.danny.tools.test.measure.listener;

/**
 * @author huyuyang@lxfintech.com
 * @Title: StringUtil
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-07-02 11:01:50
 */
public final class Listeners {
	public static MeasureListener[] getDefault() {
		 return new MeasureListener[] {new TimerMeasureListener(), new TotalTimerMeasureListener()};
	}
}
