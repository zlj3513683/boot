package com.example.demo.bean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.apo.BeanAnn;
import com.example.demo.apo.ListNotHasNull;
import com.example.demo.apo.MyNotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.awt.print.Book;
import java.util.List;

/**
 * 功能：
 *
 * @author 2020/1/17
 * @author zoulinjun
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @MyNotNull( message="这是一个测试验证注解" )
    private String name;

//    @Valid
//    @NotEmpty(message = "所拥有书籍不能为空")
//    @ListNotHasNull(message = "List 中不能含有null元素")
//    private List books;
    @BeanAnn
    private CsInfo messageInfo;

}
