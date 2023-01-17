package team.nk.kimiljeong.presentation.base.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import team.nk.kimiljeong.BuildConfig
import java.util.*


abstract class BaseMapFragment<B : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
) : DialogFragment(), OnMapReadyCallback {

    protected lateinit var binding: B

    private val mapFragment = SupportMapFragment.newInstance()

    protected lateinit var currentLocation: LatLng

    protected lateinit var address: String

    protected var mapViewId = 0

    private val locationManager by lazy {
        requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        initView()
    }

    abstract fun initView()

    protected fun checkUserPermission() {
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
                mapViewId,
                mapFragment,
                "MapTag",
            ).commit()
    }

    protected fun addCustomMarker(
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

    protected fun setAddress(
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

    private fun checkGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.ACCESS_FINE_LOCATION,
        ) == PackageManager.PERMISSION_GRANTED
    }

    protected fun setUserLocation() {
        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.run {
            currentLocation = LatLng(this.latitude, this.longitude)
            mapFragment.getMapAsync(this@BaseMapFragment)
        }
    }

    protected fun moveToOption() {
        startActivity(
            Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + BuildConfig.APPLICATION_ID)
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}