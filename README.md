# RINGCENTRAL call log record generator app

This is a demo app to generate call log record against an account.

It is useful for creating call log records in sandbox accounts to perform call llog API calls.

## Prerequisites

You will need the following to run this program:

1. Java 1.7+

To check your version of Java run the following:

```bash
# java -version
```

## Usage

Use the following steps:

1. Download the release JAR
1. Download the `config.properties` file
1. Edit the `config.properties` file
1. Run the program using Java

For example:

```bash
wget https://github.com/vyshakhbabji/RINGCENTRAL-Call-log-record-generator-app/releases/download/0.0.1/Call-Log-Generator.jar

wget https://raw.githubusercontent.com/vyshakhbabji/RINGCENTRAL-Call-log-record-generator-app/master/config-sample.properties 

# Edit the config.properties file
vi config.properties

# Run the program and create RingOut calls

java -jar Call-Log-Generator.jar
```

If you have multiple versions of Java, replace the `java` command above with the following:

```bash
# Get your Java versions
/usr/libexec/java_home -V

# Run the program and create RingOut calls
/usr/libexec/java_home -v 1.8.0_60 --exec java -jar Call-Log-Generator.jar
```

## License

RINGCENTRAL call log record generator is available under an MIT-style license. See [LICENSE.md](LICENSE.md) for details.

RINGCENTRAL call log record generator &copy; Vyshakh Babji
