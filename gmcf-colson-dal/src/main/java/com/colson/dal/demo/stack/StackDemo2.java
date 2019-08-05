package com.colson.dal.demo.stack;

import com.colson.dal.util.SeqStack;
import com.colson.dal.util.constant.BracketConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * 括号匹配：任意括号嵌套，顺序是随机的
 * 正确示例：[{}]  [[{}{[]}]]
 */
public class StackDemo2 {

    public static void main(String[] args) {
        String s = "<{}>[{}]{<[]>}";
        System.out.println(checkLegal(s));
    }

    public static boolean checkLegal(String bracket) {
        if (null == bracket || bracket.length()%2 != 0) {
            return false;
        }
        char[] chars = bracket.toCharArray();
        Map map = initBracket();
        SeqStack<Character> seqStack = new SeqStack();
        for (char c : chars) {
            if (map.keySet().contains(c)) {
                seqStack.push(c);
            } else {
                if (seqStack.size()==0) return false;
                char last = seqStack.get(seqStack.size()-1);
                if (map.get(last).equals(c)) {
                    seqStack.pop();
                } else {
                    return false;
                }
            }
        }
        if (seqStack.size()==0) {
            return true;
        }
        return false;
    }

    public static Map initBracket() {
        Map<Character,Character> map = new HashMap<>();
        map.put(BracketConstant.LEFT_ANGLE_BRACKET,BracketConstant.RIGHT_ANGLE_BRACKET);
        map.put(BracketConstant.LEFT_BRACE,BracketConstant.RIGHT_BRACE);
        map.put(BracketConstant.LEFT_ROUND_BRACKET,BracketConstant.RIGHT_ROUND_BRACKET);
        map.put(BracketConstant.LEFT_SQUARE_BRACKET,BracketConstant.RIGHT_SQUARE_BRACKET);

        return map;
    }
}
