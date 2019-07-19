package com.thinkgem.jeesite.modules.tb.oversee.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.purchase.entity.Purchase;
import com.thinkgem.jeesite.modules.tb.oversee.entity.Oversee;
import com.thinkgem.jeesite.modules.tb.oversee.service.OverseeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping(value = "${adminPath}/tb/oversee")
public class OverseeController extends BaseController {

    @Autowired(required = false)
    private OverseeService overseeService;

    @RequestMapping("seeList")
    public String seeList(Oversee oversee,
                          HttpServletRequest request,
                          HttpServletResponse response, Model model) {
        Page<Oversee> page = overseeService.findOverseePage(new Page<Oversee>(request, response), oversee);
        model.addAttribute("page", page);
        model.addAttribute("oversee", oversee);//引用传递参数
        return "modules/tb/oversee/overseeList";  //跳转
    }

    /**
     * 上传文件功能
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file){
        String fileName = file.getOriginalFilename();
        String address = "";
        if(StringUtils.isNoneBlank(fileName)){
            String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
            address = IdGen.uuid()+suffix;
            FileOutputStream fos = null;
            try {
                byte[] fileData = file.getBytes();
                fos = new FileOutputStream(Global.getConfig("purchase")+address);
                fos.write(fileData);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return address;
    }

    /**
     * 文件下载
     * @param purchase
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("download")
    public String download(Purchase purchase, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String downloadPrefix = Global.getConfig("purchase");
        String url = downloadPrefix+purchase.getApplyAddress();
        String[] str = purchase.getApplyAddress().split("\\.");
        purchase.setApplyAddress(purchase.getpName()+"."+str[1]);
        File file = new File(url);
        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition",
                "attachment;filename=" + new String(purchase.getApplyAddress().getBytes("gbk"), "iso-8859-1")); // 转码之后下载的文件不会出现中文乱码
        response.addHeader("Content-Length", "" + file.length());
        try {
            // 以流的形式下载文件
            InputStream is = new BufferedInputStream(new FileInputStream(url));
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            os.write(buffer);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
