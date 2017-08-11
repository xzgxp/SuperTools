package top.danny.tools.file.image.similarity.no2;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huyuyang@lxfintech.com
 * @Title: PicturesCompare
 * @Copyright: Copyright (c) 2016
 * @Description: http://blog.csdn.net/luohong722/article/details/7100058
 * @Company: lxjr.com
 * @Created on 2017-06-19 16:37:24
 */

public class SimilarImageSearch {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<String> hashCodes = new ArrayList<String>();
        System.out.println(System.getProperty("user.dir"));
        String filename = ImageHelper.path + "/src/main/resources/images/";
        String hashCode = null;

        for (int i = 0; i < 5; i++) {
            hashCode = produceFingerPrint(filename + "example" + (i + 1) + ".jpg");
            hashCodes.add(hashCode);
        }
        System.out.println("Resources: ");
        System.out.println(hashCodes);
        System.out.println();

        String sourceHashCode = produceFingerPrint(filename + "source.jpg");
        System.out.println("Source: ");
        System.out.println(sourceHashCode);
        System.out.println();

        List<Integer> differences = new ArrayList<Integer>();
        for (int i = 0; i < hashCodes.size(); i++) {
            int difference = hammingDistance(sourceHashCode, hashCodes.get(i));
            differences.add(difference);
        }

        System.out.println(differences);

		/*String hashCode = "8f373714acfcf4d0";
        int difference = hammingDistance(sourceHashCode, hashCode);

		System.out.println("Hanming Distance between src: " + sourceHashCode + " and dest: " + hashCode + " is: " + difference);
		if (difference <= 5){
			System.out.println("Match");
		} else if (difference <= 10) {
			System.out.println("Like");
		} else {
			System.out.println("Not Match");
		}*/
    }

    /**
     * 计算"汉明距离"（Hamming distance）。
     * 如果不相同的数据位不超过5，就说明两张图片很相似；如果大于10，就说明这是两张不同的图片。
     *
     * @param sourceHashCode 源hashCode
     * @param hashCode       与之比较的hashCode
     */
    public static int hammingDistance(String sourceHashCode, String hashCode) {
        int difference = 0;
        int len = sourceHashCode.length();

        for (int i = 0; i < len; i++) {
            if (sourceHashCode.charAt(i) != hashCode.charAt(i)) {
                difference++;
            }
        }

        return difference;
    }

    /**
     * 生成图片指纹
     *
     * @param filename 文件名
     * @return 图片指纹
     */
    public static String produceFingerPrint(String filename) {
        BufferedImage source = ImageHelper.readPNGImage(filename);// 读取文件

        int width = 8;
        int height = 8;

        // 第一步，缩小尺寸。
        // 将图片缩小到8x8的尺寸，总共64个像素。这一步的作用是去除图片的细节，只保留结构、明暗等基本信息，摒弃不同尺寸、比例带来的图片差异。
        BufferedImage thumb = ImageHelper.thumb(source, width, height, false);

        // 第二步，简化色彩。
        // 将缩小后的图片，转为64级灰度。也就是说，所有像素点总共只有64种颜色。
        int[] pixels = new int[width * height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[i * height + j] = ImageHelper.rgbToGray(thumb.getRGB(i, j));
            }
        }

        // 第三步，计算平均值。
        // 计算所有64个像素的灰度平均值。
        int avgPixel = ImageHelper.average(pixels);

        // 第四步，比较像素的灰度。
        // 将每个像素的灰度，与平均值进行比较。大于或等于平均值，记为1；小于平均值，记为0。
        int[] comps = new int[width * height];
        for (int i = 0; i < comps.length; i++) {
            if (pixels[i] >= avgPixel) {
                comps[i] = 1;
            } else {
                comps[i] = 0;
            }
        }

        // 第五步，计算哈希值。
        // 将上一步的比较结果，组合在一起，就构成了一个64位的整数，这就是这张图片的指纹。组合的次序并不重要，只要保证所有图片都采用同样次序就行了。
        StringBuffer hashCode = new StringBuffer();
        for (int i = 0; i < comps.length; i += 4) {
            int result = comps[i] * (int) Math.pow(2, 3) + comps[i + 1] * (int) Math.pow(2, 2) + comps[i + 2] * (int) Math.pow(2, 1) + comps[i + 2];
            hashCode.append(binaryToHex(result));
        }

        // 得到指纹以后，就可以对比不同的图片，看看64位中有多少位是不一样的。
        return hashCode.toString();
    }

    /**
     * 二进制转为十六进制
     *
     * @param binary
     * @return char hex
     */
    private static char binaryToHex(int binary) {
        char ch = ' ';
        switch (binary) {
            case 0:
                ch = '0';
                break;
            case 1:
                ch = '1';
                break;
            case 2:
                ch = '2';
                break;
            case 3:
                ch = '3';
                break;
            case 4:
                ch = '4';
                break;
            case 5:
                ch = '5';
                break;
            case 6:
                ch = '6';
                break;
            case 7:
                ch = '7';
                break;
            case 8:
                ch = '8';
                break;
            case 9:
                ch = '9';
                break;
            case 10:
                ch = 'a';
                break;
            case 11:
                ch = 'b';
                break;
            case 12:
                ch = 'c';
                break;
            case 13:
                ch = 'd';
                break;
            case 14:
                ch = 'e';
                break;
            case 15:
                ch = 'f';
                break;
            default:
                ch = ' ';
        }
        return ch;
    }

}
