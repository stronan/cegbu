#!/bin/bash

mvn clean install

java -jar target/cegbu-1.0-SNAPSHOT.jar < src/test/sample.csv > output.txt
diff src/test/expected.txt output.txt