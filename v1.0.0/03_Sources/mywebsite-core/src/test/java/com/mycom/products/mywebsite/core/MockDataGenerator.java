package com.mycom.products.mywebsite.core;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.mycom.products.mywebsite.core.annotation.Valid;

public class MockDataGenerator {
	protected static final String[] EXCLUDE_FIELDS = { "serialVersionUID", "formatter" };

	public enum GenerateMode {
		SINGLE, NESTED;
	}

	public static void mock(
			Object obj,
			GenerateMode mode) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, ArrayIndexOutOfBoundsException, InstantiationException {
		Class<?> thisClass = null;
		thisClass = Class.forName(obj.getClass().getName());
		Field[] memberFields = thisClass.getDeclaredFields();
		for (Field field : memberFields) {
			int length = 0;
			boolean isReqired = false;
			if (!Arrays.asList(EXCLUDE_FIELDS).contains(field.getName())) {
				if (field.isAnnotationPresent(Valid.class)) {
					Valid valid = field.getAnnotation(Valid.class);
					length = valid.length();
					isReqired = valid.required();
				}
				mock(obj, field, length, isReqired, mode);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object mock(Object obj,
			Field field, int length, boolean isRequiredField,
			GenerateMode mode) throws IllegalArgumentException, IllegalAccessException, ArrayIndexOutOfBoundsException, InstantiationException, ClassNotFoundException {
		field.setAccessible(true);
		// Byte
		if (field.getType() == Byte.class || field.getType() == byte.class) {
			field.set(obj, 1);
		}
		// Short
		else if (field.getType() == Short.class || field.getType() == short.class) {
			field.set(obj, generateRandomShortNumber(length));
		}
		// Integer
		else if (field.getType() == Integer.class || field.getType() == int.class) {
			field.set(obj, generateRandomIntNumber(length));
		}
		// Long
		else if (field.getType() == Long.class || field.getType() == long.class) {
			field.set(obj, generateRandomLongNumber(length));
		}
		// Float
		else if (field.getType() == Float.class || field.getType() == float.class) {
			field.set(obj, generateRandomFloatNumber(Float.MIN_VALUE, Float.MAX_VALUE));
		}
		// Double
		else if (field.getType() == Double.class || field.getType() == double.class) {
			field.set(obj, generateRandomDoubleNumber(Double.MIN_VALUE, Double.MAX_VALUE));
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
				field.set(obj, generateRandomString(length));
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
			Object values = Array.newInstance(field.getType().getComponentType(), length);
			for (int i = 0; i < length; i++) {
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
		// make null for not required fields
		if (!field.getType().isPrimitive() && !isRequiredField) {
			field.set(obj, null);
		}
		return obj;

	}

	private static String generateRandomString(int length) {
		if (length == 0) {
			return null;
		}
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	private static short generateRandomShortNumber(int length) {
		return (short) generateRandomLongNumber(length);
	}

	private static int generateRandomIntNumber(int length) {
		return (int) generateRandomLongNumber(length);
	}

	private static long generateRandomLongNumber(int length) {
		if (length == 0) {
			return 0;
		}
		char[] chars = "123456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return Long.parseLong(sb.toString());
	}

	public static double generateRandomDoubleNumber(double min, double max) {
		Random random = new Random();
		double range = max - min;
		double scaled = random.nextDouble() * range;
		double shifted = scaled + min;
		return shifted;
	}

	public static double generateRandomFloatNumber(float min, float max) {
		Random random = new Random();
		float range = max - min;
		float scaled = random.nextFloat() * range;
		float shifted = scaled + min;
		return shifted;
	}
}
