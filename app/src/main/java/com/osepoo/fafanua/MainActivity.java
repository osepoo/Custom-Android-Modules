package com.osepoo.fafanua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(androidx.core.R.id.action_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);

        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.teal_700));
        }

        fragmentManager = getSupportFragmentManager();

        final PaperOnboardingFragment onBoardingFragment = PaperOnboardingFragment.newInstance(getDataForOnboarding());

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, onBoardingFragment);
        fragmentTransaction.commit();

        onBoardingFragment.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment bf = new BlankFragment();
                fragmentTransaction.replace(R.id.fragment_container, bf);
                fragmentTransaction.commit();
            }
        });
    }

    private ArrayList<PaperOnboardingPage> getDataForOnboarding() {
        // prepare data
        PaperOnboardingPage scr1 = new PaperOnboardingPage("Welcome", "I couldn't sleep so you are at our fourth app screening",
                Color.parseColor("#678FB4"), R.drawable.osepoo_210, R.drawable.iconshandshake50);
        PaperOnboardingPage scr2 = new PaperOnboardingPage("Buzz", "We create this next one depending on how much buzz we get from you",
                Color.parseColor("#65B0B4"), R.drawable.iconsbuzzlightyear200, R.drawable.iconsbee50);
        PaperOnboardingPage scr3 = new PaperOnboardingPage("Tranlator", "Like Google-Translate, we create Osepoo-Translate ",
                Color.parseColor("#9B90BC"), R.drawable.iconstranslator100, R.drawable.iconskey50);
        PaperOnboardingPage scr4 = new PaperOnboardingPage("Google Play", "We'll play this one by ear",
                Color.parseColor("#678FB4"), R.drawable.iconsgoogleplay200, R.drawable.iconslaughing48);
        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);
        elements.add(scr4);
        return elements;
    }
}