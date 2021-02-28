package cf.wellod.utils;

import cf.wellod.bean.Employee;

import java.security.MessageDigest;
import java.util.UUID;

public class MD5Util {

    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for(int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    // 密码转换为密文
    public static boolean convertPwd(Employee employee){
        employee.setPassword(employee.getPassword());
        boolean ret;
        if(employee.getPassword().length() == 0){
            ret = false;
        }else{
            String uuid = UUID.randomUUID().toString().replace("-","");
            employee.setSalt(uuid);
            employee.setPassword(string2MD5(employee.getPassword()+uuid));
            ret = true;
        }
        return ret;
    }

    public static boolean isCompete(String ciphertext, String plaintext, String salt){
        return ciphertext.equals(string2MD5(plaintext+salt));
    }
}