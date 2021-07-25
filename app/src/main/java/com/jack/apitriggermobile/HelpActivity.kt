package com.jack.apitriggermobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jack.apitriggermobile.model.HelpModel
import kotlinx.android.synthetic.main.activity_help.*

/**
 * Created by jackhau on 25/07/2021.
 */

class HelpActivity : AppCompatActivity() {

    companion object {
        const val CONTENT = "CONTENT"

        fun starter(context: Context, body: HelpModel) {
            val intent = Intent(context, HelpActivity::class.java)
            intent.putExtra(CONTENT, body)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val content = intent.getParcelableExtra<HelpModel>(CONTENT)
        setContentView(R.layout.activity_help)

        message.text = content?.message
    }
}

