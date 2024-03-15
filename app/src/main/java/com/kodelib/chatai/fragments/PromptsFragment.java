package com.kodelib.chatai.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kodelib.chatai.R;
import com.kodelib.chatai.adapter.PromptAdapter;
import com.kodelib.chatai.adapter.PromptAdapter;
import com.kodelib.chatai.models.Assistant;

import java.util.ArrayList;

public class PromptsFragment extends Fragment {

    //Productivity
    RecyclerView rvWriting;
    PromptAdapter adapterWriting;
    ArrayList<Assistant> writingList;

    RecyclerView rvCreative;
    PromptAdapter adapterCreative;
    ArrayList<Assistant> creativeList;

    RecyclerView rvBusiness;
    PromptAdapter adapterBusiness;
    ArrayList<Assistant> businessList;

    RecyclerView rvSocial;
    PromptAdapter adapterSocial;
    ArrayList<Assistant> socialList;

    RecyclerView rvDevelopment;
    PromptAdapter adapterDev;
    ArrayList<Assistant> devList;

    RecyclerView rvPersonal;
    PromptAdapter adapterPersonal;
    ArrayList<Assistant> personalList;

    RecyclerView rvOther;
    PromptAdapter adapterOther;
    ArrayList<Assistant> otherList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prompts, container, false);
        writing(view);
        creative(view);
        business(view);
        social(view);
        development(view);
        personal(view);
        other(view);
        return view;
    }

    private void writing(View view) {
        rvWriting = view.findViewById(R.id.rvWriting);
        writingList = new ArrayList<>();
        writingList.add(new Assistant("Articles Generator", "Articles","Generate articles by using our AssistantAi App.", R.drawable.writing_article));
        writingList.add(new Assistant("Academic Help", "Articles","Write Academic precis, reports, & papers.", R.drawable.writing_academic));
        writingList.add(new Assistant("Summarize Text", "Articles","Quick prompts for summarizing text and paras.", R.drawable.writing_summery));
        writingList.add(new Assistant("Plagiarism Checker", "Articles","Check your articles, etc, to avoid plagiarism.", R.drawable.writing_plagiarism));
        adapterWriting = new PromptAdapter(getActivity(), writingList);
        rvWriting.setAdapter(adapterWriting);
        rvWriting.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void creative(View view) {
        rvCreative = view.findViewById(R.id.rvCreative);
        creativeList = new ArrayList<>();
        creativeList.add(new Assistant("Movies", "Creative","Write Movie Scripts & more.", R.drawable.creative_movie_scipts));
        creativeList.add(new Assistant("Music", "Creative","Know about songs, and music.", R.drawable.creative_song));
        creativeList.add(new Assistant("Stories", "Creative","Generate interesting stories in seconds.", R.drawable.creative_storyteller));
        creativeList.add(new Assistant("Poems", "Creative","Get poems by commanding AssistantAi.", R.drawable.creative_poem));
        adapterCreative = new PromptAdapter(getActivity(), creativeList);
        rvCreative.setAdapter(adapterCreative);
        rvCreative.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void business(View view) {
        rvBusiness = view.findViewById(R.id.rvBusiness);
        businessList = new ArrayList<>();
        businessList.add(new Assistant("Advertisement", "Business","Create interactive ads that convert.", R.drawable.business_advert));
        businessList.add(new Assistant("Job Listings", "Business","Get help in job listings and hunting.", R.drawable.business_job_post));
        businessList.add(new Assistant("Interviews", "Business","Interview simulator lets you prepare effectively.", R.drawable.business_answer));
        businessList.add(new Assistant("Email Writer", "Business","Generate awesome emails that convert.", R.drawable.business_email_writter));
        adapterBusiness = new PromptAdapter(getActivity(), businessList);
        rvBusiness.setAdapter(adapterBusiness);
        rvBusiness.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void social(View view) {
        rvSocial = view.findViewById(R.id.rvSocial);
        socialList = new ArrayList<>();
        socialList.add(new Assistant("Instagram", "Social","Instagram content ideas that engage.", R.drawable.social_instagram));
        socialList.add(new Assistant("Twitter", "Social","Twitter tweets that go viral.", R.drawable.social_twitter));
        socialList.add(new Assistant("TikTok", "Social","TikTok content ideas that bring attention.", R.drawable.social_tiktok));
        socialList.add(new Assistant("Facebook", "Social","Facebook posts that get thumb up.", R.drawable.social_facebook));
        adapterSocial = new PromptAdapter(getActivity(), socialList);
        rvSocial.setAdapter(adapterSocial);
        rvSocial.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void development(View view) {
        rvDevelopment = view.findViewById(R.id.rvDeveloper);
        devList = new ArrayList<>();
        devList.add(new Assistant("Code Assistance", "Development","Generate code suggestions, help with debugging.", R.drawable.developer_write_code));
        devList.add(new Assistant("Code Review", "Development","Review code and get feedback on code quality.", R.drawable.developer_explain_code));
        devList.add(new Assistant("Algorithm Design", "Development","Get help in designing algorithms for specific tasks or problems.", R.drawable.developer_write_code));
        adapterDev = new PromptAdapter(getActivity(), devList);
        rvDevelopment.setAdapter(adapterDev);
        rvDevelopment.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void personal(View view) {
        rvPersonal = view.findViewById(R.id.rvPersonal);
        personalList = new ArrayList<>();
        personalList.add(new Assistant("Birthday", "Personal","Generate birthday messages instantly.", R.drawable.personal_birthday));
        personalList.add(new Assistant("Invitation", "Personal","Generate Invitations of different occasions.", R.drawable.personal_inv));
        personalList.add(new Assistant("Apology", "Personal","Get an apology message that helps.", R.drawable.personal_apology));
        adapterPersonal = new PromptAdapter(getActivity(), personalList);
        rvPersonal.setAdapter(adapterPersonal);
        rvPersonal.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void other(View view) {
        rvOther = view.findViewById(R.id.rvOther);
        otherList = new ArrayList<>();
        otherList.add(new Assistant("Write a Joke", "Social","Generate jokes to enlighten your mood.", R.drawable.other_joke));
        otherList.add(new Assistant("Fitness Plan", "Social","Get help in your diet and fitness.", R.drawable.other_diet));
        otherList.add(new Assistant("Food Recipes", "Social","Get quick and delicious recipes.", R.drawable.other_food_recipe));
        otherList.add(new Assistant("Conversation", "Social","Talk to the AssistantAi about anything.", R.drawable.other_create_conv));
        adapterOther = new PromptAdapter(getActivity(), otherList);
        rvOther.setAdapter(adapterOther);
        rvOther.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }


}