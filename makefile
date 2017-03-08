# 
# makefile
# 
# input file for 'make' build tool ( /usr/bin/make )
# to build https://github.com/JaysGitLab/cs-5666-greeting-bclinthall/projects/1
# following Dr. Fenwick's solution for CS 5666 JUnit sample
#
# #
# # @author B. Clint Hall
# # @author Chris Waldon
# # @author Dr Fenwick
# # @version Spring 2017
# #
#

# Set up locations for the jar files and URIs to fetch them from.
JUNIT_JAR = junit-4.12.jar
JUNIT_URI = https://github.com/junit-team/junit4/releases/download/r4.12/$(JUNIT_JAR)
JUNIT_LOCAL = jars/$(JUNIT_JAR)
HAMCREST_JAR = hamcrest-core-1.3.jar
HAMCREST_URI = http://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/$(HAMCREST_JAR)
HAMCREST_LOCAL = jars/$(HAMCREST_JAR)
CHECKSTYLE_JAR = checkstyle-7.6-all.jar
CHECKSTYLE_URI = https://sourceforge.net/projects/checkstyle/files/checkstyle/7.6/checkstyle-7.6-all.jar/download
CHECKSTYLE_LOCAL = jars/$(CHECKSTYLE_JAR)
STYLE_XML = misc/cs_appstate_checks.xml
CLASSPATH = -cp .:$(JUNIT_LOCAL)
CC = javac $(CLASSPATH) -Xlint:deprecation

# Teach make how to use javac to convert between .java and .class
.SUFFIXES: .java .class
.java.class:
	$(CC) $<

default:
	@echo "usage: make target"
	@echo "available targets: compile, test, clean"

compile: scatt/Scatt.class test/ScattTest.class
	@echo "compiled"

test/ScattTest.class: $(JUNIT_LOCAL)

style: test/ScattTest.java scatt/Scatt.java $(CHECKSTYLE_LOCAL)
	java -cp .:$(CHECKSTYLE_LOCAL) com.puppycrawl.tools.checkstyle.Main -c $(STYLE_XML) test/ScattTest.java scatt/Scatt.java

clean: 
	rm -f scatt/Scatt.class
	rm -f test/ScattTest.class
	
test:  scatt/Scatt.class test/ScattTest.class $(JUNIT_LOCAL) $(HAMCREST_LOCAL)
	java -cp .:$(JUNIT_LOCAL):$(HAMCREST_LOCAL) org.junit.runner.JUnitCore test.ScattTest

jars:
	mkdir jars
# Add makefile targets that download the jars automatically if they
# are not present locally.
$(JUNIT_LOCAL): jars
	curl $(JUNIT_URI) -o $(JUNIT_LOCAL) --silent --location
$(HAMCREST_LOCAL): jars
	curl $(HAMCREST_URI) -o $(HAMCREST_LOCAL) --silent --location
$(CHECKSTYLE_LOCAL): jars
	curl -L $(CHECKSTYLE_URI) -o $(CHECKSTYLE_LOCAL) --silent --location
