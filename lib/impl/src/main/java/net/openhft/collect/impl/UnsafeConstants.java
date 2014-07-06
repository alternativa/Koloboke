/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.openhft.collect.impl;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteOrder;

import static java.nio.ByteOrder.*;
import static java.nio.ByteOrder.nativeOrder;


public interface UnsafeConstants {
    public static final Unsafe U = Inner.U;
    static class Inner {
        private static final Unsafe U;
        static {
            try {
                Field f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                U = (Unsafe) f.get(null);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public static final long BYTE_SCALE = 1L;
    public static final int BYTE_SCALE_SHIFT = 0;

    public static final long CHAR_SCALE = 2L;
    public static final int CHAR_SCALE_SHIFT = 1;

    public static final long SHORT_SCALE = 2L;
    public static final int SHORT_SCALE_SHIFT = 1;

    public static final long INT_SCALE = 4L;
    public static final int INT_SCALE_SHIFT = 2;

    public static final long FLOAT_SCALE = 4L;
    public static final int FLOAT_SCALE_SHIFT = 2;

    public static final long LONG_SCALE = 8L;
    public static final int LONG_SCALE_SHIFT = 3;

    public static final long DOUBLE_SCALE = 8L;
    public static final int DOUBLE_SCALE_SHIFT = 3;

    public static final long BYTE_BASE = (long) Unsafe.ARRAY_BYTE_BASE_OFFSET;
    public static final long CHAR_BASE = (long) Unsafe.ARRAY_CHAR_BASE_OFFSET;
    public static final long SHORT_BASE = (long) Unsafe.ARRAY_SHORT_BASE_OFFSET;
    public static final long INT_BASE = (long) Unsafe.ARRAY_INT_BASE_OFFSET;
    public static final long FLOAT_BASE = (long) Unsafe.ARRAY_FLOAT_BASE_OFFSET;
    public static final long LONG_BASE = (long) Unsafe.ARRAY_LONG_BASE_OFFSET;
    public static final long DOUBLE_BASE = (long) Unsafe.ARRAY_DOUBLE_BASE_OFFSET;

    /**
     * In parallel key-value tables keys and values sometimes are read and written as a single
     * value of doubled size. Key is always in the lower half, value is in the higher.
     * Then the offset to keys and values depends on native byte order.
     */

    public static final long BYTE_KEY_OFFSET = nativeOrder() == LITTLE_ENDIAN ? 0L : 1L;
    public static final long BYTE_VALUE_OFFSET = 1L - BYTE_KEY_OFFSET;

    public static final long CHAR_KEY_OFFSET = nativeOrder() == LITTLE_ENDIAN ? 0L : 2L;
    public static final long CHAR_VALUE_OFFSET = 2L - CHAR_KEY_OFFSET;

    public static final long SHORT_KEY_OFFSET = CHAR_KEY_OFFSET;
    public static final long SHORT_VALUE_OFFSET = CHAR_VALUE_OFFSET;

    public static final long INT_KEY_OFFSET = nativeOrder() == LITTLE_ENDIAN ? 0L : 4L;
    public static final long INT_VALUE_OFFSET = 4L - INT_KEY_OFFSET;

    public static final long FLOAT_KEY_OFFSET = INT_KEY_OFFSET;
    public static final long FLOAT_VALUE_OFFSET = INT_VALUE_OFFSET;
}
