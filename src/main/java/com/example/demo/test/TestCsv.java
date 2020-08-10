package com.example.demo.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/3/30
 */
public class TestCsv {

    public static void main(String[] args) {
        try{

            System.out.println(UUID.randomUUID().toString().replace("-",""));

            StringBuffer sb = new StringBuffer();
            sb.append("aaaaa,");
            String b = "呵呵,哈哈";
            String bs = "\"" + b + "\"";
            sb.append(bs);

            File file =new File("E:\\zlj\\qb\\file\\1.csv");


            //使用true，即进行append file

            FileWriter fileWritter = new FileWriter(file,true);


            fileWritter.write(sb.toString());

            fileWritter.close();
            System.out.println("ok");

        }catch(IOException e){

            e.printStackTrace();

        }




    }

}
