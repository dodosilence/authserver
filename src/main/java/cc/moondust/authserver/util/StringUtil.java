package cc.moondust.authserver.util;

/**
 * Created by j0 on 2016/7/28.
 */
public class StringUtil {
    public static boolean empty(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean hasEmptyStr(String... strs) {
        for (int i = 0; i < strs.length; i++) {
            if (empty(strs[i])) {
                return true;
            }
        }
        return false;
    }
}
