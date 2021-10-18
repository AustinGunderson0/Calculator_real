package com.example.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder
import android.view.View
import android.widget.Button
import android.widget.TextView



class MainActivity : AppCompatActivity() {

    lateinit var txtInput: TextView
    var numberInput: Boolean = false
    var decimalInput :Boolean = false
    var invalidInput: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtInput = findViewById(R.id.text)
    }

    fun onNumber(view: View) {
        if(invalidInput)
        {
            txtInput.text=(view as Button).text
            invalidInput=false
        } else {
            txtInput.append((view as Button).text)
        }
        numberInput=true
    }

    fun onDecimal() {
        if(numberInput && !invalidInput && !decimalInput)
        {
            txtInput.append(".")
            numberInput=false
            decimalInput=true
        }
    }

    fun onDelete() {
        this.txtInput.text= ""
        numberInput=false
        invalidInput=false
        decimalInput=false
    }

    fun onOperator (view: View) {
        if(numberInput && !invalidInput)
        {
            txtInput.append((view as Button).text)
            numberInput=false
            decimalInput=false
        }
    }

    fun onEqual() {
        if(numberInput && !invalidInput)
        {
            val text = txtInput.text.toString()
            val expression = ExpressionBuilder(text).build()
            try {
                val result= expression.evaluate()
                txtInput.text= result.toString()
                decimalInput=true
            }catch (ex:Exception) {
                txtInput.text="Error"
                invalidInput=true
                numberInput=false
            }
        }
    }
}
