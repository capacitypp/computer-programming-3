OBJS=Car.class TextFileReader.class CarThread.class QueryGenerator.class ReadWriteLock.class
TARGET=163334_5.jar

all : $(OBJS)

%.class : %.java
	javac $<

clean :
	$(RM) *.class

release :
	jar cvfm $(TARGET) MANIFEST.MF *.class *.java

