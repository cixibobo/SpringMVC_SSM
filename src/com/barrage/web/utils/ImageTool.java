package com.barrage.web.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片工具
 * @author Po
 *
 */
public class ImageTool {
	
	private static Logger logger = Logger.getLogger(ImageTool.class);
    private static int outputWidth = 1000; // 默认输出图片宽  
    private static int outputHeight = 1000; // 默认输出图片高  
    private static boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)  
	
    public static String dirSplit = "\\";//linux windows

	/**
	 * 是否为允许图片类型
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isImageAllowType(String fileName) {
		String typeName = "";
		// 扩展名格式：
		if (fileName.lastIndexOf(".") >= 0) {
			typeName = fileName.substring(fileName.lastIndexOf("."));
		}
		// 定义允许上传的文件类型
		List<String> fileTypes = new ArrayList<String>();
		fileTypes.add(".jpg");
		fileTypes.add(".jpeg");
		fileTypes.add(".png");
		fileTypes.add(".bmp");
		return fileTypes.contains(typeName.toLowerCase());
	}

    /**
     * 
     * @param image
     * @param realPath 绝对路径（不包含文件名）
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public static String upload(MultipartFile image, String realPath)
    {
        if (image != null && StringUtil.isNotEmpty(realPath))
        {
            String imageFileName = image.getOriginalFilename();
            String typeName = "";
            // 扩展名格式：
            if (imageFileName.lastIndexOf(".") >= 0)
            {
                typeName = imageFileName.substring(imageFileName.lastIndexOf("."));
            }
            
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            Calendar calendar = Calendar.getInstance();
            String newName = df.format(calendar.getTime())+new Date().getTime();// 以当前时间为文件名
            String newFileName = newName + typeName;
            File pathFile = new File(realPath);
            if (!pathFile.exists())
            {
                pathFile.mkdirs();
            }
            String toPath = realPath + newFileName;
            try
            {
            	compressPic(image.getInputStream(), new File(toPath), "local");
                //image.transferTo(new File(toPath));
            }
            catch (IllegalStateException e)
            {
                e.printStackTrace();
                return null;
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return null;
            }
            return newFileName;
        }
        else
        {
            return null;
        }
    }
	/**
	 * 长高等比例2倍缩小图片
	 * @param targetDirectory
	 *            图片所在的文件夹路径
	 * @param imagePath
	 *            图片路径
	 * @param ext
	 *            缩略图扩展名后缀
	 * @param imageName
	 *            图片名
	 * @param w
	 *            目标宽
	 * @param h
	 *            目标高
	 * @param per
	 *            压缩 默认0.5
	 */
	public static void GenerateSmallImage(String orginFile,String targetDirectory, String ext,
			String imageName, int w, int h, float per) {
		BufferedImage src;
		int ratio=1;
		try {
			String imagePath = orginFile;
			File imageFile = new File(imagePath);
			src = javax.imageio.ImageIO.read(imageFile); // 构造Image对象
			String img_midname = targetDirectory+ System.getProperty("file.separator") + imageName.substring(0, imageName.indexOf("."))	+ imageName.substring(imageName.indexOf("."));
			int width = src.getWidth();  
            int height = src.getHeight();  
            // 缩小边长 
            BufferedImage tag = new BufferedImage(width / ratio, height / ratio, BufferedImage.TYPE_INT_RGB);  
            // 绘制 缩小  后的图片 
            tag.getGraphics().drawImage(src, 0, 0, width / ratio, height / ratio, null);  
            FileOutputStream newimage = new FileOutputStream(img_midname); // 输出到文件流
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);  
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
		    //压缩质量 
			jep.setQuality(per, true);
            encoder.encode(tag,jep);  
			// encoder.encode(tag); //近JPEG编码
			newimage.close();
		} catch (IOException ex) {
			logger.error("GenerateSmallImage is fail");
		}
	}
    
    /**
     * 删除图片文件
     * 
     * @param path
     */
    public static boolean deletePicture(String path)
    {
        try
        {
            File file = new File(path);
            if (file.exists())
            {
                file.delete();
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    /**  
     * 图片处理
     * @param inputStream
     * @param file
     * @param request
     */
    public static boolean compressPic(InputStream inputStream,File destFile,String type) {  
   
        try {  
            Image img = ImageIO.read(inputStream);   
            // 判断图片格式是否正确   
            if (img.getWidth(null) == -1) {  
                System.out.println(" can't read,retry!" + "<BR>");   
                return false;   
            } else {   
                Integer newWidth; Integer newHeight;   
                double with =  (double) img.getWidth(null); 
                double height = (double) img.getHeight(null);
                // 判断是否是等比缩放   
                if (proportion == true) {   
                    // 为等比缩放计算输出的图片宽度及高度   
                    double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;   
                    double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;   
                    // 根据缩放比率大的进行缩放控制   
                    double rate = rate1 > rate2 ? rate1 : rate2;   
               	 System.out.println(img.getWidth(null));
               	 System.out.println(img.getHeight(null));
                    newWidth = (int) (((double) img.getWidth(null))/rate);   
                    newHeight = (int) (((double) img.getHeight(null))/rate);   
                } else {   
                    newWidth = (int) ((double) img.getWidth(null)); // 输出的图片宽度   
                    newHeight = (int) ((double) img.getHeight(null)); // 输出的图片高度   
                }  
               
                
                if("local".equals(type))
                {
               	  newWidth = (int) ((double) img.getWidth(null)); // 输出的图片宽度   
                     newHeight = (int) ((double) img.getHeight(null)); // 输出的图片高度   
                }
                else if("middle".equals(type))
                {
               	 newWidth = 640;
               	 double rate = with/newWidth+0.1;
               	 newHeight = (int) (height/rate);   
                }
                else if("litter".equals(type))
                {	
               	 newWidth = 100;
               	 newHeight = 100 ;
               
                }
                
               BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);   
                 
               /* 
                * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 
                * 优先级比速度高 生成的图片质量比较好 但速度慢 
                */   
               tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);  
               FileOutputStream out = new FileOutputStream(destFile);  
               // JPEGImageEncoder可适用于其他图片类型的转换   
               JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);   
               encoder.encode(tag);   
               out.close();   
             
            }   
            return true ;
        }   
        catch (FileNotFoundException e)
        {
            logger.error("create file error", e);
            return false;
        } 
        catch (IOException ex) {   
       	 logger.error("write stream error");
            return false ; 
        }   
   }

}
