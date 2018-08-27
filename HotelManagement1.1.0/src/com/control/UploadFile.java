package com.control;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.vo.DataClass;

@SuppressWarnings("serial")
@WebServlet("/UploadFile")
public class UploadFile extends HttpServlet {

    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ���ñ���
        response.setCharacterEncoding("utf-8");
        // ����·��
        String savePath = this.getServletConfig().getServletContext().getRealPath("");
        savePath = savePath + "img\\";
        File f1 = new File(savePath);
        if (!f1.exists()) {
            f1.mkdirs();
        }
        
        DiskFileItemFactory fac = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fac);
        upload.setHeaderEncoding("utf-8");
        List<FileItem> fileList = null;
        try {
            fileList = upload.parseRequest(request);
        } catch (FileUploadException ex) {
            return;
        }

        Iterator<FileItem> it = fileList.iterator();
        String name = "";
        String extName = "";
        while (it.hasNext()) {
            FileItem item = it.next();
            if (!item.isFormField()) {
                name = item.getName();
                long size = item.getSize();
                String type = item.getContentType();
                System.out.println(size + " " + type);
                if (name == null || name.trim().equals("")) {
                    continue;
                }

                // ��չ����ʽ��
                if (name.lastIndexOf(".") >= 0) {
                    extName = name.substring(name.lastIndexOf("."));
                }

                File file = null;
                do {
                    // �����ļ�����
                    name = UUID.randomUUID().toString();
                    file = new File(savePath + name + extName);
                } while (file.exists());

                File saveFile = new File(savePath + name + extName);
                try {
                    item.write(saveFile);
                } catch (Exception exp) {
                    response.getWriter().write(exp.getMessage());
                    exp.printStackTrace();
                }
            }
        }

        DataClass r = new DataClass();
        r.setCode(0);
        r.setMsg("�ϴ��ɹ�");
        Map<String, String> data = new HashMap<String, String>();
        data.put("src", "img/" + name + extName);
        data.put("name",name + extName);
        r.setData(data);

        response.getWriter().print(r.toJson());
    }

}