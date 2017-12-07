# clj-pdf-tools

A collection of general-purpose helper functions for generating clj-pdf standard vector elements. 

## Usage

```clojure
(require '[clj-pdf.core :as pdf])
(require '[clj-pdf-tools.graphics :as graphics])
(pdf/pdf [{} [:paragraph (graphics/floating-image "your-url")]] "doc.pdf")
```
Here `floating-image` function simply expands to the desired clj-pdf standard data structure syntax so that `pdf` function can then render the desired pdf.

This is similar to how [hiccup](https://github.com/weavejester/hiccup) helper functions like [`image`](http://weavejester.github.io/hiccup/hiccup.element.html) or [`include-css`](http://weavejester.github.io/hiccup/hiccup.page.html) work.


## Documentation

[**clj-pdf-tools.graphics**](#graphics)
* [color-background](#color-background)
* [floating-image](#floating-image)
* [image-background](#image-background)

### clj-pdf-tools.graphics

```clojure
user=> (use 'clj-pdf-tools.graphics :reload)
nil
```

#### color-background
This function outputs a minimal svg that as a `:color` and an `:opacity` and that can float `:under` of over the content of a given page.

```clojure
user=> (color-background {:color "#00b8fa" :opacity "0.75" :size :letter :orientation :landscape})
[:svg {:under true} "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE svg><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"792\" height=\"612\"><rect x=\"0\" y=\"0\" width=\"792\" height=\"612\" fill=\"#00b8fa\" fill-opacity=\"0.9\"/></svg>"]
```

#### floating-image
This is like `[:image {} path]` except that the image is now floating over the page (and therefore is not pushing content forward) and that it is not subject to document margins. That means you can have bleeding images in your document.  

```clojure
user=> (floating-image {} "http://i.imgur.com/1GjPKvB.png")
[:graphics {} #object[clj_pdf_tools.graphics$floating_image$fn__1844 0x789fd424 "clj_pdf_tools.graphics$floating_image$fn__1844@789fd424"]]

```
This can be interesting to use when you have a semi-transparent png. Therefore you can have "a la carte" watermarks for a perticular page.

#### image-background
It is also interesting using that function to set background. Note that clj-pdf can set image background, but for all document. The following function allow you to set an image background for a perticular page:

```clojure
user=> (image-background "http://i.imgur.com/1GjPKvB.png")
[:graphics {:under true} #object[clj_pdf_tools.graphics$image_background$fn__2361 0x6ff3051 "clj_pdf_tools.graphics$image_background$fn__2361@6ff3051"]]
;; TODO: auto resize to page-size preserving aspect ratio and zoom or croping from center when needed.
```

## Contribute

You have a snippet you want to share? Make it as general as possible and send a PR. 

Not sure about how to format your code or where to insert it? Open an issue and start a discussion. 

### Contributors

* Dmitri Sotnikov‏ [@yogthos](https://github.com/yogthos)
* Leon Talbot [@leontalbot](https://github.com/leontalbot)


## License

Copyright © 2017 Contributors

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
