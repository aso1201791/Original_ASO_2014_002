package com.example.originalaso_2014_002;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {

	SQLiteDatabase sdb = null;
	MySQLiteOpenHelper helper =null;
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

		if(sdb == null) {
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		}catch(SQLiteException e){
			//異常終了
			return;
		}
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
		case R.id.toroku: //登録ボタンが押された
			//エディットテキストからの入力内容を取り出す
			EditText etv = (EditText)findViewById(R.id.EditHitokoto);
			String inputMsg = etv.getText().toString();

			//inptMsgがnullでない、かつ、からでない場合のみ、if分内を実行
			if(inputMsg!=null && !inputMsg.isEmpty()){
				//MySQLuteOpenHelperのインサートメソッドを呼び出し
				helper.insertHitokoto(sdb, inputMsg);
			}
			//入力欄をクリア
			etv.setText("");
			break;
		case R.id.mente:
			Intent intent =new Intent(MainActivity.this,MaintenanceActivity.class );
			startActivity(intent);
			break;
		case R.id.check:

			//MySQLiteOpenHelperのセレクト一言メソッドを呼び出して一言をランダムに取得
			String strHitokoto = helper.selectRandomHitokoto(sdb);

			//インテントのインスタンス生成
			Intent intent2 =new Intent(MainActivity.this,HitokotoActivity.class );
			//インテントに一言を混入
			intent.putExtra("hitokoto", strHitokoto);

			//次画面のアクティビティ起動
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
