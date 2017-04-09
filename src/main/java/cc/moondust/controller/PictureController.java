package cc.moondust.controller;

import cc.moondust.exception.UnKnowException;
import cc.moondust.service.PictureStorageService;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by jiang on 2017/4/9.
 */
@Controller
@RequestMapping("/api/picture")
public class PictureController {

    @Autowired
    PictureStorageService pictureStorageService;

    @RequestMapping(value = "/getpicture/{pic_id}")
    public void getPictureById(HttpServletResponse response, @PathVariable("pic_id") String picID) throws IOException {
        Long s = new Date().getTime();
        System.out.println("mongo:" + s);
        GridFSDBFile gridFSDBFile = pictureStorageService.findPictureById(picID);
        long e = new Date().getTime();
        System.out.println("mongo:" + e + ":time:" + (e - s));
        long time = 365L * 24 * 60 * 60 * 1000;
        String type = gridFSDBFile.getContentType();
        if (type == null) {
            response.setHeader("Content-type", " application/octet-stream");
            response.setHeader("Content-Disposition", " attachment; filename=" + gridFSDBFile.getFilename());
        } else {
            response.setContentType(type);
        }
        response.setHeader("Accept-Length", String.valueOf(gridFSDBFile.getLength()));
        response.setHeader("Cache-Control", "max-age=" + time);
        response.setDateHeader("Expires", System.currentTimeMillis() + time);
        response.setHeader("Pragma", "max-age=" + time);
        InputStream inputStream = gridFSDBFile.getInputStream();
        ServletOutputStream outputStream = response.getOutputStream();
        s = new Date().getTime();
        System.out.println("res:" + s);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outputStream.close();
        e = new Date().getTime();
        System.out.println("res:" + e + ":time:" + (e - s));

    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Object uploadPicture(MultipartHttpServletRequest request
            , @RequestParam(name = "pic_name", required = true) MultipartFile[] pics
    ) {
        MultipartFile file = pics[0];

        return null;
    }

}
