package com.boli.core.wallet.families.bitcoin;

import com.boli.core.network.ScriptStatus;
import com.boli.core.network.ServerClient.UnspentTx;
import com.boli.core.network.interfaces.TransactionEventListener;

import java.util.List;

/**
 * @author John L. Jegutanis
 */
public interface BitTransactionEventListener extends TransactionEventListener<BitTransaction> {
    void onUnspentTransactionUpdate(ScriptStatus status, List<UnspentTx> UnspentTxes);
}
