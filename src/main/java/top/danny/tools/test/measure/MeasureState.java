package top.danny.tools.test.measure;

/**
 * @author huyuyang@lxfintech.com
 * @Title: StringUtil
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-07-02 11:01:57
 */
public class MeasureState implements Comparable<MeasureState> {
	private String remark;
	private long startTime;
	private long endTime;
	private int index;
	private int cycleNum;
	private int threadNum;
	
	public MeasureState(int cycleNum, int threadNum, int index, String remark) {
		this.cycleNum = cycleNum;
		this.threadNum = threadNum;
		this.index = index;
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}

	public int getIndex() {
		return index;
	}

	public int getCycleNum() {
		return cycleNum;
	}

	public int getThreadNum() {
		return threadNum;
	}
	
	public long getStartTime() {
		return startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void startNow() {
		this.startTime = System.nanoTime();
	}
	
	public void endNow() {
		this.endTime = System.nanoTime();
	}

	public long getConsumeTime() {
		return endTime - startTime;
	}

	@Override
	public int compareTo(MeasureState o) {
		if (this.startTime > o.startTime)
			return -1;
		if (this.startTime < o.startTime) {
			return 1;
		}

		return 0;
	}

}
