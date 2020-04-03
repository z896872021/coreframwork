/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.core_framwork.utils;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorRes;

import com.example.core_framwork.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * ================================================
 * 处理字符串的工具类
 * <p>
 * Created by JessYan on 2016/3/16
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class CharacterHandler {

    private CharacterHandler() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static final InputFilter emojiFilter = new InputFilter() {//emoji过滤器
        Pattern emoji = Pattern.compile(
                "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart,
                                   int dend) {

            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {
                return "";
            }

            return null;
        }
    };

    /**
     * 字符串转换成十六进制字符串
     *
     * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
     */
    public static String str2HexStr(String str) {

        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;

        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString().trim();
    }

    /**
     * json 格式化
     *
     * @param json
     * @return
     */
    public static String jsonFormat(String json) {
        if (TextUtils.isEmpty(json)) {
            return "Empty/Null json content";
        }
        String message;
        try {
            json = json.trim();
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                message = jsonObject.toString(4);
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                message = jsonArray.toString(4);
            } else {
                message = json;
            }
        } catch (JSONException e) {
            message = json;
        }
        return message;
    }

    /**
     * xml 格式化
     *
     * @param xml
     * @return
     */
    public static String xmlFormat(String xml) {
        if (TextUtils.isEmpty(xml)) {
            return "Empty/Null xml content";
        }
        String message;
        try {
            Source xmlInput = new StreamSource(new StringReader(xml));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            message = xmlOutput.getWriter().toString().replaceFirst(">", ">\n");
        } catch (TransformerException e) {
            message = xml;
        }
        return message;
    }

    /**
     * 设置 TextView 中部分文字颜色.
     *
     * @param allTextStr               全部文字
     * @param colorTextStr             需要改变颜色的文字
     * @param colorId                  改变的颜色
     * @param size                     文字大小
     * @param onColorTextClickListener 改变颜色文字的点击事件.
     */
    public static SpannableString getColorAndSizeTextClick(TextView tv,
                                                           String allTextStr,
                                                           String colorTextStr,
                                                           @ColorRes int colorId,
                                                           int size,
                                                           OnColorTextClickListener onColorTextClickListener) {

        if (!allTextStr.contains(colorTextStr)) {
            throw new RuntimeException(allTextStr + "  不包含  " + colorTextStr + "  文字");
        }

        Context context = tv.getContext();

        int start = allTextStr.indexOf(colorTextStr);
        int end = start + colorTextStr.length();

        SpannableString spanText = new SpannableString(allTextStr);
        spanText.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                if (colorId != 0)
                    ds.setColor(context.getResources().getColor(colorId)); //设置文字颜色
                if (size != 0) {
                    ds.setTextSize(CoreUtils.sp2px(context, size));
                }
                ds.setUnderlineText(false);
            }

            @Override
            public void onClick(View view) {
                if (null != onColorTextClickListener) {
                    onColorTextClickListener.onColorTextClick(view);
                }
            }
        }, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setHighlightColor(context.getResources().getColor(R.color.transparent));// 设置点击后的颜色为透明，否则会一直出现高亮
        tv.setMovementMethod(LinkMovementMethod.getInstance());// 设置变色文字点击事件
        return spanText;
    }

    /**
     * 设置 TextView 中部分文字颜色.
     *
     * @param allTextStr               全部文字
     * @param colorTextStr             需要改变颜色的文字
     * @param colorId                  改变的颜色
     * @param size                     文字大小
     * @param onColorTextClickListener 改变颜色文字的点击事件.
     */
    public static void setColorAndSizeTextClick(TextView tv,
                                                String allTextStr,
                                                String colorTextStr,
                                                @ColorRes int colorId,
                                                int size,
                                                OnColorTextClickListener onColorTextClickListener) {
        tv.setText(getColorAndSizeTextClick(tv, allTextStr, colorTextStr, colorId, size, onColorTextClickListener));
    }

    public interface OnColorTextClickListener {

        void onColorTextClick(View view);
    }
}
