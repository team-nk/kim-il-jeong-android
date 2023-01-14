package team.nk.kimiljeong.presentation.view.changeuserinformation

import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import app.junsu.startactivityutil.StartActivityUtil.startActivity
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityChangeUserInformationBinding
import team.nk.kimiljeong.presentation.base.view.BaseActivity
import team.nk.kimiljeong.presentation.view.changepassword.ChangePasswordActivity
import team.nk.kimiljeong.presentation.viewmodel.changeinformation.ChangeUserInformationViewModel
import team.nk.kimiljeong.presentation.viewmodel.changepassword.ChangePasswordViewModel
import javax.inject.Inject

@AndroidEntryPoint
class ChangeUserInformationActivity @Inject constructor() :
    BaseActivity<ActivityChangeUserInformationBinding>(
        R.layout.activity_change_user_information,
    ) {

    private val imageResult = registerForActivityResult(
        StartActivityForResult(),
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val imageUri = result.data?.data
            imageUri?.let {
                if (Build.VERSION.SDK_INT < 28) {
                    val bitmap =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                } else {
                    val source = ImageDecoder.createSource(this.contentResolver, imageUri)
                    val bitmap = ImageDecoder.decodeBitmap(source)
                }
            }
        }
    }

    private val viewModel by viewModels<ChangeUserInformationViewModel>()

    override fun initView() {
        initChangeImageButton()
        initMoveChangePasswordActivityButton()
        initCancleButton()
        initChangeInformationButton()
    }


    private fun initChangeImageButton() {
        binding.tvActivityChangeUserInformationChangeImage.setOnClickListener {
            // TODO server logic
        }
    }

    private fun initMoveChangePasswordActivityButton() {
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
                if (etActivityChangeUserInformationEmail.text.isNotEmpty() && etActivityChangeUserInformationId.text.isNotEmpty()) {
                    viewModel.changeUserInformation(
                        email = etActivityChangeUserInformationEmail.text.toString(),
                        accountId = etActivityChangeUserInformationId.text.toString(),
                        profile = "https://avatars.githubusercontent.com/u/102812085?v=4",
                    )
                    // TODO get image logic
                } else {
                    // TODO show message logic
                }
            }
        }
    }

    override fun observeEvent() {
        super.observeEvent()
        viewModel.changeUserInformation.observe(
            this,
        ){
            if(it){
                showShortSnackBar(
                    text = "정보 변경 성공"
                )
                finish()
                // TODO get string resource
            }
        }
        viewModel.snackBarMessage.observe(
            this,
        ){
            showShortSnackBar(
                text = it,
            )
        }
    }
}
