package com.skaldebane.conceptualized

import android.content.ClipData
import android.content.ClipDescription
import android.content.ClipboardManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.skaldebane.conceptualized.databinding.ActivityMainBinding
import com.skaldebane.conceptualized.databinding.MaterialTextEditingDialogBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val dialogBinding by viewBinding(MaterialTextEditingDialogBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.actionMaterialTextEditingDialog.setOnClickListener {
            val dialog = AlertDialog.Builder(this@MainActivity).create()
            dialogBinding.dialogSave.setOnClickListener { dialog.dismiss() }
            dialogBinding.dialogCopy.setOnClickListener {
                val clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                clipboardManager.setPrimaryClip(ClipData(
                        ClipDescription("text", arrayOf("text")),
                        ClipData.Item(dialogBinding.dialogEdit.text.toString())
                ))
                Toast.makeText(
                        this@MainActivity,
                        "Copied to Clipboard",
                        Toast.LENGTH_SHORT
                ).show()
            }
            dialog.setView(dialogBinding.root)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }
    }
}