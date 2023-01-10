package team.nk.kimiljeong.presentation.view.main

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import app.junsu.startactivityutil.StartActivityUtil.startActivity
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityMainBinding
import team.nk.kimiljeong.presentation.base.view.BaseActivity
import team.nk.kimiljeong.presentation.view.calendar.CalendarFragment
import team.nk.kimiljeong.presentation.view.map.MapFragment
import team.nk.kimiljeong.presentation.view.mypage.MyPageFragment
import team.nk.kimiljeong.presentation.view.post.PostFragment
import team.nk.kimiljeong.presentation.view.start.StartActivity
import team.nk.kimiljeong.presentation.viewmodel.main.MainViewModel

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main,
) {

    private val viewModel by viewModels<MainViewModel>()

    override fun initView() {
        initBottomNavigationView()
    }

    private fun initBottomNavigationView() {
        with(binding.bottomNavigationViewActivityMain) {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.item_bottom_navigation_main_calendar -> {
                        changeFragment(CalendarFragment())
                        return@setOnItemSelectedListener true
                    }
                    R.id.item_bottom_navigation_main_map -> {
                        changeFragment(MapFragment())
                        return@setOnItemSelectedListener true
                    }
                    R.id.item_bottom_navigation_main_post -> {
                        changeFragment(PostFragment())
                        return@setOnItemSelectedListener true
                    }
                    R.id.item_bottom_navigation_main_mypage -> {
                        changeFragment(MyPageFragment())
                        return@setOnItemSelectedListener true
                    }
                }
                false
            }
            selectedItemId = R.id.item_bottom_navigation_main_calendar
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(
            binding.containerActivityMain.id, fragment,
        ).commitAllowingStateLoss()
    }

    override fun observeEvent() {
        super.observeEvent()

        observeLoginStatus()
    }

    private fun observeLoginStatus() {
        viewModel.isLoggedIn.observe(
            this@MainActivity,
        ) {
            if (it) {
                TODO("Renew Token!! viewModel.renewToken()")
            } else {
                // requireLogin()
                startActivity(
                    this, StartActivity::class.java,
                )
            }
        }
    }
}
