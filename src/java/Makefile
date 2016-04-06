# Ensimag 2A POO - TP 2015/16
# ============================
# EDITE PAR :
# LUCAS MAHIEU 
# ============================
#
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     Pour un package (ici gui.jar), il est aussi dans bin.
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: jeuxGUI exeJeuxGUI

jeuxGUI:
	javac -d bin -classpath bin/gui.jar -sourcepath src/ src/JeuxGUI.java

# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin TestGUI
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeIHM

exeJeuxGUI:
	java -classpath bin:bin/gui.jar JeuxGUI

clean:
	rm -rf bin/*.class
