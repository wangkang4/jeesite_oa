package com.thinkgem.jeesite.modules.newTrends.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.newTrends.entity.NewTrends;
import com.thinkgem.jeesite.modules.newTrends.entity.NewTrendsText;
import com.thinkgem.jeesite.modules.newTrends.service.NewTrendsService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/newTrends")
public class NewTrendsController extends BaseController {

    @Autowired
    private NewTrendsService newTrendsService;

    /**
     * 通过id获取对象信息
     *
     * @param id 实例id
     * @return newTrends对象
     */
    @ModelAttribute
    public NewTrends get(String id) {
        NewTrends newTrends = null;
        if (StringUtils.isNotBlank ( id )) {
            newTrends = newTrendsService.get ( id );
        }
        if (newTrends == null) {
            newTrends = new NewTrends ();
        }
        return newTrends;
    }

    @RequestMapping(value = "list")
    public String list(NewTrends newTrends, Model model, HttpServletRequest request, HttpServletResponse response) {
        Page<NewTrends> page = newTrendsService.findPages ( new Page<NewTrends> ( request, response ), newTrends );
        model.addAttribute ( "page", page );
        return "modules/newTrends/list";
    }

    @RequestMapping(value = "add")
    public String add(NewTrends newTrends, Model model) {
        newTrends.setUser ( UserUtils.getUser () );
        newTrends.setOffice ( UserUtils.getUser ().getOffice () );
        newTrends.setDate ( new Date () );
        List<String> textStr = newTrendsService.findText ( newTrends.getTextId () );
        String text = "";
        for (String te : textStr) {
            text += te;
        }
        newTrends.setText ( text );
        model.addAttribute ( "newTrends", newTrends );
        return "modules/newTrends/add";
    }

    @RequestMapping(value = "toAdd")
    public String toAdd(NewTrends newTrends, HttpServletRequest request) throws ParseException {
        newTrends.setUser ( UserUtils.getUser () );
        newTrends.setOffice ( UserUtils.getUser ().getOffice () );
        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );
        newTrends.setDate ( sdf.parse ( sdf.format ( new Date () ) ) );
        String text = HtmlUtils.htmlUnescape ( newTrends.getText () );
//		String text = newTrends.getText();
        String id = "";
        if (StringUtils.isNotBlank ( newTrends.getTextId () )) {
            id = newTrends.getTextId ();
            newTrendsService.deleteNewText ( id );
        } else {
            id = IdGen.uuid ();
        }
        for (int i = 0; i * 50000 < text.length (); i++) {
            NewTrendsText newText = new NewTrendsText ();
            newText.setId ( id );
            newText.setNumber ( i );
            if ((i + 1) * 50000 > text.length ()) {
                newText.setText ( text.substring ( i * 50000 ) );
            } else {
                newText.setText ( text.substring ( i * 50000, (i + 1) * 50000 ) );
            }
            newTrendsService.saveNewText ( newText );
        }
//		newTrends.setText(HtmlUtils.htmlUnescape(newTrends.getText()));
        newTrends.setTextId ( id );
        newTrendsService.save ( newTrends );
        return "redirect:" + adminPath + "/newTrends/list";
    }

    @RequestMapping(value = "view")
    public String view(NewTrends newTrends, Model model) {
        List<String> textStr = newTrendsService.findText ( newTrends.getTextId () );
        String text = "";
        for (String te : textStr) {
            text += te;
        }
        newTrends.setText ( text );
        model.addAttribute ( "newTrends", newTrends );
        return "modules/newTrends/view";
    }

    @RequestMapping(value = "list2")
    public String list2(NewTrends newTrends, Model model, HttpServletRequest request, HttpServletResponse response) {
        newTrends.setUser ( UserUtils.getUser () );
        Page<NewTrends> page = newTrendsService.findPage2 ( new Page<NewTrends> ( request, response ), newTrends );
        model.addAttribute ( "page", page );
        return "modules/newTrends/list2";
    }

    @RequestMapping(value = "del")
    public String delete(NewTrends newTrends) {
        newTrends.setUser ( UserUtils.getUser () );
        newTrendsService.delete ( newTrends );
        return "redirect:" + adminPath + "/newTrends/list2";
    }

    @ResponseBody
    @RequestMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename ();
        String address = "";
        if (StringUtils.isNoneBlank ( fileName )) {
            String suffix = fileName.substring ( fileName.lastIndexOf ( "." ) );//后缀名
            address = IdGen.uuid () + suffix;
            FileOutputStream fos = null;
            try {
                byte[] fileData = file.getBytes ();
                fos = new FileOutputStream ( Global.getConfig ( "newTrendsUploadPath" ) + address );
                fos.write ( fileData );
                fos.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
        return address;
    }

    @RequestMapping("download")
    public String download(NewTrends newTrends, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String downloadPrefix = Global.getConfig ( "newTrendsUploadPath" );
        String url = downloadPrefix + newTrends.getAddress ();
        String[] str = newTrends.getAddress ().split ( "\\." );
        newTrends.setAddress ( newTrends.getTitle () + "." + str[1] );
        File file = new File ( url );
        // 清空response
        response.reset ();
        // 设置response的Header
        response.addHeader ( "Content-Disposition",
                "attachment;filename=" + new String ( newTrends.getAddress ().getBytes ( "gbk" ), "iso-8859-1" ) ); // 转码之后下载的文件不会出现中文乱码
        response.addHeader ( "Content-Length", "" + file.length () );

        try {
            // 以流的形式下载文件
            InputStream is = new BufferedInputStream ( new FileInputStream ( url ) );
            byte[] buffer = new byte[is.available ()];
            is.read ( buffer );
            is.close ();

            OutputStream os = new BufferedOutputStream ( response.getOutputStream () );
            os.write ( buffer );
            os.flush ();
            os.close ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return null;
    }
}
