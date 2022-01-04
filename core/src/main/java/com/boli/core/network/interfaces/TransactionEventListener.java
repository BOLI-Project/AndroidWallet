package com.boli.core.network.interfaces;

import com.boli.core.network.ScriptStatus;
import com.boli.core.network.BlockHeader;
import com.boli.core.network.ServerClient.HistoryTx;

import java.util.List;

/**
 * @author John L. Jegutanis
 */
public interface TransactionEventListener<T> {
    void onNewBlock(BlockHeader header);

    void onBlockUpdate(BlockHeader header);

    void onScriptStatusUpdate(ScriptStatus status);

    void onTransactionHistory(ScriptStatus status, List<HistoryTx> historyTxes);

    void onTransactionUpdate(T transaction);

    void onTransactionBroadcast(T transaction);

    void onTransactionBroadcastError(T transaction);
}
