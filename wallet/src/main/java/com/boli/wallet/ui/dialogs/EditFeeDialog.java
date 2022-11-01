package com.boli.wallet.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import com.boli.core.coins.CoinID;
import com.boli.core.coins.CoinType;
import com.boli.core.coins.Value;
import com.boli.core.coins.ValueType;
import com.boli.wallet.Configuration;
import com.boli.wallet.Constants;
import com.boli.wallet.R;
import com.boli.wallet.WalletApplication;
import com.boli.wallet.databinding.EditFeeDialogBinding;
import com.boli.wallet.ui.DialogBuilder;

import static com.boli.core.Preconditions.checkState;

/**
 * @author John L. Jegutanis
 */
public class EditFeeDialog extends DialogFragment {

    Configuration configuration;
    Resources resources;

    private EditFeeDialogBinding binding;

    public static EditFeeDialog newInstance(ValueType type) {
        EditFeeDialog dialog = new EditFeeDialog();
        dialog.setArguments(new Bundle());
        dialog.getArguments().putString(Constants.ARG_COIN_ID, type.getId());
        return dialog;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        WalletApplication application = (WalletApplication) activity.getApplication();
        configuration = application.getConfiguration();
        resources = application.getResources();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        checkState(getArguments().containsKey(Constants.ARG_COIN_ID), "Must provide coin id");
        binding = EditFeeDialogBinding.inflate(LayoutInflater.from(getContext()));

        // TODO move to xml
        binding.feeAmount.setSingleLine(true);

        final CoinType type = CoinID.typeFromId(getArguments().getString(Constants.ARG_COIN_ID));
        binding.feeAmount.resetType(type);

        String feePolicy;
        switch (type.getFeePolicy()) {
            case FEE_PER_KB:
                feePolicy = resources.getString(R.string.tx_fees_per_kilobyte);
                break;
            case FLAT_FEE:
                feePolicy = resources.getString(R.string.tx_fees_per_transaction);
                break;
            default:
                throw new RuntimeException("Unknown fee policy " + type.getFeePolicy());
        }
        binding.feeDescription.setText(resources.getString(R.string.tx_fees_description, feePolicy));

        final Value fee = configuration.getFeeValue(type);
        binding.feeAmount.setAmount(fee, false);

        final DialogBuilder builder = new DialogBuilder(getActivity());
        builder.setTitle(resources.getString(R.string.tx_fees_title, type.getName()));
        builder.setView(binding.getRoot());
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Value newFee = binding.feeAmount.getAmount();
                        if (newFee != null && !newFee.equals(fee)) {
                            configuration.setFeeValue(newFee);
                        }
                        break;
                    case DialogInterface.BUTTON_NEUTRAL:
                        configuration.resetFeeValue(type);
                        break;
                }


            }
        };
        builder.setNegativeButton(R.string.button_cancel, onClickListener);
        builder.setNeutralButton(R.string.button_default, onClickListener);
        builder.setPositiveButton(R.string.button_ok, onClickListener);

        return builder.create();
    }
}
