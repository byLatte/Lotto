package com.latte.lotto.history

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.latte.lotto.R
import com.latte.lotto.database.NumberHistoryRepository
import java.lang.IllegalStateException

private const val TAG = "RemoveDialog"

class NumberHistoryRemoveDialog : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
        // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("전체 삭제 하시겠습니까?")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, id ->
                        NumberHistoryRepository.get().deleteAll()
                    })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, id ->
                        Log.d(TAG,"취소")
                    })
            builder.create()
        }?:throw IllegalStateException("Activity is null ")
    }
}