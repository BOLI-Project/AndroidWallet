package com.boli.core.coins.families;

import com.boli.core.messages.MessageFactory;
import com.boli.core.wallet.families.vpncoin.VpncoinTxMessage;

import javax.annotation.Nullable;

/**
 * @author John L. Jegutanis
 *
 * This family contains Vpncoin
 */
public abstract class VpncoinFamily extends BitFamily {
    {
        family = Families.VPNCOIN;
    }

    @Override
    @Nullable
    public MessageFactory getMessagesFactory() {
        return VpncoinTxMessage.getFactory();
    }
}