package com.cococue.tebakbuahsayur.ads

import android.content.Context
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

data class AdConfig(
    val admobAppId: String = "ca-app-pub-5135473927373558~8679540646",
    val bannerAdUnitId: String = "ca-app-pub-3940256099942544/6300978111x",
    val interstitialAdUnitId: String = "ca-app-pub-3940256099942544/1033173712x",
    val appOpenAdUnitId: String = "ca-app-pub-3940256099942544/9257390721x",
    val nativeAdUnitId: String = "ca-app-pub-3940256099942544/2247696110x",
    val interstitialInterval: Int = 3, // Default: 3 game sessions per 1 interstitial ad
    val isAdsEnabled: Boolean = true
)

object AdConfigManager {

    // Remote GitHub Raw JSON URL for Ad Unit Config (User can change this URL to their GitHub raw link)
    var githubJsonUrl: String = "https://raw.githubusercontent.com/kebol97/tebakbuahsayur/refs/heads/master/adsconfig.json"

    // Active AdMob Unit Configuration (Default: Official Google AdMob Test Ad Units)
    var currentConfig: AdConfig = AdConfig()
        private set

    suspend fun fetchRemoteConfig(context: Context): AdConfig {
        return withContext(Dispatchers.IO) {
            try {
                val jsonString = URL(githubJsonUrl).readText()
                val parsedConfig = Gson().fromJson(jsonString, AdConfig::class.java)
                if (parsedConfig != null) {
                    currentConfig = parsedConfig
                }
            } catch (_: Exception) {
                // Keep default Google AdMob Test Units on network error or offline
            }
            currentConfig
        }
    }
}
