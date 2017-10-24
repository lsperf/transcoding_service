package com.test.transcoder.config;

import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.test.transcoder.sqs.SQSListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * Created by ls on 2/24/17.
 */
@Configuration
public class JMSSQSConfig {

	@Value("${queue.endpoint}")
	private String endpoint;

	@Value("${queue.name}")
	private String queueName;

	@Value("${aws.key}")
	private String awsKey;

	@Value("${aws.secret}")
	private String awsSecret;

	@Autowired
	private SQSListener sqsListener;

	@Bean
	public DefaultMessageListenerContainer jmsListenerContainer() {

		SQSConnectionFactory sqsConnectionFactory = SQSConnectionFactory.builder()
			.withAWSCredentialsProvider(new DefaultAWSCredentialsProviderChain())
			.withEndpoint(endpoint)
			.withAWSCredentialsProvider(awsCredentialsProvider)
			.withNumberOfMessagesToPrefetch(10).build();

		DefaultMessageListenerContainer dmlc = new DefaultMessageListenerContainer();
		dmlc.setConnectionFactory(sqsConnectionFactory);
		dmlc.setDestinationName(queueName);

		dmlc.setMessageListener(sqsListener);

		return dmlc;
	}

//	@Bean
//	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
//		DefaultJmsListenerContainerFactory factory =
//			new DefaultJmsListenerContainerFactory();
//		factory.setConnectionFactory(this.connectionFactory);
//		factory.setDestinationResolver(new DynamicDestinationResolver());
//		factory.setConcurrency("3-10");
//		factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
//		return factory;
//	}


	@Bean
	public JmsTemplate createJMSTemplate() {

		SQSConnectionFactory sqsConnectionFactory = SQSConnectionFactory.builder()
			.withAWSCredentialsProvider(awsCredentialsProvider)
			.withEndpoint(endpoint)
			.withNumberOfMessagesToPrefetch(10).build();

		JmsTemplate jmsTemplate = new JmsTemplate(sqsConnectionFactory);
		jmsTemplate.setDefaultDestinationName(queueName);
		jmsTemplate.setDeliveryPersistent(false);


		return jmsTemplate;
	}

	private final AWSCredentialsProvider awsCredentialsProvider = new AWSCredentialsProvider() {
		@Override
		public AWSCredentials getCredentials() {
			return new BasicAWSCredentials(awsKey, awsSecret);
		}

		@Override
		public void refresh() {

		}
	};

}
