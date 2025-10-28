package fisei.uta.edu.ec.android_base_datos_app;

// Proyecto con Base de Datos
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.VibrationAttributes;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextCode;
    private  EditText editTextName;
    private  EditText editTextQuantity;
    private  EditText editTextPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // obtener las referencias de los controles (views) del Layout
        editTextCode = findViewById(R.id.editTextCode);
        editTextName = findViewById(R.id.editTextName);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        editTextPrice = findViewById(R.id.editTextPrice);
    }

    public void clicButtonInsert(View view) {
        // Crear la BD si no esta creada
        AdminPersistence persistence = new AdminPersistence(this, "SALES",
                null, 1);

        // Abrir la BD para escritura (INSERT)-Open
        SQLiteDatabase database = persistence.getWritableDatabase();

        try {
            // Obtener los datos para el INSERT
            //String code = editTextCode.getText().toString();
            String name = editTextName.getText().toString();
            String quantity = editTextQuantity.getText().toString();
            String price = editTextPrice.getText().toString();

            // INSERTAR DATOS DIRECTOS SIN PARAMETROS
            //database.execSQL("INSERT INTO Products (Name, Quantity, Price) VALUES ('Portatil', 1, 1500.50)");
            // SQL Inyection
            //database.execSQL("INSERT INTO Products (Name, Quantity, Price) VALUES ('"+name+"', "+quantity+", "+price+")");

            // Codigo recomendado para evitar SQL Inyection
            ContentValues values = new ContentValues();
            values.put("Name", name);
            values.put("Quantity", quantity);
            values.put("Price", price);
            // insertar el registro en la BD
            database.insert("Products", null, values);

            cleanControls();

            Toast.makeText(this, "Registro Insertado", Toast.LENGTH_SHORT).show();
        }
        catch (Exception exception) {
            Toast.makeText(this, "Error al Insertar", Toast.LENGTH_SHORT).show();
        }
        finally {
            // cerrar la base de datos
            database.close();
        }
    }

    // limpiar los controles
    private void cleanControls() {
        editTextCode.setText("");
        editTextName.setText("");
        editTextQuantity.setText("");
        editTextPrice.setText("");
    }

    public void clicButtonSelectById(View view) {
        // Crear la BD si no esta creada
        AdminPersistence persistence = new AdminPersistence(this, "SALES",
                null, 1);

        // Abrir la BD para lectura (SELECT)-Open
        SQLiteDatabase database = persistence.getReadableDatabase();

        try {
            String code = editTextCode.getText().toString();

            // Utilizar un cursor para recuperar el primer registro de la consulta (SELECT)
            String SELECTBYCODE = "SELECT Name, Quantity, Price " +
                                   "FROM Products " +
                                   "WHERE Code=";
            Cursor row = database.rawQuery(SELECTBYCODE + code, null);

            // comprobar si hay un registro recuperado
            if (row.moveToFirst()) {
                editTextName.setText(row.getString(0));
                editTextQuantity.setText(row.getString(1));
                editTextPrice.setText(row.getString(2));
            }
            else {
                Toast.makeText(this, "El registro indicado no existe",
                        Toast.LENGTH_SHORT).show();
                cleanControls();
            }
        }
        catch (Exception exception) {
            Toast.makeText(this, "Error de Base de Datos",
                    Toast.LENGTH_SHORT).show();
        }
        finally {
            database.close();
        }
    }
    public void clicButtonUpdate(View view) {
        // Crear la BD si no esta creada
        AdminPersistence persistence = new AdminPersistence(this, "SALES",
                null, 1);

        // Abrir la BD para escritura (SELECT)-Open
        SQLiteDatabase database = persistence.getWritableDatabase();

        try {
            // Obtener los datos para el UPDATE
            String code = editTextCode.getText().toString();
            String name = editTextName.getText().toString();
            String quantity = editTextQuantity.getText().toString();
            String price = editTextPrice.getText().toString();

            // Codigo recomendado para evitar SQL Inyection
            ContentValues values = new ContentValues();
            values.put("Name", name);
            values.put("Quantity", quantity);
            values.put("Price", price);
            // para el WHERE
            values.put("Code", code);

            //database.execSQL("UPDATE....");
            int count = database.update("Products", values,
                    "Code=" + code, null);

            if (count > 0) {
                Toast.makeText(this, "Registro actualizado",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "El codigo no existe",
                        Toast.LENGTH_SHORT).show();
            }
            cleanControls();
        }
        catch (Exception exception) {
            Toast.makeText(this, "Error de Base de Datos",
                    Toast.LENGTH_SHORT).show();
        }
        finally {
            database.close();
        }
    }
    public void clicButtonDelete(View view) {
        // Crear la BD si no esta creada
        AdminPersistence persistence = new AdminPersistence(this, "SALES",
                null, 1);

        // Abrir la BD para escritura (SELECT)-Open
        SQLiteDatabase database = persistence.getWritableDatabase();

        try {
            String code = editTextCode.getText().toString();

            int count = database.delete("Products", "Code=" + code, null);

            if (count > 0) {
                Toast.makeText(this, "Registro eliminado",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "El codigo no existe",
                        Toast.LENGTH_SHORT).show();
            }
            cleanControls();
        }
        catch (Exception exception) {
            Toast.makeText(this, "Error de Base de Datos",
                    Toast.LENGTH_SHORT).show();
        }
        finally {
            database.close();
        }
    }
    public void clicButtonSelectAll(View view) {
        Intent intent = new Intent(this, AllProductsActivity.class);
        startActivity(intent);
    }
}