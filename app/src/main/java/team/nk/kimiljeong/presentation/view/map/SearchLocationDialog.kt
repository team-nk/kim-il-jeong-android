package team.nk.kimiljeong.presentation.view.map

import android.app.Dialog
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogSearchLocationBinding
import team.nk.kimiljeong.presentation.base.view.BaseBottomSheetDialogFragment

class SearchLocationDialog : BaseBottomSheetDialogFragment<DialogSearchLocationBinding>(
    R.layout.dialog_search_location,
), OnMapReadyCallback {

    private val mapFragment by lazy {
        SupportMapFragment.newInstance()
    }

    override fun initView() {
        initMapView()
        initCloseButton()
    }

    override fun onCreateDialog(
        savedInstanceState: Bundle?,
    ): Dialog {

        isCancelable = false

        return BottomSheetDialog(
            requireContext()
        ).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        behavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }
            })
        }
    }

    private fun initMapView() {
        childFragmentManager.beginTransaction()
            .replace(R.id.map_dialog_search_location_map_main, mapFragment, "MapTag").commit()
        mapFragment.getMapAsync(this@SearchLocationDialog)
    }

    private fun initCloseButton() {
        binding.btnDialogSearchLocationCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.run {
            addMarker(
                MarkerOptions()
                    .position(LatLng(3.4, 5.6))
            )
            setMinZoomPreference(10F)
            setMaxZoomPreference(18F)
            moveCamera(
                CameraUpdateFactory.newLatLng(LatLng(3.4, 5.6))
            )
            animateCamera(
                CameraUpdateFactory.zoomTo(500F)
            )
            mapType = GoogleMap.MAP_TYPE_NORMAL
        }
    }
}
