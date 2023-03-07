package com.bankapp.application.util;

import java.io.Serializable;
import java.util.Random;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class Random4DigitIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sesion, Object object) {
        Random random = new Random();
        int id = 1000 + random.nextInt(9000); //gera codigo aleatorio de 4 digitos
        return id;
    }
}
