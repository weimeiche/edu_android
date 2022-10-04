package com.navyliu.widget.constants;

/**
 * Author: navyLiu
 * Time: 2017-12-07 15:40
 * Description: this is TimeConstants
 * qq :497488219
 */

public class TimeConstants {
	private static String curr_date = "-1";
	private static String curr_year = "2017";
	private static String curr_month = "06";
	private static String curr_day = "26";
	private static long curr_hour = 0;
	private static long curr_min = 0;
	private static long curr_sec = -1;
	private static String into_product_datetime = "-1";
	private static String qr_datetime = "";
	private static int countdown_sec = 0; // 倒计时数
	private static int countdown_limit = 300; // 返回首页计时限制
	private static int countdown_max_limit = 90; // 首页显示大广告计时限制
	private static int countdown_poll_limit = 6000; // 广告轮询时间限制 6s

	public static int getCountdown_poll_limit() {
		return countdown_poll_limit;
	}

	public static void setCountdown_poll_limit(int countdown_poll_limit) {
		TimeConstants.countdown_poll_limit = countdown_poll_limit;
	}

	public static int getCountdown_max_limit() {
		return countdown_max_limit;
	}

	public static void setCountdown_max_limit(int countdown_max_limit) {
		TimeConstants.countdown_max_limit = countdown_max_limit;
	}

	public static int getCountdown_limit() {
		return countdown_limit;
	}

	public static void setCountdown_limit(int countdown_limit) {
		TimeConstants.countdown_limit = countdown_limit;
	}

	public static int getCountdown_sec() {
		return countdown_sec;
	}

	public static void setCountdown_sec(int countdown_sec) {
		TimeConstants.countdown_sec = countdown_sec;
	}

	public static String getCurr_date() {
		return curr_date;
	}

	public static void setCurr_date(String curr_date) {
		TimeConstants.curr_date = curr_date;
	}

	public static String getCurr_year() {
		return curr_year;
	}

	public static void setCurr_year(String curr_year) {
		TimeConstants.curr_year = curr_year;
	}

	public static String getCurr_month() {
		return curr_month;
	}

	public static void setCurr_month(String curr_month) {
		TimeConstants.curr_month = curr_month;
	}

	public static long getCurr_hour() {
		return curr_hour;
	}

	public static void setCurr_hour(long curr_hour) {
		TimeConstants.curr_hour = curr_hour;
	}

	public static long getCurr_min() {
		return curr_min;
	}

	public static void setCurr_min(long curr_min) {
		TimeConstants.curr_min = curr_min;
	}

	public static long getCurr_sec() {
		return curr_sec;
	}

	public static void setCurr_sec(long curr_sec) {
		TimeConstants.curr_sec = curr_sec;
	}

	public static String getCurr_day() {
		return curr_day;
	}

	public static void setCurr_day(String curr_day) {
		TimeConstants.curr_day = curr_day;
	}

	public static String getInto_product_datetime() {
		return into_product_datetime;
	}

	public static void setInto_product_datetime(String into_product_datetime) {
		TimeConstants.into_product_datetime = into_product_datetime;
	}

	public static String getQr_datetime() {
		return qr_datetime;
	}

	public static void setQr_datetime(String qr_datetime) {
		TimeConstants.qr_datetime = qr_datetime;
	}


	private static long lastClickTime=0;
	/**
	 * 防止重复点击
	 * @return
	 */
	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if ( 0 < timeD && timeD < 900) {
			return true;
		}
		lastClickTime = time;
		return false;
	}
}
