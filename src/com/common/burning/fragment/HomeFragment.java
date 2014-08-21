package com.common.burning.fragment;

import com.common.architecture.R;
import com.common.burning.widget.TitleView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class HomeFragment extends Fragment implements OnClickListener {
	private View mParent;
	private TitleView mTitle;
	private FragmentActivity mActivity;
	public int getShownIndex() {
		return getArguments().getInt("index", 0);
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mActivity = getActivity();
		mParent = getView();
		initTitle();
		ininView();
	}
	private void initTitle() {
		mTitle = (TitleView) mParent.findViewById(R.id.home_title);
		mTitle.setTitle("首页");
		mTitle.removeLeftButton();
		mTitle.removeRightButton();
	}

	private void ininView() {
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		default:
			break;
		}
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
