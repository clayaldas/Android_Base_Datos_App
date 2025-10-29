package fisei.uta.edu.ec.android_base_datos_app;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AllProductsActivity extends AppCompatActivity {

    // para mostrar todos los datos de la tabla products
    private ListView listViewProducts;
    ArrayList<String> list;// mostrar los datos de los productos
    ArrayAdapter adapter;// auxuliar parea llenar el listiview

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_products);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listViewProducts = findViewById(R.id.listViewProducts);

        // llenar los datos con un metodo que retorna un ArrayList
        list = FillListView();

        // crear el adapter
        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        // vincular el adapter al ListView
        listViewProducts.setAdapter(adapter);
    }

    private ArrayList FillListView() {
        AdminPersistence persistence = new AdminPersistence(this, "SALES", null, 1);
        SQLiteDatabase database = persistence.getReadableDatabase();

        ArrayList<String> arrayList = new ArrayList<String>();
        String SELECT = "SELECT Code, Name, Quantity, Price " +
                "FROM Products";
        try
        {
            Cursor row = database.rawQuery(SELECT, null);

            //if ( row.moveToFirst()) {
                //Toast.makeText(this, "datos", Toast.LENGTH_SHORT).show();
                while (row.moveToNext()) {
                    arrayList.add(row.getString(0) + " " + row.getString(1) +
                            "  " + row.getString(2) + " " + row.getString(3));
                }
            //}
            // Cerrar el cursor
            row.close();
        }
        catch (Exception exception) {
            Toast.makeText(this, "Error de Base de Datos", Toast.LENGTH_SHORT).show();
        }
        finally {
            database.close();
        }
        return arrayList;
    }
}
