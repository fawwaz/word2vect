/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmif.word2vectmaven2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.LineSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentencePreProcessor;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.nd4j.linalg.api.ndarray.INDArray;
/**
 *
 * @author Asus
 */
public class Main {
    public Main() {
        
            /*
            // TODO code application logic here
            String filepath = "output_dump";
            SentenceIterator iter = new LineSentenceIterator(new File(filepath));
            iter.setPreProcessor(new SentencePreProcessor() {
            
            @Override
            public String preProcess(String string) {
            return string.toLowerCase();
            }
            });
            
            TokenizerFactory t = new DefaultTokenizerFactory();
            t.setTokenPreProcessor(new CommonPreprocessor());
            
            Word2Vec vec = new Word2Vec.Builder()
            .minWordFrequency(5)
            .iterations(2)
            .layerSize(100)
            .seed(42)
            .windowSize(5)
            .iterate(iter)
            .tokenizerFactory(t)
            .build();
            
            vec.fit();
            
            try {
            WordVectorSerializer.writeWordVectors(vec, "word_2_vec_result");
            WordVectorSerializer.writeFullModel(vec, "word_2_vec_result_full_model");
            } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
            
            // tes...
        try{    
            Word2Vec vec = WordVectorSerializer.loadFullModel("word_2_vec_result_full_model");
            Collection<String> lst = vec.wordsNearest("MALIK", 3);
            Collection<String> isi = vec.wordsNearest("ke", 3);
            System.out.println(isi);
            double[] vector = vec.getWordVector("lomba");
            for (int i = 0; i < vector.length; i++) {
                double w = vector[i];
                System.out.println("["+i+"] "+w);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static void main(String[] args) {
        Main m = new Main();
    }
}
