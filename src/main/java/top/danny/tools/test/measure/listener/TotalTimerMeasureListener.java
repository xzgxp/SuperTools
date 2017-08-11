package top.danny.tools.test.measure.listener;


import top.danny.tools.string.StringUtil;
import top.danny.tools.test.measure.MeasureState;

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
public class TotalTimerMeasureListener extends AbstractMeasureListener{
    private AtomicInteger countAtomicLong = new AtomicInteger();
    private AtomicLong sumAtomicLong = new AtomicLong();
    private AtomicLong maxAtomicLong = new AtomicLong();
    private AtomicLong mixAtomicLong = new AtomicLong(Long.MAX_VALUE);
    private long totalCount = 0;

    public TotalTimerMeasureListener() {
    }

    public TotalTimerMeasureListener(long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public void calMeasure(MeasureState measureState) {
        int count = countAtomicLong.incrementAndGet();
        long consumerTime = measureState.getConsumeTime();
        long sumTimes = sumAtomicLong.addAndGet(consumerTime);

        if(consumerTime > maxAtomicLong.get()) {
            maxAtomicLong.set(consumerTime);
        }
        if(consumerTime < mixAtomicLong.get()) {
            mixAtomicLong.set(consumerTime);
        }

        if(totalCount == countAtomicLong.get()) {
            String message = StringUtil.getMessageFormat("sumTimes {}, max {}, mix {}, avg {}, count {}",
                    getTotalTimeMs(sumTimes), getTotalTimeMs(maxAtomicLong.get()), getTotalTimeMs(mixAtomicLong.get()),
                    getAvgTimeMs(sumTimes, count), count);
            System.out.println(message) ;
        }
    }
}
