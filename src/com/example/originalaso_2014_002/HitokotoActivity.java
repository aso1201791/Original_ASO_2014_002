package com.example.originalaso_2014_002;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HitokotoActivity extends Activity implements View.OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hitokoto_activity);
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

		//画面（Activity）に渡されたインテントを取得
		Intent intent = this.getIntent();
		//intentから、Extralに混入させた値をキーワードで取得
		String strHitokoto = intent.getStringExtra("hitokoto");

		//取得したStringを、TxtViewにセット
		TextView txvHITIKOTO = (TextView)findViewById(R.id.txvHITOKOTO);
		txvHITOKOTO.setText(strHitokoto);
	}

}
