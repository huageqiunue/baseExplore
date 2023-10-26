package com.junhua.myapplication.dialog

import android.app.Dialog
import android.content.DialogInterface

abstract class BaseDialog<out D : DialogInterface> {
    abstract fun BuildDialog(): D
}