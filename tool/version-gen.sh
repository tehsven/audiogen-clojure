#!/bin/bash
echo "brew :" `brew --version`
git --version | sed 's/version/:/'
lein --version | echo "lein :" `cut -d \  -f 2`