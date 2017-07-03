package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.evotor.framework.Utils;
import ru.evotor.framework.payment.PaymentSystem;
import ru.evotor.framework.payment.PaymentType;

public final class PaymentSystemMapper {
    private static final String KEY_PAYMENT_TYPE = "paymentType";
    private static final String KEY_USER_DESCRIPTION = "userDescription";

    @Nullable
    public static PaymentSystem from(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        PaymentType paymentType = Utils.safeValueOf(PaymentType.class, bundle.getString(KEY_USER_DESCRIPTION), PaymentType.UNKNOWN);
        String userDescription = bundle.getString(KEY_USER_DESCRIPTION);
        return new PaymentSystem(
                paymentType,
                userDescription
        );
    }

    @Nullable
    public static Bundle toBundle(@Nullable PaymentSystem paymentSystem) {
        if (paymentSystem == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PAYMENT_TYPE, paymentSystem.getPaymentType().name());
        bundle.putString(KEY_USER_DESCRIPTION, paymentSystem.getUserDescription());

        return bundle;
    }

    private PaymentSystemMapper() {
    }

}
