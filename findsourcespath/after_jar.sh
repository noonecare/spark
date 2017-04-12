#!/usr/bin/env bash
java -cp target/find-resources-path.jar PrintResourcesFile test.txt auxillary/test1.txt
java -cp target/find-resources-path.jar PrintResourcesFile test.txt test1.txt
