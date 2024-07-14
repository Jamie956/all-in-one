package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
public class UploadTest {
    /**
     * upload file:
     * 1.multipart file transfer file to target location
     *
     * headers: Content-Type: multipart/form-data;
     * Body: form-data file=[select file]
     * method: post
     * url: http://localhost:8080/upload
     */
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile multipartFile, HttpSession session) throws IOException {
        String fileName = multipartFile.getOriginalFilename();

        ServletContext servletContext = session.getServletContext();
        String path = servletContext.getRealPath("up");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        String finalPath = path + File.separator + fileName;
        multipartFile.transferTo(new File(finalPath));
        return "index";
    }
}
