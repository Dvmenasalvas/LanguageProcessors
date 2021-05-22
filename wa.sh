#! /bin/bash
cd codeGenerated
./wat2wasm CodigoMaquina.wat
node main.js
cd ..