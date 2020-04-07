package com.thinkgem.jeesite.modules.zjvideo.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import nl.bitwalker.useragentutils.UserAgent;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.BCodec;

/**
 * 此类实现了DownloadFileNameEncoder接口，用户对文件下载时的文件名进行编码。 由于各个浏览器对文件下载时文件名解析的方式不一样，
 * 因此需要扩展，以适应不同浏览器在下载时不会出现乱码。
 * IE时，需要先将文件名转为UTF-8，然后再x-www-form-url编码，Firefox则使用RFC2047规定的方式。
 *
 * 参考：
 * http://java-xp.iteye.com/blog/903048
 * http://greenbytes.de/tech/tc2231/
 *
 * 类库：
 *   user-agent-utils
 *     http://java.net/projects/user-agent-utils
 *
 * @author btpka3@163.com
 */
public class DownloadFileNameEncoder {
    private final BCodec bCodec = new BCodec();

    /**
     * 按照浏览器信息不同，对文件名进行编码，默认情况下使用IE的编码方式。
     */
    public String encode(String fileName, String userAgent) {
        if (fileName == null) {
            return "";
        }
        String safeFileName = getSafeFileName(fileName);

        UserAgent agent = UserAgent.parseUserAgentString(userAgent);
        switch (agent.getBrowser().getGroup()) {
        case IE:
            return encodeURLEncoder(safeFileName);
        case OPERA:
            return encoedeRFC2231(safeFileName);
        case SAFARI:
            return encodedISO(safeFileName);
        case FIREFOX:
            return encodeBase64(safeFileName);
        default:
            return encodeURLEncoder(safeFileName);
        }
    }

    // Content-Disposition: attachment;
    // filename="struts2.0%E4%B8%AD%E6%96%87%E6%95%99%E7%A8%8B.chm"
    protected String encodeURLEncoder(String safeFileName) {
        try {
            String result = URLEncoder.encode(safeFileName, "UTF-8");
            result = result.replace("+", "%20");
            return result;
        } catch (UnsupportedEncodingException e) {
            return safeFileName;
        }
    }

    // Content-Disposition: attachment;
    // filename="=?UTF8?B?c3RydXRzMi4w5Lit5paH5pWZ56iLLmNobQ==?="
    protected String encodeBase64(String safeFileName) {
        try {
            return bCodec.encode(safeFileName);
        } catch (EncoderException e) {
            return safeFileName;
        }
    }

    // Content-Disposition: attachment; filename*=UTF-8''%E5%9B%9E%E6%89%A7.msg
    protected String encoedeRFC2231(String safeFileName) {
        try {
            String result = "Content-Disposition: attachment; filename*=UTF-8''"
                    + URLEncoder.encode(safeFileName, "UTF8");
            return result;
        } catch (UnsupportedEncodingException e) {
            return safeFileName;
        }
    }

    // Content-Disposition: attachment;filename="测试.txt"
    protected String encodedISO(String safeFileName) {
        try {
            String result = "Content-Disposition: attachment; filename="
                    + new String(safeFileName.getBytes("UTF-8"), "ISO8859-1");
            return result;
        } catch (UnsupportedEncodingException e) {
            return safeFileName;
        }
    }

    /**
     * 将特殊字符替换成下划线
     *
     * @param fileName
     *            可能含有特殊字符的文件名
     * @return 替换后的文件名
     */
    protected static String getSafeFileName(String fileName) {
        // / \ : * ? " < > |
        List<Character> reservedChars = Arrays.asList('\\', '/', ':', '*', '?',
                '"', '<', '>', '|');
        char[] charArr = fileName.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            if (reservedChars.contains(charArr[i])) {
                charArr[i] = ' ';
            }
        }
        return new String(charArr);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "a b+c\\d\"";
        s = getSafeFileName(s);
        System.out.println("[" + s + "]");
        s = URLEncoder.encode(s, "UTF-8");
        System.out.println("[" + s + "]");

        System.out.println(new String("中 国".getBytes("UTF-8"), "ISO-8859-1"));
    }
}
