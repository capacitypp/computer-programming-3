OBJS=CSV2XML.class Car.class TextFileReader.class
TARGET=163334_4.jar

all : $(OBJS)

%.class : %.java
	javac $<

clean :
	$(RM) *.class

release :
	jar cvfm $(TARGET) MANIFEST.MF *.class *.java

