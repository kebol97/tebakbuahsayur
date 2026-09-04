package com.cococue.tebakbuahsayur.ads

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd

class AppOpenAdManager(
    private val application: Application
) : Application.ActivityLifecycleCallbacks, DefaultLifecycleObserver {

    private var appOpenAd: AppOpenAd? = null
    private var isLoadingAd = false
    private var isShowingAd = false
    private var currentActivity: Activity? = null

    // Callbacks waiting for ad load during splash
    private val pendingCallbacks = mutableListOf<() -> Unit>()

    init {
        application.registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    fun loadAd() {
        val unitId = AdConfigManager.currentConfig.appOpenAdUnitId.ifEmpty {
            "ca-app-pub-3940256099942544/9257390721"
        }

        if (isLoadingAd || isAdAvailable() || !AdConfigManager.currentConfig.isAdsEnabled) {
            return
        }
        isLoadingAd = true
        Log.d("AdMobAppOpen", "Loading App Open Ad with Unit ID: $unitId")

        val request = AdRequest.Builder().build()
        AppOpenAd.load(
            application,
            unitId,
            request,
            AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
            object : AppOpenAd.AppOpenAdLoadCallback() {
                override fun onAdLoaded(ad: AppOpenAd) {
                    Log.d("AdMobAppOpen", "App Open Ad Loaded Successfully!")
                    appOpenAd = ad
                    isLoadingAd = false

                    // If an activity was waiting for the ad (e.g. Splash Screen)
                    val activity = currentActivity
                    if (activity != null && pendingCallbacks.isNotEmpty()) {
                        val callbacks = pendingCallbacks.toList()
                        pendingCallbacks.clear()
                        showAdIfAvailable(activity) {
                            callbacks.forEach { it.invoke() }
                        }
                    }
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    Log.e("AdMobAppOpen", "App Open Ad Failed To Load: ${loadAdError.message} (code ${loadAdError.code})")
                    isLoadingAd = false
                    appOpenAd = null

                    val callbacks = pendingCallbacks.toList()
                    pendingCallbacks.clear()
                    callbacks.forEach { it.invoke() }
                }
            }
        )
    }

    fun isAdAvailable(): Boolean {
        return appOpenAd != null
    }

    fun showAdIfAvailable(activity: Activity, onAdComplete: (() -> Unit)? = null) {
        if (!AdConfigManager.currentConfig.isAdsEnabled) {
            onAdComplete?.invoke()
            return
        }

        if (isShowingAd) {
            onAdComplete?.invoke()
            return
        }

        if (isAdAvailable()) {
            Log.d("AdMobAppOpen", "Showing App Open Ad now...")
            appOpenAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d("AdMobAppOpen", "App Open Ad Dismissed.")
                    appOpenAd = null
                    isShowingAd = false
                    loadAd()
                    onAdComplete?.invoke()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    Log.e("AdMobAppOpen", "App Open Ad Failed To Show: ${adError.message}")
                    appOpenAd = null
                    isShowingAd = false
                    loadAd()
                    onAdComplete?.invoke()
                }

                override fun onAdShowedFullScreenContent() {
                    isShowingAd = true
                }
            }
            isShowingAd = true
            appOpenAd?.show(activity)
        } else {
            Log.d("AdMobAppOpen", "App Open Ad not available yet. isLoadingAd=$isLoadingAd")
            if (isLoadingAd && onAdComplete != null) {
                // Add callback to pending queue so it shows as soon as load finishes
                pendingCallbacks.add(onAdComplete)
            } else {
                loadAd()
                onAdComplete?.invoke()
            }
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        currentActivity?.let { activity ->
            if (activity.javaClass.simpleName != "SplashScreen") {
                showAdIfAvailable(activity)
            }
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity
    }
    override fun onActivityResumed(activity: Activity) {
        currentActivity = activity
    }
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {
        if (currentActivity == activity) {
            currentActivity = null
        }
    }
}
