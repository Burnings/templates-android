package com.common.burning.architecture;

import com.common.architecture.R;
import com.common.burning.widget.IndicatorFragment;
import com.common.burning.widget.IndicatorFragment.OnIndicateListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
/**
 * ��ҳ��
 * ʵ�֣���ʼ���ײ���������ʵ��fragment ��ת
 * @author Administrator
 *
 */
public class MainActivity extends FragmentActivity {
	
	public static Fragment[] mFragments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ���ر���
		setContentView(R.layout.activity_main);
		setFragmentIndicator(0);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**
	 * ��ʼ��fragment
	 */
	private void setFragmentIndicator(int whichIsDefault) {
		mFragments = new Fragment[3];
		mFragments[0] = getSupportFragmentManager().findFragmentById(
				R.id.fragment_home);
		mFragments[1] = getSupportFragmentManager().findFragmentById(
				R.id.fragment_life);
		mFragments[2] = getSupportFragmentManager().findFragmentById(
				R.id.fragment_user);
		getSupportFragmentManager().beginTransaction().hide(mFragments[0])
				.hide(mFragments[1]).hide(mFragments[2]).show(mFragments[whichIsDefault]).commit();

		IndicatorFragment mIndicator = (IndicatorFragment) findViewById(R.id.indicator);
		IndicatorFragment.setIndicator(whichIsDefault);
		mIndicator.setOnIndicateListener(new OnIndicateListener() {
			@Override
			public void onIndicate(View v, int which) {
				getSupportFragmentManager().beginTransaction()
						.hide(mFragments[0]).hide(mFragments[1])
						.hide(mFragments[2]).show(mFragments[which]).commit();
			}
		});
	}
}
