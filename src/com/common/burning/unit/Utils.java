/**
 * TODO
 * com.coupleworld2.ui.activity.Login.Welcome--Utils.java
 * Copyright qianbaiyang
 * @Dec 9, 2012
 */
package com.common.burning.unit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.StatFs;


/**
 * @author linjinbin Utils.java Dec 9, 2012
 * 
 */
public class Utils {

	private static final String FILENAME_FORMAT = "%s_%d.%s";
	public static final int IMAGE_HEIGHT_THRESHOLD = 800;

	public static final int IMAGE_WIDTH_THRESHOLD = 480;

	public static final int IMAGE_COMPRESS_MODE_QUALITY = 1;

	public static final int IMAGE_COMPRESS_MODE_SIZE = 2;

	/**
	 * @param root
	 * @param filename
	 * @return
	 */
	public static String getUniqueFileName(final File root,
			final String filename) {
		final File file = new File(root, filename);
		if (!file.exists()) {
			return file.getAbsolutePath();
		}
		final int dotPos = filename.lastIndexOf('.');
		String part1 = filename;
		String part2 = "";
		if (dotPos > 0) {
			part1 = filename.substring(0, dotPos);
			part2 = filename.substring(dotPos + 1);
		}
		for (int i = 1; true; i++) {
			final String res = String.format(FILENAME_FORMAT, part1, i, part2);
			final File t = new File(root, res);
			if (!t.exists()) {
				return t.getAbsolutePath();
			}
		}
	}

