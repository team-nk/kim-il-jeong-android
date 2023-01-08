package app.junsu.kimiljeong.presentation.view.introduction

import android.os.Bundle
import app.junsu.kimiljeong.presentation.base.view.BaseActivity
import com.gram.kimiljeong.R
import com.gram.kimiljeong.databinding.ActivityIntroductionBinding

class IntroductionActivity : BaseActivity<ActivityIntroductionBinding>(
    R.layout.activity_introduction,
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        initViewPager()
        initNextButton()
        initTabLayout()
    }

    private fun initViewPager() {
        binding.vpIntroduction.adapter = IntroductionAdapter(this)
        binding.tabLayoutIntroductionViewPager.setupWithViewPager(binding.vpIntroduction)
    }

    private fun initNextButton() {
        binding.btnIntroductionNext.setOnClickListener {
            with(binding.vpIntroduction) {
                if (currentItem < 3) {
                    setCurrentItem(currentItem + 1, true)
                } else {
                    // TODO else logic
                    finish()
                }
            }
        }
    }

    private fun initTabLayout() {
        with(binding.tabLayoutIntroductionViewPager) {
            when (id) {
                0 -> getTabAt(0)?.select()
                1 -> getTabAt(1)?.select()
                2 -> getTabAt(2)?.select()
                3 -> getTabAt(3)?.select()
                4 -> getTabAt(4)?.select()
                else -> {}
            }
        }
    }
}
