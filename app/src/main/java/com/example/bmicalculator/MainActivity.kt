package com.example.bmicalculator

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val weight=findViewById<EditText>(R.id.n1)
        val height=findViewById<EditText>(R.id.n2)
        val b1=findViewById<Button>(R.id.b1)

        b1.setOnClickListener {
            val w=weight.text.toString()
            val h=height.text.toString()
            if(validate(w,h)){
            var result=w.toFloat()/((h.toFloat()/100)*(h.toFloat()/100))
            result=String.format("%.2f",result).toFloat()

            displayResult(result)
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun validate(weight:String?,height:String?):Boolean{
        return when{
            weight.isNullOrEmpty()->{     //if weight was float thn weight==0.0f
                Toast.makeText(this,"Please put weight",Toast.LENGTH_SHORT).show()
                 false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this,"Please put Height",Toast.LENGTH_SHORT).show()
                 false
            }
            else->{
                 true
            }
        }
    }

    private fun displayResult(result:Float){
        val resultIndex=findViewById<TextView>(R.id.bmi)
        val resultDes=findViewById<TextView>(R.id.tvresult)
        val info=findViewById<TextView>(R.id.tv1)

        resultIndex.text=result.toString()
        info.text="Normal range is 18.5 - 24.9"

        var resultText=" "
        var color=0

        when{
            result<18.5 ->{
                resultText="underweight"
                color=R.color.underweight
            }
            result in 18.50..24.99->{
                resultText="healthy!!"
                color=R.color.normal
            }
            result in 25.00..29.99->{
                resultText="overweight"
                color=R.color.Overweight
            }
            result>30->{
                resultText="obese!!!"
                color=R.color.Obese
            }
        }

        resultDes.setTextColor(ContextCompat.getColor(this,color))
        resultDes.text=resultText


    }
}