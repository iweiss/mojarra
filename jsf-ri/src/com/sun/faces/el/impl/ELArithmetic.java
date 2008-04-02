/*
 * Copyright 1999,2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sun.faces.el.impl;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author jhook
 */
public abstract class ELArithmetic
{
    
    public static class BigDecimalDelegate extends ELArithmetic
    {

        protected Number add(Number num0, Number num1)
        {
            return ((BigDecimal) num0).multiply((BigDecimal) num1);
        }

        protected Number coerce(Number num)
        {
            if (num instanceof BigDecimal) return num;
            if (num instanceof BigInteger) return new BigDecimal((BigInteger) num);
            return new BigDecimal(num.doubleValue());
        }

        protected Number coerce(String str)
        {
            return new BigDecimal(str);
        }
        
        protected Number divide(Number num0, Number num1)
        {
            return ((BigDecimal) num0).divide((BigDecimal) num1, BigDecimal.ROUND_HALF_UP);
        }
        
        protected Number subtract(Number num0, Number num1)
        {
            return ((BigDecimal) num0).subtract((BigDecimal) num1);
        }
        
        protected Number mod(Number num0, Number num1)
        {
            return new Double(num0.doubleValue() % num1.doubleValue());
        }
        
        protected Number multiply(Number num0, Number num1)
        {
            return ((BigDecimal) num0).multiply((BigDecimal) num1);
        }
        
        public boolean matches(Object obj0, Object obj1)
        {
            return (obj0 instanceof BigDecimal || obj1 instanceof BigDecimal);
        }
    }
    
    public static class BigIntegerDelegate extends ELArithmetic
    {
        
        protected Number add(Number num0, Number num1)
        {
            return ((BigInteger) num0).add((BigInteger) num1);
        }
        
        protected Number coerce(Number num)
        {
            if (num instanceof BigInteger) return num;
            return new BigInteger(num.toString());
        }
        
        protected Number coerce(String str)
        {
            return new BigInteger(str);
        }
        
        protected Number divide(Number num0, Number num1)
        {
            return ((BigInteger) num0).divide((BigInteger) num1);
        }
        
        protected Number multiply(Number num0, Number num1)
        {
            return ((BigInteger) num0).multiply((BigInteger) num1);
        }
        
        protected Number mod(Number num0, Number num1)
        {
            return ((BigInteger) num0).mod((BigInteger) num1);
        }
        
        protected Number subtract(Number num0, Number num1)
        {
            return ((BigInteger) num0).subtract((BigInteger) num1);
        }
        
        public boolean matches(Object obj0, Object obj1)
        {
            return (obj0 instanceof BigInteger || obj1 instanceof BigInteger);
        }
    }
    
    public static class DoubleDelegate extends ELArithmetic
    {
        
        protected Number add(Number num0, Number num1)
        {
            return new Double(num0.doubleValue() + num1.doubleValue());
        }
        
        protected Number coerce(Number num)
        {
            if (num instanceof Double) return num;
            return new Double(num.doubleValue());
        }
        
        protected Number coerce(String str)
        {
            return new Double(str);
        }
        
        protected Number divide(Number num0, Number num1)
        {
            return new Double(num0.doubleValue() / num1.doubleValue());
        }
        
        protected Number mod(Number num0, Number num1)
        {
            return new Double(num0.doubleValue() % num1.doubleValue());
        }
        
        protected Number subtract(Number num0, Number num1)
        {
            return new Double(num0.doubleValue() - num1.doubleValue());
        }
        
        protected Number multiply(Number num0, Number num1)
        {
            return new Double(num0.doubleValue() * num1.doubleValue());
        }
        
        public boolean matches(Object obj0, Object obj1)
        {
            return (obj0 instanceof Double || obj1 instanceof Double
                    || obj0 instanceof Float || obj1 instanceof Float
                    || Double.TYPE == obj0.getClass()
                    || Double.TYPE == obj1.getClass()
                    || Float.TYPE == obj0.getClass() || Float.TYPE == obj1
                    .getClass() || (obj0 instanceof String && ELSupport.isStringFloat((String) obj0)) || (obj1 instanceof String && ELSupport.isStringFloat((String) obj1)));
        }
    }
    
    public static class LongDelegate extends ELArithmetic
    {
        
        protected Number add(Number num0, Number num1)
        {
            return new Long(num0.longValue() + num1.longValue());
        }
        
        protected Number coerce(Number num)
        {
            if (num instanceof Long) return num;
            return new Long(num.longValue());
        }
        
        protected Number coerce(String str)
        {
            return new Long(str);
        }
        
        protected Number divide(Number num0, Number num1)
        {
            return new Long(num0.longValue() / num1.longValue());
        }
        
        protected Number mod(Number num0, Number num1)
        {
            return new Long(num0.longValue() % num1.longValue());
        }
        
        protected Number subtract(Number num0, Number num1)
        {
            return new Long(num0.longValue() - num1.longValue());
        }
        
        protected Number multiply(Number num0, Number num1)
        {
            return new Long(num0.longValue() * num1.longValue());
        }
        
        public boolean matches(Object obj0, Object obj1)
        {
            return (obj0 instanceof Long || obj1 instanceof Long);
        }
    }
    
