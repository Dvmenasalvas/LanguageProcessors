(module
(type $_sig_void (func ))
(type $_sig_i32 (func (param i32)))
(type $_sig_ri32 (func (result i32)))
(import "runtime" "exceptionHandler" (func $exception (type $_sig_i32)))
(import "runtime" "print" (func $print (type $_sig_i32)))
(import "runtime" "read" (func $read (type $_sig_ri32)))
(memory 2000)
(start $main)
(func $main  (type $_sig_void)
   ;; instructions
