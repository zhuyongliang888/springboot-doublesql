package com.hoperun.oracle.demo.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hoperun.mysql2.demo.model.HpTest;
import com.hoperun.oracle.demo.common.PortalUtils;
import com.hoperun.oracle.demo.facade.UserService;

@RestController
public class TestMysql2Controller
{
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/mysql2/query", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public String getTestListInfo(HttpServletRequest request, HttpServletResponse response)
    {
	PortalUtils.setResponseAttribute(response);
	List<HpTest> dbValues = userService.getTestListInfo();
	String result = JSON.toJSONString(dbValues, SerializerFeature.WriteMapNullValue);
	response.setStatus(200);
	return result;
    }

    @RequestMapping(value = "/mysql2/insert", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String addTest(@RequestBody HpTest hptest, HttpServletRequest request, HttpServletResponse response)
    {
	PortalUtils.setResponseAttribute(response);
	List<HpTest> dbValues = userService.addTest(hptest);
	String result = JSON.toJSONString(dbValues, SerializerFeature.WriteMapNullValue);
	response.setStatus(200);
	return result;
    }

    // 单文件上传
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file)
    {
	try
	{
	    if (file.isEmpty())
	    {
		return "文件为空";
	    }

	    String fileName = file.getOriginalFilename();

	    String suffixName = fileName.substring(fileName.lastIndexOf("."));

	    // 设置文件存储路径
	    String filePath = "D://aim//";
	    String path = filePath + fileName + suffixName;

	    File dest = new File(path);
	    // 检测是否存在目录
	    if (!dest.getParentFile().exists())
	    {
		dest.getParentFile().mkdirs();// 新建文件夹
	    }
	    file.transferTo(dest);// 文件写入
	    return "上传成功";
	} catch (IllegalStateException e)
	{
	    e.printStackTrace();
	} catch (IOException e)
	{
	    e.printStackTrace();
	}
	return "上传失败";
    }

    // 多文件上传
    @RequestMapping(value = "/uploadMore", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request)
    {
	List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
	MultipartFile file = null;
	BufferedOutputStream stream = null;
	for (int i = 0; i < files.size(); ++i)
	{
	    file = files.get(i);
	    String filePath = "D://aim//";
	    if (!file.isEmpty())
	    {
		try
		{
		    byte[] bytes = file.getBytes();
		    stream = new BufferedOutputStream(
			    new FileOutputStream(new File(filePath + file.getOriginalFilename())));// 设置文件路径及名字
		    stream.write(bytes);// 写入
		    stream.close();
		} catch (Exception e)
		{
		    stream = null;
		    return "第 " + i + " 个文件上传失败  ==> " + e.getMessage();
		}
	    } else
	    {
		return "第 " + i + " 个文件上传失败因为文件为空";
	    }
	}
	return "上传成功";
    }
    
  //文件下载相关代码
    @RequestMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "aim_test.txt";// 设置文件名，根据业务需要替换成要下载的文件名
        if (fileName != null) {
            //设置文件路径
            String realPath = "D://aim//";
            File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

}
