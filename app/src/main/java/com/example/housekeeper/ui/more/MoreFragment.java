package com.example.housekeeper.ui.more;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.housekeeper.R;
import com.example.housekeeper.adapter.AdapterMoreList;
import com.example.housekeeper.model.ModelMoreList;

import java.util.ArrayList;
import java.util.List;

import static com.example.housekeeper.R.layout.fragment_more;

public class MoreFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ModelMoreList> moreList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

                ViewModelProviders.of(this).get(MoreViewModel.class);
        View root = inflater.inflate(fragment_more, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.morelist_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        moreList = new ArrayList<>();

        /*for (int i=0; i<10; i++) {
                ModelMoreList modelMoreList = new ModelMoreList(
                        "Name " + (i+1),
                        "Image "
                );

            moreList.add(modelMoreList);
        }*/

        moreList.add(new ModelMoreList("Settings", ""));
        moreList.add(new ModelMoreList("Language", ""));
        moreList.add(new ModelMoreList("Sign Out", ""));

        adapter = new AdapterMoreList(getActivity(), moreList);
        recyclerView.setAdapter(adapter);

//        final TextView textView = root.findViewById(R.id.text_more);
//        moreViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        }\

        return root;
    }


}