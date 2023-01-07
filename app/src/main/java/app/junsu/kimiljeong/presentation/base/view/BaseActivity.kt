package app.junsu.kimiljeong.presentation.base.view

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
    }
}
