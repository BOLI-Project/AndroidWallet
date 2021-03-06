package com.boli.core.coins;

import com.boli.core.coins.families.BitFamily;
import com.boli.core.coins.families.Families;

/**
 * @author John L. Jegutanis
 */
public class MfcoinMain extends BitFamily {

    private MfcoinMain() {
        id = "mfcoin.main";

        addressHeader = 51;
        p2shHeader = 5;
        acceptableAddressCodes = new int[]{addressHeader, p2shHeader};
        spendableCoinbaseDepth = 80;
        dumpedPrivateKeyHeader = 179;

        name = "MFCoin";
        symbol = "MFC";
        uriScheme = "mfcoin";
        bip44Index = 99;
        unitExponent = 8;
        feeValue = value(10000);
        minNonDust = value(1); // 0.00001 LTC mininput
        softDustLimit = value(1000000); // 0.001 LTC
        //softDustPolicy = SoftDustPolicy.BASE_FEE_FOR_EACH_SOFT_DUST_TXO;
        softDustPolicy = SoftDustPolicy.AT_LEAST_BASE_FEE_IF_SOFT_DUST_TXO_PRESENT;
        signedMessageHeader = toBytes("MFCoin Signed Message:\n");
        family = Families.PEERCOIN;
    }

    private static MfcoinMain instance = new MfcoinMain();

    public static synchronized CoinType get() {
        return instance;
    }
}