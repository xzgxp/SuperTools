package top.danny.tools.test;

import org.junit.Test;
import top.danny.tools.test.thread.MyThread;
import top.danny.tools.test.thread.StaticData;

/**
 * @author huyuyang@lxfintech.com
 * @Title: QuickMeasuerTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-08-11 11:26:49
 */
public class QuickMeasuerTest {

    @Test
    public void a(){
        StaticData staticData=new StaticData(0);
        MyThread myThread=new MyThread(staticData);
        QuickMeasuer.create().cycleNum(100).threadNum(100).doMeasure(myThread);
        System.out.println(staticData.getCount());
    }

}
