package com.cococue.tebakbuahsayur

import android.app.Application
import android.util.Log
import com.cococue.tebakbuahsayur.ads.AdConfigManager
import com.cococue.tebakbuahsayur.ads.AppOpenAdManager
import com.cococue.tebakbuahsayur.ads.InterstitialAdManager
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainApplication : Application() {

    lateinit var appOpenAdManager: AppOpenAdManager

    override fun onCreate() {
        super.onCreate()

        appOpenAdManager = AppOpenAdManager(this)

        // Initialize Mobile Ads SDK and start loading ads immediately
        MobileAds.initialize(this) { status ->
            Log.d("MainApplication", "MobileAds initialized: $status")
            appOpenAdManager.loadAd()
            InterstitialAdManager.loadAd(this@MainApplication)
        }

        // Fetch Remote Ad Configuration from GitHub JSON in parallel
        CoroutineScope(Dispatchers.IO).launch {
            AdConfigManager.fetchRemoteConfig(this@MainApplication)
            launch(Dispatchers.Main) {
                appOpenAdManager.loadAd()
                InterstitialAdManager.loadAd(this@MainApplication)
            }
        }
    }
}
