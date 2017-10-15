#
# A simple makefile for compiling java classes
#

# define a makefile variable for the java compiler
#
JCC = javac

# define a makefile variable for compilation flags
# the -g flag compiles with debugging information
#
JFLAGS = -g

# typing 'make' will invoke the first target entry in the makefile 
# (the default one in this case)
#
default: BinaryHeap.class FourWayHeap.class PairingHeap.class FrequencyTable.class encoder.class decoder.class

# this target entry builds the class files
# and the rule associated with this entry gives the command to create it
#
BinaryHeap.class: BinaryHeap.java
	$(JCC) $(JFLAGS) BinaryHeap.java
FourWayHeap.class: FourWayHeap.java
	$(JCC) $(JFLAGS) FourWayHeap.java
PairingHeap.class: PairingHeap.java
	$(JCC) $(JFLAGS) PairingHeap.java
FrequencyTable.class: FrequencyTable.java
	$(JCC) $(JFLAGS) FrequencyTable.java
encoder.class: encoder.java
	$(JCC) $(JFLAGS) encoder.java
decoder.class: decoder.java
	$(JCC) $(JFLAGS) decoder.java
	
# To start over from scratch, type 'make clean'.  
# Removes all .class files, so that the next make rebuilds them
#
clean:
	$(RM) *.class


