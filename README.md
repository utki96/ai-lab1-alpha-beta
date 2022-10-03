## System requirements to build and run the project:

1. Apache Maven - (3.6.0)
2. JDK - (1.8)
3. The code has been tested on `crackle1` server

## Steps:

1. Change the working directory to the root folder of this project.
2. Execute command: `mvn clean`
3. Execute command: `mvn package`
4. Now you have an executable jar in *"./target/minmax-1.jar"*
5. Run this jar file by this command:\
   ```java -jar target/minmax-1.jar -v -ab max path_to_file```

Eg: ```java -jar target/minmax-1.jar -v -ab min /home/utkarshtyg/Documents/input2.txt```

###
### Important points about passing arguments:

1. All arguments should be passed after the name of the jar file
2. Arguments [ -v, -ab ] are optional but [ min/max, file_path] are mandatory
3. file_path should be the last argument being passed
4. root node config: min/max should be the 2nd last config being passed
5. Args -v, -ab are interchangeable but should be passed before min/max and file_path
