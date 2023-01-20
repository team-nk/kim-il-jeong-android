package team.nk.kimiljeong.presentation.view.map

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogSearchLocationBinding
import team.nk.kimiljeong.presentation.base.view.BaseMapFragment

class SearchLocationDialog : BaseMapFragment<DialogSearchLocationBinding>(
    R.layout.dialog_search_location,
), OnMapReadyCallback {

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

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.run {
            mapType = GoogleMap.MAP_TYPE_NORMAL
            addCustomMarker(
                googleMap = googleMap,
                latitude = currentLocation.latitude,
                longtitude = currentLocation.longitude,
                isCurrent = true,
            )
            setMinZoomPreference(10F)
            setMaxZoomPreference(18F)
            moveCamera(
                CameraUpdateFactory.newLatLng(currentLocation)
            )
            animateCamera(
                CameraUpdateFactory.zoomTo(500F)
            )
            setOnMapClickListener {
                addCustomMarker(
                    googleMap = googleMap,
                    latitude = it.latitude,
                    longtitude = it.longitude,
                    isCurrent = false,
                )
            }
            setAddress(
                latitude = currentLocation.latitude,
                longtitude = currentLocation.longitude,
            )
        }
    }

    override fun initView() {
        mapViewId = binding.mapDialogSearchLocationMapMain.id
        initCloseButton()
        checkUserPermission()
        initSelectButton()
    }

    private fun initCloseButton() {
        binding.btnDialogSearchLocationCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun initSelectButton() {
        binding.btnDialogSearchLocationSelect.setOnClickListener {
            setFragmentResult("address", bundleOf("address" to address))
            dismiss()
        }
    }
}
