package com.chens;
import org.apache.commons.codec.binary.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
public class BaseMd5 {
    public static String createId(){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        return uuid.toString().replace("-", "");
    }

    public  static  String manage(String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());
            byte[] ms = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < ms.length; offset++) {
                i = ms[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static  String md5(String src) {
        MessageDigest md ;
        try {
            md = MessageDigest.getInstance("MD5");
           byte [] bytes = md.digest(src.getBytes());
           //将MD5处理结果利用bASE64
            String str = Base64.encodeBase64String(bytes);
            return str;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }
}
