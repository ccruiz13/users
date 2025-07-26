package com.aws.app.users.commons.mapper;

import com.aws.app.users.commons.annotations.FieldMapping;
import com.aws.app.users.exception.EntityMappingException;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class EntityDTOBuilder {

    private EntityDTOBuilder() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static <E, D> D mapEntityToDto(E entity, Class<D> dtoClass) {
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            Map<String, String> fieldMapping = getFieldMappings(dtoClass);

            for (Field entityField : entity.getClass().getDeclaredFields()) {
                if (isSkippableField(entityField)) continue;
                String dtoFieldName = fieldMapping.getOrDefault(entityField.getName(), entityField.getName());
                setDtoFieldValue(dto, dtoClass, entity, entityField, dtoFieldName);
            }
            return dto;
        } catch (ReflectiveOperationException e) {
            throw new EntityMappingException("Error mapping entity to DTO", e);
        }
    }

    public static <D, E> E mapDtoToEntity(D dto, Class<E> entityClass) {
        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();
            Map<String, String> fieldMapping = getFieldMappings(dto.getClass());

            for (Field dtoField : dto.getClass().getDeclaredFields()) {
                if (isSkippableField(dtoField)) continue;
                String entityFieldName = fieldMapping.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(dtoField.getName()))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(dtoField.getName());
                setEntityFieldValue(dto, entityClass, entity, dtoField, entityFieldName);
            }
            return entity;
        } catch (ReflectiveOperationException e) {
            throw new EntityMappingException("Error mapping DTO to entity", e);
        }
    }

    private static boolean isSkippableField(Field field) {
        int modifiers = field.getModifiers();
        return Modifier.isStatic(modifiers)
                || Modifier.isFinal(modifiers)
                || Collection.class.isAssignableFrom(field.getType());
    }

    private static <E, D> void setDtoFieldValue(D dto, Class<D> dtoClass, E entity, Field entityField, String dtoFieldName) {
        try {
            Field dtoField = dtoClass.getDeclaredField(dtoFieldName);
            if (Modifier.isStatic(dtoField.getModifiers()) || Modifier.isFinal(dtoField.getModifiers())) return;
            dtoField.setAccessible(true);
            entityField.setAccessible(true);
            dtoField.set(dto, entityField.get(entity));
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            log.warn("Campo no encontrado o inaccesible en el DTO: {}", dtoFieldName);
        }
    }

    private static <D, E> void setEntityFieldValue(D dto, Class<E> entityClass, E entity, Field dtoField, String entityFieldName) {
        try {
            Field entityField = entityClass.getDeclaredField(entityFieldName);
            if (Modifier.isStatic(entityField.getModifiers()) || Modifier.isFinal(entityField.getModifiers())) return;
            entityField.setAccessible(true);
            dtoField.setAccessible(true);
            entityField.set(entity, dtoField.get(dto));
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            log.warn("Campo no encontrado o inaccesible en la entidad: {}", entityFieldName);
        }
    }

    private static Map<String, String> getFieldMappings(Class<?> clazz) {
        Map<String, String> mapping = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            FieldMapping annotation = field.getAnnotation(FieldMapping.class);
            if (annotation != null) {
                mapping.put(annotation.value(), field.getName());
            }
        }
        return mapping;
    }

}