	public static void createDirForNewFile(final String fileName) {
		final String dir = fileName.substring(0, fileName.lastIndexOf("/"));
		final File dirFile = new File(dir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
	}

	public static boolean mergeBitmap(final Bitmap oriBmp, final Bitmap subBmp) {
		return mergeBitmap(oriBmp, subBmp, new Rect(0, 0, oriBmp.getWidth(),
				oriBmp.getHeight()));
	}

	/**
	 * 将subBmp图像合并到oriBmp中
	 * 
	 * @param oriBmp
	 * @param subBmp
	 * @param rc
	 *            subFilePath的图像覆盖的区域
	 * @return
	 */
	public static boolean mergeBitmap(final Bitmap oriBmp, final Bitmap subBmp,
			final Rect rc) {
		return mergeBitmap(oriBmp, subBmp, rc, new Paint());
	}

	/**
	 * 将subBmp图像合并到oriBmp中
	 * 
	 * @param oriBmp
	 * @param subBmp
	 * @param rc
	 *            subFilePath的图像覆盖的区域
	 * @param paint
	 * @return
	 */
	public static boolean mergeBitmap(final Bitmap oriBmp, final Bitmap subBmp,
			final Rect rc, final Paint paint) {
		try {
			if (subBmp == null) {
				return false;
			}
			final Canvas cvs = new Canvas(oriBmp);
			final Rect rcSub = new Rect(0, 0, subBmp.getWidth(),
					subBmp.getHeight());
			cvs.drawBitmap(subBmp, rcSub, rc, paint);

			return true;
		} catch (final OutOfMemoryError e) {
		}
		return false;
	}

	/**
	 * 没有检测到SD卡
	 * 
	 * @return
	 */
	public static boolean isSDCardUnavailable() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_REMOVED);
	}

	/**
	 * @return true 如果SD卡处于不可读写的状态
	 */
	public static boolean isSDCardBusy() {
		return !Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	/**
	 * 检查ＳＤ卡是否已满。如果ＳＤ卡的剩余空间小于１００ｋ，则认为ＳＤ卡已满。
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isSDCardFull() {
		return getSDCardAvailableBytes() <= (100 * 1024);
	}

	public static boolean isSDCardUseful() {
		return (!isSDCardBusy()) && (!isSDCardFull())
				&& (!isSDCardUnavailable());
	}

	/**
	 * 获取ＳＤ卡的剩余字节数。
	 * 
	 * @return
	 */
	public static long getSDCardAvailableBytes() {
		if (isSDCardBusy()) {
			return 0;
		}

		final File path = Environment.getExternalStorageDirectory();
		final StatFs stat = new StatFs(path.getPath());
		final long blockSize = stat.getBlockSize();
		final long availableBlocks = stat.getAvailableBlocks();
		return blockSize * (availableBlocks - 4);
	}

	public static boolean isWIFIConnected(final Context context) {
		final ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (cm == null) {
			return false;
		}

		final NetworkInfo info = cm.getActiveNetworkInfo();
		if (info == null) {
			return false;
		}

		return ConnectivityManager.TYPE_WIFI == info.getType();
	}
//
//	public static File makeDirsIfNeeded(MESSAGE_TYPE messageType) {
//		if (Utils.isSDCardBusy())
//			return null;
//
//		if (messageType == MESSAGE_TYPE.image) {
//			String path = Environment.getExternalStorageDirectory()
//					.getAbsolutePath()
//					+ File.separator
//					+ Constants.PENGYOU
//					+ File.separator + "images";
//			File imageRoot = new File(path);
//			imageRoot.mkdirs();
//			return imageRoot;
//		} else if (messageType == MESSAGE_TYPE.sound) {
//			String path = Environment.getExternalStorageDirectory()
//					.getAbsolutePath()
//					+ File.separator
//					+ Constants.PENGYOU
//					+ File.separator + "audio";
//			File audioRoot = new File(path);
//			audioRoot.mkdirs();
//			return audioRoot;
//		}
//
//		throw new IllegalArgumentException("hey, what are you passing in ? "
//				+ messageType);
//	}

	/**
	 * Indicates whether the specified action can be used as an intent. This
	 * method queries the package manager for installed packages that can
	 * respond to an intent with the specified action. If no suitable package is
	 * found, this method returns false.
	 * 
	 * @param context
	 *            The application's environment.
	 * @param action
	 *            The Intent action to check for availability.
	 * @return True if an Intent with the specified action can be sent and
	 *         responded to, false otherwise.
	 */
	public static boolean isIntentAvailable(final Context context,
			final String action) {
		final PackageManager packageManager = context.getPackageManager();
		final Intent intent = new Intent(action);
		final List<ResolveInfo> list = packageManager.queryIntentActivities(
				intent, PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	/**
	 * Indicates whether the specified action can be used as an intent. This
	 * method queries the package manager for installed packages that can
	 * respond to an intent with the specified action. If no suitable package is
	 * found, this method returns false.
	 * 
	 * @param context
	 *            The application's environment.
	 * @param intent
	 *            The Intent to check for availability.
	 * @return True if an Intent with the specified action can be sent and
	 *         responded to, false otherwise.
	 */
	public static boolean isIntentAvailable(final Context context,
			final Intent intent) {
		final PackageManager packageManager = context.getPackageManager();
		final List<ResolveInfo> list = packageManager.queryIntentActivities(
				intent, PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	/**
	 * 将 bitmap压缩至目标大小, 如果目标宽高比和原宽高比不一致,这里会导致压缩失真
	 * 
	 * @param srcBmp
	 * @param targetWidth
	 * @param targetHeight
	 * @param config
	 * @return
	 */
	public static Bitmap compressBitmap(final Bitmap srcBmp,
			final int targetWidth, final int targetHeight,
			final Bitmap.Config config) {
		final int srcWidth = srcBmp.getWidth();
		final int srcHeight = srcBmp.getHeight();
		if ((srcWidth < targetWidth) && (srcHeight < targetWidth)) {
			return srcBmp;
		} else {
			final Rect rcSrc = new Rect(0, 0, srcWidth, srcHeight);
			final Rect rcDest = new Rect(0, 0, targetWidth, targetHeight);
			final Bitmap targetBmp = Bitmap.createBitmap(targetWidth,
					targetHeight, config);
			final Canvas canvas = new Canvas(targetBmp);
			canvas.drawBitmap(srcBmp, rcSrc, rcDest, new Paint());
			return targetBmp;
		}
	}

	/** 保留源图片 比例的 compressBitmap */
	public static Bitmap compressBitmapWithNoDistortion(final Bitmap srcBmp,
			int targetWidth, int targetHeight, final Bitmap.Config config) {
		final int srcWidth = srcBmp.getWidth();
		final int srcHeight = srcBmp.getHeight();

		if ((srcWidth < targetWidth) && (srcHeight < targetWidth)) {
			return srcBmp;
		}

		final double rate = (srcWidth / (double) targetWidth) > (srcHeight / (double) targetHeight) ? srcWidth
				/ (double) targetWidth
				: srcHeight / (double) targetHeight;
		targetWidth = (int) (srcWidth / rate);
		targetHeight = (int) (srcHeight / rate);
		return compressBitmap(srcBmp, targetWidth, targetHeight, config);
	}

	/**
	 * Compute the closest power-of-two scale factor and pass that to
	 * sBitmapOptionsCache.inSampleSize, which will result in faster decoding
	 * and better quality
	 * 
	 * @param filePath
	 * @param width
	 *            期望的最小短边
	 * @param height
	 *            期望的最小长边
	 * @return
	 */
	public static Bitmap decodeFile2(final String filePath,
			final int expectedMin, final int expectedMax) throws IOException {
		try {

			int sampleSize = 1;
			final BitmapFactory.Options sBitmapOptionsCache = new BitmapFactory.Options();
			// Compute the closest power-of-two scale factor
			// and pass that to sBitmapOptionsCache.inSampleSize, which will
			// result in faster decoding and better quality
			sBitmapOptionsCache.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(filePath, sBitmapOptionsCache);

			int max = sBitmapOptionsCache.outWidth;
			int min = sBitmapOptionsCache.outHeight;
			if (sBitmapOptionsCache.outWidth < sBitmapOptionsCache.outHeight) {
				max = sBitmapOptionsCache.outHeight;
				min = sBitmapOptionsCache.outWidth;
			}

			int nextMax = max;
			int nextMin = min;
			while ((nextMax > expectedMax * 1.5)
					&& (nextMin > expectedMin * 1.5)) {
				sampleSize <<= 1;
				nextMax >>= 1;
				nextMin >>= 1;
			}

			sBitmapOptionsCache.inSampleSize = sampleSize;
			sBitmapOptionsCache.inJustDecodeBounds = false;

			final Bitmap compressedBmp = BitmapFactory.decodeFile(filePath,
					sBitmapOptionsCache);
			if (compressedBmp == null) {
				return null;
			}

			int orientation = 0;
			try {
				final ExifInterface exif = new ExifInterface(filePath);
				if (exif != null) {
//					orientation = (int) ImageExifUtils
//							.exifOrientationToDegrees(exif.getAttributeInt(
//									ExifInterface.TAG_ORIENTATION,
//									ExifInterface.ORIENTATION_NORMAL));
				}
			} catch (final Exception ex) {
				ex.printStackTrace();
			}

			final Matrix mat = new Matrix();
			mat.postRotate(orientation);
			final Bitmap rotatedBmp = Bitmap.createBitmap(compressedBmp, 0, 0,
					compressedBmp.getWidth(), compressedBmp.getHeight(), mat,
					true);
			if (rotatedBmp != compressedBmp) {
				// 在CM rom中，如果源图片的尺寸和目标图片的尺寸一模一样，则会直接将source bitmap返回，
				// 此时不能recycle，因为是同一个bitmap
				compressedBmp.recycle();
			}
			return rotatedBmp;
		} catch (final OutOfMemoryError e) {
			throw new IOException("decode file out of memory");
		}
	}

//	public static File getAppRoot() {
////		File root = new File(Environment.getExternalStorageDirectory(),
////				Constants.PENGYOU + "/");
//		if (!root.isDirectory()) {
//			root.mkdirs();
//		}
//		return root;
//	}
//
//	public static File getImageRoot() {
//		File root = new File(Environment.getExternalStorageDirectory(),
//				Constants.PENGYOU + "/images");
//		if (!root.isDirectory()) {
//			root.mkdirs();
//		}
//		return root;
//	}
//
//	public static File getAudioRoot() {
//		File root = new File(Environment.getExternalStorageDirectory(),
//				Constants.PENGYOU + "/audio");
//		if (!root.isDirectory()) {
//			root.mkdirs();
//		}
//		return root;
//	}

	public static boolean saveBitmap(final Bitmap bmp, final String fileName,
			final CompressFormat format, final int quality) {
		FileOutputStream output = null;
		try {
			createDirForNewFile(fileName);
			final File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			output = new FileOutputStream(file);
			return bmp.compress(format, quality, output);
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

//	public static String writeBmpToFile(Bitmap bitmap) {
//		String filePath = Utils.getUniqueFileName(Utils.getImageRoot(), UUID
//				.randomUUID().toString() + ".jpg");
//		FileOutputStream outputStream = null;
//		File file = new File(filePath);
//		try {
//			if (file.exists()) {
//				file.delete();
//			}
//			Bitmap bitmapResize = null;
//			file.createNewFile();
//			outputStream = new FileOutputStream(file, false);
//			bitmap.compress(CompressFormat.JPEG, 100, outputStream);
//			outputStream.flush();
//			outputStream.close();
//			bitmapResize = FLImage.adjustBitmap(filePath);
//			if (file.exists()) {
//				file.delete();
//			}
//			file.createNewFile();
//			outputStream = new FileOutputStream(file, false);
//			bitmapResize.compress(CompressFormat.JPEG, 100, outputStream);
//			outputStream.flush();
//			outputStream.close();
//			bitmap.recycle();
//			bitmap = null;
//			bitmapResize.recycle();
//			bitmapResize = null;
//		} catch (final FileNotFoundException e) {
//			e.printStackTrace();
//			return "";
//		} catch (IOException e) {
//			e.printStackTrace();
//			return "";
//		} finally {
//			if (null != outputStream) {
//				try {
//					outputStream.close();
//				} catch (final IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return filePath;
//	}
//
//	public static String writeSmallBmpToFile(Bitmap bitmap) {
//		String filePath = "small_"
//				+ Utils.getUniqueFileName(Utils.getImageRoot(), UUID
//						.randomUUID().toString() + ".jpg");
//		FileOutputStream outputStream = null;
//		File file = new File(filePath);
//		try {
//			if (file.exists()) {
//				file.delete();
//			}
//			Bitmap bitmapResize = null;
//			file.createNewFile();
//			outputStream = new FileOutputStream(file, false);
//			bitmap.compress(CompressFormat.JPEG, 100, outputStream);
//			outputStream.flush();
//			outputStream.close();
//			bitmapResize = FLImage.adjustBitmap(filePath);
//			if (file.exists()) {
//				file.delete();
//			}
//			file.createNewFile();
//			outputStream = new FileOutputStream(file, false);
//			bitmapResize.compress(CompressFormat.JPEG, 100, outputStream);
//			outputStream.flush();
//			outputStream.close();
//			bitmap.recycle();
//			bitmap = null;
//			bitmapResize.recycle();
//			bitmapResize = null;
//		} catch (final FileNotFoundException e) {
//			e.printStackTrace();
//			return "";
//		} catch (IOException e) {
//
//			e.printStackTrace();
//			return "";
//		} finally {
//			if (null != outputStream) {
//				try {
//					outputStream.close();
//				} catch (final IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return filePath;
//	}

	/**
	 * 传入距离现在gap秒，返回适合显示的字符串
	 * 
	 * @param gap
	 * @return
	 */
	public static String computeFromNowStr(long timemillis) {
		long gap = (System.currentTimeMillis() - timemillis) / 1000;
		if (gap < 0) {
			gap = 0;
		}
		String[] danweis = new String[] { "秒前", "分钟前", "小时前", "" };
		String danwei = danweis[0];
		String tmpGap;
		if (gap >= 60) {
			gap = gap / 60;// 以分钟为单位
			danwei = danweis[1];
			if (gap >= 60) {
				gap = gap / 60;// 以小时为单位
				danwei = danweis[2];
				if (gap >= 24) {
//					return DateTimeUtil.TimeStamp2Date(timemillis / 1000 + "",
//							"yyyy-MM-dd");
				}
			}
		} else {
			return "刚刚";
		}
		return gap + danwei;
	}

//	public static String DatetimeFromNow(java.util.Date datetime) {
//
//		long timemillis = datetime.getTime();
//		String strRet = "";
//		long gap = (System.currentTimeMillis() - timemillis) / 1000;
//		timemillis = timemillis / 1000;
//		String rtime = DateUtils.formartDate(datetime, "yyyy-MM-dd HH:mm");
//		String thisYearTime = DateUtils.formartDate(datetime, "MM-dd HH:mm");
//		String htime = DateUtils.formartDate(datetime, "HH:mm");
//
//		java.util.Date nowDate = new java.util.Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
//		Date d;
//		boolean flag = true; // 是否为当前天
//
//		boolean isThisYear = true; // 是否为当前年
//		try {
//			d = sdf.parse(rtime);
//			if (DateUtils.formartDate(d, "yyyy-MM-dd").equals(
//					DateUtils.formartDate(nowDate, "yyyy-MM-dd"))) {
//				flag = true;
//			} else {
//				flag = false;
//			}
//			if (DateUtils.formartDate(d, "yyyy").equals(
//					DateUtils.formartDate(nowDate, "yyyy"))) {
//				isThisYear = true;
//			} else {
//				isThisYear = false;
//			}
//
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		if (gap < 60) {
//			strRet = "刚刚";
//		} else if (gap < 60 * 60) {
//			String strMin = (gap / 60) + "";
//			strRet = strMin + "分钟前";
//		} else if (gap < 60 * 60 * 24) {
//			String strH = (gap / (60 * 60)) + "";
//			if (flag) {
//				strRet = htime;
//			} else {
//				strRet = "昨天 " + htime;
//			}
//		} else if (gap < 60 * 60 * 24 * 3) {
//
//			String strD = (gap / (60 * 60 * 24)) + "";
//			if ("1".equals(strD)) {
//				strRet = "昨天 " + htime;
//			} else {
//				strRet = "前天 " + htime;
//			}
//		} else {
//			if (isThisYear) {
//				strRet = thisYearTime;
//			} else {
//				strRet = rtime;
//			}
//
//		}
//		return strRet;
//	}
//
//	public static String getFormatDate(java.util.Date datetime) {
//
//		String rtime = DateUtils.formartDate(datetime, "yyyy年MM月dd日");
//		String thisYearTime = DateUtils.formartDate(datetime, "MM月dd日");
//
//		java.util.Date nowDate = new java.util.Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
//		Date d;
//		boolean isThisYear = true; // 是否为当前年
//		if (DateUtils.formartDate(datetime, "yyyy").equals(
//				DateUtils.formartDate(nowDate, "yyyy"))) {
//			isThisYear = true;
//			return thisYearTime;
//		} else {
//			isThisYear = false;
//			return rtime;
//		}
//	}
}
