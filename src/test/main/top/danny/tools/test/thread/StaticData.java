package top.danny.tools.test.thread;

/**
 * @author huyuyang@lxfintech.com
 * @Title: StaticData
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-08-11 11:36:10
 */
public class StaticData {

    public static int count;

    public StaticData(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        StaticData.count = count;
    }

}
