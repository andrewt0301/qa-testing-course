;******************************************************************
; Microsoft Research Z3 SMT solver
;
; Links:
;   Z3 online - https://rise4fun.com/Z3
;   Z3 tutorial - https://rise4fun.com/Z3/tutorial/guide
;   SMT standard - http://smtlib.cs.uiowa.edu
;******************************************************************

(declare-const     rs (_ BitVec 32))
(declare-const     rt (_ BitVec 32))
(declare-const temp33 (_ BitVec 33))

(assert (= temp33 (bvadd (concat ((_ extract 31 31) rs) ((_ extract 31 0) rs))
                         (concat ((_ extract 31 31) rt) ((_ extract 31 0) rt)))))

(assert (not (= ((_ extract 32 32) temp33)
                ((_ extract 31 31) temp33))))

(check-sat)
(get-value (rs rt temp33))
