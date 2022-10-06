package com.example.coroutinesapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    var customProgressDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val constBtn: Button = findViewById(R.id.btn_const)
        constBtn.setOnClickListener {
            showProgressDialog()
            lifecycleScope.launch{
                execute("Task executed successfully")
            }
        }
    }

    private suspend fun execute(result: String){
        withContext(Dispatchers.IO){
            for(i in 1..10000){
                Log.e("delay : ", "" + i)
            }
            runOnUiThread{
                cancelProgressDialog()
                Toast.makeText(this@MainActivity, result, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun cancelProgressDialog(){
        if(customProgressDialog!= null){
            customProgressDialog?.dismiss()
            customProgressDialog = null
        }
    }

    private fun showProgressDialog(){
        customProgressDialog = Dialog(this)
        customProgressDialog?.setContentView(R.layout.custom_dialog)
        customProgressDialog?.show()
    }
}



