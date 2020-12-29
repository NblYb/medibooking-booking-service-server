package com.medibooking.messages.email;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class EmailSerializer extends StdSerializer<Email> {

    public EmailSerializer() {
        this(null);
    }

    public EmailSerializer(Class<Email> t) {
        super(t);
    }

    @Override
    public void serialize(
            Email email, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("email", email.getEmail());
        jsonGenerator.writeStringField("date", email.getDate().toString());
        jsonGenerator.writeStringField("startingTime", email.getStartingTime().toString());
        jsonGenerator.writeStringField("notes", email.getNotes());
        jsonGenerator.writeStringField("patientFirstName", email.getPatientFirstName());
        jsonGenerator.writeStringField("patientLastName", email.getPatientLastName());
        jsonGenerator.writeStringField("doctorFirstName", email.getDoctorFirstName());
        jsonGenerator.writeStringField("doctorLastName", email.getDoctorLastName());
        jsonGenerator.writeEndObject();
    }
}
