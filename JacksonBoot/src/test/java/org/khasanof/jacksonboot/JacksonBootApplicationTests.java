package org.khasanof.jacksonboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

class JacksonBootApplicationTests {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void contextLoads() throws JsonProcessingException {

		String date = "2023-06-15T10:54:52.450+0500";

		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		Filter filter = new Filter("Hello", Instant.now());
		String value = objectMapper.writeValueAsString(date);
		System.out.println("value = " + value);

		Instant now = Instant.now();
		System.out.println("now = " + now);

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_INSTANT;

		String format = dateTimeFormatter.format(now);
		System.out.println("format = " + format);
	}

}
