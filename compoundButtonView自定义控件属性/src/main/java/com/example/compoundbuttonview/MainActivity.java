package com.example.compoundbuttonview;

import com.example.compoundbuttonview.view.CheckSwitchButton;
import com.example.compoundbuttonview.view.SlideSwitchView;
import com.example.compoundbuttonview.view.SlideSwitchView.OnSwitchChangedListener;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;
/**
 * @author RA
 * @blog http://blog.csdn.net/vipzjyno1
 */
public class MainActivity extends Activity {
	private ToggleButton mTogBtn;
	private CheckSwitchButton mCheckSwithcButton;
	private CheckSwitchButton mEnableCheckSwithcButton;
	private SlideSwitchView mSlideSwitchView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
	}

	private void initView() {
		mTogBtn = (ToggleButton) findViewById(R.id.mTogBtn); // ��ȡ���ؼ�
		mTogBtn.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					//ѡ��
				}else{
					//δѡ��
				}
			}
		});// ��Ӽ����¼�
		mCheckSwithcButton = (CheckSwitchButton)findViewById(R.id.mCheckSwithcButton);
		mEnableCheckSwithcButton = (CheckSwitchButton)findViewById(R.id.mEnableCheckSwithcButton);
		mCheckSwithcButton.setChecked(false);
		mCheckSwithcButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mEnableCheckSwithcButton.setEnabled(false);
					mSlideSwitchView.setEnabled(false);
				}else{
					mEnableCheckSwithcButton.setEnabled(true);
					mSlideSwitchView.setEnabled(true);
				}
			}
		});
		mSlideSwitchView = (SlideSwitchView) findViewById(R.id.mSlideSwitchView);
		mSlideSwitchView.setOnChangeListener(new OnSwitchChangedListener() {
			
			@Override
			public void onSwitchChange(SlideSwitchView switchView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
