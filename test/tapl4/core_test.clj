(ns tapl4.core-test
  (:require [clojure.test :refer :all]
            [tapl4.core :refer :all]))

(deftest myeval-test
  (testing ":true"
    (is (= (myeval :true) :true)))
  (testing ":false"
    (is (= (myeval :false) :false)))
  (testing ":zero"
    (is (= (myeval :zero) :zero)))
  (testing ":succ"
    (are [x y] (= (myeval '(:succ x)) y)
         :zero         '(:succ :zero)
         (:pred :zero) :zero))
  (testing ":pred"
    (are [x y] (= (myeval '(:pred x)) y)
         :zero         '(:pred :zero)
         (:succ :zero) :zero))
  (testing "nested :succ"
    (are [x y] (= (myeval x) y)
         (myeval '(:succ :zero)) '(:succ :zero)
         (myeval '(:succ (:succ :zero))) '(:succ (:succ :zero))))
  (testing "nested :pred"
    (are [x y] (= (myeval x) y)
         (myeval '(:pred :zero)) '(:pred :zero)
         (myeval '(:pred (:pred :zero))) '(:pred (:pred :zero))))
  (testing ":iszero"
    (are [x y] (= (myeval '(:iszero x)) y)
         :zero         :true
         (:succ :zero) :false
         (:pred :zero) :false))
  (testing ":ifthenelse"
    (are [c t e x] (= (myeval '(:ifthenelse c t e)) x)
         :true  :zero :false :zero
         :false :true :zero  :zero)))
