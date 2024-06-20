plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.jetbrains.kotlin.android)
	id("kotlin-parcelize")
	id("com.google.devtools.ksp")
	alias(libs.plugins.google.gms.google.services)
}

android {
	namespace = "com.capstone.berrets"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.capstone.berrets"
		minSdk = 24
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

		buildConfigField("String", "API_BASE_URL", "\"https://capstone-berrets-427010.et.r.appspot.com/api/v1/\"")
		buildConfigField("String", "AUTH_WEB_CLIENT_ID", "\"405057640680-s9lfmq487prd9uhsmsgrld9o30cskgm1.apps.googleusercontent.com\"")
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}

	buildFeatures {
		viewBinding = true
		buildConfig = true
		mlModelBinding = true
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}

	kotlinOptions {
		jvmTarget = "1.8"
	}
}

dependencies {
	// AndroidX
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.androidx.activity)
	implementation(libs.androidx.fragment)
	implementation(libs.androidx.fragment.ktx)
	implementation(libs.androidx.constraintlayout)
	implementation(libs.androidx.navigation.ui.ktx)
	implementation(libs.androidx.navigation.fragment.ktx)
	implementation(libs.androidx.navigation.fragment)

	// Camera
	implementation(libs.androidx.camera.camera2)
	implementation(libs.androidx.camera.lifecycle)
	implementation(libs.androidx.camera.view)

	// Coroutines
	implementation(libs.kotlinx.coroutines.core)
	implementation(libs.kotlinx.coroutines.android)

	// Datastore
	implementation(libs.androidx.datastore.preferences)

	// ExifInterface
	implementation(libs.androidx.exifinterface)

	// Firebase
	implementation(libs.firebase.auth)

	// Google Play Services
	implementation(libs.play.services.auth)
	implementation(libs.androidx.credentials)
	implementation(libs.androidx.credentials.play.services.auth)
	implementation(libs.googleid)

	// Image Viewer
	implementation(libs.glide)
	implementation(libs.circle.image.view)

	// Legacy
	implementation(libs.androidx.legacy.support.v4)

	// Lifecycle
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.lifecycle.viewmodel.ktx)
	implementation(libs.androidx.lifecycle.livedata.ktx)

	// Material Theme
	implementation(libs.material)

	// OkHttp
	implementation(libs.logging.interceptor)

	// Retrofit
	implementation(libs.retrofit)
	implementation(libs.retrofit.converter.gson)

	// Room
	implementation(libs.androidx.room.runtime)
	ksp(libs.room.compiler)

	// Tensorflow
	implementation(libs.tensorflow.lite.support)
	implementation(libs.tensorflow.lite.metadata)
	implementation(libs.tensorflow.lite.task.vision)

	// Testing
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}