package top.danny.tools.file.image.similarity.no1;


import top.danny.tools.file.image.similarity.no2.ImageHelper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

/**
 * @author huyuyang@lxfintech.com
 * @Title: ImageCompare
 * @Copyright: Copyright (c) 2016
 * @Description: http://blog.csdn.net/hephec/article/details/38469939
 * @Company: lxjr.com
 * @Created on 2017-06-19 18:00:16
 */
public class ImageCompare {
    // 全流程
    public static void main(String[] args) throws IOException {
        /*// 获取图像
        File imageFile = new File(ImageHelper.path + "/src/main/resources/images/example2.jpg");
        Image image = ImageIO.read(imageFile);
        // 转换至灰度
        image = toGrayscale(image);
        // 缩小成32x32的缩略图
        image = scale(image);
        // 获取灰度像素数组
        int[] pixels = getPixels(image);
        // 获取平均灰度颜色
        int averageColor = getAverageOfPixelArray(pixels);
        // 获取灰度像素的比较数组（即图像指纹序列）
        pixels = getPixelDeviateWeightsArray(pixels, averageColor);*/

        int[] pixels1 = getHanmingDistance_TEMP(ImageHelper.path + "/src/test/resources/images/example4.jpg");
        int[] pixels2 = getHanmingDistance_TEMP(ImageHelper.path + "/src/test/resources/images/example5.jpg");
        // 获取两个图的汉明距离（假设另一个图也已经按上面步骤得到灰度比较数组）
        int hammingDistance = getHammingDistance(pixels1, pixels2);
        // 通过汉明距离计算相似度，取值范围 [0.0, 1.0]
        double similarity = calSimilarity(hammingDistance);
        System.out.println(similarity);
    }

    /**
     * 获得两张图片的相似度
     * @param filePath1
     * @param filePath2
     * @return
     * @throws IOException
     */
    public static double getSimilarity(String filePath1,String filePath2) throws IOException {
        int[] pixels1 = getHanmingDistance_TEMP(filePath1);
        int[] pixels2 = getHanmingDistance_TEMP(filePath2);
        int hammingDistance = getHammingDistance(pixels1, pixels2);
        double similarity = calSimilarity(hammingDistance);
        return similarity;
    }

    public static int[] getHanmingDistance_TEMP(String filePath) throws IOException {
        // 获取图像
        File imageFile = new File(filePath);
        Image image = ImageIO.read(imageFile);
        // 转换至灰度
        image = toGrayscale(image);
        // 缩小成32x32的缩略图
        image = scale(image);
        // 获取灰度像素数组
        int[] pixels = getPixels(image);
        // 获取平均灰度颜色
        int averageColor = getAverageOfPixelArray(pixels);
        // 获取灰度像素的比较数组（即图像指纹序列）
        pixels = getPixelDeviateWeightsArray(pixels, averageColor);
        return pixels;
    }

    // 将任意Image类型图像转换为BufferedImage类型，方便后续操作
    public static BufferedImage convertToBufferedFrom(Image srcImage) {
        BufferedImage bufferedImage = new BufferedImage(srcImage.getWidth(null),
                srcImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(srcImage, null, null);
        g.dispose();
        return bufferedImage;
    }

    // 转换至灰度图
    public static BufferedImage toGrayscale(Image image) {
        BufferedImage sourceBuffered = convertToBufferedFrom(image);
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        BufferedImage grayBuffered = op.filter(sourceBuffered, null);
        return grayBuffered;
    }

    // 缩放至32x32像素缩略图
    public static Image scale(Image image) {
        image = image.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        return image;
    }

    // 获取像素数组
    public static int[] getPixels(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        int[] pixels = convertToBufferedFrom(image).getRGB(0, 0, width, height,
                null, 0, width);
        return pixels;
    }

    // 获取灰度图的平均像素颜色值
    public static int getAverageOfPixelArray(int[] pixels) {
        Color color;
        long sumRed = 0;
        for (int i = 0; i < pixels.length; i++) {
            color = new Color(pixels[i], true);
            sumRed += color.getRed();
        }
        int averageRed = (int) (sumRed / pixels.length);
        return averageRed;
    }

    // 获取灰度图的像素比较数组（平均值的离差）
    public static int[] getPixelDeviateWeightsArray(int[] pixels, final int averageColor) {
        Color color;
        int[] dest = new int[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            color = new Color(pixels[i], true);
            dest[i] = color.getRed() - averageColor > 0 ? 1 : 0;
        }
        return dest;
    }

    // 获取两个缩略图的平均像素比较数组的汉明距离（距离越大差异越大）
    public static int getHammingDistance(int[] a, int[] b) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] == b[i] ? 0 : 1;
        }
        return sum;
    }

    // 通过汉明距离计算相似度
    public static double calSimilarity(int hammingDistance) {
        int length = 32 * 32;
        double similarity = (length - hammingDistance) / (double) length;

        // 使用指数曲线调整相似度结果
        similarity = Math.pow(similarity, 2);
        return similarity;
    }
}
