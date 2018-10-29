package v2.trabalho.lfa.trabalho_lfav2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layoutEstados;
    private EditText etEstados;

    private LinearLayout layoutSimbolosEntrada;
    private EditText etSimbolosEntrada;

    private LinearLayout layoutSimbolosCompleto;
    private EditText etSimbolosCompleto;

    private LinearLayout layoutEstadosInicialFinal;
    private EditText etEstadoInicial;
    private EditText etEstadosFinais;

    private LinearLayout layoutFuncoes;
    private EditText etFuncoes;

    private Button btnValidarCampos;

    private LinearLayout layoutTestes;
    private EditText etPalavraTeste;
    private EditText etSaidaTeste;

    private Button btnTestarPalavra;

    private AlertDialog alerta;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createProgressDialog();

        layoutEstados = (LinearLayout) findViewById(R.id.layoutEstados);
        etEstados = (EditText) findViewById(R.id.etEstados);

        layoutSimbolosEntrada = (LinearLayout) findViewById(R.id.layoutSimbolosEntrada);
        etSimbolosEntrada = (EditText) findViewById(R.id.etSimbolosEntrada);

        layoutSimbolosCompleto = (LinearLayout) findViewById(R.id.layoutSimbolosCompleto);
        etSimbolosCompleto = (EditText) findViewById(R.id.etSimbolosCompleto);

        layoutEstadosInicialFinal = (LinearLayout) findViewById(R.id.layoutEstadosInicialFinal);
        etEstadoInicial = (EditText) findViewById(R.id.etEstadoInicial);
        etEstadosFinais = (EditText) findViewById(R.id.etEstadosFinais);

        layoutFuncoes = (LinearLayout) findViewById(R.id.layoutFuncoes);
        etFuncoes = (EditText) findViewById(R.id.etFuncoes);

        btnValidarCampos = (Button) findViewById(R.id.btnValidarCampos);

        layoutTestes = (LinearLayout) findViewById(R.id.layoutTestes);
        etPalavraTeste = (EditText) findViewById(R.id.etPalavraTeste);
        etSaidaTeste = (EditText) findViewById(R.id.etSaidaTeste);

        btnTestarPalavra = (Button) findViewById(R.id.btnTestarPalavra);

        btnTestarPalavra.setVisibility(View.GONE);
        layoutTestes.setVisibility(View.GONE);

        btnValidarCampos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (etEstados.getText().toString().trim().length() == 0) {
                    showMensagem(R.string.msg_estados_vazio);
                }else if (etEstados.getText().toString().trim().charAt(etEstados.getText().toString().trim().length()-1) == ',') {
                    showMensagem(R.string.msg_estados_invalido);
                }else if (etSimbolosEntrada.getText().toString().trim().length() == 0) {
                    showMensagem(R.string.msg_simbolos_entrada_vazio);
                }else if (etSimbolosEntrada.getText().toString().trim().charAt(etSimbolosEntrada.getText().toString().trim().length()-1) == ',') {
                    showMensagem(R.string.msg_simbolos_entrada_invalidos);
                }else if (etSimbolosCompleto.getText().toString().trim().length() == 0) {
                    showMensagem(R.string.msg_simbolos_completo_vazio);
                }else if (etSimbolosCompleto.getText().toString().trim().charAt(etSimbolosCompleto.getText().toString().trim().length()-1) == ',') {
                    showMensagem(R.string.msg_simbolos_completo_invalido);
                }else if (etEstadoInicial.getText().toString().trim().length() == 0) {
                    showMensagem(R.string.msg_estado_inicial_vazio);
                }else if (etEstadosFinais.getText().toString().trim().length() == 0) {
                    showMensagem(R.string.msg_estados_finais_vazio);
                }else if (etEstadosFinais.getText().toString().trim().charAt(etEstadosFinais.getText().toString().trim().length()-1) == ',') {
                    showMensagem(R.string.msg_estados_finais_invalidos);
                }else if (etFuncoes.getText().toString().trim().length() == 0) {
                    showMensagem(R.string.msg_funcoes_vazio);
                }else {
                    btnTestarPalavra.setVisibility(View.VISIBLE);
                    layoutTestes.setVisibility(View.VISIBLE);

                    TaskSendFields task = new TaskSendFields();
                    task.execute();
                }

            }

        });

    }

    @SuppressLint("StaticFieldLeak")
    private class TaskSendFields extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Gson gsonConvert = new Gson();
            try {
                String json = "{";
                json += "\"estados\":" + etEstados.getText().toString().trim() + "\",";
                json += "\"simbolosEntrada\":" + etSimbolosEntrada.getText().toString().trim() + "\",";
                json += "\"simbolosCompleto\":" + etSimbolosCompleto.getText().toString().trim() + "\",";
                json += "\"estadoInicial\":" + etEstadoInicial.getText().toString().trim() + "\",";
                json += "\"estadosFinais\":" + etEstadosFinais.getText().toString().trim() + "\",";
                json += "\"palavraTeste\":" + etEstadosFinais.getText().toString().trim() + "\",";
                json += "\"funcoes\":" + etFuncoes.getText().toString().trim() + "\"}";

                String responseString = MakeRequest.runWithPost(json , "http://127.0.0.1:8000/verify_mt/");

                final Retorno retorno = gsonConvert.fromJson(responseString, Retorno.class);

                if (retorno.getStatus() == 200) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            etSaidaTeste.setText(retorno.getSaida());
                        }
                    });

                }else {
                    showMensagem(R.string.erro_interno);
                }

            }catch (Exception e) {
                showMensagem(R.string.erro_interno);
                e.printStackTrace();
            }
            return null;
        }
    }

    void showMensagem(final int mensagem) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle(0);
                builder.setMessage(mensagem);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alerta.dismiss();
                    }
                });

                alerta = builder.create();
                alerta.show();

            }
        });

    }

    void createProgressDialog() {
        progress = new ProgressDialog(this);
        progress.setCanceledOnTouchOutside(true);
        progress.setCancelable(false);
        progress.setTitle("Carregando...");
    }

    void showProgressDialog() {
        if (!this.progress.isShowing()) {
            this.progress.show();
        }
    }

    void cancelProgressDialog() {
        if (this.progress.isShowing()) {
            this.progress.dismiss();
        }
    }

    private class Retorno {
        private int status;
        private int saida;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getSaida() {
            return saida;
        }

        public void setSaida(int saida) {
            this.saida = saida;
        }
    }
}
