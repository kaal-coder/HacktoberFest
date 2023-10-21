print("NOTE: You can only perform calculatons on 4 numbers!!")
def sum(a, b, c = 0, d = 0):
  return a + b + c + d
def sub(a, b, c = 0, d = 0):
  return a - b - c - d
def mul(a, b, c = 1, d = 1):
  return a * b * c * d
def div(a, b, c = 1, d = 1):
  return a / b / c / d
def quit():
  pass
while True:
  print("Choose from \'sum\' for addition (+), \'sub\' for subtraction (-), \'mul\' for multiplication (x), \'div\' for division (/) and \'quit\' to quit.")
  do = eval(input("What do you want to do?: "))
  if (do == sum or do == sub or do == mul or do == div):
    z = int(input("Enter the first number: "))
    y = int(input("Enter the second number: "))
    a = input("Do you want to add more numbers? (y/n): ")
    if a == "y":
      x = int(input("Enter the third number: "))
      a = input("Do you want to add more numbers? (y/n): ")
      if a == "y":
        w = int(input("Enter the fourth number: "))
        print(f"The result of the numbers is {do(z, y, x, w)}")
      elif a == 'n':
        print(f"The result of the numbers is {do(z, y, x)}")
      else:
        print("Please enter either 'y' or 'n'.")
    elif a == 'n':
      print(f"The result of the numbers is {do(z, y)}")
    else:
      print("Please enter either 'y' or 'n'.")
  elif do == quit:
    break
  else:
    print("Please choose only from the mentioned options.")