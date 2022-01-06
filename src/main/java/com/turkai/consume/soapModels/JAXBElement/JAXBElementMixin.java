package com.turkai.consume.soapModels.JAXBElement;

import com.fasterxml.jackson.annotation.JsonValue;

public interface JAXBElementMixin {
    @JsonValue
    Object getValue();
}
