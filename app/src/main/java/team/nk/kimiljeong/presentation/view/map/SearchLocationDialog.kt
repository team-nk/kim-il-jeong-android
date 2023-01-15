package team.nk.kimiljeong.presentation.view.map

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
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
import java.util.*

class SearchLocationDialog : BaseBottomSheetDialogFragment<DialogSearchLocationBinding>(
    R.layout.dialog_search_location,
), OnMapReadyCallback {

    private val mapFragment by lazy {
        SupportMapFragment.newInstance()
    }

    private lateinit var currentLocation: LatLng

    private lateinit var address: String

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

    private fun checkGranted(): Boolean =
        ContextCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.ACCESS_FINE_LOCATION,
        ) == PackageManager.PERMISSION_GRANTED

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.run {
            mapType = GoogleMap.MAP_TYPE_NORMAL
            addCustomMarker(
                googleMap = googleMap,
                latitude = currentLocation.latitude,
                longtitude = currentLocation.longitude,
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
                )
            }
            setAddress(
                latitude = currentLocation.latitude,
                longtitude = currentLocation.longitude,
            )
        }
    }

    private fun addCustomMarker(
        googleMap: GoogleMap,
        latitude: Double,
        longtitude: Double,
    ) {
        setAddress(
            latitude = latitude,
            longtitude = longtitude,
        )
        googleMap.addMarker(
            MarkerOptions()
                .title(address)
                .position(LatLng(latitude, longtitude))
        )
    }

    private fun setAddress(
        latitude: Double,
        longtitude: Double,
    ) {
        address = Geocoder(
            requireActivity(),
            Locale.KOREA,
        ).getFromLocation(
            latitude,
            longtitude,
            1,
        )?.first()?.getAddressLine(0).toString()
    }

    private fun setUserLocation() {
        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.run {
            currentLocation = LatLng(this.latitude, this.longitude)
            mapFragment.getMapAsync(this@SearchLocationDialog)
        }
    }

    private fun initMapView() {
        childFragmentManager.beginTransaction()
            .replace(R.id.map_dialog_search_location_map_main, mapFragment, "MapTag").commit()
    }

    private fun moveToOption() {
        startActivity(
            Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + BuildConfig.APPLICATION_ID)
            )
        )
    }

    private fun initSelectButton() {
        binding.btnDialogSearchLocationSelect.setOnClickListener {
            setFragmentResult("address", bundleOf("address" to address))
            dismiss()
        }
    }
}
