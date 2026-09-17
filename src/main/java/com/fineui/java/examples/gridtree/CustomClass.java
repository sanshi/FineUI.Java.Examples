package com.fineui.java.examples.gridtree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 树表格 · 强类型对象绑定（路由 {@code grid-tree/custom-class}）：数据源是一组自定义对象（{@code TheFileInfo}），
 * 层级不用 ParentId 字段，而用对象间的 {@code Parent} 引用；父字段用点路径 {@code Parent.Id} 取父节点 id。
 */
@FineUIPage("grid-tree/custom-class")
public class CustomClass extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setDataSource(getTreeDataList());
        Grid1.dataBind();
    }

    private List<TheFileInfo> getTreeDataList() {
        List<TheFileInfo> infos = new ArrayList<>();

        // basic
        TheFileInfo basicInfo = new TheFileInfo();
        basicInfo.setId(50);
        basicInfo.setParent(null);
        basicInfo.setName("basic");
        basicInfo.setType("文件夹");
        basicInfo.setSize(null);
        basicInfo.setModifyDate(LocalDate.of(2014, 11, 3));
        infos.add(basicInfo);

        // basic -> captcha
        TheFileInfo captchaInfo = new TheFileInfo();
        captchaInfo.setId(54);
        captchaInfo.setParent(basicInfo);
        captchaInfo.setName("captcha");
        captchaInfo.setType("文件夹");
        captchaInfo.setSize(null);
        captchaInfo.setModifyDate(LocalDate.of(2014, 8, 17));
        infos.add(captchaInfo);

        TheFileInfo info = new TheFileInfo();
        info.setId(55);
        info.setParent(captchaInfo);
        info.setName("CaptchaController.java");
        info.setType("Java文件");
        info.setSize(1);
        info.setModifyDate(LocalDate.of(2014, 7, 5));
        infos.add(info);

        info = new TheFileInfo();
        info.setId(56);
        info.setParent(captchaInfo);
        info.setName("CaptchaService.java");
        info.setType("Java文件");
        info.setSize(2);
        info.setModifyDate(LocalDate.of(2014, 7, 5));
        infos.add(info);

        // basic -> hello.html
        info = new TheFileInfo();
        info.setId(51);
        info.setParent(basicInfo);
        info.setName("hello.html");
        info.setType("HTML文件");
        info.setSize(1);
        info.setModifyDate(LocalDate.of(2014, 7, 5));
        infos.add(info);

        info = new TheFileInfo();
        info.setId(52);
        info.setParent(basicInfo);
        info.setName("HelloController.java");
        info.setType("Java文件");
        info.setSize(1);
        info.setModifyDate(LocalDate.of(2014, 8, 24));
        infos.add(info);

        info = new TheFileInfo();
        info.setId(53);
        info.setParent(basicInfo);
        info.setName("HelloModel.java");
        info.setType("Java文件");
        info.setSize(2);
        info.setModifyDate(LocalDate.of(2014, 7, 5));
        infos.add(info);

        info = new TheFileInfo();
        info.setId(100);
        info.setParent(null);
        info.setName("index.html");
        info.setType("HTML文件");
        info.setSize(31);
        info.setModifyDate(LocalDate.of(2014, 11, 15));
        infos.add(info);

        info = new TheFileInfo();
        info.setId(101);
        info.setParent(null);
        info.setName("Application.java");
        info.setType("Java文件");
        info.setSize(13);
        info.setModifyDate(LocalDate.of(2014, 10, 27));
        infos.add(info);

        info = new TheFileInfo();
        info.setId(102);
        info.setParent(null);
        info.setName("pom.xml");
        info.setType("XML文件");
        info.setSize(12);
        info.setModifyDate(LocalDate.of(2014, 10, 12));
        infos.add(info);

        info = new TheFileInfo();
        info.setId(105);
        info.setParent(null);
        info.setName("application.properties");
        info.setType("配置文件");
        info.setSize(3);
        info.setModifyDate(LocalDate.of(2014, 11, 6));
        infos.add(info);

        return infos;
    }

    /** 自定义文件信息对象：层级用 {@code parent} 引用表达（根节点 parent 为 null）。 */
    public static class TheFileInfo {

        private int id;
        private String name;
        private String type;
        private Integer size;
        private LocalDate modifyDate;
        private TheFileInfo parent;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public LocalDate getModifyDate() {
            return modifyDate;
        }

        public void setModifyDate(LocalDate modifyDate) {
            this.modifyDate = modifyDate;
        }

        public TheFileInfo getParent() {
            return parent;
        }

        public void setParent(TheFileInfo parent) {
            this.parent = parent;
        }
    }
}
