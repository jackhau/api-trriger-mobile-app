package com.jack.apitriggermobile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jack.apitriggermobile.utility.ServerReceiver

class MainActivity : AppCompatActivity() {

    companion object {
        fun starter(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stopService(Intent(this, ServerReceiver::class.java))
        startService(Intent(this, ServerReceiver::class.java))
    }
}