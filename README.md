# codingExercise
The following steps has to be done before running the program.
1. Make sure the java jdk path (v1.8) is added to the PATH environment variable. E.g.
> set path=%path%;D:\Program Files\Java\jdk1.8.0_101\bin
2. Go to the root foloder of the project. Compile the CodingExercise.java file and copy all class files to bin folder:
>javac -d bin -sourcepath src -cp lib/jackson-annotations-2.2.3.jar;lib/jackson-core-2.2.3.jar;lib/jackson-databind-2.2.3.jar src/com/company/CodingExercise.java
3. Run:
>java -cp bin;lib/jackson-annotations-2.2.3.jar;lib/jackson-core-2.2.3.jar;lib/jackson-databind-2.2.3.jar com.company.CodingExercise

or run with arguments (only --ignore-donuts is supported for now):

>java -cp bin;lib/jackson-annotations-2.2.3.jar;lib/jackson-core-2.2.3.jar;lib/jackson-databind-2.2.3.jar com.company.CodingExercise --ignore-donuts
