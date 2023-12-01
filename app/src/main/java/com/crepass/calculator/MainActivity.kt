package com.crepass.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.crepass.calculator.databinding.ActivityMainBinding
import java.lang.StringBuilder
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val firstNumber=StringBuilder("")
    private val secondNumber=StringBuilder("")
    private val operator=StringBuilder("")
    private val decimal=DecimalFormat("#,###")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    fun numberClick(view : View){
        val numberString=(view as? Button)?.text?.toString()?:""
        val numberText=if(operator.isEmpty()) firstNumber else secondNumber

        numberText.append(numberString)

        update()
    }
    fun clearClick(view: View){
        firstNumber.clear()
        secondNumber.clear()
        operator.clear()
        binding.View.text=""
        update()
    }
    fun equaClick(view: View){
        if(firstNumber.isEmpty()||secondNumber.isEmpty()||operator.isEmpty()){
            Toast.makeText(this,"잘못된 수식입니다",Toast.LENGTH_SHORT).show()
            return
        }
        val firstS=firstNumber.toString().toBigDecimal()
        val secondS=secondNumber.toString().toBigDecimal()

        val result=when(operator.toString()){
            "+"->firstS+secondS
            "-"->firstS-secondS
            else->{
                "잘못된 수식입니다."
            }
        }.toString()
        binding.View.text=result

    }
    fun operator(view: View){
        val operatorString=(view as? Button)?.text?.toString()?:""
        if(firstNumber.isEmpty()){
            Toast.makeText(this,"먼저 숫자를 입력해주세요",Toast.LENGTH_SHORT).show()
            return
        }

        if(secondNumber.isNotEmpty()){
            Toast.makeText(this,"1개의 연산자에 대해서만 연산이 가능합니다",Toast.LENGTH_SHORT).show()
            return
        }
        if(operator.isNotEmpty()){
            Toast.makeText(this,"1개의 연산자에 대해서만 연산이 가능합니다",Toast.LENGTH_SHORT).show()
            return
        }
        operator.append(operatorString)

        update()

    }

    private fun update(){
        val firstFormat=if(firstNumber.isNotEmpty())decimal.format(firstNumber.toString().toBigDecimal()) else ""
        val secondFormat=if(secondNumber.isNotEmpty())decimal.format(secondNumber.toString().toBigDecimal()) else ""
        binding.equ.text="$firstFormat $operator $secondFormat"
    }

}