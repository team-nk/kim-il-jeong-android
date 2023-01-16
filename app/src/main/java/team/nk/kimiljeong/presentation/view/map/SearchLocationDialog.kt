package team.nk.kimiljeong.presentation.view.map

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogSearchLocationBinding
import team.nk.kimiljeong.presentation.base.view.BaseMapFragment

class SearchLocationDialog : BaseMapFragment<DialogSearchLocationBinding>(
    R.layout.dialog_search_location,
) {

    override fun onCreateDialog(
        savedInstanceState: Bundle?,
    ): Dialog {

        isCancelable = false

        return BottomSheetDialog(
            requireContext()
        ).apply {
            behavior.run {
                val expanded = BottomSheetBehavior.STATE_EXPANDED
                state = expanded
                addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                            state = expanded
                        }
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {}
                })
            }
        }
    }

    override fun initView() {
        initMapView()
        initCloseButton()
        checkUserPermission()
        initSelectButton()
    }

    private fun initCloseButton() {
        binding.btnDialogSearchLocationCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun checkUserPermission() {
        if (checkGranted()) {
            setUserLocation()
            initMapView()
        } else {
            moveToOption()
        }
    }

    private fun initMapView() {
        childFragmentManager.beginTransaction()
            .replace(
                R.id.map_dialog_search_location_map_main,
                mapFragment,
                "MapTag",
            ).commit()
    }

    private fun initSelectButton() {
        binding.btnDialogSearchLocationSelect.setOnClickListener {
            setFragmentResult("address", bundleOf("address" to address))
            dismiss()
        }
    }
}
