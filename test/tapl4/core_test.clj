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
  )
