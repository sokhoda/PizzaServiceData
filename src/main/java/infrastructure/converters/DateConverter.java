package infrastructure.converters;

import javax.persistence.AttributeConverter;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateConverter implements AttributeConverter<LocalDateTime, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDateTime attribute) {
        return attribute == null ? null : Date.from(attribute.toInstant(ZoneOffset.UTC));
    }
    @Override
    public LocalDateTime convertToEntityAttribute(Date dbData) {
        return dbData == null ? null : LocalDateTime.ofInstant(dbData.toInstant(),
                ZoneOffset.UTC);
    }
}
