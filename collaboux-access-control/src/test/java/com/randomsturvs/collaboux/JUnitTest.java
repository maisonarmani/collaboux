package com.randomsturvs.collaboux;

import com.randomsturvs.collaboux.encoder.CollabouxPasswordEncoder;
import com.randomsturvs.collaboux.model.User;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.DumpedPrivateKey;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import javax.validation.ValidationException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class JUnitTest {

    @Mock
    CollabouxPasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(JUnitTest.class);

    @Before
    public void init() {
        Mockito.when(passwordEncoder.encode(any())).thenReturn("Maison");
    }

    @Test
    public void testMockedPasswordEncoder() {
        logger.info(passwordEncoder.encode("P"));
    }

    @Test(expected = ValidationException.class)
    public void testValidator() {

        User user = new User();
        user.setPhoneNumber("08167267326");
        user.setUserName("Maison Armani");
    }

    // 2227f444-9e0c-46e7-ae30-0bcddfa20217
    @Test
    public void testBitCoin() throws IOException {
        //Scanner reader  = new Scanner(System.in);
        //System.out.println("Enter Bitcoin Address: ");
        String GivenAddress = "2227f444-9e0c-46e7-ae30-0bcddfa20217";
        PrintWriter writer = new PrintWriter(new FileWriter("foundaddress.txt"));

        for (; ; ) {
            String net = "prod";

            ECKey key = new ECKey();
            final NetworkParameters netParams;
            if (net.equals("prod")) {
                netParams = NetworkParameters.prodNet();
            } else {
                netParams = NetworkParameters.testNet();
            }
            Address addressFromKey = key.toAddress(netParams);

            DumpedPrivateKey privatekey2 = key.getPrivateKeyEncoded(netParams);


            if (GivenAddress.equals(addressFromKey)) {
                writer.print(addressFromKey + " " + privatekey2);
                writer.flush();
                System.out.println("Using " + net + " network, Generated address:\n" + addressFromKey +  privatekey2);
                break;
            }

        }
        //reader.close();
        writer.flush();
        writer.close();
    }


    @Test
    public void testConicsl() throws UnknownHostException {
        System.out.println(java.net.InetAddress.getLocalHost().getCanonicalHostName());
    }
}

