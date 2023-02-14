package com.challenge.chatbotbahar;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;

public class NlpUtils {
    private static final StanfordCoreNLP pipeline;

    static {
        // Create properties for the StanfordCoreNLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, sentiment");

        // Create a new instance of the StanfordCoreNLP pipeline
        pipeline = new StanfordCoreNLP(props);
    }

    public static List<CoreMap> processText(String text) {
        // Create a new Annotation object and set the text to be processed
        Annotation document = new Annotation(text);

        // Run the StanfordCoreNLP pipeline on the input text
        pipeline.annotate(document);

        // Get the sentences from the processed text
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        return sentences;
    }
}
