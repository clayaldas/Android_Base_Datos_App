package fisei.uta.edu.ec.android_base_datos_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminPersistence extends SQLiteOpenHelper {

    public AdminPersistence(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Crear los objetos de base datos (Tablas, Vistas, SP, Triggers)
    // Alimentar los datos para las tablas
    // INSERT....
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Crear los objetos de la BD
        //String CREATE_TABLE = "create table "
        String CREATE_TABLE = "CREATE TABLE Products " +
                              "(Code INTEGER PRIMARY KEY AUTOINCREMENT, " +
                              "Name TEXT, Quantity INTEGER, Price REAL)";
        // crear la tabla
        sqLiteDatabase.execSQL(CREATE_TABLE);
        //sqLiteDatabase.execSQL("INSERT INTO Products....");
    }

    // para actulizar algun objeto de la base de datos(Tablas, Vistas, SP, Triggers)|
    // public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL(CREATE_TABLE);
    }
}
