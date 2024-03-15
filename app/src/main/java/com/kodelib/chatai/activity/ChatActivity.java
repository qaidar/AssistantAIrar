package com.kodelib.chatai.activity;

import static com.kodelib.chatai.Config.OPENAI_API_KEY;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.card.MaterialCardView;
import com.kodelib.chatai.Config;
import com.kodelib.chatai.R;
import com.kodelib.chatai.adapter.ChatAdapter;
import com.kodelib.chatai.models.Message;
import com.kodelib.chatai.utils.AdsUtility;
import com.kodelib.chatai.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = ChatActivity.class.getSimpleName();
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    private RequestQueue requestQueue;

    private RecyclerView chatRecyclerView;
    private ChatAdapter chatAdapter;
    private List<Message> messages;

    RelativeLayout animationView;

    TextView tv1, tv2, tv3;
    RelativeLayout btn1, btn2, btn3;
    EditText messageEditText;

    String a_1 = "Write an article discussing the benefits and challenges of remote work in modern workforce.";
    String a_2 = "Craft an article exploring the impact of social media on mental health.";
    String a_3 = "Write an article exploring the concept of universal basic income (UBI).";

    String c_1 = "Compose a short story set in a futuristic world where humans have colonized other planets.";
    String c_2 = "Create a poem that captures the beauty of nature during the changing seasons.";
    String c_3 = "Design a concept for a futuristic cityscape, describing its architectural features.";

    String b_1 = "Write an article outlining the key strategies for effective brand building.";
    String b_2 = "Develop a comprehensive business plan for a tech startup.";
    String b_3 = "Write a blog post highlighting the importance of customer relationship management (CRM).";

    String s_1 = "Write an article discussing the impact of social media influencers on consumer behavior.";
    String s_2 = "Create a social media campaign concept for non-profit organization aiming to raise awareness.";
    String s_3 = "Write a guide on using social media platforms effectively for small businesses";

    String d_1 = "Write a Python function that takes a list of numbers as input and returns the sum.";
    String d_2 = "Write a Java program that calculates the factorial of a given number.";
    String d_3 = "Implement a JavaScript function that reverses a given string.";

    String p_1 = "Write an article exploring the power of self-reflection in personal growth.";
    String p_2 = "Compose a guide on setting and achieving personal goals, providing practical steps.";
    String p_3 = "Write an essay discussing the importance of embracing failure as a catalyst for personal growth.";

    String o_1 = "Explain the concept of machine learning and its applications in real-world.";
    String o_2 = "Discuss the ethical implications of artificial intelligence in today's society.";
    String o_3 = "Explain the concept of blockchain technology and its potential impact on various industries.";

    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_USAGE_COUNT = "usageCount";
    private static final String KEY_LAST_USAGE_TIMESTAMP = "lastUsageTimestamp";
    private static final int MAX_USAGE_LIMIT = 2;
    private static final long TIME_LIMIT_MILLIS = 24 * 60 * 60 * 1000; // 24 hours in milliseconds

    int usageCount;
    long lastUsageTimestamp;
    long currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.hideStatus(this);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        setContentView(R.layout.activity_chat);



        AdsUtility.loadNativeAd(this, findViewById(R.id.nativeAdSmall), R.layout.ad_unified_small);


        btn1 = (RelativeLayout) findViewById(R.id.btn1);
        btn2 = (RelativeLayout) findViewById(R.id.btn2);
        btn3 = (RelativeLayout) findViewById(R.id.btn3);
        tv1 = (TextView) findViewById(R.id.txt1);
        tv2 = (TextView) findViewById(R.id.txt2);
        tv3 = (TextView) findViewById(R.id.txt3);

        animationView = findViewById(R.id.animationView);
        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(this);
        chatRecyclerView.setAdapter(chatAdapter);

        RecyclerView.ItemAnimator itemAnimator = new SlideInUpAnimator(new OvershootInterpolator(5f));
        chatRecyclerView.setItemAnimator(itemAnimator);

        messages = new ArrayList<>();
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);


        if (!Utils.isStart) {
            Intent intent = getIntent();
            if (intent != null) {
                TextView textView = findViewById(R.id.title);
                String name = intent.getStringExtra("category");
                textView.setText(name);

                if (Utils.isPrompt) {
                    checkCategory(name);
                } else {

                    if (!Config.vip_subscription) {


                        usageCount = preferences.getInt(KEY_USAGE_COUNT, 0);
                        lastUsageTimestamp = preferences.getLong(KEY_LAST_USAGE_TIMESTAMP, 0);

                        currentTime = System.currentTimeMillis();
                        long timeSinceLastUsage = currentTime - lastUsageTimestamp;
                        if (timeSinceLastUsage >= TIME_LIMIT_MILLIS) {
                            usageCount = 0;
                        }

                        if (usageCount >= MAX_USAGE_LIMIT) {
                            Utils.animate(findViewById(R.id.parent), findViewById(R.id.limitLayout), true);
                        } else {

                            findViewById(R.id.midLayout).setVisibility(View.GONE);
                            String prompt = "Hi, I want you to behave as " + intent.getStringExtra("name") + ". Let's start our friendly chat. Respond to my message \"Hi\"";
                            makeChatRequest(prompt);

                            usageCount++;
                            lastUsageTimestamp = currentTime;

                            // Save the updated values to shared preferences
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putInt(KEY_USAGE_COUNT, usageCount);
                            editor.putLong(KEY_LAST_USAGE_TIMESTAMP, lastUsageTimestamp);
                            editor.apply();

                        }

                    } else {

                        findViewById(R.id.midLayout).setVisibility(View.GONE);
                        String prompt = "Hi, I want you to behave as " + intent.getStringExtra("name") + ". Let's start our friendly chat. Respond to my message \"Hi\"";
                        makeChatRequest(prompt);

                    }



                }
            }
        }


        findViewById(R.id.btnAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdsUtility.showRewardAd(ChatActivity.this, new AdsUtility.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        findViewById(R.id.limitLayout).setVisibility(View.GONE);
                        usageCount--;
                        lastUsageTimestamp = currentTime;

                        // Save the updated values to shared preferences
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt(KEY_USAGE_COUNT, usageCount);
                        editor.putLong(KEY_LAST_USAGE_TIMESTAMP, lastUsageTimestamp);
                        editor.apply();
                    }
                });


            }
        });

        findViewById(R.id.btnSubscribe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdsUtility.showInterAds(ChatActivity.this, new AdsUtility.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        startActivity(new Intent(ChatActivity.this, InAppPurchaseActivity.class));
                    }
                });
            }
        });

        messageEditText = findViewById(R.id.messageEditText);
        messageEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                findViewById(R.id.inputLayout).setBackgroundResource(R.drawable.bg_edit_text);
            }
        });

        MaterialCardView sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString().trim();
                if (!message.isEmpty()) {

                    findViewById(R.id.midLayout).setVisibility(View.GONE);

                    if (!Config.vip_subscription){




                        usageCount = preferences.getInt(KEY_USAGE_COUNT, 0);
                        lastUsageTimestamp = preferences.getLong(KEY_LAST_USAGE_TIMESTAMP, 0);

                        currentTime = System.currentTimeMillis();
                        long timeSinceLastUsage = currentTime - lastUsageTimestamp;
                        if (timeSinceLastUsage >= TIME_LIMIT_MILLIS) {
                            usageCount = 0;
                        }

                        if (usageCount >= MAX_USAGE_LIMIT) {

                            Utils.animate(findViewById(R.id.parent), findViewById(R.id.limitLayout), true);

                        } else {

                            messages.add(new Message(message, true));
                            chatAdapter.setMessages(messages);
                            chatRecyclerView.setItemAnimator(new DefaultItemAnimator());

                            chatRecyclerView.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
                            messageEditText.setText("");
                            makeChatRequest(message);
                            // Increment the usage count and update the last usage timestamp
                            usageCount++;
                            lastUsageTimestamp = currentTime;

                            // Save the updated values to shared preferences
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putInt(KEY_USAGE_COUNT, usageCount);
                            editor.putLong(KEY_LAST_USAGE_TIMESTAMP, lastUsageTimestamp);
                            editor.apply();

                        }
                    } else {
                        messages.add(new Message(message, true));
                        chatAdapter.setMessages(messages);
                        chatRecyclerView.setItemAnimator(new DefaultItemAnimator());

                        chatRecyclerView.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
                        messageEditText.setText("");
                        makeChatRequest(message);
                    }



                }
            }
        });

        findViewById(R.id.ivBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdsUtility.showInterAds(ChatActivity.this, new AdsUtility.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        onBackPressed();
                    }
                });
            }
        });
    }

    private void checkCategory(String name) {
        if (name.contains("Articles")) {
            tv1.setText(a_1);
            tv2.setText(a_2);
            tv3.setText(a_3);
        } else if (name.contains("Creative")) {
            tv1.setText(c_1);
            tv2.setText(c_2);
            tv3.setText(c_3);
        } else if (name.contains("Business")) {
            tv1.setText(b_1);
            tv2.setText(b_2);
            tv3.setText(b_3);
        } else if (name.contains("Social")) {
            tv1.setText(s_1);
            tv2.setText(s_2);
            tv3.setText(s_3);
        } else if (name.contains("Personal")) {
            tv1.setText(p_1);
            tv2.setText(p_2);
            tv3.setText(p_3);
        } else if (name.contains("Other")) {
            tv1.setText(o_1);
            tv2.setText(o_2);
            tv3.setText(o_3);
        } else if (name.contains("Development")) {
            tv1.setText(d_1);
            tv2.setText(d_2);
            tv3.setText(d_3);
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageEditText.setText(tv1.getText().toString());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageEditText.setText(tv2.getText().toString());
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageEditText.setText(tv3.getText().toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    public void makeChatRequest(String message) {

        Utils.animate(findViewById(R.id.parent), animationView, true);

        requestQueue = Volley.newRequestQueue(ChatActivity.this);

        try {
            JSONObject requestObject = new JSONObject();
            requestObject.put("model", "gpt-3.5-turbo");

            JSONArray messagesArray = new JSONArray();

            JSONObject messageObject = new JSONObject();
            messageObject.put("role", "user");
            messageObject.put("content", message);

            messagesArray.put(messageObject);

            requestObject.put("messages", messagesArray);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, API_URL, requestObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                findViewById(R.id.midLayout).setVisibility(View.GONE);
                                Utils.animate(findViewById(R.id.parent), animationView, false);

                                JSONArray choicesArray = response.getJSONArray("choices");
                                JSONObject firstChoiceObject = choicesArray.getJSONObject(0);
                                JSONObject messageObject = firstChoiceObject.getJSONObject("message");
                                String content = messageObject.getString("content");

                                Log.d(TAG, "onResponse: " + content);
                                Message msg = new Message(content, false);
                                messages.add(msg);

                                chatAdapter.setMessages(messages);
                                chatRecyclerView.smoothScrollToPosition(chatAdapter.getItemCount() - 1);

                            } catch (JSONException e) {
                                Log.e(TAG, "Error parsing response JSON: " + e.getMessage());
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (error.networkResponse != null) {
                                String errorResponse = new String(error.networkResponse.data);
                                Log.e(TAG, "Client error response: " + errorResponse);
                            }
                            Log.e(TAG, "Error making API request: " + error);
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    headers.put("Authorization", "Bearer " + OPENAI_API_KEY);
                    return headers;
                }
            };
            int socketTimeout = 10000;
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            Log.e(TAG, "Error creating JSON request: " + e.getMessage());
        }
    }

}