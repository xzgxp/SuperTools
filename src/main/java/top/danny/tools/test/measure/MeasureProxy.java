package top.danny.tools.test.measure;



import top.danny.tools.test.measure.listener.MeasureListener;

import java.util.List;
import java.util.concurrent.CountDownLatch;


/**
 * @author huyuyang@lxfintech.com
 * @Title: StringUtil
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-07-02 11:01:57
 */
public class MeasureProxy implements Runnable {
	private MeasureState state;
    private Runnable runnable;
    private List<MeasureListener> listeners;
    private CountDownLatch measureLatch;
    
    public MeasureProxy(MeasureState state, Runnable runnable,
						List<MeasureListener> listeners, CountDownLatch measureLatch) {
    	this.state = state;
    	this.runnable = runnable;
    	this.listeners = listeners;
    	this.measureLatch = measureLatch;
    }

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			this.state.startNow();
			this.runnable.run();
			this.state.endNow();
			//System.out.println("thread name-->" + Thread.currentThread().getName());
			this.notifyMeasurement(state);
			this.measureLatch.countDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void notifyMeasurement(MeasureState state) {
		for (MeasureListener listener : this.listeners) {
			listener.calMeasure(state);
		}
	}
}
