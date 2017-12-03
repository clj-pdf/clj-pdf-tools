(ns clj-pdf-tools.graphics
  (:require [clj-pdf.core :as pdf] 
            [clojure.java.io :as io])
  (:import [javax.imageio ImageIO]))

(def page-sizes
  "All page sizes [width height]
   These are portrait oriented"
  {:a0 [2384 3370]
   :a1 [1684 2384]
   :a2 [1191 1684]
   :a3 [842 1191]
   :a4 [595 842]
   :a5 [420 595]
   :a6 [297 420]
   :a7 [210 297]
   :a8 [148 210]
   :a9 [105 148]
   :a10 [73 105]
   :arch-a [648 864]
   :arch-b [864 1296]
   :arch-c [1296 1728]
   :arch-d [1728 2592]
   :arch-e [2592 3456]
   :b0 [2834 4008]
   :b1 [2004 2834]
   :b2 [1417 2004]
   :b3 [1000 1417]
   :b4 [708 1000]
   :b5 [498 708]
   :b6 [354 498]
   :b7 [249 354]
   :b8 [175 249]
   :b9 [124 175]
   :b10 [87 124]
   :crown-octavo [348 527]
   :crown-quarto [535 697]
   :demy-octavo [391 612]
   :demy-quarto [620 782]
   :executive [522 756]
   :flsa [612 936]
   :flse [648 936]
   :halfletter [396 612]
   :id-1 [243 153]
   :id-2 [297 210]
   :id-3 [354 249]
   :large-crown-octavo [365 561]
   :large-crown-quarto [569 731]
   :ledger [1224 792]
   :legal [612 1008]
   :letter [612 792]
   :note [540 720]
   :penguin-large-paperback [365 561]
   :penguin-small-paperback [314 513]
   :postcard [283 416]
   :royal-octavo [442 663]
   :royal-quarto [671 884]
   :small-paperback [314 504]
   :tabloid [792 1224]})

(defn color-background
  "Puts an color background on the page. 
   Could also be used to create a semi-transparent foreground
   Accepts svg options plus the following keys:"
  [{:keys [color opacity orientation size]
    :or {opacity 1 orientation :portrait size :a4}
    :as options}]
  
  (let [meta-svg (dissoc options :color :opacity :orientation :size) 
        size (cond->> size
               (keyword? size) (get page-sizes)
               (= orientation :landscape) reverse)
        [width height] size]

    [:svg 
     (merge {:under true} meta-svg)
     (str "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE svg>"
          "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\""
          width 
          "\" height=\""
          height
          "\">"
          "<rect x=\"0\" y=\"0\" width=\""
          width
          "\" height=\""
          height
          "\" fill=\""
          color
          "\" fill-opacity=\""
          opacity
          "\"/></svg>")]))

(defn floating-image [meta path]
  (let [read (pdf/load-image path (:base64 meta))] 
    [:graphics meta (fn [g2d] (.drawImage g2d read 0 0 nil))]))

(defn image-background 
  "Special case of floating-image that puts image under content"
  ;; TODO ... and fullscreens it while preserving aspect ratio.
  ([path]
   (image-background {} path))
  ([{:keys [page-size page-orientation]} path]
   (floating-image {:under true} path)))
