package top.danny.tools.test;

import top.danny.tools.collection.ListUtil;
import top.danny.tools.test.measure.Measure;
import top.danny.tools.test.measure.MeasureProxy;
import top.danny.tools.test.measure.MeasureState;
import top.danny.tools.test.measure.listener.Listeners;
import top.danny.tools.test.measure.listener.MeasureListener;
import top.danny.tools.test.measure.listener.TimerMeasureListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author huyuyang@lxfintech.com
 * @Title: StringUtil
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-07-02 11:01:57
 */
public class QuickMeasuer implements Measure {
	private int cycleNum = 10;
	private int threadNum = 1;
	private CountDownLatch measureLatch;
	private List<MeasureListener> listeners = new ArrayList<MeasureListener>();
	
	public static QuickMeasuer create() {
		return new QuickMeasuer();
	}
	
	public QuickMeasuer cycleNum(int cycleNum) {
		this.cycleNum = cycleNum;
		return this;
	}
	
	public QuickMeasuer threadNum(int threadNum) {
		this.threadNum = threadNum;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.ucf.test.performance.Measure#measure(java.lang.Runnable)
	 */
	@Override
	public void doMeasure(Runnable runnable) {
		doMeasure("", runnable);
	}

	/* (non-Javadoc)
	 * @see com.ucf.test.performance.Measure#measure(java.lang.String, java.lang.Runnable)
	 */
	@Override
	public void doMeasure(String remark, Runnable runnable) {
		this.measureLatch = new CountDownLatch(this.cycleNum);
		this.handleMeasure(remark, runnable);
	}
	
	private void handleMeasure(String remark, Runnable runnable) {
		 Executor executor = Executors.newFixedThreadPool(this.threadNum);
		 for(int i=0; i<this.cycleNum; i++) {
			 executor.execute(new MeasureProxy(
					 new MeasureState(this.cycleNum, this.threadNum, i, remark),
					 runnable, this.getListeners(), measureLatch));
		 }
		 try {
			this.measureLatch.await();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private synchronized List<MeasureListener> getListeners() {
		if(ListUtil.isEmpty(listeners)) {
			this.listeners = Arrays.asList(Listeners.getDefault());
		}
		return this.listeners;
	}
	
	public QuickMeasuer addListener(MeasureListener listener) {
		if(this.listeners.isEmpty()) {
			this.listeners.add(new TimerMeasureListener());
		}
	    this.listeners.add(listener);
		return this;
	}
}
