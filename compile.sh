#! /bin/bash
cd asint
java java_cup.Main -parser AnalizadorSintacticoTiny -symbols ClaseLexica -nopositions Tiny.cup
cd ..
java JLex.Main alex/AnalizadorLexicoTiny
javac */*.java
