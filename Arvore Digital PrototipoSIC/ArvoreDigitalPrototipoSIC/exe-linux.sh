#!/bin/bash

srcDir="src"
libDir="lib"
binDir="bin"

# Criar o diretório bin se não existir
if [ ! -d "$binDir" ]; then
  mkdir "$binDir"
fi

classpath=$(find "$libDir" -name '*.jar' | tr '\n' ':')

echo "Compiling..."
javac -cp "$classpath" -d "$binDir" $srcDir/Program.java $srcDir/Estrutura/*.java $srcDir/Importacoes/*.java $srcDir/Individuo/*.java $srcDir/Persistencia/*.java $srcDir/Relatorio/*.java $srcDir/Telas/*.java $srcDir/Timer/*.java

if [ $? -ne 0 ]; then
  echo "Compilation failed."
  exit 1
fi

echo "Compilation finished."

echo "Running..."
java -cp "$binDir:$classpath" Program

exit 0

