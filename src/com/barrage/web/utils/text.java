package com.barrage.web.utils;

/*
 * File: WebFormatter.java
 * Created on 2005-6-24
 * Author: Liao Xuefeng, asklxf@163.com
 * Copyright (C) 2005, Liao Xuefeng.
 */
import java.util.*;
import java.text.SimpleDateFormat;

/**
 * Do some format on web display.
 * 
 * @author Xuefeng
 */
public class text {

    public static String html2text(String html) {
        StringBuffer sb = new StringBuffer(html.length());
        char[] data = html.toCharArray();
        int start = 0;
        boolean previousIsPre = false;
        Token token = null;
        for(;;) {
            token = parse(data, start, previousIsPre);
            if(token==null)
                break;
            previousIsPre = token.isPreTag();
            sb = sb.append(token.getText());
            start += token.getLength();
        }
        return sb.toString();
    }

    private static Token parse(char[] data, int start, boolean previousIsPre) {
        if(start>=data.length)
            return null;
        // try to read next char:
        char c = data[start];
        if(c=='<') {
            // this is a tag or comment or script:
            int end_index = indexOf(data, start+1, '>');
            if(end_index==(-1)) {
                // the left is all text!
                return new Token(Token.TOKEN_TEXT, data, start, data.length, previousIsPre);
            }
            String s = new String(data, start, end_index-start+1);
            // now we got s="<...>":
            if(s.startsWith("<!--")) { // this is a comment!
                int end_comment_index = indexOf(data, start+1, "-->");
                if(end_comment_index==(-1)) {
                    // illegal end, but treat as comment:
                    return new Token(Token.TOKEN_COMMENT, data, start, data.length, previousIsPre);
                }
                else
                    return new Token(Token.TOKEN_COMMENT, data, start, end_comment_index+3, previousIsPre);
            }
            String s_lowerCase = s.toLowerCase();
            if(s_lowerCase.startsWith("<script")) { // this is a script:
                int end_script_index = indexOf(data, start+1, "</script>");
                if(end_script_index==(-1))
                    // illegal end, but treat as script:
                    return new Token(Token.TOKEN_SCRIPT, data, start, data.length, previousIsPre);
                else
                    return new Token(Token.TOKEN_SCRIPT, data, start, end_script_index+9, previousIsPre);
            }
            else { // this is a tag:
                return new Token(Token.TOKEN_TAG, data, start, start+s.length(), previousIsPre);
            }
        }
        // this is a text:
        int next_tag_index = indexOf(data, start+1, '<');
        if(next_tag_index==(-1))
            return new Token(Token.TOKEN_TEXT, data, start, data.length, previousIsPre);
        return new Token(Token.TOKEN_TEXT, data, start, next_tag_index, previousIsPre);
    }

    private static int indexOf(char[] data, int start, String s) {
        char[] ss = s.toCharArray();
        // TODO: performance can improve!
        for(int i=start; i<(data.length-ss.length); i++) {
            // compare from data[i] with ss[0]:
            boolean match = true;
            for(int j=0; j<ss.length; j++) {
                if(data[i+j]!=ss[j]) {
                    match = false;
                    break;
                }
            }
            if(match)
                return i;
        }
        return (-1);
    }

    private static int indexOf(char[] data, int start, char c) {
        for(int i=start; i<data.length; i++) {
            if(data[i]==c)
                return i;
        }
        return (-1);
    }

