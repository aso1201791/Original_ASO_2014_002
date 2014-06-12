package com.example.originalaso_2014_002;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MaintenanceActivity extends Activity implements View.OnClickListener {

	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		//ボタンをIDで探してボタン変数をつくる
		Button modoru = (Button) findViewById(R.id.modoru);
		Button sakujo = (Button) findViewById(R.id.sakujo);
		//ボタン変数にリスナーを登録する
		modoru.setOnClickListener(this);
		sakujo.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maintenance_activity);
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch (v.getId()) {//どのボタンが押されたか判定
		case R.id.sakujo:
			break;
		case R.id.modoru:
			Intent intent = new Intent(MaintenanceActivity.this, MainActivity.class);
			startActivity(intent);
			break;
		}

	}
}
