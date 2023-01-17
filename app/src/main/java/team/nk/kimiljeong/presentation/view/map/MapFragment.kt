package team.nk.kimiljeong.presentation.view.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.model.remote.common.ScheduleInformation
import team.nk.kimiljeong.data.model.remote.response.InquireScheduleListResponse
import team.nk.kimiljeong.databinding.FragmentMapBinding
import team.nk.kimiljeong.presentation.adapter.recyclerviewadapter.ScheduleAdapter
import team.nk.kimiljeong.presentation.base.view.BaseMapFragment
import team.nk.kimiljeong.presentation.util.ShowSnackBarUtil.showShortSnackBar
import team.nk.kimiljeong.presentation.viewmodel.ScheduleViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MapFragment : BaseMapFragment<FragmentMapBinding>(
    R.layout.fragment_map,
) {

    private val viewModel by viewModels<ScheduleViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapViewId = binding.mapFragmentMapMain.id
        checkUserPermission()
        observeEvent()
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

    private fun observeEvent() {
        viewModel.schedules.observe(
            viewLifecycleOwner,
        ) {
            binding.rvFragmentMapTodaySchedule.run {
                adapter = ScheduleAdapter(it.schedules)
                layoutManager = LinearLayoutManager(requireActivity())
            }
        }
    }
}
