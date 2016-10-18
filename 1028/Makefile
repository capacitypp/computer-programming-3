OBJS=GUI.class
TARGET=163334_3.jar

all : $(OBJS)

%.class : %.java
	javac $<

clean :
	$(RM) *.class

release :
	jar cvfm $(TARGET) MANIFEST.MF *.class *.java

