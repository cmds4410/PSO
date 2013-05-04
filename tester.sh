#!/bin/sh

###############################################
#  PSO TESTER SHELL SCRIPT
#  Brian Jacobel, Connor Smith, Jackson Moniaga
#  Written for CS344 Assignment 4, the PSO
#  5/6/2013
###############################################

#In each of these arrays, if we decide we don't want to vary it at all, simply remove all but one element of the array

NEIGHBORHOOD_TOPOLOGY = ()

INCLUDE_SELF = ()

INFLUENCE_STRUCTURE = ()

SWARM_SIZE = ()

FUNCTION = ()

DIMENSIONS = ()

TRIES=5

OUTFILE="./testresults"

##if $outfile exists already, wipe it out and recreate it
if [ -f $OUTFILE ]; then
   rm $OUTFILE
fi
:>$OUTFILE

#this is kind of a mess
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
          for dim in "${DMENSIONS[@]}"
          do
            for try in "${TRIES[@]}"
            do 
              echo ">>>>>>>>>>>>>>>> For parameter combination [ntopo=$ntopo inclme=$inclme struct=$struct size=$size funct=$funct dim=$dim try=$try] >>>>>>>>>>>>>>>>>>>" >> testresults
              javac PSO.java
              java PSO $ntopo $inclme $struct $size $funct $dim $try >> testresults
            done
          done
        done
      done
    done
  done
done



# The executable 'acotsp' provides the following command line options
# (given are the short and the long options):

# -r, --tries          # number of independent trials
# -s, --tours          # number of steps in each trial
# -t, --time           # maximum time for each trial
#     --seed           # seed for the random number generator 
# -i, --tsplibfile     f inputfile (TSPLIB format necessary)
# -o, --optimum        # stop if tour better or equal optimum is found
# -m, --ants           # number of ants
# -g, --nnants         # nearest neighbours in tour construction
# -a, --alpha          # alpha (influence of pheromone trails)
# -b, --beta           # beta (influence of heuristic information)
# -e, --rho            # rho: pheromone trail evaporation
# -q, --q0             # q_0: prob. of best choice in tour construction
# -c, --elitistants    # number of elitist ants
# -f, --rasranks       # number of ranks in rank-based Ant System
# -k, --nnls           # No. of nearest neighbors for local search
# -l, --localsearch    0: no local search   1: 2-opt   2: 2.5-opt   3: 3-opt
# -d, --dlb            1 use don't look bits in local search
# -u, --as               apply basic Ant System
# -v, --eas              apply elitist Ant System
# -w, --ras              apply rank-based version of Ant System
# -x, --mmas             apply MAX-MIN ant system
# -y, --bwas             apply best-worst ant system
# -z, --acs              apply ant colony system
# -h, --help             display the help text and exit

# Options -u --as, -v --eas, -w --ras, -x --mmas, -y --bwas, -z --acs,
# -h, --help don't need arguments, while all the others do. 


