#!/bin/bash
java -version 2>&1>/dev/null | grep 'version' | echo "java :" `cut -d \" -f 2`
echo "brew :" `brew --version`
git --version | sed 's/version/:/'
lein --version | echo "lein :" `cut -d \  -f 2`