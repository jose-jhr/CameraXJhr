package com.ingenieriajhr.testcameraxjhr

import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.camera.core.ImageProxy
import com.ingenieriajhr.testcameraxjhr.databinding.ActivityMainBinding
import com.ingenieriiajhr.jhrCameraX.BitmapResponse
import com.ingenieriiajhr.jhrCameraX.CameraJhr
import com.ingenieriiajhr.jhrCameraX.ImageProxyResponse

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    lateinit var cameraJhr: CameraJhr

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cameraJhr = CameraJhr(this)

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (cameraJhr.allpermissionsGranted()&&!cameraJhr.ifStartCamera){
            starCameraJhr()
        }else{
            cameraJhr.noPermissions()
        }
    }

    private fun starCameraJhr() {
        cameraJhr.addlistenerBitmap(object :BitmapResponse{
            override fun bitmapReturn(bitmap: Bitmap?) {
               runOnUiThread {

               }
            }
        })

        cameraJhr.addlistenerImageProxy(object :ImageProxyResponse{
            override fun imageProxyReturn(imageProxy: ImageProxy) {
                try {

                
                }catch (e: IllegalStateException) {
                    // Handle the exception here
                    println("error en conversion imageproxy")
                }
            }
        })

        cameraJhr.initBitmap()
        cameraJhr.initImageProxy()

        cameraJhr.start(0,0,binding.cameraPreview,false,true,false)

    }

    fun Bitmap.rotate(degrees:Float)= Bitmap.createBitmap(this,0,0,width,height, Matrix().apply {
        postRotate(degrees)
    },true)


}