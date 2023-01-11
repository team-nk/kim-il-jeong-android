package team.nk.kimiljeong.presentation.base.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import team.nk.kimiljeong.presentation.common.ShowSnackBar
import team.nk.kimiljeong.presentation.util.ShowSnackBarUtil.showLongSnackBar
import team.nk.kimiljeong.presentation.util.ShowSnackBarUtil.showShortSnackBar

abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes layoutId: Int,
) : AppCompatActivity(), ShowSnackBar {

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

    protected abstract fun initView()

    protected open fun observeEvent() {
        observeSnackBarMessage()
    }

    protected open fun observeSnackBarMessage() {}

    override fun showShortSnackBar(text: String) {
        binding.root.showShortSnackBar(
            text = text,
        )
    }

    override fun showLongSnackBar(text: String) {
        binding.root.showLongSnackBar(
            text = text,
        )
    }
}
