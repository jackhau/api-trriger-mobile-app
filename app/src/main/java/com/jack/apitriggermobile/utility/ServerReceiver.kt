package com.jack.apitriggermobile.utility

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.jack.apitriggermobile.HelpActivity
import com.jack.apitriggermobile.MainActivity
import com.jack.apitriggermobile.model.HelpModel
import com.koushikdutta.async.http.body.AsyncHttpRequestBody
import com.koushikdutta.async.http.server.AsyncHttpServer
import com.koushikdutta.async.http.server.AsyncHttpServerRequest
import com.koushikdutta.async.http.server.AsyncHttpServerResponse
import org.json.JSONObject

/**
 * Created by jackhau on 25/07/2021.
 */

class ServerReceiver: Service() {

    private var asyncHttpServer: AsyncHttpServer? = AsyncHttpServer()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        asyncHttpServer = AsyncHttpServer()

        asyncHttpServer?.get("/MainActivity") { request, response ->
            try {
                MainActivity.starter(applicationContext)
                responseNormal(request, response)
            } catch (e: Exception) {
                responseNormalError(request, response, "Page not found.")
            }
        }

        asyncHttpServer?.post("/Help") { request, response ->
            try {
                val body = request.getBody<AsyncHttpRequestBody<Any>>().get().toString()
                HelpActivity.starter(applicationContext, HelpModel.deserialize(body))
                responseNormal(request, response)
            } catch (e: Exception) {
                responseNormalError(request, response, "Page not found.")
            }
        }

        asyncHttpServer?.listen(    3000)
    }

    private fun responseNormal(
        request: AsyncHttpServerRequest,
        response: AsyncHttpServerResponse
    ) {
        response.code(200)
        val json = JSONObject()
        json.put("error", "")
        json.put("status", 0)
        response.send(json)
        request.close()
    }

    private fun responseNormalError(
        request: AsyncHttpServerRequest,
        response: AsyncHttpServerResponse,
        errorMessage: String
    ) {
        response.code(500)
        val json = JSONObject()
        json.put("error", errorMessage)
        json.put("status", 1)
        response.send(json)
        request.close()
    }

    override fun onDestroy() {
        super.onDestroy()
        asyncHttpServer?.stop()
    }
}

