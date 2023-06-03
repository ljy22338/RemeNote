package com.study.xuan.editor.operate.parse;

import android.text.TextUtils;

/**
 * Author : xuan.
 * Date : 18-4-10.
 * Description : the file description
 */
public class MarkDownFormater implements Formater {
    private StringBuilder strBuilder;

    public MarkDownFormater() {
        strBuilder = new StringBuilder();
    }

    @Override
    public String formatBold(String str) {
        return appendStr("**", str, "**");
    }

    @Override
    public String formatItalics(String str) {
        return appendStr("*", str, "*");
    }

    @Override
    public String formatCenterLine(String str) {
        return appendStr("~~", str, "~~");
    }

    @Override
    public String formatUnderLine(String str) {
        return appendStr("<span style=\"border-bottom:2px dashed yellow;\">", str, "</span>");
    }

    @Override
    public String formatLink(String name, String str) {
        return appendStringBuilder("[", name, "]")
                + appendStr("(", str, ")");
    }

    @Override
    public String formatH1(String h1) {
        return appendStr("#", h1, null);
    }

    @Override
    public String formatH2(String h2) {
        return appendStr("##", h2, null);
    }

    @Override
    public String formatH3(String h3) {
        return appendStr("###", h3, null);
    }

    @Override
    public String formatH4(String h4) {
        return appendStr("####", h4, null);
    }

    @Override
    public String formatRefer(String refer) {
        return appendStr(">", refer, null);
    }

    @Override
    public String formatCenter(String str) {
        return appendStr("<center>", str, "</center>");
    }

    private String appendStr(String left, String center, String right) {
        return appendStringBuilder(left, center, right).toString();
    }

    private StringBuilder appendStringBuilder(String left, String center, String right) {
        strBuilder.setLength(0);
        if (TextUtils.isEmpty(right)) {
            return strBuilder.append(left).append(center);
        } else {
            return strBuilder.append(left).append(center).append(right);
        }
    }
}
