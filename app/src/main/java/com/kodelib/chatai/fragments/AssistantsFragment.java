package com.kodelib.chatai.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kodelib.chatai.R;
import com.kodelib.chatai.adapter.AssistantAdapter;
import com.kodelib.chatai.models.Assistant;

import java.util.ArrayList;

public class AssistantsFragment extends Fragment {

    //Productivity
    RecyclerView rvProductivity;
    AssistantAdapter adapterPro;
    ArrayList<Assistant> proList;
    //Wellness
    RecyclerView rvWellness;
    AssistantAdapter adapterWell;
    ArrayList<Assistant> listWellness;
    //rvHistory
    RecyclerView rvHistory;
    AssistantAdapter adapterHistory;
    ArrayList<Assistant> listHistory;
    //rvCelebrity
    RecyclerView rvCelebrity;
    AssistantAdapter adapterCelebrity;
    ArrayList<Assistant> listCelebrity;
    //rvCEO
    RecyclerView rvCEO;
    AssistantAdapter adapterCEO;
    ArrayList<Assistant> listCEO;
    //rvAthlete
    RecyclerView rvAthlete;
    AssistantAdapter adapterAthlete;
    ArrayList<Assistant> listAthlete;
    //rvAuthor
    RecyclerView rvAuthor;
    AssistantAdapter adapterAuthor;
    ArrayList<Assistant> listAuthor;
    //rvStars
    RecyclerView rvStars;
    AssistantAdapter adapterStars;
    ArrayList<Assistant> listStars;
    //rvHarry
    RecyclerView rvHarry;
    AssistantAdapter adapterHarry;
    ArrayList<Assistant> listHarry;

