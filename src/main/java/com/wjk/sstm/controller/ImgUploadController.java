package com.wjk.sstm.controller;

import com.wjk.sstm.until.Result;
import com.wjk.sstm.until.ResultFactory;
import com.wjk.sstm.until.StringUtils;
import com.wjk.sstm.vo.ImgUrl;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @Author: yuanci
 * @Date: 2018/12/25 13:47
 * @Version 1.0
 */
@RestController
@RequestMapping("/api")
public class ImgUploadController {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ImgUploadController.class);

    @RequestMapping("/upload")
    public Result imgupload(@RequestParam MultipartFile file, HttpServletRequest request ,
                            String uid){
        // uid是vue上传图片返回确认是否上传成功的id
        log.info("\n-------------------Method : 上传图片start--------------------\n");
        //取得upload文件夹路径
//        String path = request.getSession().getServletContext().getRealPath("/upload");
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+ File.separator + "static" + File.separator + "upload";
        File nfile = new File(path);
        if(!nfile.exists() && !nfile.isDirectory()){
            nfile.mkdir();
            log.info("初始化上传图片目录");
        }

        String originalFileName = file.getOriginalFilename();
        log.info("初始文件名称：" + originalFileName);
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
//        logger.info("文件类型为：" + type);

        //生成不重复的名称
        String fileName = StringUtils.UUID() + "." + type ;
        log.info("生成文件名："+fileName);
        File targerFile = new File(path,fileName);

        try {
            file.transferTo(targerFile);
            String url = "http://localhost:8080/upload/" +fileName;
            ImgUrl imgUrl = new ImgUrl(fileName,url,uid);
            log.info("\n-------------------Method : 上传图片over--------------------\n");
            return ResultFactory.buildSuccessResult(imgUrl);
        }catch (Exception e){
            e.printStackTrace();
            log.error("出错："+ e);
            return ResultFactory.buildFailResult("上传失败");
        }
    }
}
