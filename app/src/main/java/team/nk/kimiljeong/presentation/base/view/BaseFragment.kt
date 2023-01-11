package team.nk.kimiljeong.presentation.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import team.nk.kimiljeong.presentation.common.ShowSnackBar
import team.nk.kimiljeong.presentation.util.ShowSnackBarUtil.showLongSnackBar
import team.nk.kimiljeong.presentation.util.ShowSnackBarUtil.showShortSnackBar

abstract class BaseFragment<B : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
) : Fragment(), ShowSnackBar {

    protected lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            layoutId,
            container,
            false,
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        observeEvent()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }

    abstract fun initView()

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
