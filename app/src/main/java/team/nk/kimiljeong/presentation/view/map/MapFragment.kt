package team.nk.kimiljeong.presentation.view.map

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.FragmentMapBinding
import team.nk.kimiljeong.presentation.base.view.BaseMapFragment

@AndroidEntryPoint
class MapFragment : BaseMapFragment<FragmentMapBinding>(
    R.layout.fragment_map,
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapViewId = binding.mapFragmentMapMain.id
        checkUserPermission()
    }

    override fun initView() {}

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
            setAddress(
                latitude = currentLocation.latitude,
                longtitude = currentLocation.longitude,
            )
        }
    }
}
