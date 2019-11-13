import random
def quicksort(L):
    if len(L) > 1:
         pivot = random.randrange(len(L))
         elements = L[:pivot]+L[pivot+1:]
         left  = [element for element in elements if element < L[pivot]] 
         right =[element for element in elements if element >= L[pivot]]
         return quicksort(left)+[L[pivot]]+quicksort(right)
return L
