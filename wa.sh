#! /bin/bash
cd out
~/Documents/Procesadores\ del\ lenguaje/wabt-1.0.23/bin/wat2wasm CodigoMaquina.wat
node main.js
cd ..