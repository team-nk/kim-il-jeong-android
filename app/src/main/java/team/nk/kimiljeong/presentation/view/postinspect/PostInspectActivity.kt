package team.nk.kimiljeong.presentation.view.postinspect

import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityPostInspectBinding
import team.nk.kimiljeong.presentation.base.view.BaseActivity
import javax.inject.Inject

@AndroidEntryPoint
class PostInspectActivity @Inject constructor(
) : BaseActivity<ActivityPostInspectBinding>(
    R.layout.activity_post_inspect,
) {
    override fun initView() {

    }
}

// 이런 코드는 클린하지 않아요. 사용하면 안됩니다 ㅠㅠ
var selectedPostInformation: PostInformation? = null
