package YzSystem.J_System;

import YzSystem.JMain.*;

import java.io.File;
import java.io.FileReader;
import javax.servlet.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.*;
import java.util.*;
import javax.faces.context.FacesContext;

public class FrmRptManage extends FrmFunctionBase {
    public FrmRptManage() {
    }
    /**
     * genHtmlMain
     * 功能：产生页面主要信息
     * @return String         httpString.
     */
    public String genHtmlMain() throws wlglException {
        String result = "";
        result = genHtmlPos();
        seqn = UtilCommon.NVL(UtilWebTools.getRequestParameter("seqn"));
        if (mode.equals("query")) {
            //取得目前目录的磁盘目录
            String path = "EleWork" + File.separator + "rpt"+File.separator;
            //建立代表目前目录位置的d变量
            File d = new File(path);
            //取得代表目录中所有文件
            File list[] = d.listFiles();

            // 增删改查按钮-----------------------------
            HttpBase base = new HttpBase();
            HttpBase table = HttpBase.genHttpTable("btd", "100%", "1", "5",
                    "0");
            base.getElements().add(table);
            HttpBase tr = HttpBase.genHttpTR("btd");
            table.getElements().add(tr);
            HttpBase td = new HttpBase();
            td.setTag("td");
            td.getProperties().setProperty("height", "36");
            td.getProperties().setProperty("align", "right");
            tr.getElements().add(td);

            HttpBase uploadbutton=UtilWebFactory.genHttpBaseBtn("upload","上传文件","formSubmit('uploadrpt.jsp');");
            td.getElements().add(uploadbutton);

            HttpBase btnDel = UtilWebFactory.genHttpBaseBtnDel("btnDel");
            HttpBase td2 = new HttpBase();;
            td2.setTag("td");
            td2.getProperties().setProperty("height", "36");
            td2.getProperties().setProperty("align", "right");
            tr.getElements().add(td2);
            td2.getElements().add(btnDel);

            result += base.GenHtml();

            HttpBase hb = new HttpBase();
            HttpBase table11 = new HttpBase();
            table11.setTag("table");
            table11.getProperties().setProperty("class", "btd3");
            table11.getProperties().setProperty("border", "0");
            table11.getProperties().setProperty("cellspacing", "1");
            table11.getProperties().setProperty("cellpadding", "5");
            table11.getProperties().setProperty("width", "100%");
            hb.getElements().add(table11);
            HttpBase tr111 = null;
            for (int i = 0; i < list.length; i++) {
                int m = i % 4;
                if (m == 0) {
                    tr111 = HttpBase.genHttpTR("btd");
                    table11.getElements().add(tr111);
                }

                HttpBase td1 = new HttpBase();
                td1.setTag("td");
                tr111.getElements().add(td1);

                String fname = list[i].getName();
                HttpBase rb = HttpBase.genHttpInputRadio("radio", fname,
                        "Javascript:doWhich();");
                File fileName = new File(path,fname);
                HttpBase val = HttpBase.genHttpValue(fname+"("+fileName.length()+")");
                td1.getElements().add(rb);
                td1.getElements().add(val);
            }
            result += hb.GenHtml();
        }
        else if (mode.equals("del")) {
            String path = "EleWork" + File.separator + "rpt"+File.separator;
            File fileName = new File(path, seqn);
            //检查File.txt是否存在
            if (fileName.exists()) {
                //删除File.txt档
                fileName.delete();
                String tmp;
                tmp = "alert('删除成功!');\n"
                      + "self.location.href='" + UtilWebTools.getRequestPath() +
                      findparam + "';";
                HttpBase val = HttpBase.genHttpScript(tmp);
                result += val.GenHtml();
            }
        }
        else if (UtilWebTools.getRequestParameter("action").equals("upload")) {
            // 增删改查按钮-----------------------------
            HttpBase base = new HttpBase();
            HttpBase table = HttpBase.genHttpTable("btd", "100%", "1", "5",
                    "0");
            base.getElements().add(table);
            HttpBase tr1 = HttpBase.genHttpTR("btd");
            table.getElements().add(tr1);
            HttpBase su=HttpBase.genHttpValue("上传成功!");
            tr1.getElements().add(su);

            HttpBase tr = HttpBase.genHttpTR("btd");
            table.getElements().add(tr);
            HttpBase td = new HttpBase();
            td.setTag("td");
            td.getProperties().setProperty("height", "36");
            td.getProperties().setProperty("align", "right");
            tr.getElements().add(td);

            HttpBase btnCancel = UtilWebFactory.genHttpBaseBtnCancel("btnCancel");
            td.getElements().add(btnCancel);

            result += base.GenHtml();
            ServletFileUpload fu = new ServletFileUpload();
            // 设置允许用户上传文件大小,单位:字节
            fu.setSizeMax(10000000);
           // 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
           //fu.setRepositoryPath("rpt");
           //开始读取上传信息
           try{
               List fileItems = fu.parseRequest(UtilWebTools.getRequest());
               // 依次处理每个上传的文件
               Iterator iter = fileItems.iterator();
               while (iter.hasNext()) {
                   FileItem item = (FileItem) iter.next();
                   //忽略其他不是文件域的所有表单信息
                   if (!item.isFormField()) {
                       String name = item.getName();
                       long size = item.getSize();
                       if ((name == null || name.equals("")) && size == 0)
                           continue;
                       //保存上传的文件到指定的目录
                       name = name.replace(':', '_');
                       name = name.replace('\\', '_');
                       String path = "EleWork" + File.separator + "rpt" +
                                     File.separator;
                       File f = new File(path + name);
                       if (f.exists()) {
                           f.delete();
                       }
                       item.write(f);
                   }
               }
           }catch (org.apache.commons.fileupload.FileUploadException ex){
               wlglException.ProcessMainWebExceptionMessage("101",
                       "失败.",
                       ex);
           }
           catch (java.lang.Exception ex){
               wlglException.ProcessMainWebExceptionMessage("101",
                       "失败.",
                       ex);
           }

        }

        else if (mode.equals("view")) {
            // 增删改查按钮-----------------------------
            HttpBase base = new HttpBase();
            HttpBase table = HttpBase.genHttpTable("btd", "100%", "1", "5",
                    "0");
            base.getElements().add(table);
            HttpBase tr = HttpBase.genHttpTR("btd");
            table.getElements().add(tr);
            HttpBase td = new HttpBase();
            td.setTag("td");
            td.getProperties().setProperty("height", "36");
            td.getProperties().setProperty("align", "right");
            tr.getElements().add(td);

            HttpBase btnCancel = UtilWebFactory.genHttpBaseBtnCancel(
                    "btnCancel");

            td.getElements().add(btnCancel);

            result += base.GenHtml();
            String path = "EleWork" + File.separator + "rpt"+File.separator;
            File fileName = new File(path, seqn);
            try {
                FileReader fr = new FileReader(path + seqn); //建立FileReader变量,并设定由fr变量变数引用
                int c = fr.read(); //从文件中读取一个字节
                while (c != -1) { //判断是否已读到文件的结尾
                    result += (char) c; //输出读取到的数据
                    c = fr.read(); //从文件中读取数据
                    if (c == 13) { //判断是否为断行字节
                        result += "<BR>"; //输出分行标签
                        fr.skip(1); //略过一个字节
                        c = fr.read(); //读取一个字节
                    }
                }
            }
            catch (java.io.IOException ex){
                wlglException.ProcessMainWebExceptionMessage("101",
                        "失败.",
                        ex);
            }

        }
        return result;
    }

}
