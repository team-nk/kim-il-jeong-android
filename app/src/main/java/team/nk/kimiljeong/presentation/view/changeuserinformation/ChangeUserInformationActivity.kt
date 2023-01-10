package team.nk.kimiljeong.presentation.view.changeuserinformation

import app.junsu.startactivityutil.StartActivityUtil.startActivity
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityChangeUserInformationBinding
import team.nk.kimiljeong.presentation.view.base.view.BaseActivity
import team.nk.kimiljeong.presentation.view.changepassword.ChangePasswordActivity

class ChangeUserInformationActivity : BaseActivity<ActivityChangeUserInformationBinding>(
    R.layout.activity_change_user_information,
) {
    override fun initView() {
        initChangeImageButton()
        initMoveChangePasswordActivityButton()
        initCancleButton()
        initChangeInformationButton()
    }

    private fun initChangeImageButton(){
        binding.tvActivityChangeUserInformationChangeImage.setOnClickListener {
            // TODO server logic
        }
    }

    private fun initMoveChangePasswordActivityButton(){
        binding.tvActivityChangeUserInformationChangePassword.setOnClickListener {
            startActivity(
                context = this,
                to = ChangePasswordActivity::class.java,
            )
        }
    }

    private fun initCancleButton() {
        binding.btnActivityChangeUserInformationCancel.setOnClickListener {
            finish()
        }
    }

    private fun initChangeInformationButton() {
        binding.run {
            btnActivityChangeUserInformationChange.setOnClickListener {
                if (
                    etActivityChangeUserInformationEmail.text.isNotEmpty() &&
                    etActivityChangeUserInformationId.text.isNotEmpty()
                ){
                    // TODO server logic
                }else{
                    // TODO show message logic
                }
            }
        }
    }
}
