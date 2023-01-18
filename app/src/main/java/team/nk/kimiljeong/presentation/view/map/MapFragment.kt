package team.nk.kimiljeong.presentation.view.map

import android.location.Geocoder
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.model.remote.common.ScheduleInformation
import team.nk.kimiljeong.databinding.FragmentMapBinding
import team.nk.kimiljeong.presentation.adapter.recyclerviewadapter.ScheduleAdapter
import team.nk.kimiljeong.presentation.base.view.BaseMapFragment
import team.nk.kimiljeong.presentation.viewmodel.ScheduleViewModel

@AndroidEntryPoint
class MapFragment : BaseMapFragment<FragmentMapBinding>(
    R.layout.fragment_map,
) {

    private val viewModel by viewModels<ScheduleViewModel>()

    private val addressList: ArrayList<String?> = ArrayList()

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
            for (i in 0.until(addressList.size)) {
                Geocoder(requireActivity()).getFromLocationName(addressList[i].toString(), 10)
                    ?.get(0)?.run {
                    addCustomMarker(
                        googleMap = googleMap,
                        latitude = latitude,
                        longtitude = longitude
                    )
                }
            }
        }
    }

    private fun observeEvent() {
//        viewModel.schedules.observe(
//            viewLifecycleOwner,
//        ) { it ->
            binding.rvFragmentMapTodaySchedule.run {
                adapter = ScheduleAdapter(
                    schedules = arrayListOf(
                        ScheduleInformation(
                            1,
                            "대덕대학교 자습",
                            "PURPLE",
                            "대전광역시 유성구 가정동 가정북로 74",
                            "2023-01-30T13:00:00",
                            "2023-01-30T15:00:00",
                            false,
                        )
                    ),
                    onItemClick = object : ScheduleItemClickListener {
                        override fun onScheduleItemClick(
                            scheduleId: Int,
                            color: String,
                            content: String,
                            address: String,
                            startsAt: String,
                            endsAt: String,
                            isAllDay: Boolean,
                        ) {
                            ScheduleDetailDialog().run {
                                show(
                                    this@MapFragment.requireActivity().supportFragmentManager,
                                    tag
                                )
                                arguments = Bundle().also {
                                    it.putInt("scheduleId", scheduleId)
                                    it.putString("color", color)
                                    it.putString("content", content)
                                    it.putString("address", address)
                                    it.putString("startsAt", startsAt)
                                    it.putString("endsAt", endsAt)
                                    it.putBoolean("isAllDay", isAllDay)
                                }
                            }
                        }

                    })
                layoutManager = LinearLayoutManager(requireActivity())
            }
//            for (i in it.schedules.indices) {
//                addressList.add(it.schedules[i].address)
//            }
//        }
    }
}

interface ScheduleItemClickListener {
    fun onScheduleItemClick(
        scheduleId: Int,
        color: String,
        content: String,
        address: String,
        startsAt: String,
        endsAt: String,
        isAllDay: Boolean,
    )
}
