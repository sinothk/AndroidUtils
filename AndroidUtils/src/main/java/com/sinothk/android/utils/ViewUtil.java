package com.sinothk.android.utils;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LYT on 2017/8/11.
 * 功能：处理
 */
public class ViewUtil {

    private Context context;

    ViewUtil(Context mContext) {
        context = mContext;
    }

    /**
     * 根据手机宽度和指定的宽高比来动态设置View的高度(父类为LinearLayout)，主要使用ImageView显示。
     *
     * @param baseView 要设置高度的View
     * @param ratio    显示比例: w/h = ratio
     */
    public void createNewViewHeight(View baseView, float ratio) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) baseView.getLayoutParams();
        params.height = (int) (width / ratio);
        baseView.setLayoutParams(params);
    }

    /**
     * EditText中有数据时，将光标移到最后
     *
     * @param editText
     */
    public void focusMoveToEnd(EditText editText) {
        // 有内容，将光标移最后
        Editable eText = editText.getText();
        Selection.setSelection(eText, eText.length());
    }

    /**
     * 输入框内容提示
     *
     * @param context
     * @param editText
     * @param max_length
     * @param err_msg
     */
    public void inputMaxTip(final Context context, final EditText editText, final TextView inputMaxTipTv, final int max_length, final String err_msg) {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                int len = s.toString().length();

                inputMaxTipTv.setText(len + "/" + max_length);
            }
        });

        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(max_length) {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                int destLen = dest.toString().length();
                int sourceLen = source.toString().length();

                if (destLen + sourceLen > max_length + 1) {
                    Toast.makeText(context, err_msg, Toast.LENGTH_SHORT).show();
                    return "";
                }
                return source;
            }
        };
        editText.setFilters(filters);
    }

    /**
     * @param content
     * @return
     * @description 获取一段字符串的字符个数(包含中英文, 一个中文算2个字符)
     */
    private int getCharacterNum(final String content) {
        if (null == content || "".equals(content)) {
            return 0;
        } else {
            return (content.length() + getChineseNum(content));
        }
    }

    /**
     * @param s
     * @return
     * @description 返回字符串里中文字或者全角字符的个数
     */
    private int getChineseNum(String s) {

        int num = 0;
        char[] myChar = s.toCharArray();
        for (int i = 0; i < myChar.length; i++) {
            if ((char) (byte) myChar[i] != myChar[i]) {
                num++;
            }
        }
        return num;
    }

    public void showSoftInput(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();

        InputMethodManager inputManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        assert inputManager != null;
        inputManager.showSoftInput(editText, 0);
    }
}
