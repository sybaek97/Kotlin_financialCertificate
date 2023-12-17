package com.example.kotlin_financialcertificate

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.example.kotlin_financialcertificate.databinding.ActivityIdentityInputBinding
import com.example.kotlin_financialcertificate.util.ViewUtil.hideKeyboard
import com.example.kotlin_financialcertificate.util.ViewUtil.setOnEditorActionListener
import com.example.kotlin_financialcertificate.util.ViewUtil.showKeyboard
import com.example.kotlin_financialcertificate.util.ViewUtil.showKeyboardDelay

class IdentityInputActivicy : AppCompatActivity() {


    private lateinit var binding: ActivityIdentityInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        binding.nameEdit.showKeyboardDelay()
    }

    private fun initView() {
        with(binding) {

            nameEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT) {
                if(validName()){
                    nameLayout.error=null
                    if(phoneLayout.isVisible){
                        confirmButton.isVisible=true
                    }else{
                        birthdayLayout.isVisible = true
                        birthdayEdit.showKeyboard()
                    }
                }else{
                    confirmButton.isVisible=false
                    nameLayout.error="1자 이상의 한글을 입력해주세요"
                }
            }

            birthdayEdit.doAfterTextChanged {
                if (birthdayEdit.length() > 7) {
                    if(validbirthday()){
                        birthdayLayout.error=null
                        if(phoneLayout.isVisible){
                            confirmButton.isVisible=true
                        }else{
                            genderLayout.isVisible=true
                            birthdayEdit.hideKeyboard()
                        }
                    }else{
                        confirmButton.isVisible=false
                        binding.birthdayLayout.error="생년월일 형식이 다릅니다."
                    }
                }
            }
            birthdayEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE){
                val isvalid=validbirthday()&&birthdayEdit.length()>7
                if(isvalid){
                    confirmButton.isVisible=phoneLayout.isVisible
                    birthdayLayout.error=null
                }else{
                    binding.birthdayLayout.error="생년월일 형식이 다릅니다."
                }
                birthdayEdit.hideKeyboard()
            }


            genderChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                if (!telecomLayout.isVisible) {
                    telecomLayout.isVisible = true
                }
            }
            telecomChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                if (!phoneLayout.isVisible) {
                    phoneLayout.isVisible = true
                    phoneEdit.showKeyboard()
                }
            }
            phoneEdit.doAfterTextChanged {
                if (phoneEdit.length() > 10) {
                    if(validphone()){
                        phoneLayout.error=null
                        confirmButton.isVisible = true
                        phoneEdit.hideKeyboard()
                    }else{
                        confirmButton.isVisible=false
                        phoneLayout.error="생년월일 형식이 다릅니다."
                    }

                }
            }
            phoneEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE) {
                    confirmButton.isVisible = phoneEdit.length() > 9&&validphone()
                    phoneEdit.hideKeyboard()
            }
        }
    }

    fun onClickDone(){
        if(!validName()){
            binding.nameLayout.error="1자 이상의 한글을 입력해주세요."
            return
        }
        if(!validbirthday()) {
            binding.birthdayLayout.error="생년월일 형식이 다릅니다."
            return
        }
        if(!validphone()){
            binding.phoneLayout.error="전화번호 형식이 다릅니다"
            return
        }
    }


    private fun validName() = !binding.nameEdit.text.isNullOrBlank()
            && REGEX_NAME.toRegex().matches(binding.nameEdit.text!!)

    private fun validbirthday() = !binding.birthdayEdit.text.isNullOrBlank()
            && REGEX_BIRTHDAY.toRegex().matches(binding.birthdayEdit.text!!)

    private fun validphone() = !binding.phoneEdit.text.isNullOrBlank()
            && REGEX_PHONE.toRegex().matches(binding.phoneEdit.text!!)

    companion object {
        private const val REGEX_NAME = "^[가-힣]{2,}\$"
        private const val REGEX_BIRTHDAY =
            "^(19|20)[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[0-1])"
        private const val REGEX_PHONE = "^01([016789])([0-9]{3,4})(0-9{4})"
    }

}