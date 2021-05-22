#! /bin/bash
cd out
./wat2wasm CodigoMaquina.wat
node main.js
cd ..