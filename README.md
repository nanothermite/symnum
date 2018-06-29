# symnum
Detect next palindrome give a sequence of numeric strings by splitting the strings in half and comparing them by 
walking across each character in the same direction by reversing the right half. The lower value is bumped by 1
while the larger value is returned. The larger value is then duplicated and reversed on the other side to yield
a palindrome. In the case of an odd number sized input string, the center is duplicated when submitted to the comparison
step. Then the first character of the duplicated returned value is dropped to yield the same sized string. In the
case of even number sized input strings, the strings are split equally and duplicated equally as well.
