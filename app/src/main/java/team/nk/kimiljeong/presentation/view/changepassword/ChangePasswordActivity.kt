package team.nk.kimiljeong.presentation.view.changepassword

import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityChangePasswordBinding
import team.nk.kimiljeong.presentation.base.view.BaseActivity
import team.nk.kimiljeong.presentation.viewmodel.changepassword.ChangePasswordViewModel

@AndroidEntryPoint
class ChangePasswordActivity : BaseActivity<ActivityChangePasswordBinding>(
    R.layout.activity_change_password,
) {

    private val viewModel by viewModels<ChangePasswordViewModel>()

    override fun initView() {
        initCancelButton()
        initChangePasswordButton()
    }

    private fun initCancelButton() {
        binding.btnActivityChangePasswordCancel.setOnClickListener {
            finish()
        }
    }

    private fun initChangePasswordButton() {
        with(binding) {
            btnActivityChangePasswordChange.setOnClickListener {
                Log.e(this.javaClass.simpleName, "initChangePasswordButton: ")
                viewModel.changePassword(
                    oldPassword = etActivityChangePasswordOldPassword.text.toString(),
                    newPassword = etActivityChangePasswordNewPassword.text.toString(),
                    newPasswordRepeat = etActivityChangePasswordNewPasswordRepeat.text.toString(),
                )
            }
        }
    }

    override fun observeEvent() {
        super.observeEvent()
        observeIsChangePasswordSucceed()
    }

    private fun observeIsChangePasswordSucceed() {
        viewModel.isChangePasswordSucceed.observe(
            this,
        ) {
            if (it) {
                finish()
            }
        }
    }

    override fun observeSnackBarMessage() {
        super.observeSnackBarMessage()
        viewModel.snackBarMessage.observe(
            this,
        ) {
            showShortSnackBar(it)
        }
    }
}
