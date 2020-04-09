package com.nowcoder.community;

import java.io.IOException;

public class WKTests {

    public static void main(String[] args) {
        String cmd = "d:/work/data/wkhtmltopdf/bin/wkhtmltoimage --quality 75 https://www.nowcoder.com d:/work/data/wkhtmltopdf/wk-images/3.png";
        try {
            Runtime.getRuntime().exec(cmd);
            // 直接执行打印ok，java不管了，交给操作系统去做
            System.out.println("OK");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
