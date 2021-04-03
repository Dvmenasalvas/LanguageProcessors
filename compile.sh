#! /bin/bash
java -cp "jlex.jar" JLex.Main src/alex/AnalizadorLexico
cd src/constructorast 
java -cp "../../cup.jar" java_cup.Main -parser ConstructAST -symbols ClaseLexica -nopositions ConstructorAST.cup
cd ../..
