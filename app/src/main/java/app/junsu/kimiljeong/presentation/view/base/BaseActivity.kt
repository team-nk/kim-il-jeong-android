package app.junsu.kimiljeong.presentation.view.base

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<V : ViewDataBinding, ViewModel>(
    @LayoutRes layoutId : Int,
) : AppCompatActivity(){

    private val binding : V by lazy {
        DataBindingUtil.setContentView(
            this,
            layoutId,
        )
    }

    private val viewModel : ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
    }
}