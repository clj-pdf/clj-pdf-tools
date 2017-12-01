# clj-pdf-tools

A collection of general-purpose tools and snippets for building PDF documents with clj-pdf.

## Usage

#### Graphics
```clojure
user=> (use 'clj-pdf-tools.graphics :reload)
nil

user=> (color-background {:color "#00b8fa" :opacity "0.9" :size :letter :orientation :landscape})
[:svg {:under true} "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE svg><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"792\" height=\"612\"><rect x=\"0\" y=\"0\" width=\"792\" height=\"612\" fill=\"#00b8fa\" fill-opacity=\"0.9\"/></svg>"]
```


## Contribute

You have a snippet you want to share? 

Make your function as general as possible and send a PR. 

Not sure about how to format your code? Open an issue and start a discussion. 

### Contributors

* Dmitri Sotnikov‏ [@yogthos](https://github.com/yogthos)
* Leon Talbot [@leontalbot](https://github.com/leontalbot)


## License

Copyright © 2017 Contributors

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.



