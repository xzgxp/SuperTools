package top.danny.tools.test.measure;


/**
 * @author huyuyang@lxfintech.com
 * @Title: StringUtil
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-07-02 11:01:57
 */
public interface Measure {
    /**
     * 性能测试
     * @param runnable
     */
    public void doMeasure(Runnable runnable);

    /**
     * 性能测试
     * @param remark
     * @param runnable
     */
    public void doMeasure(String remark, Runnable runnable);
}
