package app.junsu.kimiljeong.presentation.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class BaseFragment<V : ViewDataBinding, ViewModel>(
    @LayoutRes private val layoutId : Int,
) : Fragment(){

    protected lateinit var binding : V

    protected val viewModel : ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        DataBindingUtil.inflate<ViewDataBinding?>(
            inflater,
            layoutId,
            container,
            false,
        ).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}