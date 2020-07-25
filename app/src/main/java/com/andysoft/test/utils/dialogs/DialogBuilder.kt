package com.andysoft.test.utils.dialogs

import android.content.Context
import android.content.DialogInterface
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.andysoft.test.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shagi.materialdatepicker.date.DatePickerFragmentDialog
import java.util.*

class DialogBuilder {
    companion object {
        fun showDialog(context: Context, titleString: String, listener: DialogListener) {
            val dialogBuilder = MaterialAlertDialogBuilder(context)
                .setTitle(titleString)
//            .setView(dialogView)
                .setNegativeButton(context.resources.getString(R.string.no), null)
                .setPositiveButton(context.resources.getString(R.string.yes), null)
                .setCancelable(false)
                .create()
            dialogBuilder.setOnShowListener { dialogInterface ->
                listener.onDialogShow(dialogInterface)
                dialogBuilder.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                    dialogBuilder.dismiss()
                    listener.onPositiveClick(it)
                }
                dialogBuilder.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener {
                    dialogBuilder.dismiss()
                    listener.onNegativeClick(it)
                }
            }
            dialogBuilder.show()
        }


        fun showDateDialog(
            context: Context,
            maxDate: Calendar?,
            fragmentManager: FragmentManager,
            listener: DialogListener
        ) {
            val dateDialog =
                DatePickerFragmentDialog.newInstance { view, year, monthOfYear, dayOfMonth ->
                    val cal = Calendar.getInstance()
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    listener.onDateSelection(cal)
                }
            maxDate?.let { dateDialog?.maxDate = it }

            dateDialog?.setOkColor(ContextCompat.getColor(context, R.color.P700))
            dateDialog?.accentColor = ContextCompat.getColor(context, R.color.P700)
            dateDialog?.setCancelColor(ContextCompat.getColor(context, R.color.P700))

            dateDialog?.show(fragmentManager, "Paperking")

        }
    }
}
