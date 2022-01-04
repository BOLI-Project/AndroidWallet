package com.boli.wallet.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;

import com.boli.wallet.R;
import com.boli.wallet.util.Fonts;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseWalletActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        TextView version = (TextView) findViewById(R.id.about_version);
        if (getWalletApplication().packageInfo() != null) {
            version.setText(getWalletApplication().packageInfo().versionName);
        } else {
            version.setVisibility(View.INVISIBLE);
        }

        //Fonts.setTypeface(findViewById(R.id.translation_globe), Fonts.Font.COINOMI_FONT_ICONS);
    }

    public void myFace(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pg/BolivarcoinAPP/"));
        startActivity(browserIntent);
    }

    public void myInstag(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/bolicoin/"));
        startActivity(browserIntent);
    }

    public void myTwitt(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/BolivarCoin_XT"));
        startActivity(browserIntent);
    }

    public void myTeleg(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/bolivarcoinoficial"));
        startActivity(browserIntent);
    }

    public void myDiscord(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://discordapp.com/invite/Ea8Xprg"));
        startActivity(browserIntent);
    }
}