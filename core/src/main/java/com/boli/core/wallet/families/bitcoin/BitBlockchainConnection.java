package com.boli.core.wallet.families.bitcoin;

import com.boli.core.network.ScriptStatus;
import com.boli.core.network.interfaces.BlockchainConnection;

/**
 * @author John L. Jegutanis
 */
public interface BitBlockchainConnection extends BlockchainConnection<BitTransaction> {
    void getUnspentTx(ScriptStatus status, BitTransactionEventListener listener);
}
