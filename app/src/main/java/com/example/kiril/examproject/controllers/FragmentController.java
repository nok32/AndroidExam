package com.example.kiril.examproject.controllers;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.example.kiril.examproject.R;

/**
 * Created by Kiril on 08/10/2016.
 */

public class FragmentController {
    private Activity mActivity;
    private FrameLayout mFragmentContainer;

    public FragmentController(Activity activity){
        mActivity = activity;
        mFragmentContainer = (FrameLayout) this.mActivity.findViewById(R.id.fragmentContainer);
    }

    public void dettachFragment(Fragment frag){
        mActivity.getFragmentManager()
                .beginTransaction()
                .remove(frag)
                .commit();

        if (frag != null){
            mFragmentContainer.setClickable(false);
        }

        RecyclerView rF = (RecyclerView) mActivity.findViewById(R.id.foodsRecyclerView);
        if (rF != null){
            rF.setVisibility(View.VISIBLE);
        }
        RecyclerView rC = (RecyclerView)mActivity.findViewById(R.id.categoriesRecyclerView);
        if (rC != null){
            rC.setVisibility(View.VISIBLE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void attachFragment(Fragment frag){
        dettachFragment(frag);

        mActivity.getFragmentManager()
                .beginTransaction()
                .add(mFragmentContainer.getId(), frag)
                .commit();

        mActivity.getFragmentManager().executePendingTransactions();

        // TODO: 09/10/2016 This is stupid
        RecyclerView rF = (RecyclerView) mActivity.findViewById(R.id.foodsRecyclerView);
        if (rF != null){
            rF.setVisibility(View.GONE);
        }
        RecyclerView rC = (RecyclerView)mActivity.findViewById(R.id.categoriesRecyclerView);
        if (rC != null){
            rC.setVisibility(View.GONE);
        }

        mFragmentContainer.setClickable(true);
    }

}
