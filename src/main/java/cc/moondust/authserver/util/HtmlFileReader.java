package cc.moondust.authserver.util;

import java.io.*;

/**
 * Created by j0 on 2016/7/28.
 */
public class HtmlFileReader {
    public static String readTxtFile(File html) {
        String htmlText="";
        if (html.isFile() && html.exists()) { //判断文件是否存在
            try {
                InputStreamReader read = null;//考虑到编码格式
                read = new InputStreamReader(
                        new FileInputStream(html), "GBK");

                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                StringBuffer stringBuffer = new StringBuffer();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    stringBuffer.append(lineTxt);
                }
                read.close();
                htmlText=stringBuffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return htmlText;
    }
}
