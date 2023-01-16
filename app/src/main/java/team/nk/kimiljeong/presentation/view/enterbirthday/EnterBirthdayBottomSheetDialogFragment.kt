package team.nk.kimiljeong.presentation.view.enterbirthday

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogEnterBirthdayBinding
import team.nk.kimiljeong.presentation.base.view.BaseBottomSheetDialogFragment
import team.nk.kimiljeong.presentation.util.ShowSnackBarUtil.showShortSnackBar
import team.nk.kimiljeong.presentation.viewmodel.main.MainViewModel
import java.util.*
// TODO 프래그먼트가 다시 표시 안되는 버그 수정
class EnterBirthdayBottomSheetDialogFragment :
    BaseBottomSheetDialogFragment<DialogEnterBirthdayBinding>(
        R.layout.dialog_enter_birthday,
    ) {

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    private var isBirthdaySelected = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onEnterButtonClick()
        onBirthdayButtonClick()
        onCancelButtonClick()
        observeEvent()
    }

    private fun onEnterButtonClick() {
        binding.btnDlgEnterBirthdayEnter.setOnClickListener {
            if (isBirthdaySelected) {
                viewModel.enterBirthday()
            } else {
                dialog!!.window!!.decorView.showShortSnackBar(
                    getString(
                        R.string.dlg_birthday_please_select_birthday,
                    ),
                )
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onBirthdayButtonClick() {
        binding.btnDlgEnterBirthdaySelectBirthday.setOnClickListener {
            // TODO date select calendar dialog, and show selected date
            // save received date at viewModel
            // val birthday = TODO("get birthday")
            isBirthdaySelected = true

            GregorianCalendar().let { today ->

                DatePickerDialog(
                    requireActivity(),
                    { _, selectedYear, selectedMonth, selectedDate ->
                        "${selectedYear.toString().padStart(4, '0')}-${
                            (selectedMonth + 1).toString().padStart(2, '0')
                        }-${selectedDate.toString().padStart(2, '0')}".apply {
                            viewModel.setBirthday(this)
                            binding.btnDlgEnterBirthdaySelectBirthday.text = this
                        }
                    },
                    today.get(Calendar.YEAR),
                    today.get(Calendar.MONTH),
                    today.get(Calendar.DATE),
                ).show()
            }
        }
    }

    private fun onCancelButtonClick() {
        binding.btnDlgEnterBirthdayCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun observeEvent() {
        viewModel.isEnterBirthdaySuccess.observe(
            viewLifecycleOwner,
        ) {
            dismiss()
        }

        viewModel.snackBarMessage.observe(
            viewLifecycleOwner,
        ) {
            binding.root.showShortSnackBar(it)
        }
    }

    override fun initView() {}
}
