package com.test.transcoder.sqs;

import com.test.transcoder.TranscodingStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


@Component
public class SQSListener implements MessageListener {



	private static final Logger LOGGER = LoggerFactory.getLogger(SQSListener.class);

	public void onMessage(Message message) {

		TextMessage textMessage = (TextMessage) message;

		try {

			// code to store transcoding status
			TranscodingStatus.jsonTranscodingStatus = textMessage.getText();

		} catch (Exception e) {
			LOGGER.error("Error processing message: ", e);

		}

		try {
			LOGGER.info("Received message " + textMessage.getText());
		} catch (JMSException e) {
			LOGGER.error("Error processing message ", e);
		}
	}
}
