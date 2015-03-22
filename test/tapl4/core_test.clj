(ns tapl4.core-test
  (:require [clojure.test :refer :all]
            [tapl4.core :refer :all]))

(deftest myeval-test
  (testing "numericval?"
    (is (= true  (numericval? '(:zero))))
    (is (= true  (numericval? '(:succ (:zero)))))
    (is (= true  (numericval? '(:pred (:zero)))))
    (is (= false (numericval? '(:true))))
    (is (= false (numericval? '(:false))))
    )
  (testing "val?"
    (is (= true  (val? '(:true))))
    (is (= true  (val? '(:false))))
    (is (= true  (val? '(:zero))))
    (is (= true  (val? '(:succ (:zero)))))
    (is (= true  (val? '(:pred (:zero)))))
    (is (= false (val? '(:if (:true) (:zero) (:false)))))
    (is (= false (val? '(:iszero (:zero)))))
    )
  (testing "eval1"
    (is (= '(:zero) (eval1 '(:if (:true) (:zero) (:false)))))
    (is (= '(:zero) (eval1 '(:if (:false) (:true) (:zero)))))
    (is (=        '(:if              (:true)           (:zero) (:false))
           (eval1 '(:if (:if (:true) (:true) (:false)) (:zero) (:false)))))
    (is (=        '(:succ              (:zero))
           (eval1 '(:succ (:if (:true) (:zero) (:false))))))
    (is (= '(:zero) (eval1 '(:pred (:zero)))))
    (is (=                      '(:zero)
           (eval1 '(:pred (:succ (:zero))))))
    (is (=        '(:pred              (:zero))
           (eval1 '(:pred (:if (:true) (:zero) (:false))))))
    )
  )