    public static void main(String[] args) {
    	String bbtxt= html2text("<p style=\"text-indent:28px\"><span style=\"font-family:宋体\">意识渐渐清醒后，视野里是月光照耀下暗蓝的光景。</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">那是突然出现的台阶，一层层累叠螺旋。</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">随着晃荡的意识身体轻飘飘，对眼前场景的突兀也失去了惊叹的力气，踏上台阶，不知何时却成了一种极其自然的行为。</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">“新来的……是嘛。”</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">回荡在耳边的嘶哑之声，那是通过回廊传递而来。</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">上方，有某个人在那里。</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">那个人是谁？</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">而我……有为何在这里？</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">疑惑驱使着身体挪动沉重的步伐，紊乱的记忆杂乱纠缠，连触碰的欲望都不曾有。唯一想做的，只有放空思想，然后前行，前行，前行……</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">尽头，开阔的视野被无数的书架填满。</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">那是多么夸张的藏书……目光所到之处，书架、墙壁，全部都塞满了书籍。在暗淡月光的照耀之下，与其称这里为图书馆，不如称之为书籍墓场。</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">“久候多时了，新来的。”</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">正前方，在一个被书本累成的小山丘上，他就坐在那里——杂乱披肩的白色长发，骨瘦如柴的躯干，单薄的白色长跑，布满皱纹的脸庞，还有那沧桑嘶哑的声音。</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">这老家伙究竟活了多久……只能得出这样的思考。只是思考仍未结束，老人的声音再度将思绪拉回现实。</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">“你做好心理准备了吗？”</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">嘶哑中，却没有一丝动摇；眼眉间，却隐藏着些许尖锐。</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">“什么意思？我……”</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">“你杀了我的所有随从，现在又来到了我的面前，你应该有什么想说的……不，我大概明白了。”</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">闭上眼睛点点头，好似明白了什么的老人重新把锐利的眼神投射过来。</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">“恭喜你通关了地下城，冒险者。”</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">地下、地下……城……</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">——地下城！？</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">这一个关键词，若落入平静池塘的巨石，在脑海掀起狂澜。</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">“而在你面前的我，则是这个地下城的管理者，如你所见。”</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">拿起拐杖从书堆站起来，身体重心极度不稳，老人仿佛一个不注意就要摔倒。</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">“在解决所有事情之前，我有个问题想要问你——”</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">但是，拄着拐杖拼了命站直的他，却挺着胸膛，冷冷地看了过来。</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">“对你来说地下城究竟是什么，年轻人。”</span></p><p><span style=\"font-size:14px;font-family: &#39;Calibri&#39;,sans-serif\"><br clear=\"all\" style=\";page-break-before:always\"/> </span> </p><p style=\"text-indent:28px\"><span>&nbsp;</span></p><p style=\"text-indent:28px\"><span style=\"font-family:宋体\">——地下城非指地下城，它只是异空间的一种代称。</span></p><p><br/></p>");
    	System.out.println(bbtxt);
	}
}

class Token {

    public static final int TOKEN_TEXT    = 0; // html text.
    public static final int TOKEN_COMMENT = 1; // comment like <!-- comments... -->
    public static final int TOKEN_TAG     = 2; // tag like <pre>, <font>, etc.
    public static final int TOKEN_SCRIPT  = 3;

    private static final char[] TAG_BR  = "<br".toCharArray();
    private static final char[] TAG_P   = "<p".toCharArray();
    private static final char[] TAG_LI  = "<li".toCharArray();
    private static final char[] TAG_PRE = "<pre".toCharArray();
    private static final char[] TAG_HR  = "<hr".toCharArray();

    private static final char[] END_TAG_TD = "</td>".toCharArray();
    private static final char[] END_TAG_TR = "</tr>".toCharArray();
    private static final char[] END_TAG_LI = "</li>".toCharArray();

    private static final Map SPECIAL_CHARS = new HashMap();

    private int type;
    private String html;           // original html
    private String text = null;    // text!
    private int length = 0;        // html length
    private boolean isPre = false; // isPre tag?

    static {
        SPECIAL_CHARS.put("&quot;", "\"");
        SPECIAL_CHARS.put("&lt;",   "<");
        SPECIAL_CHARS.put("&gt;",   ">");
        SPECIAL_CHARS.put("&amp;",  "&");
        SPECIAL_CHARS.put("&reg;",  "(r)");
        SPECIAL_CHARS.put("&copy;", "(c)");
        SPECIAL_CHARS.put("&nbsp;", " ");
        SPECIAL_CHARS.put("&pound;", "?");
    }

    public Token(int type, char[] data, int start, int end, boolean previousIsPre) {
        this.type = type;
        this.length = end - start;
        this.html = new String(data, start, length);
        System.out.println("[Token] html=" + html + ".");
        parseText(previousIsPre);
        System.out.println("[Token] text=" + text + ".");
    }

    public int getLength() {
        return length;
    }

    public boolean isPreTag() {
        return isPre;
    }

