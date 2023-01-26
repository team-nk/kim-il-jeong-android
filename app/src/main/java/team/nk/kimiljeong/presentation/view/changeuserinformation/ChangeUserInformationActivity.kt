package team.nk.kimiljeong.presentation.view.changeuserinformation

import android.net.Uri
import android.provider.MediaStore
import androidx.activity.viewModels
import app.junsu.startactivityutil.StartActivityUtil.startActivity
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import gun0912.tedimagepicker.builder.TedImagePicker
import gun0912.tedimagepicker.builder.type.MediaType
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityChangeUserInformationBinding
import team.nk.kimiljeong.presentation.adapter.bindingadapter.loadImageFrom
import team.nk.kimiljeong.presentation.base.view.BaseActivity
import team.nk.kimiljeong.presentation.view.changepassword.ChangePasswordActivity
import team.nk.kimiljeong.presentation.viewmodel.changeinformation.ChangeUserInformationViewModel
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class ChangeUserInformationActivity @Inject constructor() :
    BaseActivity<ActivityChangeUserInformationBinding>(
        R.layout.activity_change_user_information,
    ) {

    private lateinit var image: File

    private val viewModel by viewModels<ChangeUserInformationViewModel>()

    override fun initView() {
        initChangeImageButton()
        initMoveChangePasswordActivityButton()
        initCancleButton()
        initChangeInformationButton()
        initLoadProfileImage()
    }

    private fun initChangeImageButton() {
        binding.tvActivityChangeUserInformationChangeImage.setOnClickListener {
            TedImagePicker.with(this)
                .mediaType(MediaType.IMAGE)
                .start { uri ->
                    initProfileImageView(uri)
                    image = File(getRealPathFromUri(uri).toString())
                }
        }
    }

    private fun initProfileImageView(uri: Uri) {
        Glide.with(this)
            .load(uri)
            .into(binding.imageActivityChangeUserInformationUserProfile)
    }

    private fun getRealPathFromUri(uri: Uri): String? {
        val loader = contentResolver.query(
            uri,
            arrayOf(MediaStore.Images.Media.DATA),
            null,
            null,
            null,
        )
        loader!!.moveToFirst()
        return loader.getString(loader.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
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
                        profile = image,
                    )
                }
            }
        }
    }

    override fun observeEvent() {
        super.observeEvent()
        viewModel.changeUserInformation.observe(
            this,
        ) {
            if (it) {
                setResult(RESULT_OK)
                finish()
            }
        }
        viewModel.snackBarMessage.observe(
            this,
        ) {
            showShortSnackBar(
                text = it,
            )
        }
    }

    private fun initLoadProfileImage(){
        binding.imageActivityChangeUserInformationUserProfile.loadImageFrom(
            url = intent.getStringExtra("imageUrl")
        )
    }
}
