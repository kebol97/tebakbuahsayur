package com.cococue.tebakbuahsayur.ads

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

object InterstitialAdManager {

    private var interstitialAd: InterstitialAd? = null
    private var isLoading = false

    // Counter tracking how many game sessions have been played
    private var sessionCounter = 0

    fun loadAd(context: Context) {
        val unitId = AdConfigManager.currentConfig.interstitialAdUnitId.ifEmpty {
            "ca-app-pub-3940256099942544/1033173712"
        }

        if (isLoading || interstitialAd != null || !AdConfigManager.currentConfig.isAdsEnabled) {
            return
        }
        isLoading = true
        Log.d("AdMobInterstitial", "Loading Interstitial Ad with Unit ID: $unitId")

        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            context,
            unitId,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    Log.d("AdMobInterstitial", "Interstitial Ad Loaded Successfully!")
                    interstitialAd = ad
                    isLoading = false
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    Log.e("AdMobInterstitial", "Interstitial Ad Failed To Load: ${loadAdError.message}")
                    interstitialAd = null
                    isLoading = false
                }
            }
        )
    }

    fun showAd(activity: Activity, onAdDismissed: () -> Unit) {
        val interval = AdConfigManager.currentConfig.interstitialInterval.coerceAtLeast(1)
        sessionCounter++
        Log.d("AdMobInterstitial", "Game session counter: $sessionCounter / remote interval $interval")

        // Only show ad every N sessions configured from Remote GitHub JSON (e.g. interval = 3)
        if (sessionCounter % interval != 0) {
            Log.d("AdMobInterstitial", "Skipping Interstitial Ad for session $sessionCounter (Configured interval = $interval)")
            loadAd(activity)
            onAdDismissed()
            return
        }

        val ad = interstitialAd
        if (ad != null && AdConfigManager.currentConfig.isAdsEnabled) {
            Log.d("AdMobInterstitial", "Showing Interstitial Ad at session $sessionCounter (Interval = $interval)!")
            ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d("AdMobInterstitial", "Interstitial Ad Dismissed.")
                    interstitialAd = null
                    loadAd(activity)
                    onAdDismissed()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    Log.e("AdMobInterstitial", "Interstitial Ad Failed To Show: ${adError.message}")
                    interstitialAd = null
                    loadAd(activity)
                    onAdDismissed()
                }
            }
            ad.show(activity)
        } else {
            Log.d("AdMobInterstitial", "Interstitial Ad not ready yet or disabled. Preloading...")
            loadAd(activity)
            onAdDismissed()
        }
    }
}
