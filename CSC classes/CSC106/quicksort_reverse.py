# This is quicksort implemented as described in the lectures and in class.
# Your first task is to change it so that the numbers are REVERSE sorted [5,4,3,2,1]
#
# Your second task is to make the pivot selection in partition RANDOM
# rather than just selecting leftmost element.
# i.e. we want to choose a random element in the array to be the pivot, rather than always choosing A[low]
#
# You can generate random numbers using 
# random.randint(a, b)
# See https://docs.python.org/2/library/random.html
################################################################################

# we need to import the module random to generate random number
import random

############# quicksort(A, low, high) definition #############
## the code in the definition is only executed if called ##

def quicksort(A, low, high):
    if (low < high-1):
        pivot_location = partition(A,low,high)
        quicksort(A,low, pivot_location)
        quicksort(A, pivot_location + 1, high)


############# partition(A, low, high) definition #############
##  the code in the definition is only executed if called   ##
# All changes required for completing the tasks should be made in this part of the code
# There are some lines of code commented out in the loop that print out the content of A as your code executes.
# You may want to uncomment them to help you understand what is happening.

def partition(A, low, high):
    #print("\t\tPartition(A, %i, %i)" % (low, high))
    pivot = A[random.randint(low,high-1)]
    leftwall = low

    for i in range(low+1, high):
        if (pivot<A[i]):
            leftwall = leftwall + 1

            # swapping A[i] with A[leftwall]
            tmp = A[i]
            A[i] = A[leftwall]
            A[leftwall] = tmp

            #print("\t\t\tLoop swap A["+str(i)+"] and A["+str(leftwall)+"]")
            #print("A = %s " % A)

    # swapping A[low] with A[leftwall]
    tmp = A[low]
    A[low] = A[leftwall]
    A[leftwall] = tmp

    #print("\t\tFinal swap A["+str(i)+"] and A["+str(leftwall)+"]")
    #print("\t\tA = %s \n" % A)

    return leftwall


############### main script #####################
## this part of the code is executed each time ##
if __name__ == '__main__':

    # Lets start with an unsorted array of length = 6
    A = [5,8,3,7,1,10]


    # Printing the unsorted list
    print("A= %s"%A)

    # call to quicksort(), passing array A, low=0 and len(A)=high as arguments
    quicksort(A, 0, len(A))

    print("A= %s"%A)
