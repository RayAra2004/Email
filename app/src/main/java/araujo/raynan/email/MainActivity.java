package araujo.raynan.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Capturando os dados digitados{
                EditText etEmail = findViewById(R.id.etEmail);
                String email = etEmail.getText().toString();

                EditText etAssunto = findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString();

                EditText etTexto = findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString();
                //}
                //Criando uma intent implícita
                Intent i = new Intent(Intent.ACTION_SENDTO);

                //Informando quais tipos de app estamos interessados
                i.setData(Uri.parse("mailto:"));

                //Preenchendo o intent com os dados capturados
                String[] emails = new String[] {email};
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                //Tentando executar o Intent, se der certo você irá escolher um app
                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                }
                //caso haja um erro o catch será executado
                catch (ActivityNotFoundException e){
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso realizar essa operação", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}