package com.kodelib.chatai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryPurchasesParams;
import com.kodelib.chatai.Config;
import com.kodelib.chatai.R;
import com.kodelib.chatai.utils.AdsUtility;
import com.kodelib.chatai.utils.AppOpenManagerTwo;
import com.kodelib.chatai.utils.Utils;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private BillingClient billingClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.hideStatus(this);
        setContentView(R.layout.activity_splash2);
        AdsUtility.initAdmob(this);

        billingClient = BillingClient.newBuilder(this)
                .setListener((billingResult, purchases) -> {
                    // To be implemented in a later section.
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
                        for (Purchase purchase : purchases) {
                            handlePurchase(purchase);
                        }
                    } else if (billingResult.getResponseCode() ==
                            BillingClient.BillingResponseCode.USER_CANCELED) {
                        // Handle an error caused by a user cancelling the purchase flow.
                    } else {
                        // Handle any other error codes.
                    }

                })
                .enablePendingPurchases()
                .build();

        checkSubscription();
        proceedWithAppLogic();


    }


    private void handlePurchase(Purchase purchase) {

        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {

            AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener = billingResult -> {

                // the user's purchase has been successful
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK)

                    //TODO set the user's premium status to true
                    Log.d("Acknowledged", "successfully acknowledged product");
            };

            if (!purchase.isAcknowledged()) {
                AcknowledgePurchaseParams acknowledgePurchaseParams =
                        AcknowledgePurchaseParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();

                Log.d("Not_Acknowledged", "no");
                billingClient.acknowledgePurchase(acknowledgePurchaseParams,
                        acknowledgePurchaseResponseListener);
            }
        }
    }

    void checkSubscription() {

        billingClient = BillingClient.newBuilder(this).enablePendingPurchases().setListener((billingResult, list) -> {
        }).build();
        final BillingClient finalBillingClient = billingClient;
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {

            }

            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {

                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    finalBillingClient.queryPurchasesAsync(
                            QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.SUBS).build(), (billingResult1, list) -> {
                                if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                    Log.d("testOffer", list.size() + " size");
                                    if (list.size() > 0) {
                                        Config.vip_subscription = true;
                                        Config.all_subscription = true;
                                    } else {
                                        Config.vip_subscription = false;
                                        Config.all_subscription = false;

                                    }

                                }
                            });

                }

            }
        });
    }


    // Implement the PurchasesUpdatedListener to handle purchase flow
    private final PurchasesUpdatedListener purchasesUpdatedListener = (billingResult, purchases) -> {
        // Handle purchase updates if necessary
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Disconnect the BillingClient when the activity is destroyed
        if (billingClient != null && billingClient.isReady()) {
            billingClient.endConnection();
        }
    }

    private void proceedWithAppLogic() {

        new CountDownTimer(5000, 500) {
            @Override
            public void onFinish() {
                if (Config.vip_subscription) {
                    Log.d("TAGGGGEEG", "onBillingSetupFinished: vip_subscription");
                    // User has an active subscription, navigate to the main activity
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();

                } else {

                    startActivity(new Intent(SplashActivity.this, InAppPurchaseActivity.class));
                    finish();

                }
            }

            @Override
            public void onTick(long l) {

            }
        }.start();


    }


}