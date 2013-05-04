#!/bin/sh

###############################################
#  PSO TESTER SHELL SCRIPT
#  Brian Jacobel, Connor Smith, Jackson Moniaga
#  Written for CS344 Assignment 4, the PSO
#  5/6/2013
###############################################

#In each of these arrays, if we decide we don't want to vary it at all, simply remove all but one element of the array

NEIGHBORHOOD_TOPOLOGY=(gbest ring von_neumann)

INCLUDE_SELF=(0 1)

INFLUENCE_STRUCTURE=(NORMAL FIPS)

SWARM_SIZE=(9 36 49 100)

FUNCTION=(sphere rosenbrock griewank ackley rastrigin)

ITER=2000

DIMENSIONS=30

OUTFILE="./testresults"

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
          echo ">>>>>>>>>>>>>>>> For parameter combination [ntopo=$ntopo inclme=$inclme struct=$struct size=$size funct=$funct dim=$dim try=$try] >>>>>>>>>>>>>>>>>>>" >> testresults
          for try in {0..50}
          do 
            echo ">>>> trial $try >>>>>>" >> testresults
            java PSO $ntopo $inclme $struct $size $iter $funct $dim $try >> testresults
          done
        done
      done
    done
  done
done 


