package com.example.demo.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/3/4
 */
public class ParmTag extends SimpleTagSupport {

    private Map map;
    private List<Map> b;

    public List<Map> getB() {
        return b;
    }

    public void setB(List<Map> b) {
        this.b = b;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    @Override
    public void doTag() throws JspException, IOException {
        for (Map bk:
             b) {
            String method = (String)bk.get("method");
             String[] marr = method.split("~");
             StringBuilder stringBuilder = new StringBuilder();
            for (String m:
                 marr) {
                Object mk = map.get(m);
                stringBuilder.append(mk + "!");
            }
            getJspContext().getOut().write(stringBuilder.toString());
            getJspContext().getOut().write("<br>");
        }



    }

}
