package team.nk.kimiljeong.presentation.view.report

import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityReportBinding
import team.nk.kimiljeong.presentation.base.view.BaseActivity

@AndroidEntryPoint
class ReportActivity : BaseActivity<ActivityReportBinding>(
    R.layout.activity_report,
) {
    override fun initView() {
        initReportButton()
        initCancelButton()
    }

    private fun initReportButton() {
        binding.btnActivityReportNewPostCreate.setOnClickListener {
            if (binding.etReportNewPostTitle.text.isNotBlank() &&
                binding.etReportNewPostContent.text.isNotBlank()
            ) {
                setResult(200)
                finish()
            }else if(binding.etReportNewPostTitle.text.isBlank()){
                showShortSnackBar(
                    text = getString(
                        R.string.post_create_please_enter_title,
                    )
                )
            }else{
                showShortSnackBar(
                    text = getString(
                        R.string.post_create_please_enter_content,
                    )
                )
            }
        }
    }

    private fun initCancelButton(){
        binding.btnActivityReportNewPostCancel.setOnClickListener {
            finish()
        }
    }
}