package org.example.service.implementation;

import me.dynomake.yookassa.Yookassa;
import me.dynomake.yookassa.exception.BadRequestException;
import me.dynomake.yookassa.exception.UnspecifiedShopInformation;
import me.dynomake.yookassa.model.Amount;
import me.dynomake.yookassa.model.Payment;
import org.example.service.PaymentService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


@Service
public class PaymentServiceImpl implements PaymentService {

    public String payForOffer(Integer offerId, String name) {
        try {
            Yookassa yookassa = Yookassa.initialize(356950, "test_BPXHmGzpHRmRz4d1yP7QnEdcmroALAerh44khS6MwPI");
            Payment payment = yookassa.createPayment(new Amount(BigDecimal.valueOf(1), "EUR"), "Test Payment", "");
            return payment.confirmation.confirmation_url;
        } catch (UnspecifiedShopInformation | IOException | BadRequestException exception) {
            throw new RuntimeException(exception);
        }
    }
}
