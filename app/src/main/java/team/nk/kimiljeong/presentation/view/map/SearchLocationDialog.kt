package team.nk.kimiljeong.presentation.view.map

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import team.nk.kimiljeong.BuildConfig
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
        checkUserPermission()
    }

    private lateinit var currentLocation: LatLng

    private val locationManager: LocationManager by lazy {
        requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
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

    private fun checkGranted(): Boolean =
        ContextCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.ACCESS_FINE_LOCATION,
        ) == PackageManager.PERMISSION_GRANTED

    private fun moveToOption() {
        startActivity(
            Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + BuildConfig.APPLICATION_ID)
            )
        )
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.run {
            addMarker(
                MarkerOptions()
                    .position(currentLocation)
            )
            setMinZoomPreference(10F)
            setMaxZoomPreference(18F)
            moveCamera(
                CameraUpdateFactory.newLatLng(currentLocation)
            )
            animateCamera(
                CameraUpdateFactory.zoomTo(500F)
            )
            mapType = GoogleMap.MAP_TYPE_NORMAL
        }
    }

    private fun setUserLocation() {
        locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)?.run {
            currentLocation = LatLng(this.latitude, this.longitude)
            mapFragment.getMapAsync(this@SearchLocationDialog)
        }
    }
}
