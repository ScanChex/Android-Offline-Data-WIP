package com.scanchex.database;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "scanchexdb.db";

	// Contacts table name
	private static final String TABLE_TRADES = "trades";

	// Contacts Table Columns names
	private static final String KEY_ID = "TradeId";
	private static final String KEY_SYMBOL = "Symbol";
	private static final String KEY_BUYPRICE = "BuyPrice";
	private static final String KEY_BUYIMAGE = "BuyImage";
	private static final String KEY_BUYCOMMENT = "BuyComment";
	private static final String KEY_BUYDATETIME = "BuyDateTime";
	private static final String KEY_QUANTITY = "Quantity";
	private static final String KEY_SALEPRICE = "SalePrice";
	private static final String KEY_SALEIMAGE = "SaleImage";
	private static final String KEY_SALECOMMENT = "SaleComment";
	private static final String KEY_SELLDATETIME = "SellDateTime";
	private static final String KEY_RATING = "Rating";
	private static final String KEY_ISACTIVETRADE = "IsActiveTrade";
	private static final String KEY_TOTALAMOUNT = "TotalAmount";
	private static final String KEY_STOP = "Stop";
	// private Activity context;

	private float commission = 0.0f;
	ArrayList<TradeProperties> trades_list = new ArrayList<TradeProperties>();
	public static final String MY_PREFS = "SharedPreferences";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		SharedPreferences prefs = context.getSharedPreferences(MY_PREFS, 0);
		commission = prefs.getFloat("CommissionPrice", (float) 20.0);
		// Log.i("DBHelper", "commission: " + commission);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TRADES + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_SYMBOL + " TEXT,"
				+ KEY_BUYPRICE + " FLOAT," + KEY_BUYIMAGE + " TEXT,"
				+ KEY_BUYCOMMENT + " TEXT," + KEY_BUYDATETIME + " DATETIME,"
				+ KEY_QUANTITY + " INTEGER," + KEY_SALEPRICE + " FLOAT,"
				+ KEY_SALEIMAGE + " TEXT," + KEY_SALECOMMENT + " TEXT,"
				+ KEY_SELLDATETIME + " DATETIME," + KEY_RATING + " INTEGER,"
				+ KEY_ISACTIVETRADE + " BOOLEAN," + KEY_TOTALAMOUNT
				+ " DOUBLE," + KEY_STOP + " FLOAT" + ")";

		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		//db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRADES);

		// Create tables again
		//onCreate(db);
	}

	public long addTrades(TradeProperties trade) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_SYMBOL, trade.getSymbol());
		values.put(KEY_BUYPRICE, trade.getBuyPrice());
		values.put(KEY_BUYIMAGE, trade.getBuyImage());
		values.put(KEY_BUYCOMMENT, trade.getBuyComment());
		values.put(KEY_BUYDATETIME, trade.getBuyDateTime());
		values.put(KEY_QUANTITY, trade.getQuantity());
		values.put(KEY_ISACTIVETRADE, trade.isActiveTrade());
		values.put(KEY_TOTALAMOUNT, trade.getTotalAmount());
		values.put(KEY_STOP, trade.getStop());
		// Inserting Row
		long success = db.insert(TABLE_TRADES, null, values);

		Log.i("Open Trade", "success: " + success);
		db.close();

		return success;

	}

	public int updateTrade(TradeProperties trade) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(KEY_SALEPRICE, trade.getSalePrice());
		values.put(KEY_SALEIMAGE, trade.getSaleImage());
		values.put(KEY_SALECOMMENT, trade.getSaleComment());
		values.put(KEY_SELLDATETIME, trade.getSellDateTime());
		values.put(KEY_RATING, trade.getRating());
		values.put(KEY_ISACTIVETRADE, trade.isActiveTrade());

		Log.i("CloseTrade", "tradeId" + trade.getTradeId());

		// updating row
		int result = db.update(TABLE_TRADES, values, KEY_ID + " = ?",
				new String[] { String.valueOf(trade.getTradeId()) });

		db.close();

		return result;

	}

	public int updateImage(String imageString, int tradeId, int updateImage) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		if (updateImage == 1) {

			values.put(KEY_BUYIMAGE, imageString);
		} else {

			values.put(KEY_SALEIMAGE, imageString);
		}

		int result = db.update(TABLE_TRADES, values, KEY_ID + " = ?",
				new String[] { String.valueOf(tradeId) });

		db.close();

		return result;
	}

	public void updateStop(float stop, int tradeId) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(KEY_STOP, stop);

		db.update(TABLE_TRADES, values, KEY_ID + " = ?",
				new String[] { String.valueOf(tradeId) });

		db.close();

	}

	// delete row
	public int deleteTrade(int tradeId) {

		SQLiteDatabase db = this.getWritableDatabase();
		int result = db.delete(TABLE_TRADES, KEY_ID + " = ?",
				new String[] { String.valueOf(tradeId) });
		db.close();

		return result;
	}

	// Getting single Trades
	public TradeProperties getTradeDetails(int tradeId) {

		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_TRADES + " WHERE "
				+ KEY_ID + " = " + tradeId;

		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();

		TradeProperties trade = new TradeProperties();

		trade.setTradeId(cursor.getInt(0));
		trade.setSymbol(cursor.getString(1));
		trade.setBuyPrice(cursor.getFloat(2));
		trade.setBuyImage(cursor.getString(3));
		trade.setBuyComment(cursor.getString(4));
		trade.setBuyDateTime(cursor.getString(5));
		trade.setQuantity(cursor.getInt(6));
		trade.setSalePrice(cursor.getFloat(7));
		trade.setSaleImage(cursor.getString(8));
		trade.setSaleComment(cursor.getString(9));
		trade.setSellDateTime(cursor.getString(10));
		trade.setRating(cursor.getFloat(11));

		int isActiveTrade = cursor.getInt(12);
		boolean isActive;
		if (isActiveTrade == 1) {

			isActive = true;

		} else {

			isActive = false;
		}

		trade.setIsActiveTrade(isActive);
		trade.setTotalAmount(cursor.getFloat(13));
		trade.setStop(cursor.getFloat(14));

		float buyPrice = cursor.getFloat(2);
		int quantity = cursor.getInt(6);
		float salePrice = cursor.getFloat(7);
		double totalInvested = cursor.getFloat(13);

		Log.i("DBHelper", "commission: " + commission);
		float gainOrLossPrice = (((salePrice - buyPrice) * quantity) - commission);
		trade.setWinOrLoss(gainOrLossPrice);

		float percentage = (float) ((gainOrLossPrice / totalInvested) * 100);
		trade.setPercentage(percentage);

		cursor.close();
		db.close();

		return trade;
	}

	// Getting All Trades
	public ArrayList<TradeProperties> getAllTrades() {
		try {
			trades_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_TRADES;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					TradeProperties trade = new TradeProperties();

					trade.setTradeId(cursor.getInt(0));
					trade.setSymbol(cursor.getString(1));
					trade.setBuyPrice(cursor.getFloat(2));
					trade.setBuyImage(cursor.getString(3));
					trade.setBuyComment(cursor.getString(4));
					trade.setBuyDateTime(cursor.getString(5));
					trade.setQuantity(cursor.getInt(6));
					trade.setSalePrice(cursor.getFloat(7));
					trade.setSaleImage(cursor.getString(8));
					trade.setSaleComment(cursor.getString(9));
					trade.setSellDateTime(cursor.getString(10));
					trade.setRating(cursor.getFloat(11));

					int isActiveTrade = cursor.getInt(12);
					boolean isActive;
					if (isActiveTrade == 1) {

						isActive = true;

					} else {

						isActive = false;
					}

					trade.setIsActiveTrade(isActive);
					trade.setTotalAmount(cursor.getFloat(13));
					trade.setStop(cursor.getFloat(14));

					float buyPrice = cursor.getFloat(2);
					int quantity = cursor.getInt(6);
					float salePrice = cursor.getFloat(7);
					double totalInvested = cursor.getFloat(13);

					Log.i("DBHelper", "commission: " + commission);
					float gainOrLossPrice = (((salePrice - buyPrice) * quantity) - commission);
					trade.setWinOrLoss(gainOrLossPrice);

					float percentage = (float) ((gainOrLossPrice / totalInvested) * 100);
					trade.setPercentage(percentage);

					trades_list.add(trade);
				} while (cursor.moveToNext());
			}

			cursor.close();
			db.close();
			// return trades_list;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("all_contact", "" + e);
		}

		return trades_list;
	}
}
