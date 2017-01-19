package com.mycom.products.mywebsite.core;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

public class MockDataGenerator {
	private static Logger logger = Logger.getLogger("stdout");
	protected static final String[] EXCLUDE_FIELDS = { "serialVersionUID", "formatter" };

	public enum GenerateMode {
		SINGLE, NESTED;
	}

	@SuppressWarnings("unchecked")
	public static <T> Set<T> generateMockList(
			Class<T> clazz) throws InstantiationException, IllegalAccessException, ArrayIndexOutOfBoundsException, IllegalArgumentException, ClassNotFoundException {
		Set<T> objects = new HashSet<>();
		Field[] memberFields = clazz.getDeclaredFields();
		int size = memberFields.length;
		logger.info("Total field counts >>> " + size);
		int fieldCount = 1;
		while (fieldCount <= size) {
			for (int i = 0; i < fieldCount; i++) {
				Field field = memberFields[i];
				if (!Arrays.asList(EXCLUDE_FIELDS).contains(field.getName())) {
					Object obj = clazz.newInstance();
					objects.add((T) mock(obj, field, GenerateMode.SINGLE));
				}
			}
			fieldCount++;
		}
		return objects;
	}

	public static void mock(
			Object obj,
			GenerateMode mode) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, ArrayIndexOutOfBoundsException, InstantiationException {
		Class<?> thisClass = null;
		thisClass = Class.forName(obj.getClass().getName());
		Field[] memberFields = thisClass.getDeclaredFields();
		for (Field field : memberFields) {
			if (!Arrays.asList(EXCLUDE_FIELDS).contains(field.getName())) {
				mock(obj, field, mode);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object mock(Object obj,
			Field field,
			GenerateMode mode) throws IllegalArgumentException, IllegalAccessException, ArrayIndexOutOfBoundsException, InstantiationException, ClassNotFoundException {
		field.setAccessible(true);
		// Byte
		if (field.getType() == Byte.class || field.getType() == byte.class) {
			field.set(obj, 1);
		}
		// Short
		else if (field.getType() == Short.class || field.getType() == short.class) {
			field.set(obj, 12);
		}
		// Integer
		else if (field.getType() == Integer.class || field.getType() == int.class) {
			field.set(obj, 123);
		}
		// Long
		else if (field.getType() == Long.class || field.getType() == long.class) {
			field.set(obj, 123456789);
		}
		// Float
		else if (field.getType() == Float.class || field.getType() == float.class) {
			field.set(obj, 1.1);
		}
		// Double
		else if (field.getType() == Double.class || field.getType() == double.class) {
			field.set(obj, 12.34);
		}
		// Boolean
		else if (field.getType() == Boolean.class || field.getType() == boolean.class) {
			field.set(obj, true);
		}
		// Character
		else if (field.getType() == Character.class || field.getType() == char.class) {
			field.set(obj, 'A');
		}
		// String
		else if (field.getType() == String.class) {
			if (field.getName().indexOf("Date") > -1) {
				field.set(obj, "01/01/20017");
			} else {
				field.set(obj, "xxxxxx");
			}
		}
		// Enum
		else if (field.getType().isEnum()) {
			field.set(obj, field.getType().getEnumConstants()[0]);
		}
		// Util Date
		else if (field.getType() == java.util.Date.class) {
			field.set(obj, new java.util.Date());
		}
		// SQL Date
		else if (field.getType() == java.util.Date.class) {
			field.set(obj, new java.util.Date(System.currentTimeMillis()));
		}
		// Timestamp
		else if (field.getType() == java.sql.Timestamp.class) {
			field.set(obj, new java.sql.Timestamp(System.currentTimeMillis()));
		}
		// Array
		else if (field.getType().isArray()) {
			Object values = Array.newInstance(field.getType().getComponentType(), 3);
			for (int i = 0; i < 3; i++) {
				Array.set(values, i, field.getType().getComponentType().newInstance());
			}
			field.set(obj, values);
		}
		// ArrayList
		else if (field.getType() == java.util.List.class && mode == GenerateMode.NESTED) {
			ParameterizedType listType = ((ParameterizedType) field.getGenericType());
			Class<?> clazz = (Class<?>) listType.getActualTypeArguments()[0];
			List list = new ArrayList();
			if (clazz == Integer.class) {
				list.addAll(Arrays.asList(1, 2, 3));
			} else {
				Object t = clazz.newInstance();
				mock(t, mode);
				list.add(t);
			}
			field.set(obj, list);
		}
		return obj;

	}
}
