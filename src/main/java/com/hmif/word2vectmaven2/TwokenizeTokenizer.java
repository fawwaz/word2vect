/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmif.word2vectmaven2;

import java.util.List;
import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;

/**
 *
 * @author dosen
 */
public class TwokenizeTokenizer implements Tokenizer{
    private TokenPreProcess tpp;
    private List<String> tokenized;
    private Integer index=0;
    
    public TwokenizeTokenizer(String text){
        this.tokenized = Twokenize.tokenize(text);
    }

    public boolean hasMoreTokens() {
        return index<tokenized.size()-1;
    }

    public int countTokens() {
        return tokenized.size();
    }

    public String nextToken() {
        String retval = tokenized.get(index);
        index = index + 1;
        return retval;
    }

    public List<String> getTokens() {
        return tokenized;
    }

    public void setTokenPreProcessor(TokenPreProcess tpp) {
        this.tpp = tpp;
    }
    
}
