package com.kodelib.chatai.activity;

import static com.android.billingclient.api.BillingClient.SkuType.SUBS;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryPurchasesParams;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.kodelib.chatai.Config;
import com.kodelib.chatai.R;
import com.kodelib.chatai.utils.AdsUtility;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InAppPurchaseActivity extends AppCompatActivity implements PurchasesUpdatedListener, BillingClientStateListener {

    private SkuDetails currentSub;
    private SkuDetails weeklySub;
    private SkuDetails monthlySub;
    private SkuDetails annualSub;
    private static String TAG = InAppPurchaseActivity.class.getSimpleName().concat(":SUBS");
    private BillingClient mBillingClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_app_purchase);

        mBillingClient = BillingClient.newBuilder(this)
                .setListener(this) // Set the BillingClient listener to the dialog
                .enablePendingPurchases()
                .build();


        findViewById(R.id.ivBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdsUtility.showInterAds(InAppPurchaseActivity.this, new AdsUtility.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        startActivity(new Intent(InAppPurchaseActivity.this, MainActivity.class));
                        finish();
                    }
                });

            }
        });

        findViewById(R.id.btnWeekly).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSub = weeklySub;
                final BillingClient finalBillingClient = mBillingClient;

                BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                        .setSkuDetails(currentSub)
                        .build();
                BillingResult result = mBillingClient.launchBillingFlow(InAppPurchaseActivity.this, billingFlowParams);
                // Handle the response code here if needed
                if (result.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    finalBillingClient.queryPurchasesAsync(
                            QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.SUBS).build(), (billingResult1, list) -> {
                                if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                    Log.d("testOffer", list.size() + " size");
                                    if (list.size() > 0) {
                                        Config.vip_subscription = true;
                                        Config.all_subscription = true;
                                        Toast.makeText(InAppPurchaseActivity.this, "Subscribed!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Config.vip_subscription = false;
                                        Config.all_subscription = false;
                                        Toast.makeText(InAppPurchaseActivity.this, "Failed to subscribe.", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                }

            }
        });

        findViewById(R.id.btnMonthly).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentSub = monthlySub;
                final BillingClient finalBillingClient = mBillingClient;

                BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                        .setSkuDetails(currentSub)
                        .build();
                BillingResult result = mBillingClient.launchBillingFlow(InAppPurchaseActivity.this, billingFlowParams);
                // Handle the response code here if needed
                if (result.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    finalBillingClient.queryPurchasesAsync(
                            QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.SUBS).build(), (billingResult1, list) -> {
                                if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                    Log.d("testOffer", list.size() + " size");
                                    if (list.size() > 0) {
                                        Config.vip_subscription = true;
                                        Config.all_subscription = true;
                                        Toast.makeText(InAppPurchaseActivity.this, "Subscribed!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Config.vip_subscription = false;
                                        Config.all_subscription = false;
                                        Toast.makeText(InAppPurchaseActivity.this, "Failed to subscribe.", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                }


            }
        });

        findViewById(R.id.btnAnnual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSub = annualSub;
                final BillingClient finalBillingClient = mBillingClient;

                BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                        .setSkuDetails(currentSub)
                        .build();
                BillingResult result = mBillingClient.launchBillingFlow(InAppPurchaseActivity.this, billingFlowParams);
                // Handle the response code here if needed
                if (result.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    finalBillingClient.queryPurchasesAsync(
                            QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.SUBS).build(), (billingResult1, list) -> {
                                if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                    Log.d("testOffer", list.size() + " size");
                                    if (list.size() > 0) {
                                        Config.vip_subscription = true;
                                        Config.all_subscription = true;
                                        Toast.makeText(InAppPurchaseActivity.this, "Subscribed!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Config.vip_subscription = false;
                                        Config.all_subscription = false;
                                        Toast.makeText(InAppPurchaseActivity.this, "Failed to subscribe.", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                }
            }
        });

    }


    @Override
    public void onBillingSetupFinished(@NotNull BillingResult billingResult) {
        // once the client has been successfully initialized, we will retrieve the products
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
            getProducts();
            set_prices_item();
        }
    }

    @Override
    public void onBillingServiceDisconnected() {
        Log.d(TAG, "onBillingServiceDisconnected");
    }

    @SuppressLint("SetTextI18n")
    private void set_prices_item() {
        Log.d(TAG, "getProducts");
        final List<String> skuList_set_price = new ArrayList<>();
        skuList_set_price.add(Config.weekly_id);
        skuList_set_price.add(Config.monthly_id);
        skuList_set_price.add(Config.annually_id);

        SkuDetailsParams params_prices = SkuDetailsParams.newBuilder()
                .setSkusList(skuList_set_price)
                .setType(BillingClient.SkuType.SUBS)
                .build();

        mBillingClient.querySkuDetailsAsync(params_prices, (billingResult, skuDetailsList) -> {
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && skuDetailsList != null) {
                if (!skuDetailsList.isEmpty()) {
                    for (SkuDetails skuDetails : skuDetailsList) {
                        switch (skuDetails.getSku()) {
                            case Config.weekly_id:
                                weeklySub = skuDetails;
                                currentSub = weeklySub;
                                break;
                            case Config.monthly_id:
                                monthlySub = skuDetails;
                                currentSub = monthlySub;
                                break;
                            case Config.annually_id:
                                annualSub = skuDetails;
                                currentSub = annualSub;
                                break;
                        }
                    }
                }
            } else {
                Toast.makeText(InAppPurchaseActivity.this, "Error in retrieving products from store", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void isUserHasSubscription_premium() {
        if (mBillingClient == null) {
            mBillingClient = BillingClient.newBuilder(InAppPurchaseActivity.this)
                    .enablePendingPurchases()
                    .build();
        }
        mBillingClient.startConnection(this);
    }

    private void getProducts() {
        Log.d(TAG, "getProducts");
        final List<String> skuList = new ArrayList<>();
        skuList.add("1week");
        skuList.add("one_week_subscription");
        skuList.add("1month");
        skuList.add("one_month_subscription");
        skuList.add("1year");
        skuList.add("one_year_subscription");

        SkuDetailsParams params = SkuDetailsParams.newBuilder()
                .setSkusList(skuList)
                .setType(BillingClient.SkuType.SUBS)
                .build();

        mBillingClient.querySkuDetailsAsync(params, (billingResult, skuDetailsList) -> {
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && skuDetailsList != null) {

            }
        });
    }

    @Override
    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {

    }
}