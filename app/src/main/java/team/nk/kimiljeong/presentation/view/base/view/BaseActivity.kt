package team.nk.kimiljeong.presentation.view.base.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes layoutId: Int,
) : AppCompatActivity() {

    protected val binding: B by lazy {
        DataBindingUtil.setContentView(
            this,
            layoutId,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        observeEvent()
        initView()
    }

    abstract fun initView() // TODO make protected

    protected open fun observeEvent() {}
}
