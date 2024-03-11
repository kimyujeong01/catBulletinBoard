package com.bitc.project_kyj.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
    public static String uploadFile(HttpServletRequest req, String saveDir) throws ServletException, IOException {
        Part part = req.getPart("uploadfiles");
        String partHeader = part.getHeader("content-disposition");
        System.out.println("partHeader = " + partHeader);

        String[] phArr = partHeader.split("filename=");
        String originalFileName = phArr[1].trim().replace("\"", "");

        if (!originalFileName.isEmpty()) {
            part.write(saveDir + File.separator + originalFileName);
        }

        return originalFileName;
    }


    public static String renameFile(String originalFileName, String saveDir) {
        String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
        String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
        String newFileName = now + ext;
        File oldFile = new File(saveDir + File.separator + originalFileName);
        File newFile = new File(saveDir + File.separator + newFileName);
        oldFile.renameTo(newFile);

        return newFileName;
    }

    public static void download(String ofile, String sfile, String saveDir, HttpServletRequest req, HttpServletResponse res) {

        try {
            File file = new File(saveDir, sfile);
            InputStream inputStream = new FileInputStream(file);

            String client = req.getHeader("User-Agent");
            if (client.indexOf("WOW64") == -1) {
                ofile = new String(ofile.getBytes("UTF-8"), "ISO-8859-1");
            } else {
                ofile = new String(ofile.getBytes("KSC5601"), "ISO-8859-1");
            }

            res.reset();
            res.setContentType("application/octet-stream");
            res.setHeader("Content-Disposition", "attachment; filename=\"" + ofile + "\"");
            res.setHeader("Content-Length", "" + file.length());

            OutputStream outputStream = res.getOutputStream();

            byte[] b = new byte[(int) file.length()];
            int readBuffer = 0;
            while ((readBuffer = inputStream.read(b)) > 0) {
                outputStream.write(b, 0, readBuffer);
            }

            inputStream.close();
            outputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("예외가 발생하였습니다.");
            e.printStackTrace();
        }
    }

    public static void deleteFile(String saveDir, String fileName) {
        File file = new File(saveDir + File.separator + fileName);

        if (file.exists()){
            file.delete();
        }
    }


}
