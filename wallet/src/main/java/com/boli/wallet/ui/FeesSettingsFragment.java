package com.boli.wallet.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.boli.core.coins.Value;
import com.boli.wallet.Configuration;
import com.boli.wallet.R;
import com.boli.wallet.WalletApplication;
import com.boli.wallet.databinding.FragmentFeesSettingsListBinding;
import com.boli.wallet.ui.adaptors.FeesListAdapter;
import com.boli.wallet.ui.dialogs.EditFeeDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Fragment that restores a wallet
 */
public class FeesSettingsFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static final String EDIT_FEE_DIALOG = "edit_fee_dialog";


    private FragmentFeesSettingsListBinding binding;

    private Configuration config;
    private Context context;
    private FeesListAdapter adapter;

    public FeesSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FeesListAdapter(context, config);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFeesSettingsListBinding.inflate(inflater, container, false);
        binding.coinsList.setAdapter(adapter);

        setOnClickListeners();

        return binding.getRoot();
    }

    private void setOnClickListeners(){
        editFee();
    }

    void editFee() {
        binding.coinsList.setOnItemClickListener((adapterView, view, i, l) -> {
            Value fee = (Value) binding.coinsList.getItemAtPosition(i);
            // Create the fragment and show it as a dialog.
            DialogFragment editFeeDialog = EditFeeDialog.newInstance(fee.type);
            editFeeDialog.show(getFragmentManager(), EDIT_FEE_DIALOG);
        });
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        this.context = context;
        WalletApplication application = (WalletApplication) context.getApplicationContext();
        config = application.getConfiguration();
        config.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        config.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (Configuration.PREFS_KEY_FEES.equals(key)) {
            adapter.update();
        }
    }
}