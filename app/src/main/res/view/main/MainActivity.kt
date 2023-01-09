package com.gram.kimiljeong.presentation.view.main

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.gram.kimiljeong.R
import com.gram.kimiljeong.databinding.ActivityMainBinding
import team.nk.kimiljeong.presentation.view.base.view.BaseActivity
import team.nk.kimiljeong.presentation.view.calendar.CalendarFragment
import team.nk.kimiljeong.presentation.view.map.MapFragment
import team.nk.kimiljeong.presentation.view.mypage.MyPageFragment
import team.nk.kimiljeong.presentation.view.post.PostFragment
import team.nk.kimiljeong.presentation.viewmodel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

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
        supportFragmentManager.beginTransaction().replace(R.id.container_activity_main, fragment)
            .commitAllowingStateLoss()
    }
}
