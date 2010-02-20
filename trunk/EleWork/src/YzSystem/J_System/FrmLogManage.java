package YzSystem.J_System;

import YzSystem.JMain.*;

import java.util.Iterator;
import java.io.*;

public class FrmLogManage extends FrmFunctionBase {
    public FrmLogManage() {
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
            String path = "EleWork" + File.separator + "log"+File.separator;
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

            HttpBase btnView = UtilWebFactory.genHttpBaseBtnView("btnView");
            HttpBase btnDel = UtilWebFactory.genHttpBaseBtnDel("btnDel");

            td.getElements().add(btnView);
            td.getElements().add(btnDel);

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
        if (mode.equals("del")) {
            String path = "EleWork" + File.separator + "log"+File.separator;
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

        if (mode.equals("view")) {
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
            String path = "EleWork" + File.separator + "log"+File.separator;
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
