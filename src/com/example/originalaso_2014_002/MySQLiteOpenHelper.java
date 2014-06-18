package com.example.originalaso_2014_002;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

	/**
	 * @param context 呼び出しコンテクスト
	 * @param name 利用DB名
	 * @param factory カーソルファクトリー
	 * @param version DBバージョン
	 */
	public MySQLiteOpenHelper(Context context) {

		super(context, "20140021201791.sqlite3", null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS" +
				"HItokoto (_id INTEGER PRIMSRY KEY AUTOINCREMENT NOT NULL , phrase TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVerdion, int newVersion) {
		db.execSQL("drop table Hitokoto");
		onCreate(db);
	}

	/**
	 * 引数のフレーズをHitokotoテーブルにインサートするプライベートメソッド
	 * @paran　SQLiteDatabase　インサート先DBのインスタンス変数
	 * @param　inputMsg　インサートするメッセージ
	 */

	public void insertHitokoto(SQLiteDatabase db, String inputMsg) {

		String sqlstr = " insert ito Hitokoto (phrase) values('" + inputMsg + "');";
		try {
			//トランザクション開始
			db.beginTransaction();
			db.execSQL(sqlstr);
			//トランザクション成功
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Log.e("ERROR", e.toString());
		} finally {
			//トランザクション終了
			db.endTransaction();
		}
		return;
	}

	public String selectRandomHitokoto(SQLiteDatabase db){

		String rtString = null;

		String sqlstr = " SELECT _id, phrase FROM Hitokoto ORDER BY RANDOM();";
				try {
					//トランザクション開始
					SQLiteCursor cursor = (SQLiteCursor)db.rawQuery(sqlstr, null);
					if(cursor.getCount()!=0){
						//カーソル開始位置を先頭にする
						cursor.moveToFirst();
						rtString = cursor.getString(1);
					}
					cursor.close();
				} catch (SQLException e){
					Log.e("ERROR" , e.toString());
				}finally{
					//既にカーソルもcloseしてあるので、何もしない
				}
		return rtString;
	}
}
