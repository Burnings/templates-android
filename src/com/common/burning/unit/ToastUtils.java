package com.common.burning.unit;

import com.common.architecture.BuildConfig;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast消息辅助类,可通过设置TOAST变量控制普通消息开关
 * 
 * @author yuanhang
 */
public class ToastUtils {

	public static boolean TOAST = true;// 控制是否打印日志,默认为打印

	/**
	 * Toast消息,可在项目配置种关闭输出
	 */
	public static void show(final Context context, final CharSequence msg) {
		if (TOAST && context != null && msg != null && !"".equals(msg)) {
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * Toast消息,可在项目配置种关闭输出
	 */
	public static void show(final Context context, final int resId) {
		if (TOAST && context != null && resId > 0) {
			Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 测试信息,发布后不显示
	 */
	public static void showDebug(final Context context, final int resId) {
		if (BuildConfig.DEBUG && context != null && resId > 0) {
			Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 测试信息,发布后不显示
	 */
	public static void showDebug(final Context context, final CharSequence msg) {
		if (BuildConfig.DEBUG && context != null && msg != null && !"".equals(msg)) {
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
		}
	}
}
