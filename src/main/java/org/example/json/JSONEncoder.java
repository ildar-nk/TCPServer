package org.example.json;

import java.io.StringWriter;
import java.lang.reflect.Field;

public class JSONEncoder {
    public String encode(final Object object) {
        final StringWriter writer = new StringWriter();
        writer.append("{");

        final Class<?> clazz = object.getClass();
        final Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (i != 0){
                writer.write(",");
            }
            final Field field = fields[i];

            final String name = field.getName();
            writer.write("\"");
            writer.write(name);
            writer.write("\":");

            try {
                field.setAccessible(true);
                final Object value = field.get(object);
                if (value instanceof Number numberValue){
                    writer.write(numberValue.toString());

                }
                if (value instanceof String stringValue){
                    writer.write("\"");
                    writer.write(stringValue);
                    writer.write("\"");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }


        writer.append("}");
        return writer.toString();
    }
}
