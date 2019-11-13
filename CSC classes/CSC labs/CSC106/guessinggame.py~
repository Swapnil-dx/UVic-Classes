def guessing_game():
	n=-1
	while n<0:
		n=raw_input("Maximum range for number " )
		n=int(n)
	import random
	secretNum= random.randint(0,n)
	
	guess=-1
	print "Try to guess the Number"
	while guess!= secretNum:
		guess=input()
		if guess==secretNum: 	
			print "YOU GOT IT!"
		elif guess <secretNum:
			print "too low"
		else:
			print "too high"

guessing_game()
