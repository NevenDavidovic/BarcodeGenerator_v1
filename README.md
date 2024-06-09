# BarcodeGenerator_v1

## Description

This project is a barcode generator that can store recipients data in a database
and generate a barcode for each recipient. It uses JavaFX for the GUI and SQLite for the database.

## Installation

Project uses java 21.0.3 version.
To create a jar file, run maven goal:

```bash
mvn clean compile assembly:single
```
Download javafx-sdk-21.0.3 from [here](https://gluonhq.com/products/javafx/) and extract it.
To run the jar file use terminal and navigate to the folder where the jar file is located.
Run the following command and adjust the --module-path to the location of the lib inside javafx-sdk-21.0.3 folder where you extracted it.
```bash
java --module-path C:\javafx-sdk-21.0.3\lib --add-modules javafx.controls,javafx.fxml -jar BarcodeGenerator_v1-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Credits
Made by Neven Davidović and Tin Pritišanac as a project for the university course "Programming on the JVM".