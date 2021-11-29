package com.slipper.common.utils;

import com.slipper.common.exception.RunException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 文件处理工具类
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Component
public class FileUtils {
    /**
     * 单文件存储
     * @param file
     * @param path
     * @return
     */
    public static Boolean save (MultipartFile file, String path) {
        // 判断是否存在文件夹 不存在则创建
        folderIsExist(path.substring(0, path.lastIndexOf("/")));
        // 创建文件
        File dest = new File(path);
        // 保存文件
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 获取文件流
     * @param response
     * @param filePath
     * @return
     * @throws IOException
     */
    public static HttpServletResponse getFlow (HttpServletResponse response, String filePath) throws IOException {
        // 读取路径下面的文件
        File file = new File(filePath);
        // 读取指定路径下面的文件
        InputStream in = new FileInputStream(file);
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        // 创建存放文件内容的数组
        byte[] buff = new byte[1024];
        //所读取的内容使用n来接收
        int n;
        // 当没有读取完时,继续读取,循环
        while((n=in.read(buff))!=-1){
            // 将字节数组的数据全部写入到输出流中
            outputStream.write(buff,0,n);
        }
        // 强制将缓存区的数据进行输出
        outputStream.flush();
        // 关流
        outputStream.close();
        in.close();

        return getResponse(response, file.getPath());
    }

    /**
     * 文件下载
     * @param response
     * @param path
     * @param name
     * @return
     */
    public static HttpServletResponse download (HttpServletResponse response, String path, String name) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 以流的形式下载文件。
            InputStream fis = null;
            try {
                fis = new BufferedInputStream(new FileInputStream(path));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RunException(Constant.WARNING_CODE, "文件不存在!");
            }
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response = getResponse(response, name, file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    /**
     * 文件、文件夹批量删除
     * @param paths
     */
    public static void delete(String[] paths) {
        for (String path : paths) {
            File file = new File(path);
            delete(file);
        }
    }
    /**
     * 文件、文件夹删除
     * @param file
     */
    public static void delete(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                // 如果是目录,就删除目录下所有的文件和文件夹
                File[] files = file.listFiles();
                // 遍历目录下的文件和文件夹
                for (File f : files) {
                    // 如果是文件,就删除
                    if (f.isFile()) {
                        // 删除文件
                        f.delete();
                    } else if (file.isDirectory()) {
                        // 如果是文件夹,就递归调用删除的方法
                        delete(f);
                    }
                }
            }
            // 如果是文件,就直接删除自己
            file.delete();
        }
    }

    /**
     * 判断文件夹是否存在 不存在则创建
     * @param path
     */
    private static void folderIsExist (String path) {
        String paths[] = path.split("/");
        String dir = paths[0];
        for (int i = 0; i < paths.length - 1; i++) {
            try {
                dir = dir + "/" + paths[i + 1];
                File dirFile = new File(dir);
                if (!dirFile.exists()) {
                    dirFile.mkdir();
                    System.out.println("创建目录为：" + dir);
                }
            } catch (Exception err) {
                System.err.println("文件夹创建发生异常");
            }
        }
    }
    /**
     * 设置文件流响应头
     * @param response
     * @param filePath
     * @return
     * @throws IOException
     */
    private static HttpServletResponse getResponse(HttpServletResponse response, String filePath) throws IOException {
        response.setContentType(Files.probeContentType(Paths.get(filePath)));
        return response;
    }
    /**
     * 设置文件下载响应头
     * @param response
     * @param fileName
     * @param size
     * @return
     * @throws IOException
     */
    private static HttpServletResponse getResponse(HttpServletResponse response, String fileName, Long size) throws IOException {
        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.addHeader("Content-Length", "" + size);
        response.setContentType("application/octet-stream");
        return response;
    }
}
