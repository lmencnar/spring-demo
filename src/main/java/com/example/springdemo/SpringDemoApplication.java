package com.example.springdemo;

import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.mq.spring.boot.MQConnectionFactoryCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;


import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

@SpringBootApplication
@EnableJms
public class SpringDemoApplication {

    static final String qName = "DEV.QUEUE.ORDER"; // A queue from the default MQ Developer container config

    public static void main(String[] args) {

        /*
        // not needed when jms customizer provided

        System.setProperty("javax.net.ssl.trustStore", "rootCA-new.p12");
        System.setProperty("javax.net.ssl.trustStorePassword", "password");
        System.setProperty("javax.net.ssl.trustStoreType", "pkcs12");

         */

        // System.setProperty("javax.net.debug", "all");

        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(SpringDemoApplication.class, args);

        // Create the JMS Template object to control connections and sessions.
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        // Send a single message with a timestamp
        String msg = "Hello from IBM MQ at " + new Date();

        // The default SimpleMessageConverter class will be called and turn a String
        // into a JMS TextMessage
        jmsTemplate.convertAndSend(qName, msg);

        status();
    }

    @Value("${trustStorePath}")
    String trustStorePath;

    @Value("${trustStorePassPath}")
    String trustStorePassPath;

    private SSLContext createSSlContext()  {
        SSLContext sslContext = null;

        try {

            sslContext = SSLContext.getInstance("TLS");
            // Load TrustStore
            KeyStore trustStore = KeyStore.getInstance("PKCS12");
            trustStore.load(getInputStream(trustStorePath), getPassword(trustStorePassPath).toCharArray());

            // Set TrustManager from trustStore
            TrustManagerFactory trustFact = TrustManagerFactory.getInstance("SunX509");
            trustFact.init(trustStore);

            // Set Context to TLS and initialize it
            sslContext.init(null, trustFact.getTrustManagers(), null);

            return sslContext;
        } catch (Exception ex) {
            System.out.println("Unable to load the SSL Config" + ex);
        }

        return sslContext;
    }

    private InputStream getInputStream(String path) throws FileNotFoundException {
        return new FileInputStream(path);
    }

    private String getPassword(String path) throws IOException {
        Path filePath = Path.of(path);
        return Files.readString(filePath);
    }

    @Bean
    public MQConnectionFactoryCustomizer myCustomizer() {

        MQConnectionFactoryCustomizer c = new MQConnectionFactoryCustomizer() {
            @Override
            public void customize(MQConnectionFactory factory) {
                SSLContext sslContext = createSSlContext();
                factory.setSSLSocketFactory(sslContext.getSocketFactory());
            }
        };
        return c;
    }


    static void status() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("MQ JMS Sample started. Message sent to queue: " + SpringDemoApplication.qName);
        System.out.println("========================================");
    }

}
