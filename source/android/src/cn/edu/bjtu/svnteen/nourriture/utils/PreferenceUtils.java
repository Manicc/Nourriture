package cn.edu.bjtu.svnteen.nourriture.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 主要用来存一些数据，比如：登录用户名，获取的token
 * 
 * @author Tans
 * 
 */
public class PreferenceUtils {
	private static final String KEY_LOGIN_STATUS = "login";
	private static final String KEY_USERNAME = "username";
	private static final String KEY_TOKEN = "token";

	private static SharedPreferences mSharedPreferences;
	private static Editor mEditor;

	public static void init(Context context) {
		mSharedPreferences = context.getSharedPreferences("nourriture",
				Context.MODE_PRIVATE);
		mEditor = mSharedPreferences.edit();
	}

	// 存当前用户的登录状态
	public static void saveLogin(boolean status) {
		mEditor.putBoolean(KEY_LOGIN_STATUS, status);
		mEditor.commit();
	}

	// 获取当前用户的登录状态
	public static boolean getLogin() {
		return mSharedPreferences.getBoolean(KEY_LOGIN_STATUS, false);
	}

	// 存当前用户的token
	public static void saveToken(String token) {
		mEditor.putString(KEY_TOKEN, token);
		mEditor.commit();
	}

	// 获取当前用户的token
	public static String getToken() {
		return mSharedPreferences.getString(KEY_TOKEN, "");
	}

	// 存当前用户的姓名
	public static void saveUserName(String username) {
		mEditor.putString(KEY_USERNAME, username);
		mEditor.commit();
	}

	// 获取当前用户的姓名
	public static String getUserName() {
		return mSharedPreferences.getString(KEY_USERNAME, "");
	}
	
	// 用户退出，清空所有数据
	public static void clearAllData(){
		saveLogin(false);
		saveToken("");
		saveUserName("");
	}
}
