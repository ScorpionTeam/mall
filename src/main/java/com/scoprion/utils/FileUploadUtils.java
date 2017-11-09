package com.scoprion.utils;

import com.scoprion.constant.Constant;
import com.scoprion.mall.domain.ImageCutSize;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author ycj
 * @version V1.0 <文件上传接口>
 * @date 2017-11-07 14:50
 */
public class FileUploadUtils {

    /**
     * 网站图片尺寸
     * 商品列表小 ：30x30
     * 商品列表大 ：220x220
     * 商品详情大：400x400
     * 商品详情小：60x60
     * 商品详情小：40x40
     */

    /**
     * @param file        文件
     * @param imageType   图片类型
     * @param cut         是否裁剪
     * @param cutSizeList 裁剪尺寸
     * @param waterRemark 是否水印
     * @return
     * @throws IOException
     */
    public static List<String> uploadImage(MultipartFile file,
                                           String imageType,
                                           String cut,
                                           List<ImageCutSize> cutSizeList,
                                           String waterRemark) throws IOException {
        String path = parseFilePathByType(imageType);
        existDir(path);
        Calendar calendar = Calendar.getInstance();
        String fileName = String.valueOf(calendar.getTime().getTime());
        String name = file.getOriginalFilename();
        String endName = name.substring(name.lastIndexOf("."));
        File image = new File(path + fileName + endName);
        file.transferTo(image);
        List<String> urlList = new ArrayList<>();
        urlList.add(getFileName(path, fileName, endName, null));
        //裁剪
        if (Constant.CUT_TRUE.equals(cut)) {
            for (ImageCutSize imageCutSize : cutSizeList) {
                String absolutePath = getAbsolutePath(path, fileName, endName, imageCutSize);
                cutImage(image, imageCutSize, absolutePath);
                if (Constant.WATER_REMARK_TRUE.equals(waterRemark)) {
                    waterRemark(imageCutSize, absolutePath);
                }
                //存储图片名
                urlList.add(getFileName(path, fileName, endName, imageCutSize));
            }
        } else {
            if (Constant.WATER_REMARK_TRUE.equals(waterRemark)) {
                String absolutePath = getAbsolutePath(path, fileName, endName, null);
                waterRemark(null, absolutePath);
                urlList.add(getFileName(path, fileName, endName, null));
            }
        }
        return urlList;
    }


    /**
     * 删除图片
     *
     * @param imgName 图片名称 /Mall/BrandImage/1510215075689.jpg
     */
    public static void deleteImage(String imgName) {
        //图片根目录
        String basePath = Constant.BASE_IMG_DIR;
        String filePath = basePath + imgName;
        if (isChildImage(imgName)) {
            //删除子图
            deleteFile(filePath);
        } else {
            //删除主图
            deleteFile(filePath);
            //循环删除子图
            for (String size : Constant.SIZE_ARR) {
                String endName = imgName.substring(imgName.lastIndexOf("."));
                String startName = imgName.substring(0, imgName.lastIndexOf("."));
                filePath = basePath + startName + "_" + size + endName;
                deleteFile(filePath);
            }
        }
    }

    /**
     * @param imageName 是否是子图
     * @return boolean
     */
    private static boolean isChildImage(String imageName) {
        for (String size : Constant.SIZE_ARR) {
            if (imageName.contains(size)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除文件
     *
     * @param filePath 绝对路径
     */
    private static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            boolean delResult = file.delete();
            System.out.print("delResult: " + delResult);
        }
    }

    /**
     * 图片裁剪
     *
     * @param image        源文件
     * @param imageCutSize 尺寸
     * @param absolutePath 目标路径
     * @throws IOException
     */
    private static void cutImage(File image, ImageCutSize imageCutSize, String absolutePath) throws IOException {
        Thumbnails.of(image)
                .size(imageCutSize.getWidth(), imageCutSize.getHeight())
                .toFile(absolutePath);
    }

    /**
     * 添加水印
     *
     * @param imageCutSize
     * @param absolutePath
     * @throws IOException
     */
    private static void waterRemark(ImageCutSize imageCutSize, String absolutePath) throws IOException {
        if (imageCutSize == null) {
            //大图加水印
            Thumbnails.of(absolutePath)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(Constant.WATER_REMARK_IMAGE_PATH)), 1f)
                    .toFile(absolutePath);
        } else if (imageCutSize.getWidth() >= Constant.WATER_REMARK_SIZE) {
            //加水印
            Thumbnails.of(absolutePath)
                    .size(imageCutSize.getWidth(), imageCutSize.getHeight())
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(Constant.WATER_REMARK_IMAGE_PATH)), 1f)
                    .toFile(absolutePath);
        }

    }

    /**
     * 获取图片名称
     *
     * @param path
     * @param fileName     示例：122334441121212
     * @param endName      后缀名 示例： .png
     * @param imageCutSize
     * @return
     */
    private static String getFileName(String path, String fileName, String endName, ImageCutSize imageCutSize) {
        path = path.replace(Constant.BASE_IMG_DIR, "");
        if (imageCutSize == null) {
            return path + fileName + endName;
        }
        return path + fileName + "_" + imageCutSize.getWidth() + "x" + imageCutSize.getHeight() + endName;
    }

    /**
     * 获取绝对路径
     *
     * @param path         示例： D:Downloads/
     * @param fileName     示例：122334441121212
     * @param endName      后缀名 示例： .png
     * @param imageCutSize
     * @return
     */
    private static String getAbsolutePath(String path, String fileName, String endName, ImageCutSize imageCutSize) {
        if (imageCutSize == null) {
            return path + fileName + endName;
        }
        return path + fileName + "_" + imageCutSize.getWidth() + "x" + imageCutSize.getHeight() + endName;
    }

    /**
     * 根据类型获取图片地址
     * 商品图片路径 0,
     * 品牌图片路径1,
     * 文章图片路径2,
     * 商品评价图片路径3,
     * 广告图片路径4,
     * 活动图片路径5,
     * 其他模块图片路径6
     *
     * @param imageType
     * @return
     */
    public static String parseFilePathByType(String imageType) {
        String path;
        switch (imageType) {
            case "0":
                // 商品图片路径 0
                path = Constant.GOODS_IMG_PATH;
                break;
            case "1":
                //品牌图片路径1
                path = Constant.BRAND_IMG_PATH;
                break;
            case "2":
                //文章图片路径2
                path = Constant.ARTICLE_IMG_PATH;
                break;
            case "3":
                //商品评价图片路径3
                path = Constant.GOODS_REVIEWS_IMG_PATH;
                break;
            case "4":
                //广告图片路径4
                path = Constant.BANNER_IMG_PATH;
                break;
            case "5":
                //活动图片路径5
                path = Constant.ACIVITY_IMG_PATH;
                break;
            case "6":
                //其他模块图片路径6
                path = Constant.OTHER_IMG_PATH;
                break;
            default:
                //其他模块图片路径6
                path = Constant.OTHER_IMG_PATH;
                break;
        }
        return path;
    }

    /**
     * 检查文件目录是否存在,不存在就创建
     *
     * @param path
     */
    public static void existDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                System.out.print("创建文件夹出错-------------------");
            }
        }
    }

    /**
     * 判断文件是否为图片
     *
     * @param imgFile
     * @return
     */
    public static boolean isImage(File imgFile) {
        Image img = null;
        try {
            img = ImageIO.read(imgFile);
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            img = null;
        }
    }
}
