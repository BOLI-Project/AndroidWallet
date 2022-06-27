package com.boli.wallet.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boli.core.coins.CoinType;
import com.boli.core.coins.Value;
import com.boli.core.util.GenericUtils;
import com.boli.core.wallet.WalletAccount;
import com.boli.wallet.ExchangeRatesProvider.ExchangeRate;
import com.boli.wallet.R;
import com.boli.wallet.databinding.CoinListRowBinding;
import com.boli.wallet.util.WalletUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author John L. Jegutanis
 */
public class CoinListItem extends LinearLayout implements Checkable {

    private CoinListRowBinding binding;
    private boolean isChecked = false;
    private CoinType type;

    public CoinListItem(Context context) {
        super(context);

        binding = CoinListRowBinding.inflate(LayoutInflater.from(context), this, true);
    }

    public void setAccount(WalletAccount account) {
        this.type = account.getCoinType();
        binding.itemText.setText(account.getDescriptionOrCoinName());
        binding.itemIcon.setImageResource(WalletUtils.getIconRes(account));
    }

    public void setCoin(CoinType type) {
        this.type = type;
        binding.itemText.setText(type.getName());
        binding.itemIcon.setImageResource(WalletUtils.getIconRes(type));
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        if (exchangeRate != null && type != null) {
            Value localAmount = exchangeRate.rate.convert(type.oneCoin());
            setFiatAmount(localAmount);
        } else {
            binding.amount.setVisibility(View.GONE);
        }
    }

    public void setAmount(Value value) {
        binding.amount.setAmount(GenericUtils.formatCoinValue(value.type, value, true));
        binding.amount.setSymbol(value.type.getSymbol());
        binding.amount.setVisibility(View.VISIBLE);
    }

    private void setFiatAmount(Value value) {
        binding.amount.setAmount(GenericUtils.formatFiatValue(value));
        binding.amount.setSymbol(value.type.getSymbol());
        binding.amount.setVisibility(View.VISIBLE);
    }

    public void setAmountSingleLine(boolean isSingleLine) {
        binding.amount.setSingleLine(isSingleLine);
    }

    @Override
    public void setChecked(boolean checked) {
        isChecked = checked;

        if (isChecked) {
            binding.getRoot().setBackgroundResource(R.color.primary_100);
        } else {
            binding.getRoot().setBackgroundResource(0);
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);
    }
}
