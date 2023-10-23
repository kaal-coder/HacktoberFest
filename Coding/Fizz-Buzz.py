# Problem Statement

# Given an integer n, for every integer i <= n, the task is to print “FizzBuzz” if i is divisible by 3
# and 5, “Fizz” if i is divisible by 3, “Buzz” if i is divisible by 5 or i (as a string) if none of the
# conditions are true.
# ------------------------------------------------------------------------------
# Example
# Input: n = 3
# Output: [1 2 Fizz]

# Input: n = 5
# Output: [1 2 Fizz 4 Buzz]

# -------------------------------------------------------------------------------

#Solution

def fizz_buzz(n):
	# Declare a list of strings to store the results
	result = []

	# Loop from 1 to n (inclusive)
	for i in range(1, n + 1):

		# Check if i is divisible by both 3 and 5
		if i % 3 == 0 and i % 5 == 0:

			# Add "FizzBuzz" to the result list
			result.append("FizzBuzz")

		# Check if i is divisible by 3
		elif i % 3 == 0:

			# Add "Fizz" to the result list
			result.append("Fizz")

		# Check if i is divisible by 5
		elif i % 5 == 0:

			# Add "Buzz" to the result list
			result.append("Buzz")
		else:

			# Add the current number as a string to the
			# result list
			result.append(str(i))

	# Return the result list
	return result


# input from user
n = int(input())

# Call the fizz_buzz function to get the result
final_answer = fizz_buzz(n)

# Print the result
print(' '.join(final_answer))

