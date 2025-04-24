package br.edu.utfpr.calculaimc_pm45s_2025_1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var etPeso : EditText
    private lateinit var etAltura : EditText
    private lateinit var tvResultado : TextView
    private lateinit var btCalcular : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPeso = findViewById( R.id.etPeso )
        etAltura = findViewById( R.id.etAltura )
        tvResultado = findViewById( R.id.tvResultado )
        btCalcular = findViewById( R.id.btCalcular )

        btCalcular.setOnClickListener {
            btCalcularOnClick()
        }

        btCalcular.setOnLongClickListener{
            Toast.makeText( this, getString(R.string.msg_click_long), Toast.LENGTH_LONG ).show()
            false
        }

    } //fim do onCreate()

    public fun btLimparOnClick( view : View ) {
        etPeso.setText( "" )
        etAltura.setText( "" )
        tvResultado.setText( getString( R.string.zeros ) )
        etPeso.requestFocus()
    }

    private fun btCalcularOnClick() {

        if ( etPeso.text.toString().equals( "" ) ) {
            etPeso.error = "Campo peso deve ser preenchido"
            return
        }


        if ( etAltura.text.toString().equals( "" ) ) {
            etAltura.error = "Campo Altura deve ser preenchido"
            return
        }


        val peso = etPeso.text.toString().toDouble()
        val altura = etAltura.text.toString().toDouble()

        var imc = 0.0

        imc = calculaIMC(
            peso,
            altura,
            Locale.getDefault().language
        )

        val numberFormat = NumberFormat.getNumberInstance( Locale.getDefault() )
        val df = numberFormat as DecimalFormat
        tvResultado.text = df.format( imc )
    }

    companion object {

        fun calculaIMC( peso : Double, altura : Double, locale : String ) : Double {
            if ( locale.equals( "en") ) {
                return 703 * ( peso / altura.pow( 2 ) )
            } else {
                return peso / altura.pow( 2 )
            }
        } //fim do calculaIMC

    } // fim do companion object

} // fim da MainActivity