package ggflmob.project.goevents.Dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ggflmob.project.goevents.R

class CreateEventDialog : DialogFragment() {

    internal lateinit var listener: NoticeDialogListener
    lateinit var eventName : TextView
    lateinit var eventNameString : String

    interface NoticeDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as NoticeDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.create_group_dialog, null)

            builder.setView(view)
                .setPositiveButton(
                    R.string.create,
                    DialogInterface.OnClickListener { dialog, id ->
                        eventName = view.findViewById(R.id.username)
                        eventNameString = eventName.text.toString()
                        listener.onDialogPositiveClick(this)
                    })
                .setNegativeButton(
                    R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        listener.onDialogNegativeClick(this)
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}