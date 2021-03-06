package com.heima.smartbeijing;

import com.heima.smartbeijing.fragment.ContentFragment;
import com.heima.smartbeijing.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

/**
 * 主界面
 * @author lenovo
 *
 */
public class MainActivity extends SlidingFragmentActivity{

	private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
	private static final String TAG_CONTENT = "TAG_CONTENT";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
	
		setBehindContentView(R.layout.left_menu);
		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//全屏触摸
//		slidingMenu.setBehindOffset(400);//屏幕预留像素
		
		//200/320 *屏幕宽度
		WindowManager wm = getWindowManager();
		int width = wm.getDefaultDisplay().getWidth();
		slidingMenu.setBehindOffset(width*200 / 320);
		
		initFragment();
	}

	//初始化fragment
	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		//用fragment替换帧布局,参数3是fragment的标记，通过标记可以方便找到fragment
		transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(), TAG_LEFT_MENU);
		transaction.replace(R.id.fl_main, new ContentFragment(), TAG_CONTENT);
		transaction.commit();//提交事务
	}
	//获取侧边栏对象
	public LeftMenuFragment getLeftFragment(){
		FragmentManager fm = getSupportFragmentManager();
		LeftMenuFragment leftFragment = (LeftMenuFragment) fm.findFragmentByTag(TAG_LEFT_MENU);
		return  leftFragment;
		
	}
	//获取ContentFragment对象
	public ContentFragment getContentFragment(){
		FragmentManager fm = getSupportFragmentManager();
		ContentFragment contentFragment = (ContentFragment) fm.findFragmentByTag(TAG_CONTENT);
		return contentFragment;
	}
}
