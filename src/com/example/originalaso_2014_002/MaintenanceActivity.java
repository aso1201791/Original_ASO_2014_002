package com.example.originalaso_2014_002;

import android.app.Activity;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MaintenanceActivity extends Activity implements
		View.OnClickListener, AdapterView.OnItemClickListener {

	// SQLiteデータベース空間を操作するインスタンス変数を宣言
	SQLiteDatabase sdb = null;
	// MYSQLiteOpenHelperを操作するインスタンス変数を宣言
	MySQLiteOpenHelper helper = null;

	//リストに選択したHitokotoテーブルのレコードの[_id]カラム値を保持する変数の宣言
	int selectedID = -1;
	//リストにで選択した行番号を保持する変数の宣言
	int lastPosition = -1;


	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		//ボタンをIDで探してボタン変数をつくる
		Button modoru = (Button) findViewById(R.id.modoru);
		Button sakujo = (Button) findViewById(R.id.sakujo);
		ListView lstHitokoto = (ListView) findViewById(R.id.LvHITOKOTO);
		//ボタン変数にリスナーを登録する
		modoru.setOnClickListener(this);
		sakujo.setOnClickListener(this);

		//ListViewにOnClickListenerをセット
		lstHitokoto.setOnItemClickListener(this);

		//ListViewにDBの値をセット
		this.setDBValuetoList(lstHitokoto);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maintenance_activity);
	}

	/**
	 * Hitokotoテーブルから、引数で指定した「_id」と同じコードを持つ削除
	 * @param　id　指定する値
	 */
	private void deleteFromHitokoto(int id){
		//クラスのフィールド変数がNULLなら、データベース空間オープン
		if(sdb == null){
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		}catch (SQLiteException e){
			//異常終了
			Log.e("ERROR" , e.toString());
		}
		//MySQLiteOpenHelperにDELETE文を実行させる
		this.helper.deleteHitokoto(sdb, id);
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch (v.getId()) {//どのボタンが押されたか判定
		case R.id.sakujo:

			//選択行があれば
			if(this.selectedID != -1){
				this.deleteFromHitokoto(this.selectedID);
				ListView lstHitokoto = (ListView) findViewById(R.id.LvHITOKOTO);
				//ListviewにDBをセット
				this.setDBValuetoList(lstHitokoto);
				//選択行を忘れる
				this.selectedID = -1;
				this.lastPosition = -1;
			}
			else{
				//なければ、トースト（簡易メッセージ）を表示
				Toast.makeText(MaintenanceActivity.this,"削除する行を選択してください", Toast.LENGTH_SHORT).show();
			}
		break;
		case R.id.modoru://戻るボタンが押された
			//今の画面Activityを消して、前の画面Activityに戻る

			finish();
			break;
		}
	}

	/**
	 * 引数のListViewにDBのデータセット
	 * @param lstHitokoto 対象となるListView
	 */
	private void setDBValuetoList(ListView lstHitokoto) {

		SQLiteCursor cursor = null;

		//クラスのフィールドの変数がNULLなら、データベース空間オープン
		if (sdb == null) {
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try {
			sdb = helper.getWritableDatabase();
		} catch (SQLiteException e) {
			//異常終了
			Log.e("ERROR", e.toString());
		}
		//MySQLiteOphenHelperにSELECT文を実行させて結果のカーソルを受け取る
		cursor = this.helper.selectHitokotoList(sdb);

		// dblayout: ListViewにさらにレイアウトを指定するもの
		int db_layout = android.R.layout.simple_list_item_activated_1;
		//from:カーソルからListviewに指定するカラムの値を指定するもの
		String[] from = { "phrase" };
		//to:Listviewの中に指定したdb_layoutに配置する、各行のview部品のid
		int[] to = new int[] { android.R.id.text1 };

		//ListViewにセットするアダプターを生成
		//カーソルをもとに、fromの列から、toのViewへ値のマッピングが行われる
		SimpleCursorAdapter adapter =
				new SimpleCursorAdapter(this, db_layout, cursor, from, to, 0);

		//アダプターを設定します
		lstHitokoto.setAdapter(adapter);
	}

	/**
	 * @param AdapterView<?> parent クリックしたListView
	 * @param View view クリックしたListViewの中の各行
	 * @param int position 何行目をクリックしたか
	 * @param long viewid 未使用
	 */
	public void onItemClick(AdapterView<?> parent, View view, int position, long viewid) {
		//前に選択中の行があれば、背景を透明にする
		if (this.selectedID != -1) {
			parent.getChildAt(this.lastPosition).setBackgroundColor(0);
		}
		//選択中の行の背景をグレーニする
		view.setBackgroundColor(android.graphics.Color.LTGRAY);

		//選択行のレコードを指示すカーソルを取得
		SQLiteCursor cursor = (SQLiteCursor) parent.getItemAtPosition(position);
		//カーソルのレコードから、「_id」を取得して記憶
		this.selectedID = cursor.getInt(cursor.getColumnIndex("_id"));
		//何行目を選択したかも記憶
		this.lastPosition = position;
	}
}
