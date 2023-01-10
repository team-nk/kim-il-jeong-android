package team.nk.kimiljeong.presentation.view.changepassword

import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityChangePasswordBinding
import team.nk.kimiljeong.presentation.view.base.view.BaseActivity

@AndroidEntryPoint
class ChangePasswordActivity : BaseActivity<ActivityChangePasswordBinding>(
    R.layout.activity_change_password,
) {
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
        binding.run {
            btnActivityChangePasswordChange.setOnClickListener {
                val oldPassword = binding.etActivityChangePasswordOldPassword.text.toString()
                val newPassword = binding.etActivityChangePasswordNewPassword.text.toString()
                val newPasswordRepeat =
                    binding.etActivityChangePasswordNewPasswordRepeat.text.toString()
                if (oldPassword.isNotEmpty() &&
                    newPassword.isNotEmpty() ==
                    newPasswordRepeat.isNotEmpty()
                ) {
                    // TODO server logic
                }
            }
        }
    }
}
