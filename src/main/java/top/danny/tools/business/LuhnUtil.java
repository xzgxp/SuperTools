package top.danny.tools.business;

/**
 * @author huyuyang@lxfintech.com
 * @Title: LuhnUtil
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-08-13 16:41:11
 */
public class LuhnUtil {

    public static void main(String[] args) {
        System.out.println(matchLuhn("6226095950521497"));
    }

    /**
     * 匹配Luhn算法：可用于检测银行卡卡号
     * @param cardNo
     * @return
     */
    public static boolean matchLuhn(String cardNo) {
        int[] cardNoArr = new int[cardNo.length()];
        for (int i=0; i<cardNo.length(); i++) {
            cardNoArr[i] = Integer.valueOf(String.valueOf(cardNo.charAt(i)));
        }
        for(int i=cardNoArr.length-2;i>=0;i-=2) {
            cardNoArr[i] <<= 1;
            cardNoArr[i] = cardNoArr[i]/10 + cardNoArr[i]%10;
        }
        int sum = 0;
        for(int i=0;i<cardNoArr.length;i++) {
            sum += cardNoArr[i];
        }
        return sum % 10 == 0;
    }

}
