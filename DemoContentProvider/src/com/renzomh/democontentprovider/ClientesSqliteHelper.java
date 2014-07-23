package com.renzomh.democontentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ClientesSqliteHelper extends SQLiteOpenHelper{

	String sqlCreate = 	"CREATE TABLE Clientes " + 
						"(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
						"nombre TEXT, " +
						"telefono TEXT, " +
						"email TEXT)";
	
	public ClientesSqliteHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		db.execSQL(sqlCreate);
		
		for(int i=1; i<=15; i++)
        {
            //Generamos los datos de muestra
            String nombre = "Cliente" + i;
            String telefono = "900-123-00" + i;
            String email = "email" + i + "@mail.com";
 
            //Insertamos los datos en la tabla Clientes
            db.execSQL("INSERT INTO Clientes (nombre, telefono, email) " +
                       "VALUES ('" + nombre + "', '" + telefono +"', '" + email + "')");
        }
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		//Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Clientes");
 
        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
	}

}
