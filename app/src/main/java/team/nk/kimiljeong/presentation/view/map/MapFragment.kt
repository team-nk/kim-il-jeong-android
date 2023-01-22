package team.nk.kimiljeong.presentation.view.map

import android.location.Geocoder
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.from
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.FragmentMapBinding
import team.nk.kimiljeong.presentation.adapter.recyclerviewadapter.ScheduleAdapter
import team.nk.kimiljeong.presentation.base.view.BaseMapFragment
import team.nk.kimiljeong.presentation.common.ShowSnackBar
import team.nk.kimiljeong.presentation.util.ShowSnackBarUtil.showShortSnackBar
import team.nk.kimiljeong.presentation.viewmodel.ScheduleViewModel

@AndroidEntryPoint
class MapFragment : BaseMapFragment<FragmentMapBinding>(
    R.layout.fragment_map,
), ShowSnackBar {

    private val viewModel by viewModels<ScheduleViewModel>()

    private val addressList: ArrayList<String?> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapViewId = binding.mapFragmentMapMain.id
        checkUserPermission()
        initFragmentResultListener()
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
                            longtitude = longitude,
                            isCurrent = false,
                        )
                    }
            }
            setFragmentResultListener("address") { _, bundle ->
                moveCamera(
                    CameraUpdateFactory.newLatLng(
                        LatLng(
                            bundle.getDouble("latitude"),
                            bundle.getDouble("longtitude")
                        )
                    )
                )
            }
        }
    }

    private fun initFragmentResultListener() {
        setFragmentResultListener("isRemoveSucceedSecondary") { _, bundle ->
            if (bundle.getBoolean("remove")) {
                showShortSnackBar(
                    text = getString(R.string.success_delete),
                )
                viewModel.inquireSpecificLocationOfScheduleList()
            }
        }
        setFragmentResultListener("isModifySucceedSecondary") { _, bundle ->
            if (bundle.getBoolean("modify")) {
                showShortSnackBar(
                    text = getString(R.string.modify_schedule_succeed)
                )
                viewModel.inquireSpecificLocationOfScheduleList()
            }
        }
    }

    private fun observeEvent() {
        viewModel.schedules.observe(
            viewLifecycleOwner,
        ) { it ->
            binding.rvFragmentMapTodaySchedule.run {
                adapter = ScheduleAdapter(
                    schedules = it.schedules,
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
                            Geocoder(requireActivity()).getFromLocationName(address, 10)?.first()
                                ?.run {
                                    setFragmentResult(
                                        "address",
                                        bundleOf("latitude" to latitude, "longtitude" to longitude)
                                    )
                                }
                            val bottomSheetBehavior = from(binding.clFragmentMapBottomSheet)
                            bottomSheetBehavior.state = STATE_COLLAPSED
                        }
                    }

                )
                layoutManager = LinearLayoutManager(requireActivity())
            }
            for (i in it.schedules.indices) {
                addressList.add(it.schedules[i].address)
            }
        }
    }

    override fun showShortSnackBar(text: String) {
        binding.root.showShortSnackBar(text)
    }

    override fun showLongSnackBar(text: String) {}
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

internal interface SelectScheduleItemClickListener {
    fun onItemClick()
}
