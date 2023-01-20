package team.nk.kimiljeong.presentation.view.calendar

import android.app.Dialog
import android.location.Geocoder
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogScheduleLocationBinding
import team.nk.kimiljeong.presentation.base.view.BaseMapFragment
import team.nk.kimiljeong.presentation.util.parseColorToResource
import team.nk.kimiljeong.presentation.view.delete.DeleteDialogFragment
import team.nk.kimiljeong.presentation.view.schedule.AddScheduleBottomSheetDialogFragment

class ScheduleLocationBottomSheetDialogFragment :
    BaseMapFragment<DialogScheduleLocationBinding>(
        R.layout.dialog_schedule_location
    ), OnMapReadyCallback {

    override fun onCreateDialog(
        savedInstanceState: Bundle?,
    ): Dialog = BottomSheetDialog(requireActivity())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapViewId = binding.mapDialogMapLocationMain.id
        checkUserPermission()
    }

    override fun initView() {
        initIndicator()
        initTextView()
        initModifyButton()
        initDeleteButton()
    }

    private fun initIndicator() {
        binding.viewDialogMapLocationColorIndicator.background = getDrawable(
            requireActivity(),
            parseColorToResource(requireArguments().getString("color")),
        )
    }

    private fun initTextView() {
        with(binding) {
            requireArguments().run {
                tvDialogMapLocationTitle.text = this.getString("content")
                tvDialogMapLocationScheduleTime.text =
                    if (this.getString("startsAt") == getString(R.string.allDay)) {
                        getString(R.string.allDay)
                    } else {
                        StringBuilder().run {
                            append(getString("startsAt"))
                            append(" ~ ")
                            append(getString("endsAt"))
                        }.toString()
                    }
            }
        }
    }

    private fun initModifyButton() {
        binding.btnDialogMapLocationModify.setOnClickListener {
            setFragmentResultListener("isModifySucceed") { _, bundle ->
                if (bundle.getBoolean("modify")) {
                    setFragmentResult("isModifySucceedSecondary", bundleOf("modify" to true))
                    dismiss()
                }
            }
            AddScheduleBottomSheetDialogFragment().run {
                arguments = Bundle().also {
                    this@ScheduleLocationBottomSheetDialogFragment.requireArguments().run {
                        it.putAll(this)
                        it.putBoolean("isModify", true)
                    }
                }
                show(
                    this@ScheduleLocationBottomSheetDialogFragment.requireActivity().supportFragmentManager,
                    tag,
                )
            }
        }
    }

    private fun initDeleteButton() {
        binding.btnDialogMapLocationDelete.setOnClickListener {
            setFragmentResultListener("isRemoveSucceed") { _, bundle ->
                if (bundle.getBoolean("remove")) {
                    setFragmentResult("isRemoveSucceedSecondary", bundleOf("remove" to true))
                    dismiss()
                }
            }
            DeleteDialogFragment().run {
                arguments = Bundle().also {
                    it.putInt(
                        "scheduleId",
                        this@ScheduleLocationBottomSheetDialogFragment.requireArguments()
                            .getInt("scheduleId"),
                    )
                }
                show(
                    this@ScheduleLocationBottomSheetDialogFragment.requireActivity().supportFragmentManager,
                    tag,
                )
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.run {
            mapType = GoogleMap.MAP_TYPE_NORMAL
            googleMap.uiSettings.isScrollGesturesEnabled = false
            Geocoder(requireActivity()).getFromLocationName(
                requireArguments().getString("address").toString(), 10
            )
                ?.get(0)?.run {
                    currentLocation = LatLng(latitude, longitude)
                    addCustomMarker(
                        googleMap = googleMap,
                        latitude = latitude,
                        longtitude = longitude,
                        isCurrent = false,
                    )
                }
            setMinZoomPreference(10F)
            setMaxZoomPreference(18F)
            moveCamera(
                CameraUpdateFactory.newLatLng(currentLocation)
            )
            animateCamera(
                CameraUpdateFactory.zoomTo(500F)
            )
        }
    }
}