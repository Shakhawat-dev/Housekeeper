package com.example.housekeeper.custom;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.housekeeper.R;
import com.example.housekeeper.model.ModelDefectDialog;

public class DefectDialog extends DialogFragment {

    DefectDialogTapListener dialogTapListener;
    private ModelDefectDialog defectDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View mView = inflater.inflate(R.layout.dialog_defect_info, null);
        builder.setView(mView);

        Spinner mDefectSpinner = mView.findViewById(R.id.department_spinner);
        CheckBox mDefectCheckbox = mView.findViewById(R.id.notify_checkbox);
        RatingBar mDefectRatingBar = mView.findViewById(R.id.priority_rating_bar);
        EditText mCaption = mView.findViewById(R.id.defect_caption_ed);
        EditText mRemark = mView.findViewById(R.id.defect_remark_ed);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                defectDialog = new ModelDefectDialog(
                        mCaption.getText().toString(),
                        (String) mDefectSpinner.getSelectedItem(),
                        mDefectCheckbox.isChecked(),
                        Integer.valueOf((int) mDefectRatingBar.getRating()),
                        mRemark.getText().toString()
                );

                dialogTapListener.onOkBtnTapped(defectDialog);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;
        try {
            dialogTapListener = (DefectDialogTapListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "Must implement DefectDialogTapListener");
        }

    }

    public interface DefectDialogTapListener {
        void onOkBtnTapped(ModelDefectDialog defectDialog);
    }
}
