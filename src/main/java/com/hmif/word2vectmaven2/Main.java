/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmif.word2vectmaven2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.plot.BarnesHutTsne;
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
    HashMap<String, double[]> token_prob = new HashMap<String, double[]>();
    
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
            
            TokenizerFactory t = new TwokenizeFactory();
            t.setTokenPreProcessor(new CommonPreprocessor());
            
            Word2Vec vec = new Word2Vec.Builder()
            .minWordFrequency(1)
            .iterations(5)
            .layerSize(20)
            .seed(42)
            .windowSize(3)
            .iterate(iter)
            .tokenizerFactory(t)
            .build();
            
            
            vec.fit();
            
            try {
            WordVectorSerializer.writeWordVectors(vec, "word_2_vec_result_unreasonable");
            WordVectorSerializer.writeFullModel(vec, "word_2_vec_result_full_model_unreasonable");
            } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            /**/
            
            // tes...
        
        
        try{    
            System.out.println("Loading full model..");
            Word2Vec vec = WordVectorSerializer.loadFullModel("word_2_vec_result_full_model");
            
            BarnesHutTsne tsne = new BarnesHutTsne.Builder()
                                    .setMaxIter(1000)
                                    .stopLyingIteration(250)
                                    .learningRate(500)
                                    .useAdaGrad(false)
                                    .theta(0.5)
                                    .setMomentum(0.5)
                                    .normalize(true)
                                    .usePca(false)
                                    .build();  
            
            vec.lookupTable().plotVocab(tsne,new File("Tes"));
            
            Collection<String> lst = vec.wordsNearest("MALIK", 3);
            Collection<String> isi = vec.wordsNearest("ke", 3);
            System.out.println(isi);
            try{
                double[] vector = vec.getWordVector("MALIK");
                for (int i = 0; i < vector.length; i++) {
                    double w = vector[i];
                    System.out.println("["+i+"] "+w);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        /**/
    }
    /*
    public void doReadTraining(){
        System.out.println("Reading Training data for iteration ");
        String filename = "NER_gold_standard";
        try{
           BufferedReader br = new BufferedReader(new FileReader(filename));
           String line;
            while((line = br.readLine())!=null){
                if(line.equals("")){
                    
                }else{
                    String[] splitted = line.split("\t");
                    if(token_prob.containsKey(splitted[0])){
                    }else{
                        double[] d = {0.0};
                        token_prob.put(splitted[0], d);
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void doReadModelfile(){
        Word2Vec vec;
        try{    
            vec = WordVectorSerializer.loadFullModel("word_2_vec_result_full_model");
            
            for (Map.Entry<String, double[]> entrySet : token_prob.entrySet()) {
                String key = entrySet.getKey();
                double[] value = entrySet.getValue();
                if(vec.getWordVector(key.toLowerCase())==null){
                    
                }else{
                    double[] d = vec.getWordVector(key.toLowerCase());
                    token_prob.put(key, d);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void writefile() throws IOException{
        FileWriter writer = new FileWriter(new File("Word2vec_mapping_result"));
        for (Map.Entry<String, double[]> entrySet : token_prob.entrySet()) {
            String key = entrySet.getKey();
            double[] value = entrySet.getValue();
            StringBuffer sb = new StringBuffer();
            System.out.println("key :"+key);
            sb.append(key);
            sb.append("\t");
            for (int i = 0; i < value.length; i++) {
                double w = value[i];
                sb.append(String.valueOf(w));
                sb.append("\t");
            }
            sb.append("\n");
            writer.write(sb.toString());
        }
        writer.close();
    }
    
    /**/
    public static void main(String[] args) {
        Main m = new Main();
        /*
        m.doReadTraining();
        m.doReadModelfile();
        try {
            m.writefile();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        /**/
    }
}
