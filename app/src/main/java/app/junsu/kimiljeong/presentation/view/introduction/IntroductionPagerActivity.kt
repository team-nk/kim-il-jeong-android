package app.junsu.kimiljeong.presentation.view.introduction

import android.os.Bundle
import app.junsu.kimiljeong.R
import app.junsu.kimiljeong.databinding.ActivityIntroductionPagerBinding
import app.junsu.kimiljeong.presentation.base.view.BaseActivity

class IntroductionPagerActivity : BaseActivity<ActivityIntroductionPagerBinding>(
    R.layout.activity_introduction_pager,
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewPager()
        initNextButton()
        initTabLayout()
    }

    private fun initViewPager() {
        binding.vpIntroduction.adapter = IntroductionPagerAdapter(this)
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
