package com.boli.core.coins;

import com.boli.core.coins.families.PeerFamily;

/**
 * Creado por ElpidioMC
 */
public class ArepacoinMain extends PeerFamily {
    private ArepacoinMain() {
        id = "arepacoin.main";

        addressHeader = 23;
        p2shHeader = 85;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        spendableCoinbaseDepth = 150;
        dumpedPrivateKeyHeader = 151;

        name = "Arepacoin";
        symbol = "AREPA";
        uriScheme = "arepacoin";
        bip44Index = 277;
        unitExponent = 8;
        feeValue = value(10000);
        minNonDust = value(1000);
        softDustLimit = value(1000000);
        softDustPolicy = SoftDustPolicy.AT_LEAST_BASE_FEE_IF_SOFT_DUST_TXO_PRESENT;
        signedMessageHeader = toBytes("Arepacoin Signed Message:\n");
    }

    private static ArepacoinMain instance = new ArepacoinMain();
    public static synchronized CoinType get() {
        return instance;
    }
}
