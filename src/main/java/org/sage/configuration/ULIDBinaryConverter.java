package org.sage.configuration;

import com.github.f4b6a3.ulid.Ulid;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ULIDBinaryConverter implements AttributeConverter<Ulid, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(Ulid val) {
        return val.toBytes();
    }

    @Override
    public Ulid convertToEntityAttribute(byte[] bytes) {
        return Ulid.from(bytes);
    }
}
