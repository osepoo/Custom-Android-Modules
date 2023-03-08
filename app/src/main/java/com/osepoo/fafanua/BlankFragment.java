package com.osepoo.fafanua;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.facebook.shimmer.ShimmerFrameLayout;


public class BlankFragment extends Fragment {

    ShimmerFrameLayout shimmerFrameLayout, shimmerFrameLayout2;
    TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);


        textView = view.findViewById(R.id.textView2);
        shimmerFrameLayout = (ShimmerFrameLayout) view.findViewById(R.id.shimmermain);
        shimmerFrameLayout2 = (ShimmerFrameLayout) view.findViewById(R.id.slpashshimmer2);

        shimmerFrameLayout.startShimmer();
        shimmerFrameLayout2.startShimmer();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(requireContext(),BottomBarActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) requireContext(), textView, ViewCompat.getTransitionName(textView));
                startActivity(intent, options.toBundle());
            }
        },3000);

        return view;
    }

}
