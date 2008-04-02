/*
 * $Id: FacesREParser.java,v 1.1 2003/08/13 18:10:49 rlubke Exp $
 */

/*
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/* Generated By:JavaCC: Do not edit this line. FacesREParser.java */

package com.sun.faces.el.ext.parser;

import com.sun.faces.el.ext.FacesArraySuffix;
import com.sun.faces.el.ext.FacesComplexValue;
import com.sun.faces.el.ext.FacesNamedValue;
import com.sun.faces.el.ext.FacesPropertySuffix;
import com.sun.faces.el.impl.ArraySuffix;
import com.sun.faces.el.impl.Expression;
import com.sun.faces.el.impl.ExpressionString;
import com.sun.faces.el.impl.IntegerLiteral;
import com.sun.faces.el.impl.Literal;
import com.sun.faces.el.impl.NamedValue;
import com.sun.faces.el.impl.StringLiteral;
import com.sun.faces.el.impl.ValueSuffix;
import com.sun.faces.el.impl.parser.ELParser;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Generated EL parser for JavaServer Faces.   This parser, based on 
 * <code>FacesELParser.jj</code>, a heavily modified version of the 
 * <code>commons-el ELParser.jj</code> contains the following differences:
 * <ul>
 * <li>FacesArraySuffix will be returned instead of the stock ArraySuffix</li>
 * <li>FacesComplexValue will be returned instead of the stock ComplexValue</li>
 * <li>The constructor, <code>FacesELParser(Reader)</code> has been changed to
 * <code>public void initParser(Reader)</code></li>
 * <li>The constructor, <code>FacesELParser(InputStream)</code> has been changed
 * to <code>public void initParser(InputStream)</code></li>
 * <li>A no-arg constructor has been added</li>
 * </ul>
 * </p>
 * 
 * <p>This parser was generated using <code>JavaCC, Version 3.1</code> which can be
 * found here: https://javacc.dev.java.net/</p> 
 */


public class FacesREParser implements ELParser, FacesREParserConstants {

    /*****************************************
     * GRAMMAR PRODUCTIONS *
     *****************************************/

