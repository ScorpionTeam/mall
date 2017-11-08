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
import java.util.Calendar;
import java.util.List;

/**
 * @author ycj
 * @version V1.0 <文件上传接口>
 * @date 2017-11-07 14:50
 */
public class FileUploadUtils {

    public static String upload(MultipartFile file, String imageType, String cut, List<ImageCutSize> cutSizeList,
                                String waterRemark, String waterRemarkText) throws IOException {
        String path = parseFilePathByType(imageType);
        existDir(path);
        Calendar calendar = Calendar.getInstance();
        String fileName = String.valueOf(calendar.getTime().getTime());
        String name = file.getOriginalFilename();
        String endName = "." + name.substring(name.lastIndexOf(".") + 1);
        File image = new File(path + fileName + endName);
        file.transferTo(image);
        if (Constant.CUT_TRUE.equals(cut)) {
            //裁剪
            for (ImageCutSize imageCutSize : cutSizeList) {
                String toFileName = getToFileName(path, fileName, endName, imageCutSize);
                Thumbnails.of(image)
                        .size(imageCutSize.getWidth(), imageCutSize.getHeight())
                        .toFile(toFileName);
                if (Constant.WATER_REMARK_TRUE.equals(waterRemark)) {
                    //加水印
                    Thumbnails.of(toFileName)
                            .size(imageCutSize.getWidth(), imageCutSize.getHeight())
                            .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("D:\\Downloads\\log.png")), 1f)
                            .toFile(toFileName);
                }
            }
        } else {
            if (Constant.WATER_REMARK_TRUE.equals(waterRemark)) {
                String toFileName = getToFileName(path, fileName, endName, null);
                //加水印
                Thumbnails.of(toFileName)
                        .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("D:\\Downloads\\log.png")), 1f)
                        .toFile(toFileName);
            }
        }
        return fileName + endName;
    }

    private static String getToFileName(String path, String fileName, String endName, ImageCutSize imageCutSize) {
        if (imageCutSize == null) {
            return path + fileName + endName;
        }
        return path + fileName + "_" + imageCutSize.getWidth() + "x" + imageCutSize.getHeight() + endName;
    }

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
