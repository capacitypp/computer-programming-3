OBJS=AbstractConverter.class Converter.class DoublingConverter.class NoConverter.class NoDigitConverter.class Processor.class ToLowerConverter.class
TARGET=163334_1.jar

all : $(OBJS)

%.class : %.java
	javac $<

clean :
	$(RM) *.class

release :
	jar cvfm $(TARGET) MANIFEST.MF *.class *.java

