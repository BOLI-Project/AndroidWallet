package com.boli.core.coins;

import com.boli.core.coins.families.BitFamily;

/**
 * Creado por ElpidioMC
 */
public class BolivarcoinMain extends BitFamily {
    private BolivarcoinMain() {
        id = "bolivarcoin.main";

        addressHeader = 85;
        p2shHeader = 5;
        acceptableAddressCodes = new int[]{addressHeader, p2shHeader};
        spendableCoinbaseDepth = 100;
        dumpedPrivateKeyHeader = 213;

        name = "Bolivarcoin";
        symbol = "BOLI";
        uriScheme = "bolivarcoin";
        bip44Index = 278;
        unitExponent = 8;
        feeValue = value(100000);
        minNonDust = value(1000);
        softDustLimit = value(100000);
        softDustPolicy = SoftDustPolicy.AT_LEAST_BASE_FEE_IF_SOFT_DUST_TXO_PRESENT;
        signedMessageHeader = toBytes("Bolivarcoin Signed Message:\n");
    }

    private static BolivarcoinMain instance = new BolivarcoinMain();
    public static synchronized CoinType get() {
        return instance;
    }
}