    //Marvel
    RecyclerView rvMarvel;
    AssistantAdapter adapterMarvel;
    ArrayList<Assistant> listMarvel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assistants, container, false);
        productivity(view);
        wellness(view);
        history(view);
        celebrity(view);
        ceo(view);
        athlete(view);
        author(view);
        stars(view);
        harry(view);
        marvel(view);
        return view;
    }

    private void productivity(View view) {
        rvProductivity = view.findViewById(R.id.rvProductivity);
        proList = new ArrayList<>();
        proList.add(new Assistant("Software Engineer", "Productivity", R.drawable.pro_software_engr));
        proList.add(new Assistant("Sales Development", "Productivity", R.drawable.pro_sales_development));
        proList.add(new Assistant("Human Resources", "Productivity", R.drawable.pro_human_resources));
        proList.add(new Assistant("Fashion Analyst", "Productivity", R.drawable.pro_fashion_stylisht));
        proList.add(new Assistant("Marketing Wizard", "Productivity", R.drawable.pro_email_marketing_wizard));
        proList.add(new Assistant("Product Manager", "Productivity", R.drawable.pro_product_manager));
        proList.add(new Assistant("Systems Engineer", "Productivity", R.drawable.pro_systems_engineer));
        proList.add(new Assistant("Social Media\nExpert", "Productivity", R.drawable.pro_social_media));
        proList.add(new Assistant("Legal Translator", "Productivity", R.drawable.pro_legal_translator));
        proList.add(new Assistant("Financial Analyst", "Productivity", R.drawable.pro_systems_engineer));
        proList.add(new Assistant("Marketing Management", "Productivity", R.drawable.pro_marketing_mgmt));
        adapterPro = new AssistantAdapter(getActivity(), proList);
        rvProductivity.setAdapter(adapterPro);
        rvProductivity.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void wellness(View view) {
        rvWellness = view.findViewById(R.id.rvWellness);
        listWellness = new ArrayList<>();
        listWellness.add(new Assistant("Personal Trainer", "Wellness", R.drawable.well_personal_trainer));
        listWellness.add(new Assistant("Fortune Teller", "Wellness", R.drawable.well_fortune_teller));
        listWellness.add(new Assistant("Dating Expert", "Wellness", R.drawable.well_dating_expert));
        listWellness.add(new Assistant("Psychologist", "Wellness", R.drawable.well_psychologist));
        listWellness.add(new Assistant("Flower Expert", "Wellness", R.drawable.well_flower_expert));
        listWellness.add(new Assistant("Michelin Star", "Wellness", R.drawable.well_michelin_star));
        listWellness.add(new Assistant("Horoscope Reader", "Wellness", R.drawable.well_horscpe_reader));
        listWellness.add(new Assistant("Interior Designer", "Wellness", R.drawable.well_interior_designer));
        adapterWell = new AssistantAdapter(getActivity(), listWellness);
        rvWellness.setAdapter(adapterWell);
        rvWellness.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void celebrity(View view) {
        rvCelebrity = view.findViewById(R.id.rvCelebrity);
        listCelebrity = new ArrayList<>();
        listCelebrity.add(new Assistant("Taylor Swift", "Celebrity", R.drawable.celeb_tailor_swift));
        listCelebrity.add(new Assistant("Joe Rogan", "Celebrity", R.drawable.celeb_joe_r));
        listCelebrity.add(new Assistant("Hannah Montana", "Celebrity", R.drawable.celeb_hannah_m));
        listCelebrity.add(new Assistant("Meryl Steep", "Celebrity", R.drawable.celeb_meryl));
        listCelebrity.add(new Assistant("Skrillex", "Celebrity", R.drawable.celeb_skrillx));
        listCelebrity.add(new Assistant("George Clooney", "Celebrity", R.drawable.celeb_george_c));
        listCelebrity.add(new Assistant("John Lennon", "Celebrity", R.drawable.celeb_john_l));
        listCelebrity.add(new Assistant("Drake", "Celebrity", R.drawable.celeb_drake));
        listCelebrity.add(new Assistant("Jocko Willink", "Celebrity", R.drawable.celeb_jocko));
        listCelebrity.add(new Assistant("Oprah Winfrey", "Celebrity", R.drawable.celeb_oprah));
        listCelebrity.add(new Assistant("Ariana Grande", "Celebrity", R.drawable.celeb_ariana_g));
        listCelebrity.add(new Assistant("Dua Lipa", "Celebrity", R.drawable.celeb_dua_lipa));
        listCelebrity.add(new Assistant("Sandra Bullock", "Celebrity", R.drawable.celeb_sandra_bullock));
        listCelebrity.add(new Assistant("Steven Spielberg", "Celebrity", R.drawable.celeb_steven_sp));
        listCelebrity.add(new Assistant("Ray Charles", "Celebrity", R.drawable.celeb_ray_charles));
        adapterCelebrity = new AssistantAdapter(getActivity(), listCelebrity);
        rvCelebrity.setAdapter(adapterCelebrity);
        rvCelebrity.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void history(View view) {
        rvHistory = view.findViewById(R.id.rvHistory);
        listHistory = new ArrayList<>();
        listHistory.add(new Assistant("Leonardo DaVinci", "History", R.drawable.his_leonardo_da));
        listHistory.add(new Assistant("Socrates", "History", R.drawable.his_socrates));
        listHistory.add(new Assistant("Cyrus", "History", R.drawable.his_cyrus));
        listHistory.add(new Assistant("Ben Franklin", "History", R.drawable.his_ban_frackln));
        listHistory.add(new Assistant("Joan of Arc", "History", R.drawable.his_john_arc));
        listHistory.add(new Assistant("Ronald Raegan", "History", R.drawable.his_ronald));
        adapterHistory = new AssistantAdapter(getActivity(), listHistory);
        rvHistory.setAdapter(adapterHistory);
        rvHistory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void ceo(View view) {
        rvCEO = view.findViewById(R.id.rvCEOs);
        listCEO = new ArrayList<>();
        listCEO.add(new Assistant("Elon Musk", "CEO", R.drawable.ceo_elon));
        listCEO.add(new Assistant("Steve Jobs", "CEO", R.drawable.ceo_steve_jobs));
        listCEO.add(new Assistant("Bill Gates", "CEO", R.drawable.ceo_bill_gates));
        adapterCEO = new AssistantAdapter(getActivity(), listCEO);
        rvCEO.setAdapter(adapterCEO);
        rvCEO.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void athlete(View view) {
        rvAthlete = view.findViewById(R.id.rvAthlete);
        listAthlete = new ArrayList<>();
        listAthlete.add(new Assistant("Maria Sharapova", "Athlete", R.drawable.athlete_maria));
        listAthlete.add(new Assistant("Tom Brady", "Athlete", R.drawable.athlete_tom));
        listAthlete.add(new Assistant("LeBron James", "Athlete", R.drawable.athlete_lebron));
        listAthlete.add(new Assistant("Roger Federer", "Athlete", R.drawable.athlete_roger));
        listAthlete.add(new Assistant("Babe Ruth", "Athlete", R.drawable.athlete_babe));
        listAthlete.add(new Assistant("Neymar", "Athlete", R.drawable.athlete_neymar));
        listAthlete.add(new Assistant("Lional Messi", "Athlete", R.drawable.athlete_lionel));
        listAthlete.add(new Assistant("David Goggins", "Athlete", R.drawable.athlete_david));
        adapterAthlete = new AssistantAdapter(getActivity(), listAthlete);

        rvAthlete.setAdapter(adapterAthlete);
        rvAthlete.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void author(View view) {
        rvAuthor = view.findViewById(R.id.rvAuthors);
        listAuthor = new ArrayList<>();
        listAuthor.add(new Assistant("Dr. Seuss", "Author", R.drawable.author_dr_seuss));
        listAuthor.add(new Assistant("RL. Stein", "Author", R.drawable.author_r_l));
        listAuthor.add(new Assistant("Stephen King", "Author", R.drawable.author_stephen_king));
        listAuthor.add(new Assistant("Jane Austen", "Author", R.drawable.author_jane));
        listAuthor.add(new Assistant("Shakespeare", "Author", R.drawable.author_shakespear));
        listAuthor.add(new Assistant("Shel Silverstein", "Author", R.drawable.author_shel));
        listAuthor.add(new Assistant("Mark Twain", "Author", R.drawable.author_mark_twain));
        listAuthor.add(new Assistant("Sun Tzu", "Author", R.drawable.author_sun));
        adapterAuthor = new AssistantAdapter(getActivity(), listAuthor);

        rvAuthor.setAdapter(adapterAuthor);
        rvAuthor.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void stars(View view) {
        rvStars = view.findViewById(R.id.rvStarWars);
        listStars = new ArrayList<>();
        listStars.add(new Assistant("Jar Blinks", "Star Wars", R.drawable.star_jar));
        listStars.add(new Assistant("Darth Vader", "Star Wars", R.drawable.star_darth));
        listStars.add(new Assistant("Chewbacca", "Star Wars", R.drawable.star_chewbacca));
        listStars.add(new Assistant("Princess Leia", "Star Wars", R.drawable.star_princess));
        listStars.add(new Assistant("Yoda", "Star Wars", R.drawable.star_yoda));
        listStars.add(new Assistant("Obi-Wan Kenobi", "Star Wars", R.drawable.star_obi));
        adapterStars = new AssistantAdapter(getActivity(), listStars);

        rvStars.setAdapter(adapterStars);
        rvStars.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void harry(View view) {
        rvHarry = view.findViewById(R.id.rvHarryP);
        listHarry = new ArrayList<>();
        listHarry.add(new Assistant("Harry Potter", "Harry Potter", R.drawable.harry_pot_herry));
        listHarry.add(new Assistant("Ron Weasly", "Harry Potter", R.drawable.harry_pot_ron));
        listHarry.add(new Assistant("Hermoine Granger", "Harry Potter", R.drawable.harry_pot_hermione));
        listHarry.add(new Assistant("Hagrid", "Harry Potter", R.drawable.harry_pot_hagrid));
        listHarry.add(new Assistant("Dobby the elf", "Harry Potter", R.drawable.harry_pot_dobby));
        listHarry.add(new Assistant("Professor Snape", "Harry Potter", R.drawable.harry_pot_professor_snap));
        listHarry.add(new Assistant("Voldemort", "Harry Potter", R.drawable.harry_pot_vold));
        listHarry.add(new Assistant("Draco Malfoy", "Harry Potter", R.drawable.harry_pot_draco));
        adapterHarry = new AssistantAdapter(getActivity(), listHarry);

        rvHarry.setAdapter(adapterHarry);
        rvHarry.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void marvel(View view) {
        rvMarvel = view.findViewById(R.id.rvMarvel);
        listMarvel = new ArrayList<>();
        listMarvel.add(new Assistant("Tony Start", "Marvel", R.drawable.marvel_stark));
        listMarvel.add(new Assistant("Natasha Romanoff", "Marvel", R.drawable.marvel_natasha));
        listMarvel.add(new Assistant("Thanos", "Marvel", R.drawable.marvel_thanos));
        listMarvel.add(new Assistant("Ant Man", "Marvel", R.drawable.marvel_antman));
        listMarvel.add(new Assistant("Star Lord", "Marvel", R.drawable.marvel_starlod));
        listMarvel.add(new Assistant("Groot", "Marvel", R.drawable.marvel_groot));
        adapterMarvel = new AssistantAdapter(getActivity(), listMarvel);

        rvMarvel.setAdapter(adapterMarvel);
        rvMarvel.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }


}