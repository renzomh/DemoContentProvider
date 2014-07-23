package com.renzomh.democontentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

public class ClientesProvider extends ContentProvider{

	private static final String uri = "content://com.renzomh.democontentprovider/clientes";
	public static final Uri CONTENT_URI = Uri.parse(uri);
	
	private ClientesSqliteHelper clidbh;
	private static final String DB_NOMBRE = "DBClientes";
	private static final int DB_VERSION = 1;
	private static final String TABLA_CLIENTES = "Clientes";
	
	private static final int CLIENTES = 1;
	private static final int CLIENTES_ID = 2;
	private static final UriMatcher uriMatcher;
	
	static{
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("com.renzomh.democontentprovider", "clientes", CLIENTES);
	    uriMatcher.addURI("com.renzomh.democontentprovider", "clientes/#", CLIENTES_ID);
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub

		int cont;
	    String where = selection;
	    
	    if(uriMatcher.match(uri) == CLIENTES_ID){
	            where = "_id=" + uri.getLastPathSegment();
	        }
	 
	    SQLiteDatabase db = clidbh.getWritableDatabase();
	 
	    cont = db.delete(TABLA_CLIENTES, where, selectionArgs);
	 
	    return cont;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub

		int match = uriMatcher.match(uri);
		 
	    switch (match)
	    {
	        case CLIENTES:
	            return "vnd.android.cursor.dir/vnd.sgoliver.cliente";
	        case CLIENTES_ID:
	            return "vnd.android.cursor.item/vnd.sgoliver.cliente";
	        default:
	            return null;
	    }
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub

		long regId = 1;
		 
	    SQLiteDatabase db = clidbh.getWritableDatabase();
	 
	    regId = db.insert(TABLA_CLIENTES, null, values);
	 
	    Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);
	 
	    return newUri;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		
		clidbh = new ClientesSqliteHelper(getContext(), DB_NOMBRE, null, DB_VERSION);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		
		String where = selection;
		
		if(uriMatcher.match(uri) == CLIENTES_ID){
			where = "_id=" + uri.getLastPathSegment();
		}
		
		SQLiteDatabase db = clidbh.getWritableDatabase();
		
		Cursor c = db.query(TABLA_CLIENTES, projection, where, selectionArgs, null, null, sortOrder);
		
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		int cont;
	    String where = selection;
	    
	    if(uriMatcher.match(uri) == CLIENTES_ID){
	            where = "_id=" + uri.getLastPathSegment();
	        }
	 
	    SQLiteDatabase db = clidbh.getWritableDatabase();
	 
	    cont = db.update(TABLA_CLIENTES, values, where, selectionArgs);
	 
	    return cont;
	}

	public static final class Clientes implements BaseColumns{
		
		private Clientes(){
		}
		
		public static final String COL_NOMBRE = "nombre";
		public static final String COL_TELEFONO = "telefono";
		public static final String COL_EMAIL = "email";
	}
}