    private void parseText(boolean previousIsPre) {
        if(type==TOKEN_TAG) {
            char[] cs = html.toCharArray();
            if(compareTag(TAG_BR, cs) || compareTag(TAG_P, cs))
                text = "\n";
            else if(compareTag(TAG_LI, cs))
                text = "\n* ";
            else if(compareTag(TAG_PRE, cs))
                isPre = true;
            else if(compareTag(TAG_HR, cs))
                text = "\n--------\n";
            else if(compareString(END_TAG_TD, cs))
                text = "\t";
            else if(compareString(END_TAG_TR, cs) || compareString(END_TAG_LI, cs))
                text = "\n";
        }
        // text token:
        else if(type==TOKEN_TEXT) {
            text = toText(html, previousIsPre);
        }
    }

    public String getText() {
        return text==null ? "" : text;
    }

    private String toText(String html, final boolean isPre) {
        char[] cs = html.toCharArray();
        StringBuffer buffer = new StringBuffer(cs.length);
        int start = 0;
        boolean continueSpace = false;
        char current, next;
        for(;;) {
            if(start>=cs.length)
                break;
            current = cs[start]; // read current char
            if(start+1<cs.length) // and next char
                next = cs[start+1];
            else
                next = '\0';
            if(current==' ') {
                if(isPre || !continueSpace)
                    buffer = buffer.append(' ');
                continueSpace = true;
                // continue loop:
                start++;
                continue;
            }
            // not ' ', so:
            if(current=='\r' && next=='\n') {
                if(isPre)
                    buffer = buffer.append('\n');
                // continue loop:
                start+=2;
                continue;
            }
            if(current=='\n' || current=='\r') {
                if(isPre)
                    buffer = buffer.append('\n');
                // continue loop:
                start++;
                continue;
            }
            // cannot continue space:
            continueSpace = false;
            if(current=='&') {
                // maybe special char:
                int length = readUtil(cs, start, ';', 10);
                if(length==(-1)) { // just '&':
                    buffer = buffer.append('&');
                    // continue loop:
                    start++;
                    continue;
                }
                else { // check if special character:
                    String spec = new String(cs, start, length);
                    String specChar = (String)SPECIAL_CHARS.get(spec);
                    if(specChar!=null) { // special chars!
                        buffer = buffer.append(specChar);
                        // continue loop:
                        start+=length;
                        continue;
                    }
                    else { // check if like '&#1234':
                        if(next=='#') { // maybe a char
                            String num = new String(cs, start+2, length-3);
                            try {
                                int code = Integer.parseInt(num);
                                if(code>0 && code<65536) { // this is a special char:
                                    buffer = buffer.append((char)code);
                                    // continue loop:
                                    start++;
                                    continue;
                                }
                            }
                            catch(Exception e) {}
                            // just normal char:
                            buffer = buffer.append("&#");
                            // continue loop:
                            start+=2;
                            continue;
                        }
                        else { // just '&':
                            buffer = buffer.append('&');
                            // continue loop:
                            start++;
                            continue;
                        }
                    }
                }
            }
            else { // just a normal char!
                buffer = buffer.append(current);
                // continue loop:
                start++;
                continue;
            }
        }
        return buffer.toString();
    }

    // read from cs[start] util meet the specified char 'util',
    // or null if not found:
    private int readUtil(final char[] cs, final int start, final char util, final int maxLength) {
        int end = start+maxLength;
        if(end>cs.length)
            end = cs.length;
        for(int i=start; i<start+maxLength; i++) {
            if(cs[i]==util) {
                return i-start+1;
            }
        }
        return (-1);
    }

    // compare standard tag "<input" with tag "<INPUT value=aa>"
    private boolean compareTag(final char[] ori_tag, char[] tag) {
        if(ori_tag.length>=tag.length)
            return false;
        for(int i=0; i<ori_tag.length; i++) {
            if(Character.toLowerCase(tag[i])!=ori_tag[i])
                return false;
        }
        // the following char should not be a-z:
        if(tag.length>ori_tag.length) {
            char c = Character.toLowerCase(tag[ori_tag.length]);
            if(c<'a' || c>'z')
                return true;
            return false;
        }
        return true;
    }

    private boolean compareString(final char[] ori, char[] comp) {
        if(ori.length>comp.length)
            return false;
        for(int i=0; i<ori.length; i++) {
            if(Character.toLowerCase(comp[i])!=ori[i])
                return false;
        }
        return true;
    }

    public String toString() {
        return html;
    }
}