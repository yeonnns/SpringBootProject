package com.test.config;


import org.springframework.context.annotation.Configuration;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

@Configuration
public class LogbackFilter extends Filter<ILoggingEvent> {
	@Override
	public FilterReply decide(ILoggingEvent event) {
		if (event.getLoggerName().contains("JexlEngine")) { // Jexl 관련 로그 안뜨게
			return FilterReply.DENY;
		} else {
			return FilterReply.ACCEPT;
		}
	}
}
