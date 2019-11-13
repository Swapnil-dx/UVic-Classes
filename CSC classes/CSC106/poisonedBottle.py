# This is a python program implementing the Poisoned Bottle problem
# Given an array of bit representing the combination of dead prisoners,
# the algorithm retrieves the poisoned bottle.

# The algorithm as it is does not working properly.
# Fix the code so it retrieves the correct bottle


############# isPoisoned(deathBin) definition #############
## the code in the definition is only executed if called ##

def isPoisoned(deathBin):
	# this part of the code does not work properly
	# your task is to fix it
	i = 0
	raisedBase = 1
	sum = 0
	while (i < len(deathBin)):
		bit = deathBin[len(deathBin)-1-i]
		sum = sum + (bit*raisedBase)
		raisedBase = raisedBase + raisedBase
		i +=1 # a python way of incrementing the value of a variable by 1	
	
	return sum



############### main script #####################
## this part of the code is executed each time ##


if __name__=='__main__':

    # lets pick a specific binary number, for example 0011
    # this should return bottle 3
    deathBin = [0,0,1,1]


    # call to isPoisoned()
    # passing the deathBin defined above as an argument
    # catching the returned value of sum and storing it in poisonedBottle
    poisonedBottle = isPoisoned(deathBin)

    # printing the retrieved poisoned bottle
    print "\nBottle " + str(poisonedBottle) + " is poisoned\n" # the \n is for printing a new line



