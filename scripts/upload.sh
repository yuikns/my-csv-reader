#!/usr/bin/env bash

#rsync -avzh --progress ./ gt-haumea:src/file-bench
scp target/scala-2.11/file-bench-0.1.1-2.11.12.jar gt-haumea:src/