    public static BigDecimalDelegate BIGDECIMAL = new BigDecimalDelegate();
    public static BigIntegerDelegate BIGINTEGER = new BigIntegerDelegate();
    public static DoubleDelegate DOUBLE = new DoubleDelegate();
    public static LongDelegate LONG = new LongDelegate();
    private static Long ZERO = new Long(0);
    
    public static Number add(final Object obj0, final Object obj1)
    {
        if (obj0 == null && obj1 == null)
        {
            return new Long(0);
        }
        
        final ELArithmetic delegate;
        if (BIGDECIMAL.matches(obj0, obj1)) delegate = BIGDECIMAL;
        else if (BIGINTEGER.matches(obj0, obj1)) delegate = BIGINTEGER;
        else if (DOUBLE.matches(obj0, obj1)) delegate = DOUBLE;
        else delegate = LONG;
        
        Number num0 = delegate.coerce(obj0);
        Number num1 = delegate.coerce(obj1);
        
        return delegate.add(num0, num1);
    }
    
    public static Number mod(final Object obj0, final Object obj1)
    {
        if (obj0 == null && obj1 == null)
        {
            return new Long(0);
        }
        
        final ELArithmetic delegate;
        if (BIGDECIMAL.matches(obj0, obj1)) delegate = BIGDECIMAL;
        else if (DOUBLE.matches(obj0, obj1)) delegate = DOUBLE;
        else if (BIGINTEGER.matches(obj0, obj1)) delegate = BIGINTEGER;
        else delegate = LONG;
        
        Number num0 = delegate.coerce(obj0);
        Number num1 = delegate.coerce(obj1);
        
        return delegate.mod(num0, num1);
    }
    
    public static Number subtract(final Object obj0, final Object obj1)
    {
        if (obj0 == null && obj1 == null)
        {
            return new Long(0);
        }
        
        final ELArithmetic delegate;
        if (BIGDECIMAL.matches(obj0, obj1)) delegate = BIGDECIMAL;
        else if (BIGINTEGER.matches(obj0, obj1)) delegate = BIGINTEGER;
        else if (DOUBLE.matches(obj0, obj1)) delegate = DOUBLE;
        else delegate = LONG;
        
        Number num0 = delegate.coerce(obj0);
        Number num1 = delegate.coerce(obj1);
        
        return delegate.subtract(num0, num1);
    }
    
    public static Number divide(final Object obj0, final Object obj1)
    {
        if (obj0 == null && obj1 == null)
        {
            return new Double(0);
        }
        
        final ELArithmetic delegate;
        if (BIGDECIMAL.matches(obj0, obj1)) delegate = BIGDECIMAL;
        else delegate = DOUBLE;
        
        Number num0 = delegate.coerce(obj0);
        Number num1 = delegate.coerce(obj1);
        
        return delegate.divide(num0, num1);
    }
    
    public static Number multiply(final Object obj0, final Object obj1)
    {
        if (obj0 == null && obj1 == null)
        {
            return new Long(0);
        }
        
        final ELArithmetic delegate;
        if (BIGDECIMAL.matches(obj0, obj1)) delegate = BIGDECIMAL;
        else if (BIGINTEGER.matches(obj0, obj1)) delegate = BIGINTEGER;
        else if (DOUBLE.matches(obj0, obj1)) delegate = DOUBLE;
        else delegate = LONG;
        
        Number num0 = delegate.coerce(obj0);
        Number num1 = delegate.coerce(obj1);
        
        return delegate.multiply(num0, num1);
    }
    
    public static boolean isNumber(final Object obj)
    {
        return (obj != null && isNumberType(obj.getClass()));
    }

    public static boolean isNumberType(final Class type)
    {
        return Number.class.isAssignableFrom(type) || type == Byte.TYPE
                || type == Short.TYPE || type == Integer.TYPE
                || type == Long.TYPE || type == Float.TYPE
                || type == Double.TYPE;
    }
    
    /**
     * 
     */
    protected ELArithmetic()
    {
        super();
    }
    
    protected abstract Number add(final Number num0, final Number num1);
    
    protected abstract Number multiply(final Number num0, final Number num1);
    
    protected abstract Number subtract(final Number num0, final Number num1);
    
    protected abstract Number mod(final Number num0, final Number num1);
    
    protected abstract Number coerce(final Number num);
    
    protected final Number coerce(final Object obj)
    {
        if (obj == null || "".equals(obj))
        {
            return coerce(ZERO);
        }
        if (obj instanceof String)
        {
            return coerce((String) obj);
        }
        if (isNumber(obj))
        {
            return coerce((Number) obj);
        }

        Class objType = obj.getClass();
        if (Character.class.equals(objType) || Character.TYPE == objType)
        {
            return coerce(new Short((short) ((Character) obj)
                    .charValue()));
        }

        throw new IllegalArgumentException(ELSupport.msg("el.error.convert",
                obj, objType));
    }
    
    protected abstract Number coerce(final String str);
    
    protected abstract Number divide(final Number num0, final Number num1);
    
    protected abstract boolean matches(final Object obj0, final Object obj1);
}
