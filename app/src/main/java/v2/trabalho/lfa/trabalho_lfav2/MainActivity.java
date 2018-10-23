package v2.trabalho.lfa.trabalho_lfav2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layoutEstados;
    private EditText etEstados;

    private LinearLayout layoutSimbolosEntrada;
    private EditText etSimbolosEntrada;

    private LinearLayout layoutSimbolosCompleto;
    private EditText etSimbolosCompleto;

    private LinearLayout layoutEstadosInicialFinal;
    private EditText etEstadoInicial;
    private EditText etEstadoFinal;

    private LinearLayout layoutFuncoes;
    private EditText etFuncoes;

    private Button btnValidarCampos;

    private LinearLayout layoutTestes;
    private EditText etPalavraTeste;
    private EditText etSaidaTeste;

    private Button btnTestarPalavra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutEstados = (LinearLayout) findViewById(R.id.layoutEstados);
        etEstados = (EditText) findViewById(R.id.etEstados);

        layoutSimbolosEntrada = (LinearLayout) findViewById(R.id.layoutSimbolosEntrada);
        etSimbolosEntrada = (EditText) findViewById(R.id.etSimbolosEntrada);

        layoutSimbolosCompleto = (LinearLayout) findViewById(R.id.layoutSimbolosCompleto);
        etSimbolosCompleto = (EditText) findViewById(R.id.etSimbolosCompleto);

        layoutEstadosInicialFinal = (LinearLayout) findViewById(R.id.layoutEstadosInicialFinal);
        etEstadoInicial = (EditText) findViewById(R.id.etEstadoInicial);
        etEstadoFinal = (EditText) findViewById(R.id.etEstadoFinal);

        layoutFuncoes = (LinearLayout) findViewById(R.id.layoutFuncoes);
        etFuncoes = (EditText) findViewById(R.id.etFuncoes);

        btnValidarCampos = (Button) findViewById(R.id.btnValidarCampos);

        layoutTestes = (LinearLayout) findViewById(R.id.layoutTestes);
        etPalavraTeste = (EditText) findViewById(R.id.etPalavraTeste);
        etSaidaTeste = (EditText) findViewById(R.id.etSaidaTeste);

        btnTestarPalavra = (Button) findViewById(R.id.btnTestarPalavra);

    }

    private class TaskSendFields extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
