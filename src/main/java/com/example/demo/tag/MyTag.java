package com.example.demo.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/3/4
 */
public class MyTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        getJspContext().getOut().write("hello walker");
    }

}
