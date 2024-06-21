package com.capstone.berrets.view.camera

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.OrientationEventListener
import android.view.Surface
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.berrets.R
import com.capstone.berrets.databinding.ActivityCameraBinding
import com.capstone.berrets.helper.compressBitmap
import com.capstone.berrets.helper.createCustomTempFile
import com.capstone.berrets.helper.imageProxyToBitmap
import java.io.ByteArrayOutputStream

class CameraActivity : AppCompatActivity() {

	private lateinit var binding: ActivityCameraBinding
	private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
	private var imageCapture: ImageCapture? = null
	private val orientationEventListener by lazy {
		object : OrientationEventListener(this) {
			override fun onOrientationChanged(orientation: Int) {
				if (orientation == ORIENTATION_UNKNOWN) {
					return
				}

				val rotation = when (orientation) {
					in 45 until 135 -> Surface.ROTATION_270
					in 135 until 225 -> Surface.ROTATION_180
					in 225 until 315 -> Surface.ROTATION_90
					else -> Surface.ROTATION_0
				}

				imageCapture?.targetRotation = rotation
			}
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityCameraBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.switchCamera.setOnClickListener {
			cameraSelector = if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) CameraSelector.DEFAULT_FRONT_CAMERA
			else CameraSelector.DEFAULT_BACK_CAMERA
			startCamera()
		}
		binding.captureImage.setOnClickListener { takePhoto() }
	}

	override fun onStart() {
		super.onStart()
		orientationEventListener.enable()
	}

	override fun onStop() {
		super.onStop()
		orientationEventListener.disable()
	}

	public override fun onResume() {
		super.onResume()
		startCamera()
	}

	private fun startCamera() {
		val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
		cameraProviderFuture.addListener({
			val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
			val preview = Preview.Builder()
				.build()
				.also {
					it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
				}

			imageCapture = ImageCapture.Builder().build()

			try {
				cameraProvider.unbindAll()
				cameraProvider.bindToLifecycle(
					this,
					cameraSelector,
					preview,
					imageCapture
				)
			} catch (exc: Exception) {
				Toast.makeText(this@CameraActivity, "Gagal homunculus kamera.", Toast.LENGTH_SHORT).show()
			}
		}, ContextCompat.getMainExecutor(this))
	}

	private fun takePhoto() {
		val imageCapture = imageCapture ?: return

		imageCapture.takePicture(ContextCompat.getMainExecutor(this), object : ImageCapture.OnImageCapturedCallback() {
			override fun onCaptureSuccess(image: ImageProxy) {
				val bitmap = imageProxyToBitmap(image)
				val byteArray = compressBitmap(bitmap, 80)
				val intent = Intent().apply {
					putExtra(EXTRA_CAMERAX_IMAGE, byteArray)
				}
				setResult(CAMERAX_RESULT, intent)
				finish()
			}

			override fun onError(exception: ImageCaptureException) {
				Toast.makeText(
					this@CameraActivity,
					"Gagal mengambil gambar.",
					Toast.LENGTH_SHORT
				).show()
				Log.e(TAG, "onError: ${exception.message}")
			}
		})
	}

	companion object {
		private const val TAG = "CameraActivity"
		const val EXTRA_CAMERAX_IMAGE = "CameraX Image"
		const val CAMERAX_RESULT = 200
	}
}