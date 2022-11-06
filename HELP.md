# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

---
JMSAdmin commands to create .bindings
http://ibmstreams.github.io/streamsx.documentation/docs/messaging/mq-create-objects-bindings-sample/
https://askmiddlewareexpert.com/detailed-steps-for-creating-binding-file-in-ibm-mq-using-jmsadmin/

---
JMSAdmin.config
SECURITY_AUTHENTICATION=simple
PROVIDER_USERDN
PROVIDER_PASSWORD


DEFINE QCF(confact) QMGR(QM1) tran(client) chan(JMS.STREAMS.SVRCONN) host(<host name>) port(1416)
DEFINE Q(dest) QUEUE(Q1) QMGR(QM1)
end

---

DEFINE QCF(APPConnectionFactory) QMGR(APPQM1) TRANSPORT(CLIENT) HOSTNAME(HOSTNAME) CHANNEL(CL.APPQM1) PORT(1414) SSLCIPHERSUITE(TLS_RSA_WITH_AES_256_CBC_SHA256)

DEFINE Q(AppQ) QMGR(APPQM1) QUEUE(TESTCLIENT)

---
DEFINE QCF(MyConnectionFactory) QMGR(QM1) TRANSPORT(CLIENT) HOSTNAME(192.168.4.5) CHANNEL(DEV.APP.SVRCONN) PORT(3414)
DEFINE Q(MyOrderQueue) QMGR(QM1) QUEUE(DEV.QUEUE.ORDER)
end

---

https://docs.delta.io/latest/delta-batch.html
http://www.java2s.com/example/java-api/java/security/cert/x509certificate/getsubjectdn-0-6.html
https://github.com/lmencnar/spring-demo
https://gist.github.com/achintya-kumar/dd767fca1a949cf398310d37556a1365
https://www.sslshopper.com/article-most-common-openssl-commands.html
https://www.ibm.com/docs/en/ibm-mq/9.3?topic=containers-mq-advanced-developers-container-image
https://github.com/ibm-messaging/mq-container/blob/master/docs/usage.md#supplying-tls-certificates
https://developer.ibm.com/tutorials/mq-secure-msgs-tls/
https://github.com/ibm-messaging/mq-container/blob/master/docs/usage.md#supplying-tls-certificates
https://www.ibm.com/docs/en/ibm-mq/9.1?topic=containers-connecting-queue-manager-deployed-in-openshift-cluster
https://www.ibm.com/docs/en/ibm-mq/9.2?topic=file-ssl-stanza-client-configuration
https://zhimin-wen.medium.com/running-ibm-mq-container-image-with-custom-configuration-and-creating-jms-jndi-bindings-e99585e47b07
https://stackoverflow.com/questions/52204895/spring-boot-set-jmstemplate-configuration-properties-from-java-class-not-from
