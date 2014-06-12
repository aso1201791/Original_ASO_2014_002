package com.example.originalaso_2014_002;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		//ボタンをIDで探してボタン変数をつくる
		Button toroku = (Button) findViewById(R.id.toroku);
		Button mente = (Button) findViewById(R.id.mente);
		Button check = (Button) findViewById(R.id.check);
		//ボタン変数にリスナーを登録する
		toroku.setOnClickListener(this);
		mente.setOnClickListener(this);
		check.setOnClickListener(this);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch (v.getId()) {//どのボタンが押されたか判定
		case R.id.sakujo:
			break;
		case R.id.mente:
			Intent intent =new Intent(MainActivity.this,MaintenanceActivity.class );
			startActivity(intent);
			break;
		case R.id.check:
			Intent intent2 =new Intent(MainActivity.this,HitokotoActivity.class );
			startActivity(intent2);
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