    /**
     *
     * Returns a String if the expression string is a single String, an
     * Expression if the expression string is a single Expression, an
     * ExpressionString if it's a mixture of both.
     **/
    final public Object ExpressionString() throws ParseException {
        Object ret = "";
        List elems = null;
        Object elem;
        /** Try to optimize for the case of a single expression or String **/
        ret = AttrValueExpression();
        label_1:
        while (true) {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case INTEGER_LITERAL:
                case STRING_LITERAL:
                case IDENTIFIER:
                    ;
                    break;
                default:
                    jj_la1[0] = jj_gen;
                    break label_1;
            }
            elem = AttrValueExpression();
            if (elems == null) {
                elems = new ArrayList();
                elems.add(ret);
            }
            elems.add(elem);
        }
        if (elems != null) {
            ret = new ExpressionString(elems.toArray());
        }
        {
            if (true) return ret;
        }
        throw new Error("Missing return statement in function");
    }

    final public Expression AttrValueExpression() throws ParseException {
        Expression exp;
        exp = Expression();
        {
            if (true) return exp;
        }
        throw new Error("Missing return statement in function");
    }

    final public Expression Expression() throws ParseException {
        Expression ret;
        ret = Value();
        {
            if (true) return ret;
        }
        throw new Error("Missing return statement in function");
    }

    final public Expression Value() throws ParseException {
        Expression prefix;
        ValueSuffix suffix;
        List suffixes = null;
        prefix = ValuePrefix();
        label_2:
        while (true) {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case DOT:
                case LBRACKET:
                    ;
                    break;
                default:
                    jj_la1[1] = jj_gen;
                    break label_2;
            }
            suffix = ValueSuffix();
            if (suffixes == null) {
                suffixes = new ArrayList();
            }
            suffixes.add(suffix);
        }
        if (suffixes == null) {
            {
                if (true) return prefix;
            }
        } else {
            {
                if (true) return new FacesComplexValue(prefix, suffixes);
            }
        }
        throw new Error("Missing return statement in function");
    }

    /**
     * This is an element that can start a value
     **/
    final public Expression ValuePrefix() throws ParseException {
        Expression ret;
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case INTEGER_LITERAL:
            case STRING_LITERAL:
                ret = Literal();
                break;
            case IDENTIFIER:
                ret = NamedValue();
                break;
            default:
                jj_la1[2] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
        {
            if (true) return ret;
        }
        throw new Error("Missing return statement in function");
    }

    final public NamedValue NamedValue() throws ParseException {
        Token t;
        t = jj_consume_token(IDENTIFIER);
        {
            if (true) return new FacesNamedValue(t.image);
        }
        throw new Error("Missing return statement in function");
    }

    final public ValueSuffix ValueSuffix() throws ParseException {
        ValueSuffix suffix;
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case DOT:
                suffix = FacesPropertySuffix();
                break;
            case LBRACKET:
                suffix = FacesArraySuffix();
                break;
            default:
                jj_la1[3] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
        {
            if (true) return suffix;
        }
        throw new Error("Missing return statement in function");
    }

    final public FacesPropertySuffix FacesPropertySuffix() throws ParseException {        
        String property;
        jj_consume_token(DOT);
        property = Identifier();
        {
            if (true) return new FacesPropertySuffix(property);
        }
        throw new Error("Missing return statement in function");
    }

    final public ArraySuffix FacesArraySuffix() throws ParseException {
        Expression index;
        jj_consume_token(LBRACKET);
        index = Expression();
        jj_consume_token(RBRACKET);
        {
            if (true) return new FacesArraySuffix(index);
        }
        throw new Error("Missing return statement in function");
    }

    final public Literal Literal() throws ParseException {
        Literal ret;
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case INTEGER_LITERAL:
                ret = IntegerLiteral();
                break;
            case STRING_LITERAL:
                ret = StringLiteral();
                break;
            default:
                jj_la1[4] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
        {
            if (true) return ret;
        }
        throw new Error("Missing return statement in function");
    }

    final public StringLiteral StringLiteral() throws ParseException {
        Token t;
        t = jj_consume_token(STRING_LITERAL);
        {
            if (true) return StringLiteral.fromToken(t.image);
        }
        throw new Error("Missing return statement in function");
    }

    final public IntegerLiteral IntegerLiteral() throws ParseException {
        Token t;
        t = jj_consume_token(INTEGER_LITERAL);
        {
            if (true) return new IntegerLiteral(t.image);
        }
        throw new Error("Missing return statement in function");
    }

    final public String Identifier() throws ParseException {
        Token t;
        t = jj_consume_token(IDENTIFIER);
        {
            if (true) return t.image;
        }
        throw new Error("Missing return statement in function");
    }

    public FacesREParserTokenManager token_source;
    SimpleCharStream jj_input_stream;
    public Token token, jj_nt;
    private int jj_ntk;
    private int jj_gen;
    final private int[] jj_la1 = new int[5];
    static private int[] jj_la1_0;

    static {
        jj_la1_0();
    }

    private static void jj_la1_0() {
        jj_la1_0 = new int[]{
            0x218,
            0xc0,
            0x218,
            0xc0,
            0x18,
        };
    }

    public FacesREParser() {
        // no-arg constructor
    }

    public void initParser(java.io.InputStream stream) {
        jj_input_stream = new SimpleCharStream(stream, 1, 1);
        token_source = new FacesREParserTokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 5; i++) jj_la1[i] = -1;
    }

    public void ReInit(java.io.InputStream stream) {
        jj_input_stream.ReInit(stream, 1, 1);
        token_source.ReInit(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 5; i++) jj_la1[i] = -1;
    }

    public void initParser(java.io.Reader stream) {
        jj_input_stream = new SimpleCharStream(stream, 1, 1);
        token_source = new FacesREParserTokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 5; i++) jj_la1[i] = -1;
    }

    public void ReInit(java.io.Reader stream) {
        jj_input_stream.ReInit(stream, 1, 1);
        token_source.ReInit(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 5; i++) jj_la1[i] = -1;
    }

    public FacesREParser(FacesREParserTokenManager tm) {
        token_source = tm;
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 5; i++) jj_la1[i] = -1;
    }

    public void ReInit(FacesREParserTokenManager tm) {
        token_source = tm;
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 5; i++) jj_la1[i] = -1;
    }

    final private Token jj_consume_token(int kind) throws ParseException {
        Token oldToken;
        if ((oldToken = token).next != null)
            token = token.next;
        else
            token = token.next = token_source.getNextToken();
        jj_ntk = -1;
        if (token.kind == kind) {
            jj_gen++;
            return token;
        }
        token = oldToken;
        jj_kind = kind;
        throw generateParseException();
    }

    final public Token getNextToken() {
        if (token.next != null)
            token = token.next;
        else
            token = token.next = token_source.getNextToken();
        jj_ntk = -1;
        jj_gen++;
        return token;
    }

    final public Token getToken(int index) {
        Token t = token;
        for (int i = 0; i < index; i++) {
            if (t.next != null)
                t = t.next;
            else
                t = t.next = token_source.getNextToken();
        }
        return t;
    }

    final private int jj_ntk() {
        if ((jj_nt = token.next) == null)
            return (jj_ntk = (token.next = token_source.getNextToken()).kind);
        else
            return (jj_ntk = jj_nt.kind);
    }

    private java.util.Vector jj_expentries = new java.util.Vector();
    private int[] jj_expentry;
    private int jj_kind = -1;

    public ParseException generateParseException() {
        jj_expentries.removeAllElements();
        boolean[] la1tokens = new boolean[14];
        for (int i = 0; i < 14; i++) {
            la1tokens[i] = false;
        }
        if (jj_kind >= 0) {
            la1tokens[jj_kind] = true;
            jj_kind = -1;
        }
        for (int i = 0; i < 5; i++) {
            if (jj_la1[i] == jj_gen) {
                for (int j = 0; j < 32; j++) {
                    if ((jj_la1_0[i] & (1 << j)) != 0) {
                        la1tokens[j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 14; i++) {
            if (la1tokens[i]) {
                jj_expentry = new int[1];
                jj_expentry[0] = i;
                jj_expentries.addElement(jj_expentry);
            }
        }
        int[][] exptokseq = new int[jj_expentries.size()][];
        for (int i = 0; i < jj_expentries.size(); i++) {
            exptokseq[i] = (int[]) jj_expentries.elementAt(i);
        }
        return new ParseException(token, exptokseq, tokenImage);
    }

    final public void enable_tracing() {
    }

    final public void disable_tracing() {
    }

}
