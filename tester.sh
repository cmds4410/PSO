#!/bin/sh

###############################################
#  PSO TESTER SHELL SCRIPT
#  Brian Jacobel, Connor Smith, Jackson Moniaga
#  Written for CS344 Assignment 4, the PSO
#  5/6/2013
###############################################

#In each of these arrays, if we decide we don't want to vary it at all, simply remove all but one element of the array

NEIGHBORHOOD_TOPOLOGY=(gbest ring von_neumann random)

INCLUDE_SELF=(no yes)

INFLUENCE_STRUCTURE=(NORMAL FIPS)

SWARM_SIZE=(9 36 49 100)

FUNCTION=(sphere rosenbrock griewank ackley rastrigin)

ITER=2000

DIMENSIONS=30

OUTFILE="./testresults.csv"

##if $outfile exists already, wipe it out and recreate it
if [ -f $OUTFILE ]; then
   rm $OUTFILE
fi
:>$OUTFILE

javac *.java

for ntopo in "${NEIGHBORHOOD_TOPOLOGY[@]}"
do  
  for inclme in "${INCLUDE_SELF[@]}"
  do
    for struct in "${INFLUENCE_STRUCTURE[@]}"
    do
      for size in "${SWARM_SIZE[@]}"
      do
        for funct in "${FUNCTION[@]}"
        do
          for try in {0..1}
          do 
            echo "$ntopo, $inclme, $struct, $size, $funct, $try, \c" >> $OUTFILE
            java PSO $ntopo $inclme $struct $size $ITER $funct $DIMENSIONS >> $OUTFILE
            echo >> $OUTFILE
          done
        done
      done
    done
  done
done 


