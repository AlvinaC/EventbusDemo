package com.eventbusdemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.eventbusdemo.R;
import com.eventbusdemo.activity.MainActivity;
import com.eventbusdemo.common.RecyclerAdapter;
import com.eventbusdemo.models.Events;
import com.eventbusdemo.util.EventbusApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;

/**
 * Created by alvina.rasquinha on 27/03/18.
 */

public class TabFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private RecyclerAdapter mAdapter;
    private int which;


    public TabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        unbinder = ButterKnife.bind(this, view);
        getArgs();
        setUpRecycler();
        registerEventBus();
        return view;
    }

    private void registerEventBus() {
        switch (which) {
            case 2:
                ((EventbusApp) getActivity().getApplication())
                        .bus()
                        .toObservable()
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object object) throws Exception {
                                if (object instanceof Events.ListChangeEvent) {
                                    recyclerView.getAdapter().notifyDataSetChanged();
                                }
                            }
                        });
                break;
        }
    }

    private void getArgs() {
        Bundle b = getArguments();
        this.which = b.getInt("which");
    }

    private void setUpRecycler() {
        mAdapter = new RecyclerAdapter(getActivity(), which);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
