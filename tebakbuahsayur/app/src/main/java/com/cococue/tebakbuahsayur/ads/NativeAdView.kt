package com.cococue.tebakbuahsayur.ads

import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView

@Composable
fun NativeAdCard(
    modifier: Modifier = Modifier
) {
    if (!AdConfigManager.currentConfig.isAdsEnabled) return

    val context = LocalContext.current
    var nativeAdState by remember { mutableStateOf<NativeAd?>(null) }

    DisposableEffect(context) {
        val adLoader = AdLoader.Builder(context, AdConfigManager.currentConfig.nativeAdUnitId)
            .forNativeAd { ad ->
                nativeAdState = ad
            }
            .build()
        adLoader.loadAd(AdRequest.Builder().build())

        onDispose {
            nativeAdState?.destroy()
        }
    }

    val nativeAd = nativeAdState
    if (nativeAd != null) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f)),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                factory = { ctx ->
                    val nativeAdView = NativeAdView(ctx)
                    val container = LinearLayout(ctx).apply {
                        orientation = LinearLayout.VERTICAL
                    }

                    // Headline
                    val headlineView = TextView(ctx).apply {
                        text = nativeAd.headline
                        textSize = 16f
                        setTypeface(null, android.graphics.Typeface.BOLD)
                        setTextColor(android.graphics.Color.parseColor("#2D3142"))
                    }
                    container.addView(headlineView)
                    nativeAdView.headlineView = headlineView

                    // Icon / Logo
                    nativeAd.icon?.let { icon ->
                        val iconView = ImageView(ctx).apply {
                            setImageDrawable(icon.drawable)
                            layoutParams = LinearLayout.LayoutParams(120, 120).apply {
                                topMargin = 8
                                bottomMargin = 8
                            }
                        }
                        container.addView(iconView)
                        nativeAdView.iconView = iconView
                    }

                    // Body
                    nativeAd.body?.let { body ->
                        val bodyView = TextView(ctx).apply {
                            text = body
                            textSize = 13f
                            setTextColor(android.graphics.Color.parseColor("#7F8C8D"))
                        }
                        container.addView(bodyView)
                        nativeAdView.bodyView = bodyView
                    }

                    // Call to Action Button
                    nativeAd.callToAction?.let { cta ->
                        val ctaView = Button(ctx).apply {
                            text = cta
                            setBackgroundColor(android.graphics.Color.parseColor("#FF6B6B"))
                            setTextColor(android.graphics.Color.WHITE)
                        }
                        container.addView(ctaView)
                        nativeAdView.callToActionView = ctaView
                    }

                    nativeAdView.setNativeAd(nativeAd)
                    nativeAdView.addView(container)
                    nativeAdView
                }
            )
        }
    }
}
