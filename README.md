## HBase Application Testing Example

This repository is an example of
- how to use `HBaseTestingUtility` to test HBase Application
- how to use [Apache Maven Shade Plugin](https://maven.apache.org/components/plugins/maven-shade-plugin/) to resolve dependency conflict

### how to run the test
```bash
// clone the code
git clone https://github.com/zhao-y/hbasetestingexample.git

// install shadow library
cd hbasetesting-shadow
mvn install

// run test
cd ../hbasetesting
mvn test
```
