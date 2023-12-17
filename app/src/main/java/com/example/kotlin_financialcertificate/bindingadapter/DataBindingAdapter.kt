package com.example.kotlin_financialcertificate.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.kotlin_financialcertificate.R

@BindingAdapter(value=["code_text","code_index"])
fun ImageView.setPin(codeText:CharSequence?, index:Int){
    if(codeText!=null){
        if(codeText.length>index){
            setImageResource(R.drawable.baseline_circle_24)
        }else{
            setImageResource(R.drawable.baseline_circle_gray)
        }
    }
}