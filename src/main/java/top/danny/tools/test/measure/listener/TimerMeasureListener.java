package top.danny.tools.test.measure.listener;



import top.danny.tools.test.measure.MeasureState;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


/**
 * @author huyuyang@lxfintech.com
 * @Title: StringUtil
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-07-02 11:01:57
 */
public class TimerMeasureListener extends AbstractMeasureListener {
	private static final DecimalFormat integerFormat = new DecimalFormat("#,##0.00");
	private static final String APPEND_FLAG = "";//\t
	private List<MeasureState> timesList = new ArrayList<MeasureState>();
	/** 统计处理次数 **/
	private AtomicInteger count = new AtomicInteger();
	/** 第一个线程处理开始时间 **/
	private AtomicLong startTime = new AtomicLong();
	private double MILLI_SECOND_UNIT = 1000000.0D;
	private double SECOND_UNIT = 1000000000.0D;
	

	/* (non-Javadoc)
	 * @see com.ucf.test.performance.listener.MeasureListener#calMeasure(com.ucf.test.performance.MeasureState)
	 */
	@Override
	public void calMeasure(MeasureState measureState) {
		this.count.getAndIncrement();
		//System.out.println("count-->" + this.count);
		this.startTime.compareAndSet(0L, System.nanoTime());
		this.calTimer(measureState);
	}
	
	private synchronized void calTimer(MeasureState measureState) {
		if(this.isEnd(measureState)) {
			 long totalTime = this.getTotalTimeNanoTime();
			 this.count.set(0);
		     this.startTime.set(0L);
		     this.printMeasureInfo(totalTime, measureState);
		}
	}
	
	private void printMeasureInfo(long totalTime, MeasureState measureState) {
		StringBuffer result = new StringBuffer();
		result.append(measureState.getRemark()).append(" result-->").append(APPEND_FLAG);
		result.append("total:").append(this.formatSecondUint(this.getTotalTimeMs(totalTime))).append(APPEND_FLAG);
		result.append("avg:").append(this.formatSecondUint(this.getAvgTimeMs(totalTime, measureState.getCycleNum()))).append(APPEND_FLAG);
		result.append("tps:").append(this.format(this.getTps(totalTime, measureState.getCycleNum()))).append(" ").append(APPEND_FLAG);
		result.append("running ").append(measureState.getCycleNum()).append(" times ").append(APPEND_FLAG);
		result.append("in ").append(measureState.getThreadNum()).append(" threads.");
		this.print(result.toString());
	}
	
	private void print(String message) {
		System.out.println(message);
	}
	

 	
	private long getTotalTimeNanoTime() {
		return System.nanoTime() - this.startTime.get();
	}
	
	private String formatSecondUint(double value) {
		return this.format(value) + " ms,";
	}
	
	private String format(double value) {
	    return integerFormat.format(value);
	}
	
	private boolean isEnd(MeasureState measureState) {
		if(this.count.get() == measureState.getCycleNum()) {
			return true;
		}
		return false;
	}

}
