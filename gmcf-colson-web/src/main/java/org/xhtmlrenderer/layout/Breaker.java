package org.xhtmlrenderer.layout;

/**
 * @author 12550
 * @program: 02src
 * @description: 解决中文不换行
 * @date 2021-04-20 14:46:40
 */

import org.xhtmlrenderer.css.constants.IdentValue;
import org.xhtmlrenderer.css.style.CalculatedStyle;
import org.xhtmlrenderer.render.FSFont;

public class Breaker {
    public static void breakFirstLetter(LayoutContext c, LineBreakContext context, int avail, CalculatedStyle style) {
        FSFont font = style.getFSFont(c);
        context.setEnd(getFirstLetterEnd(context.getMaster(), context.getStart()));
        context.setWidth(c.getTextRenderer().getWidth(c.getFontContext(), font, context.getCalculatedSubstring()));

        if (context.getWidth() > avail) {
            context.setNeedsNewLine(true);
            context.setUnbreakable(true);
        }
    }

    private static int getFirstLetterEnd(String text, int start) {
        int i = start;
        while (i < text.length()) {
            char c = text.charAt(i);
            int type = Character.getType(c);
            if ((type != 21) && (type != 22) && (type != 29) && (type != 30) && (type != 24)) {
                break;
            }

            i++;
        }

        if (i < text.length()) {
            i++;
        }
        return i;
    }

    public static void breakText(LayoutContext c, LineBreakContext context, int avail, CalculatedStyle style) {
        FSFont font = style.getFSFont(c);
        IdentValue whitespace = style.getWhitespace();

        if (whitespace == IdentValue.NOWRAP) {
            context.setEnd(context.getLast());
            context.setWidth(c.getTextRenderer().getWidth(c.getFontContext(), font, context.getCalculatedSubstring()));

            return;
        }

        if ((whitespace == IdentValue.PRE) || (whitespace == IdentValue.PRE_WRAP) || (whitespace == IdentValue.PRE_LINE)) {
            int n = context.getStartSubstring().indexOf("\n");
            if (n > -1) {
                context.setEnd(context.getStart() + n + 1);
                context.setWidth(c.getTextRenderer().getWidth(c.getFontContext(), font, context.getCalculatedSubstring()));

                context.setNeedsNewLine(true);
                context.setEndsOnNL(true);
            } else if (whitespace == IdentValue.PRE) {
                context.setEnd(context.getLast());
                context.setWidth(c.getTextRenderer().getWidth(c.getFontContext(), font, context.getCalculatedSubstring()));
            }

        }

        if ((whitespace == IdentValue.PRE) || ((context.isNeedsNewLine()) && (context.getWidth() <= avail))) {
            return;
        }

        context.setEndsOnNL(false);

        String currentString = context.getStartSubstring();
        int left = 0;
        //int right = currentString.indexOf(" ", left + 1);
        int right = getStrRight(currentString, left);
        int lastWrap = 0;
        int graphicsLength = 0;
        int lastGraphicsLength = 0;

        while ((right > 0) && (graphicsLength <= avail)) {
            lastGraphicsLength = graphicsLength;
            graphicsLength += c.getTextRenderer().getWidth(c.getFontContext(), font, currentString.substring(left, right));

            lastWrap = left;
            left = right;
            //right = currentString.indexOf(" ", left + 1);
            right = getStrRight(currentString, left+1);
        }

        if (graphicsLength <= avail) {
            lastWrap = left;
            lastGraphicsLength = graphicsLength;
            graphicsLength += c.getTextRenderer().getWidth(c.getFontContext(), font, currentString.substring(left));
        }

        if (graphicsLength <= avail) {
            context.setWidth(graphicsLength);
            context.setEnd(context.getMaster().length());

            return;
        }

        context.setNeedsNewLine(true);

        if (lastWrap != 0) {
            context.setEnd(context.getStart() + lastWrap);
            context.setWidth(lastGraphicsLength);
        } else {
            if (left == 0) {
                left = currentString.length();
            }

            context.setEnd(context.getStart() + left);
            context.setUnbreakable(true);

            if (left == currentString.length()) {
                context.setWidth(c.getTextRenderer().getWidth(c.getFontContext(), font, context.getCalculatedSubstring()));
            } else
                context.setWidth(graphicsLength);
        }
    }

    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    private static int getStrRight(String s,int left){
        if(left>=s.length())
            return -1;
        char[] ch = s.toCharArray();
        for(int i = left;i<ch.length;i++){
            if(isChinese(ch[i]) || ' ' == ch[i]){
                return i==0?i+1:i;
            }
        }
        return -1;
    }

}
