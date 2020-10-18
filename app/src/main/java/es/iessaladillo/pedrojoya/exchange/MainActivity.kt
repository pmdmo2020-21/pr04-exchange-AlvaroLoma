package es.iessaladillo.pedrojoya.exchange

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.exchange.databinding.MainActivityBinding


    class MainActivity : AppCompatActivity() {
        private lateinit var binding:MainActivityBinding
        private lateinit var textWatcher: TextWatcher
        private lateinit var fCurrency: Currency
        private lateinit var tCurrency: Currency
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding= MainActivityBinding.inflate(layoutInflater)
            setContentView(binding.root)
            setupView()
        }

        private fun setupView() {
            binding.botones1.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { _: RadioGroup, _: Int -> changeButtons1() })
            binding.botones2.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener{ _: RadioGroup, _: Int -> changeButtons2() })
            textWatcher=object :TextWatcher{
               override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               }

               override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               }
               override fun afterTextChanged(p0: Editable?) {
                   validCaracters()
                   checkText()
               }

           }
            binding.aValue.setOnEditorActionListener(TextView.OnEditorActionListener { _, _, _ ->imeOption()  })
            binding.ExchangeButton.setOnClickListener(View.OnClickListener {
                change()
            })
            binding.aValue.addTextChangedListener(textWatcher)
            binding.aValue.setOnClickListener {
                textStar()
            }
            binding.boton4.isEnabled = false
            binding.boton3.isEnabled = false
            fCurrency=Currency.DOLLAR
            tCurrency=Currency.POUND
            resetText()
        }

        private fun imeOption(): Boolean {
            SoftInputUtils.hideSoftKeyboard(binding.aValue)
            change()
            return true
        }
        
        private fun validCaracters() {
            if(binding.aValue.text.matches(Regex(pattern = "[0-9]*\\.[0-9]*\\."))){
                resetText()
            }
        }
        private fun changeButtons2() {

            if(binding.boton4.isChecked){
                binding.imagen.setImageResource(Currency.DOLLAR.drawableResId)
                binding.boton1.isEnabled = false
                binding.boton2.isEnabled = true
                binding.boton3.isEnabled = true
                tCurrency= Currency.DOLLAR
            }
            if(binding.boton5.isChecked){
                binding.imagen.setImageResource(Currency.EURO.drawableResId)
                binding.boton2.isEnabled = false
                binding.boton1.isEnabled = true
                binding.boton3.isEnabled = true
                tCurrency= Currency.EURO
            }
            if(binding.boton6.isChecked){
                binding.imagen.setImageResource(Currency.POUND.drawableResId)
                binding.boton3.isEnabled = false
                binding.boton1.isEnabled = true
                binding.boton2.isEnabled = true
                tCurrency= Currency.POUND
            }
        }
        private fun textStar() {
            binding.aValue.selectAll()
        }
        private fun change() {
            var numero:Double
            val toast:Toast
            if(fCurrency.symbol.equals(Currency.DOLLAR.symbol)){
               numero= tCurrency.toDollar(binding.aValue.text.toString().toDouble())
               toast=Toast.makeText(this, "${binding.aValue.text}${fCurrency.symbol} = $numero${tCurrency.symbol}",Toast.LENGTH_SHORT)

            }else{
                numero= fCurrency.fromDollar(binding.aValue.text.toString().toDouble())
                numero= tCurrency.toDollar(numero)
                toast=Toast.makeText(this, "${binding.aValue.text}${fCurrency.symbol} = ${String.format("%.2f",numero)}${tCurrency.symbol}",Toast.LENGTH_SHORT)

            }
            toast.show()
        }
        private fun resetText(){

            binding.aValue.setText("0")
            binding.aValue.requestFocus()
            binding.aValue.selectAll()
        }
        private fun checkText() {
            if(binding.aValue.length()<=0||binding.aValue.text.matches(Regex(pattern = "\\."))){
                resetText()
            }

        }
        private fun changeButtons1( ) {


            if(binding.boton1.isChecked){
                binding.imagen2.setImageResource(Currency.DOLLAR.drawableResId)
                binding.boton4.isEnabled = false
                binding.boton5.isEnabled = true
                binding.boton6.isEnabled = true
                fCurrency=Currency.DOLLAR
            }
            if(binding.boton2.isChecked){
                binding.imagen2.setImageResource(Currency.EURO.drawableResId)
                binding.boton5.isEnabled = false
                binding.boton4.isEnabled = true
                binding.boton6.isEnabled = true
                fCurrency=Currency.EURO
            }
            if(binding.boton3.isChecked){
                binding.imagen2.setImageResource(Currency.POUND.drawableResId)
                binding.boton6.isEnabled = false
                binding.boton5.isEnabled = true
                binding.boton4.isEnabled = true
                fCurrency=Currency.POUND
            }


        }


    }

