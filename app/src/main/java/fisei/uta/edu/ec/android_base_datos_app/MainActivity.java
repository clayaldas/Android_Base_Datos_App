package fisei.uta.edu.ec.android_base_datos_app;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        AdminPersistence persistence = new AdminPersistence(this, "productsdb",
                null, 1);

    }
    public void clicButtonSelectById(View view) {

    }
    public void clicButtonUpdate(View view) {

    }
    public void clicButtonDelete(View view) {

    }
    public void clicButtonSelectAll(View view) {

    }
}