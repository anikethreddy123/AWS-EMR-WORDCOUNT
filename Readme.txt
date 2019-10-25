1)In the java code perticularly in Nimma class change the input mapper size and also number of reducers that should be working on the input file size in the aws emr.
2)run bigdata project with maven build
3)type "clean","verify" and run.
4)Goto C:\Users\ANIKETH\eclipse-workspace\bigdata\target which is the work space and file the jar file of the executed code for word count.
5)rename it and upload it to hue users/s3670774
6)Now copyToLocal using the command.
 hadoop fs -copyToLocal /user/s3670774/50mb.jar /home/hadoop/
7)Now run the jar file on the input data in the hue crawl data and give the output to the folder called output5 in this case and the data uploaded is in the wat folder of the common crawl uploaded to the hue.
 hadoop jar /home/hadoop/50mb.jar edu.rmit.cosc2637.e20925.bigdata.Nimma /user/s3670774/wet/CC-MAIN-20171117170336-20171117190336-00000.warc.wet.gz /user/output5 /home/hadoop/
After this the code will be executed and map the data and reduce the data
8)Note down the mappers launced and reducers.
9)Now goto hue and check the output5 folder for the output.
10)select view as text in the hue.
TO VIEW THE CODE GOTO 
s3670774.code\bigdata\src\main\java\edu\rmit\cosc2637\e20925\bigdata
and POM file in the bigdata project folder 
