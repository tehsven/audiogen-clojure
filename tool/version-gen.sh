#!/bin/bash
echo "brew :" `brew --version`
git --version | sed 's/version/:/'
brew info clojure | grep clojure: | cut -d \  -f 1,3 | sed 's/e:/e :/' | sed 's/,//'
lein --version | echo "lein :" `cut -d \  -f 2`