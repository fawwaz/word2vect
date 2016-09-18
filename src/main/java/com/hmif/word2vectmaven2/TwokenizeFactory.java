/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmif.word2vectmaven2;

import java.io.InputStream;
import org.deeplearning4j.text.tokenization.tokenizer.DefaultStreamTokenizer;
import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

/**
 *
 * @author dosen
 */
public class TwokenizeFactory implements TokenizerFactory{
    
    TokenPreProcess tpp;

    public Tokenizer create(String string) {
        Tokenizer t = new TwokenizeTokenizer(string);
        t.setTokenPreProcessor(tpp);
        return t;
    }

    public Tokenizer create(InputStream in) {
        Tokenizer t = new DefaultStreamTokenizer(in);
        t.setTokenPreProcessor(tpp);
        return t;
    }

    public void setTokenPreProcessor(TokenPreProcess tpp) {
        this.tpp = tpp;
    }

    public TokenPreProcess getTokenPreProcessor() {
        return tpp;
    }
    
}
