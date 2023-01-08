package app.junsu.kimiljeong.presentation.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import app.junsu.kimiljeong.R
import app.junsu.kimiljeong.databinding.ActivityMainBinding
import app.junsu.kimiljeong.presentation.base.view.BaseActivity
import app.junsu.kimiljeong.presentation.view.calendar.CalendarFragment
import app.junsu.kimiljeong.presentation.view.map.MapFragment
import app.junsu.kimiljeong.presentation.view.mypage.MyPageFragment
import app.junsu.kimiljeong.presentation.view.post.PostFragment

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main,
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
