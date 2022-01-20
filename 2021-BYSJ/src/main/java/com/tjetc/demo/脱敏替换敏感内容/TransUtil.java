package com.tjetc.demo.脱敏替换敏感内容;

import java.util.ArrayList;
import java.util.List;

public class TransUtil {

    public static final String SPLITWORD = "[*]";

    public static final String KEY_TYPE_O = "O";

    public static final String KEY_TYPE_X = "X";

    public static final String KEY_TYPE_Z = "Z";

    public static void main(String[] args) {

         String companyName="中国联合网络通信有限公司北京市分公司";
         String rule1="X,0,1#O,1,4#X,4,7#O,7,10#X,10,Z";
         System.out.println(TransUtil.replaceSecretWord(companyName, rule1,"*"));
    }


    /**
     * 根据规则将传入的字符脱敏
     * 例：
     * String userName="孙戌杰";
     * String rule="X,0,1#O,1,Z";
     * System.out.println(TransUtils.replaceSecretWord(userName, rule,"*"));
     * 输出：*戌杰
     * String companyName="中国联合网络通信有限公司北京市分公司";
     * String rule1="X,0,1#O,1,4#X,4,7#O,7,10#X,10,Z";
     * System.out.println(TransUtils.replaceSecretWord(companyName, rule1,"*"));
     * 输出：*国联合***信有限********
     * @param originalWord 需要原始的字符
     * @param rule 规则
     * 例：X,0,1#O,1,Z 以#号分割 X表示需要替换 O表示不需要替换 Z表示原始字符的结尾
     *    X,0,1代表原始字符中0-1这部分字符需要替换
     *    O,1,Z代表从1位开始到最后一位不需要替换
     * @param safeLetter 脱敏使用的字符
     * @return String 脱敏后的字符
     * */
    public static String replaceSecretWord(String originalWord,String rule,String safeLetter) {
        List<String> parts = new ArrayList<String>();
        String[] subRuleArray = rule.split("#");
        for(int i=0;i<subRuleArray.length;i++) {
            String subRule = subRuleArray[i];
            String[] keyArray = subRule.split(",");
            if(keyArray.length!=3) {
                break;
            }
            String keyType = keyArray[0];
            String keyStart = keyArray[1];
            String keyTypeEnd = keyArray[2];
            int start = Integer.parseInt(keyStart);
            if(KEY_TYPE_O.equals(keyType)&&originalWord.length()>start) {
                if (KEY_TYPE_Z.equals(keyTypeEnd)) {
                    parts.add(originalWord.substring(start, originalWord.length()));
                } else {
                    if (originalWord.length() > Integer.parseInt(keyTypeEnd)) {
                        parts.add(originalWord.substring(start, Integer.parseInt(keyTypeEnd)));
                    }else {
                        parts.add(originalWord.substring(start, originalWord.length()));
                    }
                }
            }else if(KEY_TYPE_X.equals(keyType)&&originalWord.length()>start){
                if (KEY_TYPE_Z.equals(keyTypeEnd)) {
                    parts.add(createXWord((originalWord.length() - start),safeLetter));
                }else {
                    if(originalWord.length()>Integer.parseInt(keyTypeEnd)){
                        parts.add(createXWord((Integer.parseInt(keyTypeEnd) - start),safeLetter));
                    }else {
                        parts.add(createXWord((originalWord.length() - start),safeLetter));
                    }
                }
            }
        }
        String safeWord = "";
        for(int i=0;i<parts.size();i++) {
            safeWord = safeWord + parts.get(i);
        }
        return safeWord;
    }

    /**
     * 创建n位脱敏字符
     * 例：createXWord(3,"*") 返回 ***
     * @param length 脱敏字符长度
     * @param safeLetter 脱敏使用的字符
     * @return String
     * */
    private static String createXWord(int length,String safeLetter)
    {
        String xword = "";
        for(int i=0;i<length;i++) {
            xword = xword + safeLetter;
        }
        return xword;
    }

}
