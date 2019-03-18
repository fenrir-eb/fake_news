package com.example.jmarkovic16_homework1.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.jmarkovic16_homework1.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class SavePasswordDialog extends DialogFragment {

    public interface SavePasswordDialogListener{
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }


    SavePasswordDialogListener listener;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        alertBuilder.setMessage(R.string.save_password)
                .setPositiveButton(R.string.save_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        }).setNegativeButton(R.string.save_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });
        return alertBuilder.create();
    }
}
