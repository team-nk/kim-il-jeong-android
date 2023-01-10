package com.gram.kimiljeong.presentation.view.changepassword

import com.gram.kimiljeong.R
import com.gram.kimiljeong.databinding.ActivityChangePasswordBinding
import com.gram.kimiljeong.presentation.view.base.view.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordActivity : BaseActivity<ActivityChangePasswordBinding>(
    R.layout.activity_change_password,
) {

    override fun initView() {
        initCancelButton()
    }

    private fun initCancelButton(){
        binding.btnActivityChangePasswordCancel.setOnClickListener {
            finish()
        }
    }
}
