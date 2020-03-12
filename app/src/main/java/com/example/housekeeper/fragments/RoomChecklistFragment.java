package com.example.housekeeper.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.housekeeper.R;
import com.example.housekeeper.adapter.AdapterChecklist;
import com.example.housekeeper.model.ModelChecklist;
import com.example.housekeeper.sharedPrefManager.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoomChecklistFragment extends Fragment {

    private RecyclerView roomRecyclerVIew;
    private Button updateBtn;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private List<ModelChecklist> checklists = new ArrayList<>();
    private String mAccessToken, mPhoneNo, mLanguage;
    private String checkListTypeArg;

    public RoomChecklistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_room_checklist, container, false);

        checkListTypeArg = getArguments().getString("checklist_type");

        roomRecyclerVIew = root.findViewById(R.id.room_recyclerview);
        updateBtn = root.findViewById(R.id.update_btn);
        roomRecyclerVIew.setHasFixedSize(true);
        mAccessToken = SharedPrefManager.getInstance(root.getContext()).getLogin().getAccessToken();
        mPhoneNo = SharedPrefManager.getInstance(root.getContext()).getPhoneAndLanguage().getPhone();
        mLanguage = SharedPrefManager.getInstance(root.getContext()).getPhoneAndLanguage().getLanguage();
        linearLayoutManager = new LinearLayoutManager(getContext());


        for (int i = 0; i < 10; i++) {
//            checklists.add(new ModelChecklist(checkListTypeArg + i, false));
        }


        roomRecyclerVIew.setLayoutManager(linearLayoutManager);
        adapter = new AdapterChecklist(getContext(), checklists);
        roomRecyclerVIew.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ModelChecklist list : checklists) {

//                    Log.i("Member name: ", list.getCheckItemName() + list.isChecked());

                }
//                Log.d(TAG, "onClick: UpdateBtn" + checklists.);
            }
        });


        return root;
    }
}
