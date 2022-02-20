package org.example.reflection.json;

import java.lang.reflect.Field;

public class JSONDecoder {
    public Object decode (final String json, final Class<?> clazz){
        try {
            final Object object = clazz.newInstance();
            final String stripped = json.substring(1, json.length() - 1);
            final String[] parts = stripped.split(",");
//            System.out.println(stripped);
            for (String part : parts) {
                final String[] nameValue = part.split(":");
                final String name = nameValue[0].replace("\"", "");
                final String value = nameValue[1];
                final Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                final Class<?> fieldType = field.getType();
                if (fieldType.isPrimitive()){
                    final String fieldTypeName = fieldType.getName();
                    if (fieldTypeName.equals("long")){
                        field.setLong(object, Long.parseLong(value));
                        continue;
                    }
                }
                if (fieldType.equals(String.class)){
                    field.set(object, value.replaceAll("\"", ""));

                }
//                field.set(object, value);
            }
            return object;
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
