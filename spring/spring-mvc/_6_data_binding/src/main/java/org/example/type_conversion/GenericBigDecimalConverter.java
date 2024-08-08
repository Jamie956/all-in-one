package org.example.type_conversion;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class GenericBigDecimalConverter
        implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes () {

        ConvertiblePair[] pairs = new ConvertiblePair[] {
                new ConvertiblePair(Number.class, BigDecimal.class),
                new ConvertiblePair(String.class, BigDecimal.class)};

        Set<ConvertiblePair> set = new HashSet<>();
        set.add(pairs[0]);
        set.add(pairs[1]);
        return set;
    }

    @Override
    public Object convert (Object source, TypeDescriptor sourceType,
                           TypeDescriptor targetType) {

        if (sourceType.getType() == BigDecimal.class) {
            return source;
        }

        if(sourceType.getType() == String.class) {
            String number = (String) source;
            return new BigDecimal(number);
        } else {
            Number number = (Number) source;
            BigDecimal converted = new BigDecimal(number.doubleValue());
            return converted.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        }
    }
}