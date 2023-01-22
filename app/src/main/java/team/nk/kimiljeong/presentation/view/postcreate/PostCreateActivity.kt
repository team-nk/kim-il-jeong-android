package team.nk.kimiljeong.presentation.view.postcreate

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityPostCreateBinding
import team.nk.kimiljeong.presentation.base.view.BaseActivity
import team.nk.kimiljeong.presentation.view.scheduleinformation.IS_ITEM_CHOSEN
import team.nk.kimiljeong.presentation.view.scheduleinformation.SELECT_SCHEDULE
import team.nk.kimiljeong.presentation.view.scheduleinformation.ScheduleInformationBottomSheetDialogFragment
import team.nk.kimiljeong.presentation.view.selectschedule.SelectScheduleBottomSheetDialogFragment
import team.nk.kimiljeong.presentation.viewmodel.postcreate.PostCreateViewModel

@AndroidEntryPoint
class PostCreateActivity : BaseActivity<ActivityPostCreateBinding>(
    R.layout.activity_post_create,
) {

    private val viewModel by viewModels<PostCreateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSelectScheduleResultListener()
    }

    private fun setSelectScheduleResultListener() {
        supportFragmentManager.setFragmentResultListener(
            SELECT_SCHEDULE,
            this@PostCreateActivity
        ) { _, bundle ->
            if (bundle.getBoolean(IS_ITEM_CHOSEN, false)) {
                viewModel.canContinue = true
            }
            viewModel.selectedScheduleInformation.value?.run {
                with(binding) {
                    tvCreateNewPostSelectSchedule.text = content
                }
            }
        }
    }

    override fun initView() {
        initButtons()
    }

    private fun initButtons() {
        initCancelButton()
        initCreateButton()
        initSelectScheduleButton()
    }

    private fun initSelectScheduleButton() {
        binding.btnActivityPostCreateSelectSchedule.setOnClickListener {
            SelectScheduleBottomSheetDialogFragment().run {
                show(
                    supportFragmentManager,
                    this.tag,
                )
            }
        }
    }

    private fun initCreateButton() {
        with(binding) {
            btnActivityCreateNewPostCreate.setOnClickListener {
                viewModel.createPost(
                    etCreateNewPostTitle.text.toString(),
                    etCreateNewPostContent.text.toString(),
                )
            }
        }
    }

    private fun initCancelButton() {
        binding.btnActivityCreateNewPostCancel.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
    }

    override fun observeSnackBarMessage() {
        super.observeSnackBarMessage()
        viewModel.snackBarMessage.observe(
            this@PostCreateActivity,
        ) {
            showShortSnackBar(it)
        }
    }

    override fun observeEvent() {
        super.observeEvent()
        observeSelectedScheduleInformationId()
        observeCreateScheduleSucceed()
    }

    private fun observeCreateScheduleSucceed() {
        viewModel.isCreateScheduleSucceed.observe(
            this@PostCreateActivity,
        ) {
            if (it) {
                setResult(RESULT_OK)
                finish()
            }
        }
    }

    private fun observeSelectedScheduleInformationId() {
        viewModel.selectedScheduleInformation.observe(
            this@PostCreateActivity,
        ) {
            ScheduleInformationBottomSheetDialogFragment(it).also { dialog ->
                dialog.show(
                    supportFragmentManager,
                    this.javaClass.simpleName,
                )
            }
        }
    }
}
